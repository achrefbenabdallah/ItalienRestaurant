package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.TicketReponse;
import com.example.demo.dto.TicketRequest;

public interface TicketService {
	
	List<TicketReponse>getAllTicket();
	TicketReponse createTicketEntity(TicketRequest request);
	TicketReponse deleteTicket(long numero);
	TicketReponse updateTicket(long numero , TicketRequest request);
}
