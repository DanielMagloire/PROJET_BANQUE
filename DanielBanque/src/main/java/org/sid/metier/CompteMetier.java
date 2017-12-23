package org.sid.metier;

import org.sid.entities.Compte;

public interface CompteMetier {
	public Compte addCompte(Compte cp);
	public Compte consulterCompte(String codeCompte);
}
