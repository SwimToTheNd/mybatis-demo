package com.stte.common.encrypt;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class DencryptUtil {

	/**
	 * 使用Base64 解码一个对象标注了解码注解的属性
	 * 
	 * @param object
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static void DecryptBean(Object object) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> clazz = object.getClass();
		List<Field> listField = getDecryptFields(clazz);
		for (Field field : listField) {
			System.out.println("get: " + makeGetMethodByField(clazz, field));
			System.out.println("set: " + makeSetMethodByField(clazz, field));
			System.out.println();
			fieldProcessor(object.getClass(), field, object);
		}
	}

	/**
	 * 处理一个类的属性
	 * 
	 * @param clazz
	 * @param field
	 * @param obj
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	private static void fieldProcessor(Class<?> clazz, Field field, Object obj) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		// 获取Filed的实际类型
		Type type = field.getGenericType();
		Class<?> typeClazz = field.getType();
		// Get方法
		Method getMethod = null;
		Method setMethod = null;

		// 判断类型
		// 一般类类型
		if (type instanceof Class) {
			// String类型
			if (typeClazz == String.class) {
				getMethod = makeGetMethodByField(clazz, field);
				// 使用get方法获取属性的值
				String result = (String) getMethod.invoke(obj);
				// 使用set方法重新设置属性的值
				setMethod = makeSetMethodByField(clazz, field);
				setMethod.invoke(obj, new String(Base64.getDecoder().decode(result.getBytes())));
			} else {
				// 自定义类型
				String pacageName = typeClazz.getPackage().getName();
				if (pacageName.startsWith("me.java")) {
					getMethod = makeGetMethodByField(clazz, field);
					// 递归调用处理
					DecryptBean(getMethod.invoke(obj));
				}
			}
		} else if (type instanceof ParameterizedType) {
			// 属性类型为参数化类型
			// 泛型集合的实际类型
			System.out.println("type is ParameterizedType: " + type.getTypeName());
			Type[] types = ((ParameterizedType) type).getActualTypeArguments();
			// List<?>
			if (typeClazz == List.class) {
				System.out.println("type is list");
				Type typeIndx = types[0];
			}
		}
	}

	/**
	 * 获取一个自定义类的需要解码的属性
	 * 
	 * @return
	 */
	private static List<Field> getDecryptFields(Class<?> clazz) {
		List<Field> fieldList = new ArrayList<Field>();
		// 获取一个类的属性
		Field[] fields = clazz.getDeclaredFields();
		// 查询一个标注了需要解码的注解
		for (Field field : fields) {
			Dencrypt dencrypt = field.getDeclaredAnnotation(Dencrypt.class);
			if (dencrypt != null) {
				fieldList.add(field);
			}
		}
		return fieldList;
	}

	/**
	 * 根据类的属性类获取 get方法
	 * 
	 * @param field
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	private static Method makeGetMethodByField(Class<?> clazz, Field field)
			throws NoSuchMethodException, SecurityException {
		String fieldName = field.getName();
		StringBuilder sBuilder = new StringBuilder("get").append(fieldName);
		sBuilder.setCharAt(3, Character.toUpperCase(sBuilder.charAt(3)));
		Method method = clazz.getMethod(sBuilder.toString());

		return method;
	}

	/**
	 * 根据类的属性类获取 set方法
	 * 
	 * @param clazz
	 * @param field
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	private static Method makeSetMethodByField(Class<?> clazz, Field field)
			throws NoSuchMethodException, SecurityException {
		String fieldName = field.getName();
		StringBuilder sBuilder = new StringBuilder("set").append(fieldName);
		sBuilder.setCharAt(3, Character.toUpperCase(sBuilder.charAt(3)));
		// Class clazz = field.getType();
		// Type type = field.getGenericType();
		Method method = clazz.getMethod(sBuilder.toString(), field.getType());
		return method;
	}
}
