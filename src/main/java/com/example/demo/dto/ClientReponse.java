package com.example.demo.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientReponse {
	
	private String nom;
	private String prenom;
	private LocalDate dateNaissance;
	private String courriel;
	private String tel;
}
