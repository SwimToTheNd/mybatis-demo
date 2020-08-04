package com.stte.common.encrypt;

import java.util.List;

public class EncrypteOtherBean {

	@Dencrypt
	private String name;

	@Dencrypt
	private List<String> plans;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getPlans() {
		return plans;
	}

	public void setPlans(List<String> plans) {
		this.plans = plans;
	}

	@Override
	public String toString() {
		return "{ name: " + name + " }";
	}
}
