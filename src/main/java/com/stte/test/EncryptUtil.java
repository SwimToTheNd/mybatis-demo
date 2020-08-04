package com.stte.test;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


public class EncryptUtil {

	public static void encryptBean(Class<?> clzz, Object obj) {
		List<Field> listFields = new ArrayList<Field>();
		Field[] fields = clzz.getDeclaredFields();
		for (Field field : fields) {
			System.out.println(field.getName() + ":" + field.getType() + ":" + field.getType().getName() + ":"
					+ field.getType().getSimpleName());
			Annotation[] annotations = field.getAnnotations();
			for (Annotation annotation : annotations) {
				System.out.println(annotation.toString() + ":" + annotation.annotationType());
				if (annotation.annotationType().equals(EncodeRequried.class)) {
					System.out.println(true);
					listFields.add(field);
				}

			}
		}

		showAllEncryptedField(getEncryptedFieldList(clzz));
	}

	public static void dencrypt(Class<?> clazz, Object obj) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		List<Field> listfield = getEncryptedFieldList(clazz);
		showAllEncryptedField(listfield);
		for (Field field : listfield) {
			Method methodGet = getMethodGetByField(clazz, field);
			Object getResult = methodGet.invoke(obj);
			if (String.class.isInstance(getResult)) {
				String decodedStr = new String(Base64.getDecoder().decode(String.valueOf(getResult)));
				Method methodSet = getMethodSetByField(clazz, field);
				methodSet.invoke(obj, decodedStr);
			} else if (List.class.isInstance(getResult)) {
				List<String> decodedList = decodeList((List<String>) getResult);
				Method methodSet = getMethodSetByField(clazz, field);
				methodSet.invoke(obj, decodedList);
			}
			
		}
	}

	private static List<String> decodeList(List<String> list) {
		List<String> decodeResult = new ArrayList<String>();
		for (String src : list) {
			decodeResult.add(new String(Base64.getDecoder().decode(src)));
		}
		return decodeResult;
	}

	private static List<Field> getEncryptedFieldList(Class<?> clazz) {
		List<Field> listFields = new ArrayList<Field>();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			EncodeRequried encodeRequried = field.getDeclaredAnnotation(EncodeRequried.class);
			if (encodeRequried != null) {
				listFields.add(field);
			}
			Type type = field.getGenericType();
			if (type instanceof ParameterizedType) {
				Type[] types = ((ParameterizedType) type).getActualTypeArguments();
				for (Type typ : types) {
					System.out.println(typ+":"+typ.getTypeName());
					System.out.println(typ.getTypeName().startsWith("java.lang"));
				}
			}
		}
		return listFields;
	}

	private static void showAllEncryptedField(List<Field> list) {
		System.out.println(list.size());
		for (Field field : list) {
			System.out.println(field.getName() + " : " + field.getType());
		}
	}

	private static Method getMethodGetByField(Class<?> clazz, Field field)
			throws NoSuchMethodException, SecurityException {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("get");
		String fieldName = field.getName();
		sBuilder.append(fieldName.substring(0, 1).toUpperCase());
		sBuilder.append(fieldName.substring(1));
		System.out.println(sBuilder.toString());
		Method method = clazz.getMethod(sBuilder.toString());

		return method;
	}

	private static Method getMethodSetByField(Class<?> clazz, Field field)
			throws NoSuchMethodException, SecurityException {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("set");
		String fieldName = field.getName();
		sBuilder.append(fieldName.substring(0, 1).toUpperCase());
		sBuilder.append(fieldName.substring(1));
		System.out.println(sBuilder.toString());
		Class<?> typeclazz = field.getType();
		System.out.println(typeclazz + ":" + typeclazz.getName());
		Method method = clazz.getDeclaredMethod(sBuilder.toString(), typeclazz);
		return method;
	}
}
