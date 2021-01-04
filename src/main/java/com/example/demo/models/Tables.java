package com.example.demo.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
@javax.persistence.Table(name="Tables")
public class Tables {
	@Id
	@Column(nullable = false)
	private long numero;
	private int nbCouvert;
	@Column(length = 50)
	private String type;
	private float supplement;
	@OneToMany(mappedBy = "tables",cascade = CascadeType.REMOVE)
	private List<Ticket> tickets;
}
