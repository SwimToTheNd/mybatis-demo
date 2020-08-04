package com.stte.test;

import java.util.List;

public class EncryptBean {

	private String name;

	@EncodeRequried
	private String password;

	@EncodeRequried
	private String id;
	
	@EncodeRequried
	private List<String> hobby;

	public EncryptBean(String name, String password, String id) {
		this.name = name;
		this.password = password;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getHobby() {
		return hobby;
	}

	public void setHobby(List<String> hobby) {
		this.hobby = hobby;
	}

	@Override
	public String toString() {
		return "name: " + name + "  password: " + password + "  id: " + id+"  hobby: "+hobby;
	}

}
