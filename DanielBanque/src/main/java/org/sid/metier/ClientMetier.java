package org.sid.metier;

import java.util.List;

import org.sid.entities.Client;

public interface ClientMetier {
	public Client addClient(Client c);
	public List<Client> listClient();
	public List<Client> clientparMC(String mc);  //Methode permettant de chercher les clients par mot clé
}
