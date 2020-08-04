package com.stte.common.encrypt;

import java.lang.reflect.InvocationTargetException;
import java.util.Base64;

/**
 * 用于临时测试输出
 * 
 * @author BloodFly
 *
 */
public class Test_01_PrintTest {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {

		String password = "1231fwef我是wziw!23@##$";
		EncryptedBean encryptedBean = new EncryptedBean();
		encryptedBean.setPassword(Base64.getEncoder().encodeToString(password.getBytes()));

		//
		String name = "qwe!2@3#天下7&无双";
		EncrypteOtherBean encrypteOtherBean = new EncrypteOtherBean();
		encrypteOtherBean.setName(Base64.getEncoder().encodeToString(name.getBytes()));

		//
		encryptedBean.setEncrypteOtherBean(encrypteOtherBean);
		System.out.println("encode encryptedBean: " + encryptedBean);
		// 使用注解处理器解码自定义类的属性
		DencryptUtil.DecryptBean(encryptedBean);
		System.out.println("decode encryptedBean: " + encryptedBean);
	}

}
