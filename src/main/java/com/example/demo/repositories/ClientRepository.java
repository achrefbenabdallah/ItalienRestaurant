package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

}
