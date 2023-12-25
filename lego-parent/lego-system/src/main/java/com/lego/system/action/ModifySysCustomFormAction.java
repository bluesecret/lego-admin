package com.lego.system.action;

import com.lego.core.action.ModifyAction;
import com.lego.core.exception.BusinessException;
import com.lego.core.util.StringUtil;
import com.lego.system.dao.ISysCustomFormDao;
import com.lego.system.dao.ISysGenTableDao;
import com.lego.system.dao.ISysPermissionDao;
import com.lego.system.entity.SysCustomForm;
import com.lego.system.vo.SysCustomFormModifyVO;
import com.lego.system.vo.SysPermissionCode;

public class ModifySysCustomFormAction extends ModifyAction<SysCustomForm, ISysCustomFormDao> {

	private SysCustomFormModifyVO vo;
	private ISysGenTableDao tableDao = getDao(ISysGenTableDao.class);
	private ISysPermissionDao permissionDao = getDao(ISysPermissionDao.class);

	public ModifySysCustomFormAction(String operatorCode, SysCustomFormModifyVO vo) {
		super(SysPermissionCode.manageCustomForm, operatorCode, vo.getCode());
		this.vo = vo;
	}

	@Override
	protected void preprocess() {
		BusinessException.check(StringUtil.isNotBlank(vo.getName()), "表单名称不能为空！");
		BusinessException.check(StringUtil.isNotBlank(vo.getTableCode()), "数据表不能为空！");
		BusinessException.check(StringUtil.isNotBlank(vo.getName()), "归属菜单不能为空！");
	}

	@Override
	protected void doModify(SysCustomForm entity) {
		entity.setName(vo.getName());
		entity.setTable(tableDao.findByCode(vo.getTableCode()));
		entity.setPermission(permissionDao.findByUnsureCode(vo.getPermissionCode()));
	}
}
