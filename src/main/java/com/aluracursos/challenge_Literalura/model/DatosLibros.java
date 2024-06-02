package com.aluracursos.challenge_Literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
/*
El record es una forma más concisa de definir una clase que tiene solo campos y métodos getter.
En este caso, el record se llama DatosLibros y tiene cuatro campos: titulo, autor, idiomas
y numeroDeDescargas.
El anotación @JsonIgnoreProperties(ignoreUnknown = true) indica que, cuando se utiliza la
 biblioteca Jackson para serializar o deserializar objetos, cualquier propiedad extra
 en el JSON que no coincida con los campos del record se ignorará.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibros(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors")List<DatosAutor> autor,
        @JsonAlias("languages")List<String>idiomas,
        @JsonAlias("download_count")Double numeroDeDescargas

) {


}
