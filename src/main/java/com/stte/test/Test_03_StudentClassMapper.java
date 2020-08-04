package com.stte.test;

import com.alibaba.fastjson.JSON;
import com.stte.domain.Clazz;
import com.stte.domain.StudentClassInfo;
import com.stte.mapper.ClassStudentMapper;
import com.stte.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class Test_03_StudentClassMapper {

	public static void main(String[] args) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		ClassStudentMapper classStudentMapper = sqlSession.getMapper(ClassStudentMapper.class);
		List<Clazz> lClazzs = classStudentMapper.getClazzInfo();
		System.out.println(JSON.toJSON(lClazzs));

		StudentClassInfo sClassInfo = classStudentMapper.getStudentClassInfoByID(1);
		System.out.println(sClassInfo.getClazz());
		System.out.println(JSON.toJSON(sClassInfo));
	}

}
