package org.sid.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Client implements Serializable {
	@Id @GeneratedValue
	private Long code;
	private String nom;
	private String prenom;
	private String email;
	@OneToMany(mappedBy="client", fetch=FetchType.LAZY)
	private Collection<Compte> comptes;
	
	//constructeur sans paramètre
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//constructeur avec paramètre
	public Client(String nom, String prenom, String email) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
	}

	//GETTERS AND SETTERS
	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Collection<Compte> getComptes() {
		return comptes;
	}

	public void setComptes(Collection<Compte> comptes) {
		this.comptes = comptes;
	}

	public Client(Long code) {
		super();
		this.code = code;
	}
	
	
	
}
