package org.twocloud.java2cloud;

import org.joda.time.DateTime;

import com.google.gson.annotations.SerializedName;

public class Account {
	@SerializedName("added") protected DateTime added;
	@SerializedName("id") protected String id;
	@SerializedName("provider") protected String provider;
	@SerializedName("foreign_id") protected String foreignId;
	@SerializedName("email") protected String email;
	@SerializedName("email_verified") protected boolean emailVerified;
	@SerializedName("display_name") protected String displayName;
	@SerializedName("given_name") protected String givenName;
	@SerializedName("family_name") protected String familyName;
	@SerializedName("picture") protected String picture;
	@SerializedName("locale") protected String locale;
	@SerializedName("timezone") protected String timezone;
	@SerializedName("gender") protected String gender;
	
	public DateTime getAdded() {
		return this.added;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getProvider() {
		return this.provider;
	}
	
	public String getForeignId() {
		return this.foreignId;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public boolean isEmailVerified() {
		return this.emailVerified;
	}
	
	public String getDisplayName() {
		return this.displayName;
	}
	
	public String getGivenName() {
		return this.givenName;
	}
	
	public String getFamilyName() {
		return this.familyName;
	}
	
	public String getPictureURL() {
		return this.picture;
	}
	
	public String getLocale() {
		return this.locale;
	}
	
	public String getTimezone() {
		return this.timezone;
	}
	
	public String getGender() {
		return this.gender;
	}
}
