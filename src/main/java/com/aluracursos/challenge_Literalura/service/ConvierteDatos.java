package com.aluracursos.challenge_Literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
La clase ConvierteDatos es una clase pública que implementa la interfaz IConvierteDatos.
Está diseñada para convertir datos JSON en objetos de Java.

Tiene una instancia privada de ObjectMapper. ObjectMapper es una clase de la biblioteca
Jackson, que es una biblioteca de procesamiento de JSON en Java. Se utiliza para convertir
JSON en objetos de Java y viceversa.
El método obtenerDatos es una implementación del método obtenerDatos de la
interfaz IConvierteDatos. Este método toma una cadena JSON y una clase como
parámetros. Intenta convertir la cadena JSON en un objeto de la clase proporcionada
utilizando el método readValue del ObjectMapper.
Si la conversión es exitosa, devuelve el objeto convertido.
Si hay un error durante la conversión (por ejemplo, si la cadena JSON no es válida
 o no coincide con la clase proporcionada), lanza una RuntimeException que envuelve
  la JsonProcessingException.
 */
public class ConvierteDatos implements IConvierteDatos {
    private ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public <T> T obtenerDatos(String json, Class<T> clase)  {
        try {
            return objectMapper.readValue(json,clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
