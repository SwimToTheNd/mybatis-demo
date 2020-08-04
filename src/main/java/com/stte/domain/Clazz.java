package com.stte.domain;

import java.util.List;

public class Clazz {
	private int id;
	private String classname;
	private List<Student> students;

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return classname;
	}

	public void setCode(String code) {
		this.classname = code;
	}

}
