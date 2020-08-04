package com.stte.test;

import com.alibaba.fastjson.JSON;
import com.stte.domain.DebtPlatStruct;
import com.stte.domain.DebtPlatStructInfo;
import com.stte.mapper.DebtPlatStructMapper;
import com.stte.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * 测试一对多的映射
 * 
 * @author BloodFly
 *
 */
public class Test_02_Mapper {
	public static void main(String[] args) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		DebtPlatStructMapper dPlatStructMapper = sqlSession.getMapper(DebtPlatStructMapper.class);
		List<DebtPlatStruct> debtPlatStructs = dPlatStructMapper.getDebtPlatStruct();
		System.out.println(JSON.toJSONString(debtPlatStructs));
		
		System.out.println(JSON.toJSON(null));
		
		List<DebtPlatStructInfo> lPlatStructInfos = dPlatStructMapper.getDebtPlatStructInfoById("2018");
		System.out.println(JSON.toJSON(lPlatStructInfos));
	}
}
