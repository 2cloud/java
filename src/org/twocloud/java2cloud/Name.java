package org.twocloud.java2cloud;

import com.google.gson.annotations.SerializedName;

public class Name {
	@SerializedName("given") private String given;
	@SerializedName("family") private String family;
	
	public Name(String given, String family) {
		this.setGiven(given);
		this.setFamily(family);
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public String getGiven() {
		return given;
	}

	public void setGiven(String given) {
		this.given = given;
	}
}
