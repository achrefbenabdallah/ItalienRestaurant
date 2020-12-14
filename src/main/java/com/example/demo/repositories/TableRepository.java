package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Table;

public interface TableRepository extends JpaRepository<Table, Long>{

}
