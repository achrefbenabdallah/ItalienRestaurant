package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ClientReponse;
import com.example.demo.dto.TablesReponse;
import com.example.demo.dto.TablesRequest;
import com.example.demo.models.Client;
import com.example.demo.models.Tables;
import com.example.demo.repositories.TableRepository;
@Service
public class TablesServiceImpl implements TablesService {
	
	private TableRepository repos;
	private ModelMapper mapper;
	
	
	public TablesServiceImpl(TableRepository repos, ModelMapper mapper) {
		super();
		this.repos = repos;
		this.mapper = mapper;
	}


	@Override
	public List<TablesReponse> getAllTablesEntity() {
		// TODO Auto-generated method stub
		List<Tables> tables =repos.findAll();
		List<TablesReponse> res= new ArrayList<>();
		for(Tables table : tables)
		{
			res.add(mapper.map(table, TablesReponse.class));
		}
		return res;
		
	}


	@Override
	public TablesReponse createTablesEntity(TablesRequest table) {
		// TODO Auto-generated method stub
		Tables entity = mapper.map(table, Tables.class);
		Tables newEntity = repos.save(entity);
		TablesReponse res = new TablesReponse(table.getNbCouvert(),table.getType(),table.getSupplement());
		return res;
	}


	@Override
	public TablesReponse deleteTable(long numero) {
		// TODO Auto-generated method stub
		Optional<Tables> table= repos.findById(numero);
		TablesReponse res= new TablesReponse(table.get().getNbCouvert(),table.get().getType(),table.get().getSupplement());
		repos.deleteById(numero);;
		return res;
	}
	
	public TablesReponse getTableByNumero(long numero) {
		Optional<Tables> opt=repos.findById(numero);
		Tables entity;
		if(opt.isPresent())
			entity = opt.get();
		else
			throw new NoSuchElementException("Person with this id is not found");
		TablesReponse table = new TablesReponse(entity.getNbCouvert(),entity.getType(),entity.getSupplement());
		return table;
	}


	@Override
	public TablesReponse updateTable(long numero, TablesRequest request) {
		// TODO Auto-generated method stub
		TablesReponse test=this.getTableByNumero(numero);
		//Optional<Tables> test=repos.findById(numero);
		
			
			if(request.getNbCouvert()!=0)
				test.setNbCouvert(request.getNbCouvert());
			if(request.getSupplement()!=0)
				test.setSupplement(request.getSupplement());
			if(request.getType()!=null)
				test.setType(request.getType());
			
			Tables newTable = mapper.map(test, Tables.class);
			newTable.setNumero(numero);
			repos.save(newTable);
			test.setNbCouvert(newTable.getNbCouvert());
			test.setSupplement(newTable.getSupplement());
			test.setType(newTable.getType());
			return test;

		}
	
	
	
	
	
	
	
	

}
