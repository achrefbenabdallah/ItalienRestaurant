package com.example.demo.dto;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.PastOrPresent;

import com.example.demo.models.Client;
import com.example.demo.models.Met;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketRequest {
	@PastOrPresent
	private LocalDate date;
	private float addition;
	private int nbCouvert;
	private Client client;
	private com.example.demo.models.Tables tables;
	
}
