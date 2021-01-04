package com.example.demo.dto;

import com.example.demo.models.Type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetReponse {
	
	private String nom;
	private float prix;
	private Type type;
	

	
}
