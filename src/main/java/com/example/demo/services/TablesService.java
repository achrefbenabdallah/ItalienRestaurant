package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.TablesReponse;
import com.example.demo.dto.TablesRequest;

public interface TablesService {
	
	List<TablesReponse>getAllTablesEntity();
	TablesReponse createTablesEntity(TablesRequest request);
	TablesReponse deleteTable(long numero);
	TablesReponse updateTable(long numero , TablesRequest request);

}
