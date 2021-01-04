package com.example.demo.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TablesRequest {
	@Min(value = 1)
	private int nbCouvert;
	@Size(max = 50, min = 5)
	@Pattern(regexp = "[a-zA-Z ]+", message = "type must contain only characters")
	private String type;
	private float supplement;
}
