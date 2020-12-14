package com.example.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="Entree")
public class Entree extends Met {
	@Id
	@Column(length = 50,nullable = false)
	private String nom;
	private float prix;
}
