package com.aluracursos.challenge_Literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
En este registro DatosAutor tiene tres campos: nombre, fechaDeNacimiento y
fechaDeFallecimiento. Estos campos están anotados con @JsonAlias, lo que
significa que el nombre de la propiedad en el JSON puede ser diferente al
nombre del campo en la clase. Por ejemplo, en el JSON puede haber una propiedad llamada
"name" en lugar de "nombre". El registro se encargará de mapear las propiedades del JSON a
los campos de la clase.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosAutor(
        @JsonAlias("name")String nombre,
        @JsonAlias("birth_year")String fechaDeNacimiento,
        @JsonAlias("death_year")String fechaDeFallecimiento

) {
}
