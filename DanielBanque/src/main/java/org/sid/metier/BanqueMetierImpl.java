package org.sid.metier;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.Date;

import org.sid.dao.CompteRepository;
import org.sid.dao.OperationRepository;
import org.sid.entities.Compte;
import org.sid.entities.CompteCourant;
import org.sid.entities.Operation;
import org.sid.entities.Retrait;
import org.sid.entities.Versement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service    //Pour que Spring puisse instancier cette classe au demarrage cette annotation est utilisée pour les objets de la couche métier
@Transactional   //Demander à Spring de gérer les transactions au niveau de la couche metier
public class BanqueMetierImpl implements IBanqueMetier {
	@Autowired     // Pour linjection des dependences d'une implementation de cette interface
	private CompteRepository compteRepository; 
	@Autowired
	private OperationRepository operationRepository;

	@Override
	public Compte consulterCompte(String codeCompte) {
		Compte cp = compteRepository.findOne(codeCompte);// Consulter compte
		if(cp == null) throw new RuntimeException("Compte inexistant ou introuvable"); 
		return cp;
	}

	@Override
	public void verser(String codeCompte, double montant) {
		Compte cp = consulterCompte(codeCompte);
		Versement v = new Versement(new Date(), montant, cp);
		operationRepository.save(v);   //Enregistrer ce versement
		cp.setSolde(cp.getSolde() + montant);   //Mettre à jour le solde
		compteRepository.save(cp);  //Mise à jour du compte
	}

	@Override
	public void retirer(String codeCompte, double montant) {
		Compte cp = consulterCompte(codeCompte);
		double facilitesCaisse = 0;
		if(cp instanceof CompteCourant)//Tester si le solde est suffisant pour effectuer un retrait (compte courant ou compte epargne)
			facilitesCaisse = ((CompteCourant) cp).getDecouvert();
		if(cp.getSolde() + facilitesCaisse < montant)
			throw new RuntimeException("Solde insuffisant");
		
		Retrait r = new Retrait(new Date(), montant, cp);
		operationRepository.save(r);   //Enregistrer ce versement
		cp.setSolde(cp.getSolde() - montant);   //Mettre à jour le solde
		compteRepository.save(cp);  //Mise à jour du compte
		
	}

	@Override
	public void virement(String codeCompte1, String codeCompte2, double montant) {
		// Test permettant de ne pas faire un virement dans le même compte
		if(codeCompte1.equals(codeCompte2))
			throw new RuntimeException("Virement impossible sur le même compte");
		retirer(codeCompte1, montant);
		verser(codeCompte2, montant);
	}

	@Override
	public Page<Operation> listOperation(String codeCompte, int page, int size) {
		// TODO Auto-generated method stub
		return operationRepository.listOperation(codeCompte, new PageRequest(page, size, new Sort(Direction.DESC, "numero")));
	}

}
