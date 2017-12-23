package org.sid;

import java.util.Date;
import java.util.List;

import org.sid.dao.ClientRepository;
import org.sid.dao.CompteRepository;
import org.sid.dao.OperationRepository;
import org.sid.entities.Client;
import org.sid.entities.Compte;
import org.sid.entities.CompteCourant;
import org.sid.entities.CompteEpargne;
import org.sid.entities.Retrait;
import org.sid.entities.Versement;
import org.sid.metier.BanqueMetierImpl;
import org.sid.metier.ClientMetier;
import org.sid.metier.CompteMetier;
import org.sid.metier.IBanqueMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DanielBanqueApplication implements CommandLineRunner {

	@Autowired //Pour injecter
	private ClientRepository clientRepository; //je déclare un objet ClientRepository
	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private OperationRepository operationRepository;
	@Autowired
	private IBanqueMetier banqueMetier;
	
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(DanielBanqueApplication.class, args);
		
		ClientMetier clientMetier = ctx.getBean(ClientMetier.class);//Quand Spring va demarrer on lui demander donne moi cet objet metier
		clientMetier.addClient(new Client("A", "C", "E"));
		clientMetier.addClient(new Client("B", "D", "F"));
		
		List<Client> clients = clientMetier.listClient(); //Chercher la liste des clients
		
		System.out.println("********************************");  
		
		clients.forEach(c->System.out.println(c.getNom()));   //Pour affichier cette liste
		/*for(Client c:clients)
			System.out.println(c.getNom());*/ 
		
		System.out.println("********************************");
		
		List<Client> clts = clientMetier.clientparMC("%A%");  //Chercher les clients par mot clé "mc"
		clts.forEach(c->System.out.println(c.getNom()));
		
		
		System.out.println("****************Comptes****************");
		
		CompteMetier compteMetier = ctx.getBean(CompteMetier.class);
		compteMetier.addCompte(new CompteCourant("CC1", new Date(), 190000, new Client(1L), 50000));//Ajouter un compte courant
		compteMetier.addCompte(new CompteCourant("CC2", new Date(), 190000, new Client(2L), 50000));
		
		compteMetier.addCompte(new CompteEpargne("CE1", new Date(), 290000, new Client(1L), 5.5));//Ajouter un compte epargne
		compteMetier.addCompte(new CompteEpargne("CE2", new Date(), 90000, new Client(2L), 5.5));
		
		Compte cp = compteMetier.consulterCompte("CC1");   //consulter le compte
		System.out.println("Solde = " + cp.getSolde());//Afficher les informations sur le compte
		System.out.println("Date : " + cp.getDateCreation());
		System.out.println("Nom : " + cp.getClient());
		
		Compte cpo = compteMetier.consulterCompte("CE1");   //consulter le compte
		System.out.println("Solde = " + cpo.getSolde());//Afficher les informations sur le compte
		System.out.println("Date : " + cpo.getDateCreation());
		System.out.println("Nom : " + cpo.getClient());
	}
	
	
	@Override
	public void run(String... arg0) throws Exception {
		/*
		Client c1 = clientRepository.save(new Client("MEDOU", "Daniel", "danielmedou@gmail.com")); //Exemple d'ajout de client 
		Client c2 = clientRepository.save(new Client("MALLE", "Zoumana", "mallezoumana@gmail.com"));
		 
		//Tests effectuée pour tester la couche Dao
		
		Compte cp1 = compteRepository.save(new CompteCourant("c1", new Date(), 190000, c1, 50000)); //Exemple de création de compte
		Compte cp2 = compteRepository.save(new CompteEpargne("c2", new Date(), 70000, c2, 5.5));
		
		operationRepository.save(new Versement(new Date(), 20000, cp1));//Ajout des opérations pour le compte cp1
		operationRepository.save(new Versement(new Date(), 40000, cp1));
		operationRepository.save(new Versement(new Date(), 30000, cp1));
		operationRepository.save(new Retrait(new Date(), 25000, cp1));
		
		operationRepository.save(new Versement(new Date(), 60000, cp2));//Ajout des opérations pour le compte cp2
		operationRepository.save(new Versement(new Date(), 10000, cp2));
		operationRepository.save(new Versement(new Date(), 90000, cp2));
		operationRepository.save(new Retrait(new Date(), 55000, cp2));
		
		banqueMetier.verser("c1", 222222222);  //Tester un versement
		*/
	}
	
	
}
