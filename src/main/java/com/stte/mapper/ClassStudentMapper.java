package com.stte.mapper;


import com.stte.domain.Clazz;
import com.stte.domain.StudentClassInfo;

import java.util.List;

public interface ClassStudentMapper {

	public List<Clazz> getClazzInfo();
	
	public StudentClassInfo getStudentClassInfoByID(int id);
}
