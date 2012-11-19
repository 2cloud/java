package org.twocloud.java2cloud;

import org.joda.time.DateTime;

import com.google.gson.annotations.SerializedName;

public class User {
	
	@SerializedName("id") private String id;
	@SerializedName("username") private String username;
	@SerializedName("email") private String email;
	@SerializedName("email_unconfirmed") private boolean emailUnconfirmed;
	@SerializedName("secret") private String secret;
	@SerializedName("joined") private DateTime joined;
	@SerializedName("name") private Name name;
	@SerializedName("last_active") private DateTime lastActive;
	@SerializedName("is_admin") private boolean admin;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public DateTime getLastActive() {
		return lastActive;
	}

	public void setLastActive(DateTime lastActive) {
		this.lastActive = lastActive;
	}

	public Name getName() {
		return name;
	}

	public void setName(String given, String family) {
		this.name.setGiven(given);
		this.name.setFamily(family);
	}

	public DateTime getJoined() {
		return joined;
	}

	public String getSecret() {
		return secret;
	}

	public boolean isEmailUnconfirmed() {
		return emailUnconfirmed;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getId() {
		return id;
	}

}
