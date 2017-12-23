package org.sid.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CC") //si je cré un compte courant alors la colonne type de compte sera = CC
public class CompteCourant extends Compte {
	private double decouvert;

	//constructeur sans paramètre
	public CompteCourant() {
		super();
		// TODO Auto-generated constructor stub
	}

	//constructeur avec paramètre
	public CompteCourant(String codeCompte, Date dateCreation, double solde, Client client, double decouvert) {
		super(codeCompte, dateCreation, solde, client);
		this.decouvert = decouvert;
	}

	public double getDecouvert() {
		return decouvert;
	}

	public void setDecouvert(double decouvert) {
		this.decouvert = decouvert;
	}
	
}
