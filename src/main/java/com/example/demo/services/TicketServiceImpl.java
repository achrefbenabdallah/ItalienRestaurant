package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ClientReponse;
import com.example.demo.dto.TicketReponse;
import com.example.demo.dto.TicketRequest;
import com.example.demo.models.Client;
import com.example.demo.models.Tables;
import com.example.demo.models.Ticket;
import com.example.demo.repositories.ClientRepository;
import com.example.demo.repositories.TableRepository;
import com.example.demo.repositories.TicketRepository;
@Service
public class TicketServiceImpl implements TicketService {
	
	TicketRepository repos ;
	ClientRepository repo;
	TableRepository rep;
	ModelMapper mapper;
	
	
	@Autowired
	public TicketServiceImpl(TicketRepository repos, ModelMapper mapper,ClientRepository repo,TableRepository rep) {
		super();
		this.repos = repos;
		this.mapper = mapper;
		this.repo=repo;
		this.rep=rep;
	}



	@Override
	public List<TicketReponse> getAllTicket() {
		// TODO Auto-generated method stub
		List<Ticket> tickets= repos.findAll();
		List<TicketReponse> res=new ArrayList<>();
		for(Ticket ticket:tickets)
			res.add(mapper.map(ticket, TicketReponse.class));
		return res;
	}



	@Override
	public TicketReponse createTicketEntity(TicketRequest request) {
		// TODO Auto-generated method stub
		//Ticket entity=mapper.map(request, Ticket.class);
		//repos.save(entity);
		
		Ticket entity=mapper.map(request, Ticket.class);
		
		Client clientEntity=entity.getClient();
		
		Client clientInBase=repo.save(clientEntity);
		
		entity.setClient(clientInBase);
		
		
		
		Tables tableEntity=entity.getTables();
		Tables tableInBase=rep.save(tableEntity);
		entity.setTables(tableInBase);
		
		Ticket newEntity=repos.save(entity);
		
		
		//TicketReponse res= new TicketReponse(request.getDate(), request.getAddition(),request.getNbCouvert(), request.getClient(), request.getTables());
		//repos.save(mapper.map(res, Ticket.class));
		return mapper.map(newEntity, TicketReponse.class);
	}



	@Override
	public TicketReponse deleteTicket(long numero) {
		// TODO Auto-generated method stub
		Optional<Ticket> ticket= repos.findById(numero);
		if(ticket.isPresent()) {
			//ticket.get().getDate()
		TicketReponse res=new TicketReponse(ticket.get().getDate(), ticket.get().getAddition(),ticket.get().getNbCouvert(),ticket.get().getClient(),ticket.get().getTables());
		repos.deleteById(numero);
		return res;}
		else {
			throw new NoSuchElementException("ticket does not existe !");
		}
	}
	
	
	public TicketReponse getTicketByNumero(long numero) {
		Optional<Ticket> opt = repos.findById(numero);
		Ticket entity;
		if(opt.isPresent())
			entity = opt.get();
		else
			throw new NoSuchElementException("Person with this id is not found");
		TicketReponse ticket = new TicketReponse(entity.getDate(),entity.getAddition(),entity.getNbCouvert(),entity.getClient(),entity.getTables());
		return ticket;
	}



	@Override
	public TicketReponse updateTicket(long numero, TicketRequest request) {
		// TODO Auto-generated method stub
		TicketReponse test=this.getTicketByNumero(numero);
		//Optional<Ticket> entity = repos.findById(numero);
		
			if(request.getDate()!=null)
				test.setDate(request.getDate());
			if(request.getNbCouvert()!=0)
				test.setNbCouvert(request.getNbCouvert());
			if(request.getAddition()!=0)
				test.setAddition(request.getAddition());
			if(request.getClient()!=null)
				test.setClient(request.getClient());
			if(request.getTables()!=null)
				test.setTables(request.getTables());
			Ticket newTicket=mapper.map(test, Ticket.class);
			newTicket.setNumero(numero);
			repos.save(newTicket);
			
			test.setAddition(newTicket.getAddition());
			test.setClient(newTicket.getClient());
			test.setDate(newTicket.getDate());
			test.setNbCouvert(newTicket.getNbCouvert());
			test.setTables(newTicket.getTables());
			return test;
	}
	
	

}
