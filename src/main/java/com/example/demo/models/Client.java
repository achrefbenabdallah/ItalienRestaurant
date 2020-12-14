package com.example.demo.models;


import java.time.LocalDate;
import java.util.List;

<<<<<<< HEAD:src/main/java/com/example/demo/models/Client.java
import javax.persistence.Column;
=======
import javax.persistence.CascadeType;
>>>>>>> 01d679548fdd2f63b08f2d36c86d26543d72411d:src/main/java/com/example/models/Client.java
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="Client")
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "nom")
	private String nom;
	@Column(name = "prenom")
	private String prenom;
	@Column(name = "dateNaissance")
	private LocalDate dateNaissance;
	@Column(name = "courriel")
	private String courriel;
	@Column(name = "tel")
	private String tel;
	@OneToMany(mappedBy = "client",cascade = CascadeType.REMOVE)
	private List<Ticket> tickets;
}
