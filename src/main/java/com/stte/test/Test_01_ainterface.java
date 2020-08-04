package com.stte.test;

import com.alibaba.fastjson.JSON;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class Test_01_ainterface {

	public static void main(String[] args) {

		String password = "12314abcde1";
		String id = "500123123X";
		String enPassword = java.util.Base64.getEncoder().encodeToString(password.getBytes());
		String enId = java.util.Base64.getEncoder().encodeToString(id.getBytes());
		EncryptBean encryptBean = new EncryptBean("韩立", enPassword, enId);
		System.out.println(encryptBean);
		String jString = JSON.toJSONString(encryptBean);
		System.out.println(JSON.parse(jString));
		JSON json = (JSON) JSON.parse(jString);
		try {
			Field field1 = EncryptBean.class.getDeclaredField("password");
			Field[] fields = EncryptBean.class.getDeclaredFields();
			for (Field field : fields) {
				System.out.println(field.getName());
			}
			
			
			Annotation[]annotations =  field1.getDeclaredAnnotations();
			System.out.println(annotations.length);
			for (Annotation annotation : annotations) {
				System.out.println(annotation.annotationType());
			}
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		
	}

}
