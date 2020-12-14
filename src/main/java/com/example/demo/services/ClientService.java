package com.example.demo.services;

import java.util.List;

import com.example.demo.DTO.ClientDTO;
import com.example.demo.models.Client;

public interface ClientService {
	Client CreateClientEntity (ClientDTO entity);
	List<Client> getAllClientEntity();
	Client modifyClientEntity(Long id, Client entity);
	Client deleteClientEntity(Long id);

}
