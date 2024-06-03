package com.aluracursos.challenge_Literalura.principal;

import com.aluracursos.challenge_Literalura.model.*;
import com.aluracursos.challenge_Literalura.repository.LibrosRepository;
import com.aluracursos.challenge_Literalura.service.ConsumoApi;
import com.aluracursos.challenge_Literalura.service.ConvierteDatos;
import com.aluracursos.challenge_Literalura.service.LibroService;
import java.util.*;
import java.util.stream.Collectors;


public class Principal {

    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConvierteDatos conversor = new ConvierteDatos();
    private Scanner teclado = new Scanner(System.in);
    private LibrosRepository repositorio;
    private LibroService libroService = new LibroService();
    private Integer opcion = -1;
    public Principal(LibrosRepository repository) {
        this.repositorio = repository;
    }

    public void muestraElMenu() {

        while (opcion != 0) {
            var menu = """
                    \nElija la opcion a traves de su numero:
                             1.-Buscar libro por titulo
                             2.-Listar libros registrados
                             3.-Listar autores registrados
                             4.-Listar autores vivos en un determinado año
                             5.-Listar libros por idioma
                             0. -Salir
                    """;
            System.out.println(menu);
            if (teclado.hasNextInt()) {
                opcion = teclado.nextInt();
                if (opcion <= 5) {
                    teclado.nextLine();
                } else {
                    System.out.println("Opcion no valida");
                }
                switch (opcion) {
                    case 1:
                        buscarLibrosWeb();
                        break;
                    case 2:
                        mostrarLibrosRegistrados();
                        break;
                    case 3:
                        autoresYLibros();
                        break;
                    case 4:
                        mostrarAutoresYVivos();
                        break;
                    case 5:
                        mostrarLibrosPorIdioma();
                        break;
                    case 0:
                        System.out.println("Cerrando la aplicación...Gracias por utilizar nuestros servicios");
                        break;
                    default:

                }
            } else {
                System.out.println("Opcion no valida");
                teclado.next();

            }
        }
    }

    private void buscarLibrosWeb()  {
        System.out.println("Ingrese el libro que desea buscar");
        //Esta línea lee la entrada del usuario desde la consola y la asigna a la variable
        var tituloLibro = teclado.nextLine();
        /*
        Esta línea envía una solicitud GET a la API con el título del libro del
        usuario como parámetro. El replace(" ", "+") se utiliza para reemplazar
        los espacios en el título del libro con signos +, que es una forma común de
        codificar los espacios en las URLs. El resultado de la solicitud se asigna a la variable json.
         */
        var json = consumoApi.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replace(" ", "+"));
        /*
        : Esta línea utiliza el objeto conversor para convertir la respuesta JSON de la API en
        un objeto de tipo Datos. El resultado se asigna a la variable datosBusqueda.
         */
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);
        /*
         Esta línea filtra la lista resultados() en datosBusqueda para encontrar el primer objeto
         DatosLibros cuyo título contiene el título del libro del usuario (sin distinción de mayúsculas
          y minúsculas). El resultado se asigna a la variable libroBuscado.
         */
        Optional<DatosLibros> libroBuscado = datosBusqueda.resultados().stream()
                .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                .findFirst();

        //este if me permite insertar el libro si es que esta presente en la apì.
        if (libroBuscado.isPresent()) {

            var libro = new Libros(libroBuscado.get());
            var datosBusqueda1 = new Autor(libroBuscado.get().autor().get(0));



            //codigo de comparacion que solo permite insertar el libro solo una vez en la base de datos.
            var tituloBuscado = libroBuscado.get().titulo();
            List<String> titulos;
            titulos = repositorio.mostrarListaTitulos().stream().collect(Collectors.toList());
            boolean tituloRepetido = libroService.compararTitulos(tituloBuscado, titulos);

            if (!tituloRepetido) {
                libro.setAutor(datosBusqueda1);
                repositorio.save(libro);
                System.out.println(libro);
            } else {
                System.out.println("El libro ya esta registrado");
            }
        } else {
            System.out.println("Libro no encontrado" + "\n***************************************");
        }
        muestraElMenu();
    }

//Aca en este metodo utilizamos el metodo findAll de la interfaz JpaRepository
//    para obtener todos los registros de la entidad Libros
//    y mostrarlos en la consola.
//    es dedir mostramos todos los libros registraos
//    en la base de datos.
    public void mostrarLibrosRegistrados() {
        repositorio.findAll().forEach(System.out::println);
    }

    public void autoresYLibros() {
        //aca traemos de la interfaz JpaRepository la lista de los libros
        List<Autor> autores = repositorio.encontrarConLibros();
        libroService.mostrarAutores(autores);
    }

    //   este codigo es parecido al anteior la unica diferencia es que escogemos los registros con los
    //autores vivo segun el anio que el usuario elija, y nos lo muestra por consola.
    public void mostrarAutoresYVivos() {
        System.out.println("Ingrese El año que ud guste(entre 1000 y 2000) para que le muestre el listado de autores vivos");
        var fecha = teclado.nextInt();
        teclado.nextLine();
        if(fecha>1000 && fecha<2000){
            List<Autor> autoresVivos = repositorio.mostrarAutoresYVivos(fecha);
            libroService.mostrarAutores(autoresVivos);
        }else{
            System.out.println("Opcion no valida"+"\nPor favor ingrese un año entre 1000 y 2000");
            teclado.next();
        }

    }

    //Aca nos muestra una lista de los idiomas de los libros, y nos muestra todos lo libros segun el idioma
    //que elija el usuario.
    public void mostrarLibrosPorIdioma() {
        int opcionIdioma = -1;
        String idioma = "";
        String idiomaCompleto = "";
        while (opcionIdioma != 0) {

            var menuIdiomas = """
                    \nElija el idioma de los libros a traves de su numero:
                             1.-Ingles
                             2.-Español
                             3.-Portugues
                             4.-Italiano
                             0.-Volver al menu principal
                    """;
            System.out.println(menuIdiomas);
            if (teclado.hasNextInt()) {
                opcionIdioma = teclado.nextInt();
                if (opcionIdioma < 5) {
                    teclado.nextLine();
                } else {
                    System.out.println("Opcion no valida");
                }
                if (opcionIdioma == 1) {
                    idiomaCompleto = "Ingles";
                    idioma = "en";
                } else if (opcionIdioma == 2) {
                    idiomaCompleto = "Español";
                    idioma = "es";
                } else if (opcionIdioma == 3) {
                    idiomaCompleto = "Portugues";
                    idioma = "pt";
                } else if (opcionIdioma == 4) {
                    idiomaCompleto = "Italiano";
                    idioma = "it";
                }
                List<Libros> idiomas = repositorio.buscarPorIdioma(idioma);

                if (opcionIdioma > 0 && opcionIdioma < 5) {

                        System.out.println("\n********* Estos son Los libros que estan en idioma " + idiomaCompleto + " *********");
                        for (Libros libro : idiomas) {
                            System.out.println("\n- Libro: " + libro.getTitulo() +
                                    "\n- Autor: " + libro.getNombre() +
                                    "\n- Numero de descargas: " + libro.getNumeroDeDescargas());
                            System.out.println("\n*****************************************************************");
                        }
                    }
                } else if(opcion!=0){
                    System.out.println("Opcion no valida");
                    teclado.next();
                }
            }
        }
    }

























