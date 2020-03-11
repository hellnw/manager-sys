package com.sbs.generator.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sbs.common.entity.QueryRequest;
import com.sbs.generator.entity.Column;
import com.sbs.generator.entity.Table;

import java.util.List;

public interface IGeneratorService {

    List<String> getDatabases(String databaseType);

    IPage<Table> getTables(String tableName, QueryRequest request, String databaseType, String schemaName);

    List<Column> getColumns(String databaseType, String schemaName, String tableName);
}
