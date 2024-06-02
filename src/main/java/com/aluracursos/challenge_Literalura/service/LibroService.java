package com.aluracursos.challenge_Literalura.service;


import com.aluracursos.challenge_Literalura.excepciones.ElLibroYaExiste;
import com.aluracursos.challenge_Literalura.model.Autor;
import com.aluracursos.challenge_Literalura.model.DatosLibros;
import com.aluracursos.challenge_Literalura.model.Libros;
import com.aluracursos.challenge_Literalura.principal.Principal;
import com.aluracursos.challenge_Literalura.repository.LibrosRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.util.Locale.filter;

/*
La clase LibroService es un servicio en la aplicación
Spring que se encarga de proporcionar funcionalidades relacionadas
con libros. Aquí hay algunos detalles sobre esta clase:

La clase está anotada con @Service, lo que nos indica que es un componente de servicio en Spring.
La clase tiene una dependencia inyectada de LibrosRepository, que es una
clase que proporciona operaciones de base de datos para trabajar con libros.
La clase tiene un método compararTitulos que compara un título dado con una lista de
títulos de libros y devuelve true si hay una coincidencia, ignorando mayúsculas y minúsculas.
La clase no tiene un constructor explícito, por lo que Spring creará una instancia de esta
clase utilizando el constructor predeterminado.
 */
@Service
public class LibroService {

    private LibrosRepository libroRepository;

    public static boolean compararTitulos(String titulo, List<String> libros) {
        return libros.stream().anyMatch(l -> l.equalsIgnoreCase(titulo));
    }
    public static boolean compararAutores(String autor, List<String> autores){
        return autores.stream().anyMatch((a->a.equalsIgnoreCase(autor)));
    }
}







