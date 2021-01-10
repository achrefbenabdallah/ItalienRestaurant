package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.demo.dto.MetReponse;
import com.example.demo.dto.MetRequest;
import com.example.demo.models.Met;
import com.example.demo.repositories.MetRepository;
import com.example.demo.repositories.TicketRepository;
@Service
public class MetServiceImpl implements MetService{

	private MetRepository repos;
	private TicketRepository rep;
	private ModelMapper mapper;
	
	
	@Autowired
	public MetServiceImpl(MetRepository repos, ModelMapper mapper,TicketRepository rep) {
		super();
		this.repos = repos;
		this.mapper = mapper;
		this.rep=rep;
	}



	@Override
	public List<MetReponse> getAllMet() {
		// TODO Auto-generated method stub
		List<Met> mets =repos.findAll();
		List<MetReponse> res= new ArrayList<>();
		for(Met met : mets)
		{
			res.add(mapper.map(met, MetReponse.class));
		}
		return res;
	}



	@Override
	public MetReponse creatMet(MetRequest request) {
		// TODO Auto-generated method stub
		Met entity=mapper.map(request, Met.class);
		Met newEntity=repos.save(entity);
		
		MetReponse res= new MetReponse(entity.getNom(), entity.getPrix(),request.getType());
		return res;
	}



	@Override
	public MetReponse deleteMet(String nom) {
		// TODO Auto-generated method stub
		Optional<Met> met= repos.findById(nom);
		if (met.isPresent()) {
			MetReponse type=mapper.map(met, MetReponse.class);
			MetReponse res= new MetReponse(met.get().getNom(), met.get().getPrix(),met.get().getType());
			repos.deleteById(nom);
			return res;
		}else {
			throw new NoSuchElementException("met does not existe !!");
		}
		
	}

	public MetReponse getMetByNom(String nom) {
		Optional<Met> opt = repos.findById(nom);
		Met entity;
		if(opt.isPresent())
			entity = opt.get();
		else
			throw new NoSuchElementException("Person with this id is not found");
		MetReponse met= new MetReponse(entity.getNom(),entity.getPrix(),null);
		return met;
	}


	@Override
	public MetReponse updateMet(String nom, MetRequest request) {
		// TODO Auto-generated method stub
		MetReponse entity=this.getMetByNom(nom);
			if(request.getNom()!=null)
				entity.setNom(request.getNom());
			if(request.getPrix()!=0)
				entity.setPrix(request.getPrix());
			if(request.getType()!=null)
				entity.setType(request.getType());
			Met newMet=mapper.map(entity, Met.class);
			newMet.setNom(nom);
			repos.save(newMet);
			entity.setNom(newMet.getNom());
			entity.setPrix(newMet.getPrix());
			entity.setType(request.getType());
			return entity;
	
		
	}
	
	
	
	

}
