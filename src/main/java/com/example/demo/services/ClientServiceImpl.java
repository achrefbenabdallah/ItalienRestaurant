package com.example.demo.services;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ClientReponse;
import com.example.demo.dto.ClientRequest;
import com.example.demo.dto.MetReponse;
import com.example.demo.dto.TicketReponse;
import com.example.demo.models.Client;
import com.example.demo.models.Met;
import com.example.demo.models.Ticket;
import com.example.demo.repositories.ClientRepository;
import com.example.demo.repositories.TicketRepository;
@Service
public class ClientServiceImpl implements ClientService {
	
	private ClientRepository repos;
	private TicketRepository repo;
	private ClientRepository rep;
	private ModelMapper mapper;
	@Autowired
	public ClientServiceImpl(ClientRepository repos , ModelMapper mapper,TicketRepository repo,ClientRepository rep) {
		super();
		this.repos = repos;
		this.mapper=mapper;
		this.repo=repo;
		this.rep=rep;
	}


	@Override
	public List<ClientReponse> getAllClient() {
		// TODO Auto-generated method stub
		List<Client> clients =repos.findAll();
		List<ClientReponse> res= new ArrayList<>();
		for(Client client : clients)
		{
			res.add(mapper.map(client, ClientReponse.class));
		}
		return res;
	}


	@Override
	public ClientReponse createClientEntity(ClientRequest client) {
		Client entity = mapper.map(client, Client.class);
		Client newEntity = repos.save(entity);
		ClientReponse res=new ClientReponse(client.getNom(),client.getPrenom(),client.getDateNaissance(),
				client.getCourriel(),client.getTel());
		return res;
	}


	@Override
	public ClientReponse deleteClientById(long id) {
		Optional<Client> client = repos.findById(id);
		ClientReponse res=new ClientReponse(client.get().getNom(),client.get().getPrenom(),client.get().getDateNaissance(),
				client.get().getCourriel(),client.get().getTel());
		repos.deleteById(id);
		return res;
	}
	
	
	public ClientReponse getClientById(long id)
	{
		Optional<Client> opt = repos.findById(id);
		Client entity;
		if(opt.isPresent())
			entity = opt.get();
		else
			throw new NoSuchElementException("Person with this id is not found");
		ClientReponse client = new ClientReponse(entity.getNom(),entity.getPrenom(),entity.getDateNaissance(),entity.getCourriel(),entity.getTel());
		return client;
	}


	@Override
	public ClientReponse updateClientEntity(long id, ClientRequest request) {
		// TODO Auto-generated method stub
		ClientReponse test = this.getClientById(id);
		//Optional<Client> client = repos.findById(id);
		if(request.getNom()!=null)
			test.setNom(request.getNom());
		if(request.getPrenom()!=null)
			test.setPrenom(request.getPrenom());
		if(request.getCourriel()!=null)
			test.setCourriel(request.getCourriel());
		if(request.getDateNaissance()!=null)
			test.setDateNaissance(request.getDateNaissance());
		if(request.getTel()!=null)
			test.setTel(request.getTel());
		Client newClient = mapper.map(test, Client.class);
		newClient.setId(id);
		repos.save(newClient);
		test.setNom(newClient.getNom());
		test.setPrenom(newClient.getPrenom());
		test.setCourriel(newClient.getCourriel());
		test.setDateNaissance(newClient.getDateNaissance());
		test.setTel(newClient.getTel());
		return test;
		
	
	}


	@Override
	public String getMostPlatOrdred(String debut , String fin ) {
		// TODO Auto-generated method stub
		
		LocalDate deb = LocalDate.parse(debut);
		LocalDate f = LocalDate.parse(fin);
		List<Ticket> listTickets = repo.findAll();
		List<Ticket> listPeriode=new ArrayList<>();
		for(Ticket ticket : listTickets)
		{
			if(ticket.getDate().isBefore(f)&&ticket.getDate().isAfter(deb)||ticket.getDate().equals(deb)||ticket.getDate().equals(f))
				listPeriode.add(ticket);
		}
		HashMap<String, Integer> plat = new HashMap<>();
		for(Ticket ticket : listPeriode)
		{
			for(Met met:ticket.getMets()) {
				if(plat.containsKey(met.getNom()))
					plat.put( met.getNom(), plat.get(met.getNom())+1);
				else {
					plat.put( met.getNom(), 0);
				}
			}
			
		}
		Integer max=0;
		String key="";
		for(Integer val:plat.values())
			if(val>max) 
				max=val;
		for(String val:plat.keySet())	
			if(plat.get(val)==max)
				key=val;
			
			
			
		/*List<String> palts=new ArrayList<String>();
		List<Met> mets = new ArrayList<>();
		for(Ticket ticket : listTickets) {
			if(ticket.getDate().getYear()==date.getYear()&&ticket.getDate().getMonth()==date.getMonth()&&ticket.getDate().getDayOfMonth()==date.getDayOfMonth())
			{
				mets=ticket.getMets();
				for(Met met : mets)
					palts.add(met.getNom());
			}
			
		}
		
		for(String p : palts)
		{
			long m=0;
			//long m=palts.stream().filter(plat->p.equals(p)).count();
			for(String r:palts)
			{
				if(p!=null) {
				if(p.equalsIgnoreCase(r))
					m++;
			}}
			if(m>max) {
				max=m;
				nom=p;
			}
		}
		
		return nom;*/
		if(key=="")
			return "vide";
		else
			return key;
	}


	@Override
	public ClientReponse clientPlusFidele() {
		// TODO Auto-generated method stub
		List<Ticket> listTicket=repo.findAll();
		
		HashMap<Integer, Integer> listClient = new HashMap<>();
		for(Ticket ticket:listTicket)
		{
			if(listClient.containsKey(ticket.getClient().getId()))
				listClient.put((int) ticket.getClient().getId(), listClient.get(ticket.getClient().getId())+1);
			else {
				listClient.put((int) ticket.getClient().getId(), 0);
			}
		}
		Integer max=0;
		Integer key=0;
		for(Integer val:listClient.values())
			if(val>max) 
				max=val;
		for(Integer val:listClient.keySet())	
			if(listClient.get(val)==max)
				key=val;
		long res=(long)key;
			
		Optional<Client> client=rep.findById(res);		
		ClientReponse rep=new ClientReponse(client.get().getNom(),client.get().getPrenom(), client.get().getDateNaissance(),client.get().getCourriel(),client.get().getTel());
		return rep ;
	}
	
	@Override
	public String tablePlusReserver() {
		// TODO Auto-generated method stub
		List<Ticket> listTicket=repo.findAll();
		
		int max=0;
		long key=0;
		for(Ticket ticket:listTicket)
		{
			int nb=0;
			for(Ticket tic:listTicket) {
				if(ticket.getTables().getNumero()==tic.getTables().getNumero())
					nb++;
			}
			if(nb>max) {
				max=nb;
				key=ticket.getTables().getNumero();
			}
		}
		/*HashMap<Integer, Integer> listTable = new HashMap<>();
		for(Ticket ticket:listTicket)
		{
			if(listTable.containsKey(ticket.getTables().getNumero()))
				listTable.put((int) ticket.getTables().getNumero(), listTable.get(ticket.getTables().getNumero())+1);
			else {
				listTable.put((int) ticket.getTables().getNumero(), 0);
			}
		}
		/*for(Integer val:listTable.values())
			System.out.println(val);
		Integer max=0;
		Integer key=0;
		for(Integer val:listTable.values())
			if(val>max) 
				max=val;
		for(Integer val:listTable.keySet())	
			if(listTable.get(val)==max)
				key=val;*/
		
		return "The most reserved table is number "+key+" and the number of reservation is "+max;
	}
	
	
	@Override
	public String mostReservedDay(long id) {
		// TODO Auto-generated method stub
		List<Ticket> listTicket=repo.findAll();
		LocalDate date = null;
		int max=0;
		for(Ticket ticket:listTicket)
		{
			int nb=0;
			for(Ticket tic:listTicket)
			{
				if(ticket.getDate().equals(tic.getDate())&&ticket.getClient().getId()==id)
				{
					nb++;
				}
			}
			if(nb>max) {
				max=nb;
				date=ticket.getDate();
			}
		}
		return "The most reserved day for customer with id "+id+" is "+date.getDayOfWeek();
	}
	
	@Override
	public String incomeOfDay() {
		// TODO Auto-generated method stub
		float res=0;
		List<Ticket> listTicket=repo.findAll();
		for(Ticket ticket:listTicket)
		{
			if(ticket.getDate().equals(LocalDate.now()))
				res+=ticket.getAddition();
		}
		return "Income of today "+LocalDate.now()+" is "+res;
	}
	
	@Override
	public String incomeOfMonth() {
		// TODO Auto-generated method stub
		float res=0;
		List<Ticket> listTicket=repo.findAll();
		for(Ticket ticket:listTicket)
		{
			if(ticket.getDate().getYear()==LocalDate.now().getYear()) {
				if(ticket.getDate().getMonth().equals(LocalDate.now().getMonth()))
				{
					res+=ticket.getAddition();
				}
		}
		}
		return "Income of month "+LocalDate.now().getMonth()+" is "+res;
	}
	
	@Override
	public String incomeOfWeek() {
		// TODO Auto-generated method stub
		float res=0;
		List<Ticket> listTicket=repo.findAll();
		for(Ticket ticket:listTicket)
		{
			if(ticket.getDate().isBefore(LocalDate.now())&&ticket.getDate().isAfter(LocalDate.now().minusDays(8))||ticket.getDate().equals(LocalDate.now()))
				res+=ticket.getAddition();
					
		}
		return "Income of week between "+LocalDate.now()+" and "+LocalDate.now().minusDays(7)+" is "+res;
	}
	
	@Override
	public String incomeOfPeriod(String d, String f) {
		// TODO Auto-generated method stub
		LocalDate debut=LocalDate.parse(d);
		LocalDate fin=LocalDate.parse(f);
		float res=0;
		List<Ticket> listTicket=repo.findAll();
		for(Ticket ticket:listTicket)
		{
			if(ticket.getDate().isBefore(fin.plusDays(1))&&ticket.getDate().isAfter(debut.minusDays(1)))
				res+=ticket.getAddition();
		}
		return  "Income of period between "+debut+" and "+fin+" is "+res;
	}

}
