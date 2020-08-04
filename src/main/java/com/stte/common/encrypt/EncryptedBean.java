package com.stte.common.encrypt;

import java.util.List;
import java.util.Map;

/**
 * 属性被加密的Bean
 * 
 * @author BloodFly
 *
 */
public class EncryptedBean {

	/**
	 * String类型
	 */
	@Dencrypt
	private String password;

	/**
	 * 自定义类
	 */
	@Dencrypt
	private EncrypteOtherBean encrypteOtherBean;

	/**
	 * List<String> 泛型类型
	 */
	@Dencrypt
	private List<String> hobbys;

	/**
	 * List<EncrypteOtherBean> 泛型对象类
	 */
	@Dencrypt
	private List<EncrypteOtherBean> otherBeans;

	/**
	 * Map 泛型类型
	 */
	@Dencrypt
	private Map<String, String> address;

	/**
	 * Map 自定义对象类
	 */
	@Dencrypt
	private Map<String, EncrypteOtherBean> mapEncrypts;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public EncrypteOtherBean getEncrypteOtherBean() {
		return encrypteOtherBean;
	}

	public void setEncrypteOtherBean(EncrypteOtherBean encrypteOtherBean) {
		this.encrypteOtherBean = encrypteOtherBean;
	}

	public List<String> getHobbys() {
		return hobbys;
	}

	public void setHobbys(List<String> hobbys) {
		this.hobbys = hobbys;
	}

	public List<EncrypteOtherBean> getOtherBeans() {
		return otherBeans;
	}

	public void setOtherBeans(List<EncrypteOtherBean> otherBeans) {
		this.otherBeans = otherBeans;
	}

	public Map<String, String> getAddress() {
		return address;
	}

	public void setAddress(Map<String, String> address) {
		this.address = address;
	}

	public Map<String, EncrypteOtherBean> getMapEncrypts() {
		return mapEncrypts;
	}

	public void setMapEncrypts(Map<String, EncrypteOtherBean> mapEncrypts) {
		this.mapEncrypts = mapEncrypts;
	}

	@Override
	public String toString() {
		return "{ password: " + password + " encrypteOtherBean: " + encrypteOtherBean + " }";
	}
}
