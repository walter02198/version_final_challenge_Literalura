package com.aluracursos.challenge_Literalura;

import com.aluracursos.challenge_Literalura.principal.Principal;
import com.aluracursos.challenge_Literalura.repository.LibrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengeLiteraluraApplication implements CommandLineRunner {

	/*
	aca hacemos un inyeccion de dependencias de la interfaz de la entidad
	libros  que estamos utilizando para mapear y trabajar con el CRUD de la bases de datos
	 */
	@Autowired
	private LibrosRepository repository;
	public static void main(String[] args) {
		SpringApplication.run(ChallengeLiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal=new Principal(repository);
		principal.muestraElMenu();
	}
}
