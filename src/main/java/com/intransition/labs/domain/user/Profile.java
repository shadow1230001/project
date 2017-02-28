package com.intransition.labs.domain.user;

import java.sql.Date;

public class Profile {
	
	private int id;
	
	private String email;
	
	private String nickname;
	
	private String firstName;
	
	private String lastName;
	
	private boolean banned;
	
	private Date registered;
	
	public Profile() {}
	
	public Profile( User user ) {
		setId(user.getId());
		setEmail(user.getEmail());
		setNickname(user.getNickname());
		setFirstName(user.getFirstName());
		setLastName(user.getLastName());
		setBanned(user.isBanned());
		setRegistered(user.getRegistrationDate());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isBanned() {
		return banned;
	}

	public void setBanned(boolean banned) {
		this.banned = banned;
	}

	public Date getRegistered() {
		return registered;
	}

	public void setRegistered(Date registered) {
		this.registered = registered;
	}
	
}
