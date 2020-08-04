package com.stte.test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class Test_02_encrypTest {

	public static void main(String[] args) {

		String password = "12314abcde1我爱";
		String id = "中500123123X国";
		String enPassword = Base64.getEncoder().encodeToString(password.getBytes());
		String enId = Base64.getEncoder().encodeToString(id.getBytes());
		List<String> hobbyList = new ArrayList<String>();
		hobbyList.add("爬山");
		hobbyList.add("滑雪123");
		hobbyList.add("543钓鱼");
		EncryptBean encryptBean = new EncryptBean("韩立", enPassword, enId);
		encryptBean.setHobby(encodeListString(hobbyList));
		// EncryptUtil.encryptBean(EncryptBean.class, encryptBean);
		System.out.println(encryptBean.toString());
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		try {
			EncryptUtil.dencrypt(EncryptBean.class, encryptBean);
			System.out.println(encryptBean.toString());
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}

	}

	public static List<String> encodeListString(List<String> list) {
		List<String> list2 = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			list2.add(Base64.getEncoder().encodeToString(list.get(i).getBytes()));
		}
		return list2;
	}
}
