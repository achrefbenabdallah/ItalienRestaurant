package com.example.demo.services;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import com.example.demo.dto.ClientReponse;
import com.example.demo.dto.ClientRequest;
import com.example.demo.dto.MetReponse;
import com.example.demo.models.Client;

public interface ClientService {

	List<ClientReponse> getAllClient();
	ClientReponse createClientEntity(ClientRequest request);
	ClientReponse deleteClientById(long id);
	ClientReponse updateClientEntity(long id,ClientRequest request);
	String getMostPlatOrdred(String debut , String fin);
	ClientReponse clientPlusFidele();
	String tablePlusReserver();
	String mostReservedDay(long id);
	String incomeOfDay();
	String incomeOfMonth();
	String incomeOfWeek();
	String incomeOfPeriod(String debut,String fin);
}
