package com.solution.database.Model;

import java.util.ArrayList;

public class AppUser {
	private String name;
	private String email;
	private long phone_number;
	private String username;
	private String password;
	private String favorite1;
	private String favorite2;
	private String favorite3;

	public AppUser() {
	}

	public AppUser(String name, String email, long phone_number, String username, String password, String favorite1, String favorite2, String favorite3) {
		this.name = name;
		this.email = email;
		this.phone_number = phone_number;
		this.username = username;
		this.password = password;
		this.favorite1 = favorite1;
		this.favorite2 = favorite2;
		this.favorite3 = favorite3;
	}

	@Override
	public String toString() {
		return "AppUser{" +
				"name='" + name + '\'' +
				", email='" + email + '\'' +
				", phone_number=" + phone_number +
				", username='" + username + '\'' +
				", favorite1='" + favorite1 + '\'' +
				", favorite2='" + favorite2 + '\'' +
				", favorite3='" + favorite3 + '\'' +
				'}';
	}

	public String getFavorite1() {
		return favorite1;
	}

	public void setFavorite1(String favorite1) {
		this.favorite1 = favorite1;
	}

	public String getFavorite2() {
		return favorite2;
	}

	public void setFavorite2(String favorite2) {
		this.favorite2 = favorite2;
	}

	public String getFavorite3() {
		return favorite3;
	}

	public void setFavorite3(String favorite3) {
		this.favorite3 = favorite3;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(long phone_number) {
		this.phone_number = phone_number;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
