package org.sid.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE_OP", 
discriminatorType=DiscriminatorType.STRING, length=1) //soit versement soit retrait
public abstract class Operation implements Serializable {
	@Id @GeneratedValue //pour spécifier l'identifiant et est authomatiquement généré
	private Long numero;
	private Date dateOperation;
	private double montant;
	@ManyToOne
	@JoinColumn(name="CODE_CPTE")
	private Compte compte;
	
	//constructeur sans paramètre
	public Operation() {
		super();
		// TODO Auto-generated constructor stub
	}

	//constructeur avec paramètre
	public Operation(Date dateOperation, double montant, Compte compte) {
		super();
		this.dateOperation = dateOperation;
		this.montant = montant;
		this.compte = compte;
	}

	//GETTERS AND SETTERS
	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public Date getDateOperation() {
		return dateOperation;
	}

	public void setDateOperation(Date dateOperation) {
		this.dateOperation = dateOperation;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}
	
}
