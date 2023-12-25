package com.lego.system.service;

import java.util.List;

import com.lego.core.dto.LegoPage;
import com.lego.core.dto.TypeInfo;
import com.lego.system.dto.SysGenTableInfo;
import com.lego.system.vo.SysGenTableCreateVO;
import com.lego.system.vo.SysGenTableModifyVO;
import com.lego.system.vo.SysGenTableSearchVO;

public interface ISysGenTableService {

	LegoPage<SysGenTableInfo> findPageBy(SysGenTableSearchVO vo);

	List<SysGenTableInfo> findNotExists();

	List<TypeInfo> findSimpleType();

	SysGenTableInfo findByCode(String code);

	List<TypeInfo> findTableName();

	SysGenTableInfo findInitBy(String code);

	void add(String operatorCode, SysGenTableCreateVO vo);

	void modify(String operatorCode, SysGenTableModifyVO vo);

	void sync(String operatorCode, String code);
}
