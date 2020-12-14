package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Met;

public interface MetRepository extends JpaRepository<Met, String>{

}
