ALTER TABLE sys_permission
	CHANGE COLUMN REPORT_CODE RELATE_CODE VARCHAR(255) NULL DEFAULT NULL COMMENT '关联编码' AFTER ROUTE_TYPE_ID;

ALTER TABLE sys_custom_form DROP COLUMN PERMISSION_ID;