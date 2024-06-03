package com.aluracursos.challenge_Literalura.service;

import com.aluracursos.challenge_Literalura.model.Autor;
import com.aluracursos.challenge_Literalura.model.Libros;
import com.aluracursos.challenge_Literalura.repository.LibrosRepository;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

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

    //metodo boolean de comparacion de titulos
    public static boolean compararTitulos(String titulo, List<String> libros) {
        return libros.stream().anyMatch(l -> l.equalsIgnoreCase(titulo));
    }

/*
Este código utilizamos la anotación @Map para definir un mapa con claves de tipo
String y valores de tipo Autor. Luego, se crea una instancia del mapa utilizando new HashMap<>().

Después, iteramos sobre una lista de objetos Autor llamada autores.
Para cada objeto Autor, se verifica si ya existe una entrada en el mapa con la clave
igual al nombre del autor (autor.getNombre()). Si existe, se agregan los libros del autor
actual a la lista de libros del autor existente en el mapa. Si no existe, se agrega la entrada
 del autor actual al mapa con su nombre como clave y el objeto Autor como valor.

En decir, este código se utiliza para agrupar objetos Autor en un mapa basado en su nombre,
y para agregar los libros de cada autor a la lista de libros del autor existente en el mapa
si ya existe una entrada para ese autor.

Finalmente, se itera sobre el mapa autorMap para mostrar los datos de un unico autor,
si hay nombres de autor repetidos no los muestra, y nos agrupa todos los libros que posee en la clase de
datos
incluyendo su nombre, fecha de nacimiento y fecha de fallecimiento, y sus libros.
 */
    public void mostrarAutores( List<Autor> autores) {
        Map<String, Autor> autorMap = new HashMap<>();
        for (Autor autor : autores) {
            if (autorMap.containsKey(autor.getNombre())) {
                autorMap.get(autor.getNombre()).getLibro().addAll(autor.getLibro());
            } else {
                autorMap.put(autor.getNombre(), autor);
            }
        }
        /*
        Este fragmento de código imprime información sobre autores y sus libros. Recorre
         cada valor en el autorMap (que es un Map con los nombres de los autores como claves y
         objetos Autor como valores). Para cada objeto Autor, imprime el nombre del autor,
         la fecha de nacimiento y la fecha de fallecimiento.

        Luego, crea una lista de los libros del autor (Libros objetos) y crea una cadena de
         caracteres con los títulos de los libros separados por comas. Esta cadena se imprime,
          junto con un salto de línea.
         */
        for (Autor autor : autorMap.values()) {
            System.out.println("******************************************************************\n" +
                    "\n- Autor: " + autor.getNombre() +
                    "\n- Fecha de nacimiento: " + autor.getFechaDeNacimiento() +
                    "\n- Fecha De fallecimiento: " + autor.getFechaDeFallecimiento());

            List<Libros> libros = autor.getLibro();
            String titulosLibros = libros.stream()
                    .map(Libros::getTitulo)
                    .collect(Collectors.joining(","));
            System.out.println("- Libros: " + "[" + titulosLibros + "]" + "\n");
        }
        System.out.println("******************************************************************");
    }
}







