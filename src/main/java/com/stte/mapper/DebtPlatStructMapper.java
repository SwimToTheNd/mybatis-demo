package com.stte.mapper;


import com.stte.domain.DebtPlatStruct;
import com.stte.domain.DebtPlatStructInfo;

import java.util.List;

public interface DebtPlatStructMapper {

	public List<DebtPlatStruct> getDebtPlatStruct();
	
	public List<DebtPlatStructInfo> getDebtPlatStructInfoById(String period);
}
