package org.twocloud.java2cloud;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.joda.time.DateTime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

public class Client {
	
	public Client() {
	}
	
	public Response get(String endpoint, String device) throws IOException {
		return get(endpoint, null, null, device);
	}
	
	public Response get(String endpoint, String username, String secret, String device) throws IOException {
		return doRequest("GET", endpoint, username, secret, null, device);
	}
	
	public Response post(String endpoint, String body, String device) throws IOException {
		return post(endpoint, null, null, body, device);
	}
	
	public Response post(String endpoint, String username, String secret, String body, String device) throws IOException {
		return doRequest("POST", endpoint, username, secret, body, device);
	}
	
	public Response put(String endpoint, String body, String device) throws IOException {
		return put(endpoint, null, null, body, device);
	}
	
	public Response put(String endpoint, String username, String secret, String body, String device) throws IOException {
		return doRequest("PUT", endpoint, username, secret, body, device);
	}
	
	public Response delete(String endpoint, String device) throws IOException {
		return delete(endpoint, null, null, device);
	}
	
	public Response delete(String endpoint, String username, String secret, String device) throws IOException {
		return doRequest("DELETE", endpoint, username, secret, null, device);
	}
	
	private Response doRequest(String method, String endpoint, final String username, final String secret, String body, String device) throws IOException {
		URL url = new URL(endpoint);
		Response resp;
		Map<String, List<String>> headers = null;
		byte[] b;
		
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

		if(username != null && secret != null) {
			String encodeMe = username + ":" + secret;
			urlConnection.addRequestProperty("Authorization", "Basic "+Base64.encodeBase64String(encodeMe.getBytes()));
		}
		
		urlConnection.addRequestProperty("User-Agent", "java2cloud | 0.1.0");
		urlConnection.addRequestProperty("API-Version", "1");
		urlConnection.addRequestProperty("From", device);
		
		urlConnection.setRequestMethod(method);
		urlConnection.setInstanceFollowRedirects(false);
		
		try {
			if(body != null) {
				urlConnection.setDoOutput(true);
				//urlConnection.setFixedLengthStreamingMode(body.getBytes().length);
				urlConnection.setRequestProperty("Content-Length", body.getBytes().length+"");
				urlConnection.getOutputStream().write(body.getBytes());
			}
			if(urlConnection.getResponseCode() < 300) {
				BufferedInputStream in = new BufferedInputStream(urlConnection.getInputStream());
				b = readStream(in);
				headers = urlConnection.getHeaderFields();
			} else {
				BufferedInputStream in = new BufferedInputStream(urlConnection.getErrorStream());
				b = readStream(in);
			}
			resp = parseJson(b);
		} finally {
			urlConnection.disconnect();
		}
		if(headers != null && headers.containsKey("Warning")) {
			resp.subscriptionExpired = true;
		} else {
			resp.subscriptionExpired = false;
		}
		return resp;
	}
	
	private Response parseJson(byte[] b) {
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(DateTime.class, new DateTimeTypeConverter());
		Gson gson = builder.create();
		Response resp = null;
		try {
			resp = gson.fromJson(new String(b, "UTF-8"), Response.class);
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resp;
	}
	
    private static byte[] readStream(InputStream in) 
            throws IOException {
        byte[] buf = new byte[1024];
        int count = 0;
        ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
        while ((count = in.read(buf)) != -1) 
            out.write(buf, 0, count);
        return out.toByteArray();
    }

}
