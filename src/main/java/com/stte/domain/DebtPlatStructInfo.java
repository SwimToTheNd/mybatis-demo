package com.stte.domain;

public class DebtPlatStructInfo {
	private int id;
	private String period;
	private String debtType;
	private double debtTypeLv;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getDebtType() {
		return debtType;
	}

	public void setDebtType(String debtType) {
		this.debtType = debtType;
	}

	public double getDebtTypeLv() {
		return debtTypeLv;
	}

	public void setDebtTypeLv(double debtTypeLv) {
		this.debtTypeLv = debtTypeLv;
	}

}
