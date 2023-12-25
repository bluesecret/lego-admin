package com.lego.sharding.mapper;

import java.util.List;

import com.lego.core.dto.TypeInfo;

public interface ShardingDataSourceMapper {

	List<TypeInfo> selectValid();

	List<TypeInfo> selectValidPropertiesBy(String code);
}
