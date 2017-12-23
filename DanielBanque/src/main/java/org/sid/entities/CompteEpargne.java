package org.sid.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CE") //si je cré un compte epargne alors la colonne type de compte sera = CE 
public class CompteEpargne extends Compte {
	private double taux;

	//constructeur sans paramètre
	public CompteEpargne() {
		super();
		// TODO Auto-generated constructor stub
	}

	//constructeur avec paramètre
	public CompteEpargne(String codeCompte, Date dateCreation, double solde, Client client, double taux) {
		super(codeCompte, dateCreation, solde, client);
		this.taux = taux;
	}

	public double getTaux() {
		return taux;
	}

	//GETTERS AND SETTERS
	public void setTaux(double taux) {
		this.taux = taux;
	}
	
	
}
