package org.twocloud.java2cloud;

import java.util.List;

public class Response {
	protected int code;
	protected String message;
	protected List<User> users;
	protected List<Account> accounts;
	protected boolean subscriptionExpired;
	
	public String getMessage() {
		return this.message;
	}
	
	public int getCode() {
		return this.code;
	}
	
	public List<User> getUsers() {
		return this.users;
	}
	
	public List<Account> getAccounts() {
		return this.accounts;
	}
	
	public boolean isSubscriptionExpired() {
		return this.subscriptionExpired;
	}
}
