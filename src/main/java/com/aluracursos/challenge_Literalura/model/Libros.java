package com.aluracursos.challenge_Literalura.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

/*
Esta clase Libros es una entidad en un sistema de gestión de bases de datos
utilizando el patrón de diseño ORM (Object-Relational Mapping).

@Entity: Esta anotación nos indica que la clase Libros es una entidad en la base de datos.
@Table(name="libros"): Esta anotación especifica el nombre de la tabla en la base de datos
donde se almacenarán los objetos de esta clase. En este caso, la tabla se llama "libros".
@JsonIgnoreProperties(ignoreUnknown = true): Esta anotación indica que las propiedades desconocidas
en los objetos JSON que se deserializan a esta clase deben ignorarse.
private Long Id;: Esta es la propiedad de la identidad de la entidad. Se utiliza para
identificar de manera única cada instancia de la clase en la base de datos.
Los getter y setter son útiles porque permiten controlar cómo se accede y modifica
los datos de una clase entidad.
 */
@Entity
@Table(name="libros")
@JsonIgnoreProperties(ignoreUnknown = true)

public class Libros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String titulo;

    @ManyToOne( cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private Autor autor;

    private Double numeroDeDescargas;
    private String idiomas;
    private String nombre;

//    Este constructor de Libros, Tomamos un objeto de tipo
//    DatosLibros como parámetro.
    public Libros(DatosLibros datosLibros) {
        // Obtener el primer autor de datosLibros
        DatosAutor autor=datosLibros.autor().get(0);
        // Crear un nuevo objeto Autor utilizando el objeto DatosAutor recuperado
        Autor nombre=new Autor(autor);
        // Establecer el campo nombre del objeto actual Libros con el nombre del Autor
        this.nombre=nombre.getNombre();

        this.titulo = datosLibros.titulo();
        this.idiomas = datosLibros.idiomas().get(0);

        if (datosLibros.numeroDeDescargas() != null) {
            this.numeroDeDescargas = datosLibros.numeroDeDescargas();
        } else {
            System.out.println("null");
        }
    }
    public Libros() {
    }
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Autor getAutor() {
        return autor;
    }

    public Double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Double numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        //String lenguajeStr = lenguaje != null ? lenguaje.toString() : "N/A";
        return "\n*******************Libro*********************" +
                "\nTitulo= " + titulo +
                "\nAutor= " + nombre +
                "\nLenguaje= " + idiomas +
                "\nNumero De Descargas= " + numeroDeDescargas +
                "\n********************************************\n";
    }

}
