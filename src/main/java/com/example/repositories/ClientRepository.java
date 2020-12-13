package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.models.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

}
