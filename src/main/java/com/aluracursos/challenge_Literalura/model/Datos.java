package com.aluracursos.challenge_Literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/*En este record se define la clase Datos
 que encapsula datos y
proporciona métodos predeterminados
para trabajar con datos estructurados de manera sencilla y legible.
Este registro llamado Datos, tenemos un unico atributo,
el cual es una lista de objetos de tipo DatosLibros.
La anotación @JsonAlias("results") indica que el nombre de la propiedad en el JSON
debe ser tratado como "results" en lugar del nombre del atributo en el objeto Java.
Esto es útil cuando el nombre de la propiedad en el JSON es diferente al nombre del
atributo en el objeto Java.

y la anotacion @JsonIgnoreProperties(ignoreUnknown = true) objeto Datos debe ignorar las
propiedades desconocidas en el JSON y que la propiedad "results" en el JSON
debe ser tratada como "resultados" en el objeto Java.
*/

@JsonIgnoreProperties(ignoreUnknown = true)
public record Datos( @JsonAlias("results") List<DatosLibros> resultados) {


}
