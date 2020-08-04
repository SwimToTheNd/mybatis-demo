package com.stte.domain;

import java.util.List;

public class DebtPlatStruct {

	private String debtType;
	private List<String> periods;
	private List<Double> debtTypeLvs;

	public String getDebtType() {
		return debtType;
	}

	public void setDebtType(String debtType) {
		this.debtType = debtType;
	}

	public List<String> getPeriod() {
		return periods;
	}

	public void setPeriod(List<String> period) {
		this.periods = period;
	}

	public List<Double> getDebtTypeLv() {
		return debtTypeLvs;
	}

	public void setDebtTypeLv(List<Double> debtTypeLv) {
		this.debtTypeLvs = debtTypeLv;
	}

}
