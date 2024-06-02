package com.aluracursos.challenge_Literalura.service;

/*
¡Claro! Esta interfaz IConvierteDatos define un contrato que especifica un
método obtenerDatos que toma una cadena JSON y un tipo de clase como entrada, y
 devuelve un objeto del tipo especificado.

El <T> antes de T es una declaración de tipo genérico.
Esto significa que el método obtenerDatos puede manejar cualquier tipo de objeto,
no solo un tipo específico. El tipo real del objeto devuelto se determinará por el
parámetro Class<T> en tiempo de ejecución.
 */

public interface IConvierteDatos {


    <T> T obtenerDatos(String json, Class<T> clase);
}
