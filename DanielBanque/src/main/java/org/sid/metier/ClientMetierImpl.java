package org.sid.metier;

import java.util.List;

import org.sid.dao.ClientRepository;
import org.sid.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
@Service  //Pour que springBoot puisse instancier la couche métier au demarrage
public class ClientMetierImpl implements ClientMetier {
	@Autowired
	ClientRepository clientRepository;  //le couche métier à besoin de la couche Dao

	@Override
	public Client addClient(Client c) {
		// TODO Auto-generated method stub
		return clientRepository.save(c);
	}

	@Override
	public List<Client> listClient() {
		// TODO Auto-generated method stub
		return clientRepository.findAll();
	}

	@Override
	public List<Client> clientparMC(String mc) {
		// TODO Auto-generated method stub
		return clientRepository.clientParMC(mc);
	}

}
