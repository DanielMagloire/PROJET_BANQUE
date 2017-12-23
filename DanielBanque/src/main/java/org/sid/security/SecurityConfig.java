package org.sid.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration  //Classe de configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	//Déclarer un objet de type DataSource pour utiliser notre base de données
	@Autowired
	private DataSource dataSource; 
	
	//Redéfinition des méthodes
	
	//Premiere
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*
		// Nous definissons la manière avec laquelle nous pouvons ou allons chercher les utilisateurs
		//Nous choisissons de stocker ces derniers en mémoire
		auth.inMemoryAuthentication().withUser("admin").password("1234").roles("ADMIN", "USER");
		
		auth.inMemoryAuthentication().withUser("user").password("1234").roles("USER");
		*/
		
		auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("select username as principal, password as credentials, active from users where username = ?")
		.authoritiesByUsernameQuery("select username as principal, roles as role from users_roles where username = ?")
		.rolePrefix("ROLE_")
		.passwordEncoder(new Md5PasswordEncoder());  //La requete que spring securité devrait exécuter pour recupérer l'utilisateur, la deuxième pour les rôles, la troisième indique à spring quand il recupere un rôle il lui ajoute un préfixe, on lui indique que le mot de passe est codé en MD5
	}
	
	//Deuxième
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Définir les stratégies de la sécurité
		
		http.formLogin().loginPage("/login"); //Demander à Spring de passer par une autentification basée sur le formulaire
		http.authorizeRequests().antMatchers("/operations", "/consulterCompte").hasRole("USER");  //Sécuriser les ressources de notre application
		http.authorizeRequests().antMatchers("/saveOperation").hasRole("ADMIN");
		http.exceptionHandling().accessDeniedPage("/403");//Afficher page personnalisée. Si l'utilisateur n'a pas le droit d'accéder alors il va vers une action 403
	}
	
	
}
