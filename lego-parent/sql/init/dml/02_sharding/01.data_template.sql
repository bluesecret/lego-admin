CALL add_sharding_template('DruidDataSource', '阿里数据源', 'DataSource', '[{"code":"type","name":"类型"},{"code":"driverClassname","name":"驱动类"},{"code":"url","name":"连接地址"},{"code":"username","name":"用户名"},{"code":"password","name":"密码"}]', null);

CALL add_sharding_template('INLINE', '行分片算法模板', 'ShardingAlgorithm', '[\n  {\n    "name": "行表达式",\n    "code": "algorithm-expression"\n  },\n  {\n    "name": "是否允许范围查询",\n    "code": "allow-range-query-with-inline-sharding",\n    "description": "注意：范围查询会无视分片策略，进行全路由（boolean类型，可不设置，默认为false）"\n  }\n]', null);
CALL add_sharding_template('INTERVAL', '时间范围分片算法模板', 'ShardingAlgorithm', '[\n  {\n    "name": "分片键的时间戳格式",\n    "code": "datetime-pattern",\n    "description": "必须遵循 Java DateTimeFormatter 的格式。例如：yyyy-MM-dd HH:mm:ss，yyyy-MM-dd 或 HH:mm:ss 等。"\n  },\n  {\n    "name": "时间分片下界值",\n    "code": "datetime-lower",\n    "description": "格式与 datetime-pattern 定义的时间戳格式一致"\n  },\n  {\n    "name": "时间分片上界值",\n    "code": "datetime-upper",\n    "description": "格式与 datetime-pattern 定义的时间戳格式一致（可不设置，默认为当前时间）"\n  },\n  {\n    "name": "分片数据源或真实表的后缀格式",\n    "code": "sharding-suffix-pattern",\n    "description": "必须遵循 Java DateTimeFormatter 的格式，必须和 datetime-interval-unit 保持一致。例如：yyyyMM"\n  },\n  {\n    "name": "分片键时间间隔",\n    "code": "datetime-interval-amount",\n    "description": "超过该时间间隔将进入下一分片（可不设置，默认为1）"\n  },\n  {\n    "name": "分片键时间间隔单位",\n    "code": "datetime-interval-unit",\n    "description": "必须遵循 Java ChronoUnit 的枚举值。例如：MONTHS（可不设置，默认值为DAYS）"\n  }\n]', null);
CALL add_sharding_template('COSID_INTERVAL', '基于CosId的固定时间范围的分片算法模板', 'ShardingAlgorithm', '[\n  {\n    "name": "时区",\n    "code": "zone-id",\n	"description": "必须遵循 java.time.ZoneId 的所含值。 例如：Asia/Shanghai"\n  },\n  {\n    "name": "分片数据源或真实表的前缀格式",\n    "code": "logic-name-prefix"\n  },\n  {\n    "name": "时间分片下界值",\n    "code": "datetime-lower",\n	"description": "格式与 yyyy-MM-dd HH:mm:ss 的时间戳格式一致"\n  },\n  {\n    "name": "时间分片上界值",\n    "code": "datetime-upper",\n	"description": "格式与 yyyy-MM-dd HH:mm:ss 的时间戳格式一致"\n  },\n  {\n    "name": "分片数据源或真实表的后缀格式",\n    "code": "sharding-suffix-pattern",\n	"description": "必须遵循 Java DateTimeFormatter 的格式，必须和 datetime-interval-unit 保持一致。例如：yyyyMM"\n  },\n  {\n    "name": "分片键时间间隔",\n    "code": "datetime-interval-amount",\n	"description": "超过该时间间隔将进入下一分片"\n  },\n  {\n    "name": "分片键时间间隔单位",\n    "code": "datetime-interval-unit",\n	"description": "必须遵循 Java ChronoUnit 的枚举值。例如：MONTHS"\n  }\n]', '固定时间范围的分片算法');
CALL add_sharding_template('COMPLEX_INLINE', '复合行表达式分片算法', 'ShardingAlgorithm', '[\n  {\n    "name": "分片列名称",\n    "code": "sharding-columns",\n	"description": "多个列用逗号分隔。如不配置则不能校验"\n  },\n  {\n    "name": "分片算法的行表达式",\n    "code": "algorithm-expression"\n  },\n  {\n    "name": "是否允许范围查询",\n    "code": "allow-range-query-with-inline-sharding",\n	"description": "注意：范围查询会无视分片策略，进行全路由（boolean类型，可不设置，默认为false）"\n  }\n]', null);
CALL add_sharding_template('HINT_INLINE', '强制分片算法模板', 'ShardingAlgorithm', '[\n  {\n    "name": "分片算法的行表达式",\n    "code": "algorithm-expression"\n  }\n]', null);
CALL add_sharding_template('CLASS_BASED', '自定义类分片算法模板', 'ShardingAlgorithm', '[\n  {\n    "name": "分片策略类型",\n    "code": "strategy",\n	"description": "支持 STANDARD、COMPLEX 或 HINT（不区分大小写）"\n  },\n  {\n    "name": "分片算法全限定类名",\n    "code": "algorithm-class-name",\n	"description": "类需实现接口StandardShardingAlgorithm"\n  }\n]', null);

CALL add_sharding_template('standard', '标准分片表配置模板', 'ShardingStrategy', null, '分片列名（单列）');
CALL add_sharding_template('complex', '复合分片表配置模板', 'ShardingStrategy', null, '分片列名（多列，逗号分隔）');
CALL add_sharding_template('none', '不分片表配置模板', 'ShardingStrategy', null, null);
