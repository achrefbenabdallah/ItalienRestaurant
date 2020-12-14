package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Dessert;

public interface DessertRepository extends JpaRepository<Dessert, String> {

}
