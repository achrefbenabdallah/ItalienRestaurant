package com.example.demo.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequest {
	@NotBlank(message = "Name must not Empty")
	@Size(max = 50, min = 4)
	@Pattern(regexp = "[a-zA-Z ]+", message = "Name must contain only characters")
	private String nom;
	@NotBlank(message = "prenom must not Empty")
	@Size(max = 50, min = 5)
	@Pattern(regexp = "[a-zA-Z ]+", message = "prenom must contain only characters")
	private String prenom;
	@Past
	private LocalDate dateNaissance;
	private String courriel;
	private String tel;
}
