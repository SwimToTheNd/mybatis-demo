package com.stte.test;

import com.stte.test.printreflectinfo.Test_01_GenericBean;

import java.lang.reflect.*;

public class Test_00_PrintReflectInfo {

	public static void main(String[] args) throws NoSuchFieldException, SecurityException, ClassNotFoundException {
		Class<EncryptBean> clazz = EncryptBean.class;
		// Class.getName 包名+类名
		System.out.println("getName(): " + clazz.getName());
		// Class.getSimpleName 类名
		System.out.println("getSimpleName(): " + clazz.getSimpleName());
		// 获取父类的类型Type
		System.out.println("getGenericSuperclass(): " + clazz.getGenericSuperclass());

		//
		System.out.println("++++++++++++++++++++++++1");
		Field field = Test_01_GenericBean.class.getDeclaredField("map");
		// 返回类的属性的名称
		System.out.println("Field.getName(): " + field.getName());
		// 返回类型的类
		System.out.println("Field.getType(): " + field.getType());
		// 返回Type类
		System.out.println("Field.getGenericType(): " + field.getGenericType());
		Type type = field.getGenericType();
		System.out.println("Type.getTypeName(): " + type.getTypeName());
		//错误
//		System.out.println("Class.forName(): " + Class.forName("me.java.test.Test_00_PrintReflectInfo"));
//		System.out.println("Class.forName(Type.getTypeName()): " + Class.forName(type.getTypeName()));
		// 判断该类型Type是否为参数化类型ParameterizeType（泛型类型）
		System.out.println("type is ParameterizedType: " + (type instanceof ParameterizedType));
		if (type instanceof ParameterizedType) {
			// 获取参数类型的实际类型
			Type[] types = ((ParameterizedType) type).getActualTypeArguments();
			for (Type type2 : types) {
				// 输出参数的实际类型名 类型化参数T1
				 System.out.println(type2.getTypeName());
				// 判断参数属于何种Type
				// 是否为参数化类型
				if (type2 instanceof ParameterizedType) {
					System.out.println(type2.getTypeName() + " is ParameterizedType");
				} else if (type2 instanceof GenericArrayType) {
					// 参数类型为数组类型
					System.out.println(type2.getTypeName() + " is GenericArrayType");
				} else if (type2 instanceof TypeVariable) {
					// 参数是否为类型变量类型
					System.out.println(type2.getTypeName() + " is TypeVariable " + (type2 instanceof Class));
				} else if (type2 instanceof WildcardType) {
					// 参数为通配符
					System.out.println(type2.getTypeName() + " is WildcardType");
				} else {
					// 一般类型（Class）
					System.out.println(type2.getTypeName() + " is Class " + (type2 instanceof Class));
					System.out.println(type2.getTypeName() + " is Class " + ((Object)type2 instanceof Integer));
				}
			}
		}
	}

}
