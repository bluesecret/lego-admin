ALTER TABLE sys_custom_field
	ADD COLUMN SN INT(5) NOT NULL DEFAULT '0' AFTER GENERATOR_ID;
