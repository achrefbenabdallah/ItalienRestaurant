package com.example.demo.models;



import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


import lombok.Data;

@Data
@Entity
@Table(name="Met")
public class Met {
	@Id
	@Column(length = 50,nullable = false)
	private String nom;
	private float prix;
	@ManyToMany(mappedBy = "met",  cascade = CascadeType.REMOVE)
	private List<Ticket> tickets;

}
