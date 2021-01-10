package com.example.demo.endPoint;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ClientReponse;
import com.example.demo.dto.ClientRequest;
import com.example.demo.dto.MetReponse;
import com.example.demo.dto.MetRequest;
import com.example.demo.dto.TablesReponse;
import com.example.demo.dto.TablesRequest;
import com.example.demo.dto.TicketReponse;
import com.example.demo.dto.TicketRequest;
import com.example.demo.models.Client;
import com.example.demo.services.ClientService;
import com.example.demo.services.MetService;
import com.example.demo.services.TablesService;
import com.example.demo.services.TicketService;



@RestController
@RequestMapping("/client")
public class clientRest {
	private ClientService service;
	private TablesService serviceTable;
	private MetService serviceMet;
	private TicketService serviceTicket;
	
	@Autowired
	public clientRest(ClientService service,TablesService serviceTable,MetService serviceMet,TicketService serviceTicket) {
		super();
		this.service = service;
		this.serviceTable=serviceTable;
		this.serviceMet=serviceMet;
		this.serviceTicket= serviceTicket;
	}
	
	@GetMapping
	public List<ClientReponse> getAll() {
		return service.getAllClient();
	}
	
	@PostMapping
	public ClientReponse createPerson(@Valid @RequestBody ClientRequest client) {
		return service.createClientEntity(client);
	}
	
	@DeleteMapping("/{id}")
	public ClientReponse deleteClient(@PathVariable("id") long id) {
		return service.deleteClientById(id);
	}
	
	@PutMapping("/{id}")
	public ClientReponse updatePerson(@PathVariable("id") long id, @RequestBody ClientRequest request) {
		return service.updateClientEntity(id, request);
	}
	
	@GetMapping("/table")
	public List<TablesReponse> getAllTables() {
		return serviceTable.getAllTablesEntity();
	}
	
	@PostMapping("/table")
	public TablesReponse createTables(@Valid @RequestBody TablesRequest table) {
		return serviceTable.createTablesEntity(table);
	}
	
	@DeleteMapping("/table/{id}")
	public TablesReponse deleteTable(@PathVariable("id") long id) {
		return serviceTable.deleteTable(id);
	}
	
	@PutMapping("/table/{id}")
	public TablesReponse updateTable(@PathVariable("id") long id,@Valid @RequestBody TablesRequest request) {
		return serviceTable.updateTable(id, request);
	}
	
	@GetMapping("/met")
	public List<MetReponse>getAllMet(){
		return serviceMet.getAllMet();
	}
	
	@PostMapping("/met")
	public MetReponse createMet(@Valid @RequestBody MetRequest met) {
		return serviceMet.creatMet(met);
	}
	
	@DeleteMapping("/met/{nom}")
	public MetReponse deleteMet(@PathVariable("nom")String nom) {
		return serviceMet.deleteMet(nom);
	}
	
	@PutMapping("/met/{nom}")
	public MetReponse updateMet(@PathVariable("nom") String nom,@Valid @RequestBody MetRequest request) {
		return serviceMet.updateMet(nom, request);
	}
	
	@GetMapping("/ticket")
	public List<TicketReponse> getAllTicket() {
		return serviceTicket.getAllTicket();
	}
	
	@PostMapping("/ticket")
	public TicketReponse createTicket(@Valid @RequestBody TicketRequest request) {
		return serviceTicket.createTicketEntity(request);
	}
	
	@DeleteMapping("/ticket/{numero}")
	public TicketReponse deleteTicket(@PathVariable("numero")Long numero) {
		return serviceTicket.deleteTicket(numero);
	}
	
	@PutMapping("/ticket/{numero}")
	public TicketReponse updateTicket(@PathVariable("numero") long numero,@Valid @RequestBody TicketRequest request) {
		return serviceTicket.updateTicket(numero, request);
	}
	
	@GetMapping("/mostOrdred/{debut}/{fin}")
	public String getMostOrdredPlat(@PathVariable("debut") String debut,@PathVariable("fin") String fin) {
		return service.getMostPlatOrdred(debut, fin);
	}
	
	@GetMapping("/fidele")
	public ClientReponse getClientPlusFidele()
	{
		return service.clientPlusFidele();
	}
	
	@GetMapping("/mostReserved")
	public String getMostReservedTable() {
		return service.tablePlusReserver();
	}
	
	@GetMapping("/dayofweek/{numero}")
	public String getMostReservedDay(@PathVariable("numero")Long numero)
	{
		return service.mostReservedDay(numero);
	}
	
	@GetMapping("/incomeOfDay")
	public String incomeOfDay() {
		return service.incomeOfDay();
	}
	
	@GetMapping("/incomeOfMonth")
	public String incomeOfMonth() {
		return service.incomeOfMonth();
	}
	
	@GetMapping("/incomeOfWeek")
	public String incomeOfWeek() {
		return service.incomeOfWeek();
	}
	
	@GetMapping("/incomeOfPeriod/{debut}/{fin}")
	public String incomeOfPeriod(@PathVariable("debut") String debut,@PathVariable("fin")String fin) {
		return service.incomeOfPeriod(debut, fin);
	}
}
