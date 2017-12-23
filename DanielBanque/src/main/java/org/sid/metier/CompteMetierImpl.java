package org.sid.metier;

import org.sid.dao.CompteRepository;
import org.sid.entities.Compte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompteMetierImpl implements CompteMetier {
	@Autowired
	private CompteRepository compteRepository;

	@Override
	public Compte addCompte(Compte cp) {
		// TODO Auto-generated method stub
		return compteRepository.save(cp);
	}

	@Override
	public Compte consulterCompte(String codeCompte) {
		Compte cp = compteRepository.findOne(codeCompte);
		if(cp == null) throw new RuntimeException("Compte inexistant");
		return cp;
	}

}
