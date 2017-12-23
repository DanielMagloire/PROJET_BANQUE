package org.sid.web;

import org.sid.entities.Compte;
import org.sid.entities.Operation;
import org.sid.metier.IBanqueMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BanqueController {
	@Autowired
	private IBanqueMetier banqueMetier;
	
	@RequestMapping("/operations")  //Pour accéder à cette méthode nous utilisons cette annotation
	public String index(){
		return "comptes";
	}
	
	@RequestMapping("/consulterCompte")  //Pour accéder à cette méthode nous utilisons cette annotation
	public String consulter(Model model, String codeCompte, 
			@RequestParam(name="page", defaultValue="0")int page, 
			@RequestParam(name="size", defaultValue="3")int size){
		model.addAttribute("codeCompte", codeCompte);
		try {
			Compte cp = banqueMetier.consulterCompte(codeCompte);  //Pour consulter le compte, je fais appel à la couche metier
			Page<Operation> pageOperations = banqueMetier.listOperation(codeCompte, page, size);//Ajouter la page des 5 dernières opérations 
			model.addAttribute("listOperations", pageOperations.getContent());  //Stoker la liste des opérations dans le modèle et getContent() permet de retourner la liste des opérations
			
			int[] pages = new int[pageOperations.getTotalPages()]; //Pagination
			model.addAttribute("pages", pages);
			
			model.addAttribute("compte", cp);  //Ce compte est ensuite stocké dans le modèle parceque le message de l'exception sera affiché dans la vue
		} catch (Exception e) {
			model.addAttribute("exception", e);  //Ici nous stockons l'exception
		}
		
		return "comptes";
	}
	
	@RequestMapping(value="/saveOperation", method=RequestMethod.POST)
	public String saveOperation(Model model, String typeOperation, String codeCompte, 
			double montant, String codeCompte2){
		
		try {
			if(typeOperation.equals("VERS")){
				banqueMetier.verser(codeCompte, montant);
			}
			else if (typeOperation.equals("RET")){
				banqueMetier.retirer(codeCompte, montant);
			}
			if (typeOperation.equals("VIR")){
				banqueMetier.virement(codeCompte, codeCompte2, montant);
			}
		} catch (Exception e) {
			model.addAttribute("error", e);  //Ici nous stockons l'exception
			return "redirect:/consulterCompte?codeCompte=" + codeCompte + "&error =" + e.getMessage();
		}
		
		return "redirect:/consulterCompte?codeCompte=" + codeCompte;
	}
	
	
} 
