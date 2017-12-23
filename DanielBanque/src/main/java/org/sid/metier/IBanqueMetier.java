package org.sid.metier;

import org.sid.entities.Compte;
import org.sid.entities.Operation;
import org.springframework.data.domain.Page;

public interface IBanqueMetier {
	
	//Besoins fonctionnels
	public Compte consulterCompte(String codeCpte);  //Methode qui permet de retourner un copmte: Consulter un compte
	public void verser(String codeCpte, double montant);  //Methode permettant deffectuer un versement
	public void retirer(String codeCpte, double montant);  //Methode permettant deffectuer un retrait
	public void virement(String codeCpte1, String codeCpte2, double montant);//Methode permettant deffectuer un virement
	
	public Page<Operation> listOperation(String codeCpte, int page, int size);  //Consulter les opérations d'un compte ceci doit être fait par page
}

