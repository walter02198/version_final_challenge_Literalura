package com.aluracursos.challenge_Literalura.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
/*
 Esta clase es la responsable de realizar solicitudes HTTP a una API externa y obtener datos de ella.

La clase tiene un método llamado obtenerDatos que toma una URL como parámetro y devuelve un objeto
String que representa los datos obtenidos de la API.
El método utiliza la clase HttpClient de Java para enviar una solicitud GET a la URL proporcionada.
La respuesta de la solicitud se procesa utilizando la clase HttpResponse y se extrae el cuerpo de
la respuesta en formato de cadena (String).
Si ocurre alguna excepción durante la solicitud o el procesamiento de la respuesta,
se lanza una excepción RuntimeException.
 */
public class ConsumoApi {

    public String obtenerDatos(String url) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String json = response.body();
        return json;
    }
}
