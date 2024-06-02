package com.aluracursos.challenge_Literalura.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;
/*

Esta clase, Autor, es una entidad JPA. Se anotó con @Entity y @Table(name="autor"),
 lo que significa que es una clase persistente asignada a una tabla llamada "autor"
 en la base de datos.

Las anotaciones @Id y @GeneratedValue se utilizan para especificar
la clave principal de la entidad. En este caso, la clave principal es un
valor Long generado automáticamente.

Las propiedades de la clase autor son:
id: La clave principal de la entidad.
nombre: El nombre del autor.
fechaDeNacimiento: La fecha de nacimiento del autor.
fechaDeFallecimiento: La fecha de fallecimiento del autor.
libros: Una lista de entidades Libros, que están relacionadas con esta entidad Autor.
La relación se define utilizando la anotación @OneToMany, y la relación es bidireccional
 (desde Autor a Libros y desde Libros hacia atrás a Autor). La anotación mappedBy en
 @OneToMany especifica el lado inverso de la relación, que es la propiedad autor
  en la entidad Libros. La anotación cascade indica que todas
  las operaciones (guardar, actualizar, eliminar) se propagarán
  desde la entidad Autor a la entidad Libros. La anotación fetch
  especifica que la colección libros se cargará de forma predeterminada de forma eager.
 */
@Entity
@Table(name="autor")
public class Autor  {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private int fechaDeNacimiento;
    private int fechaDeFallecimiento;
        @OneToMany(mappedBy = "autor",cascade = CascadeType.ALL , fetch = FetchType.EAGER)
        private List<Libros> libros;

        /*
        Este constructor se utiliza para inicializar un objeto Autor con los valores de
        los campos nombre, fechaDeNacimiento y fechaDeFallecimiento de un objeto DatosAutor.
        nos permite crear un objeto Autor con los campos nombre, fechaDeNacimiento y fechaDeFallecimiento
        establecidos en los valores correspondientes del objeto DatosAutor. Si los campos fechaDeNacimiento
         o fechaDeFallecimiento en el objeto DatosAutor son nulos o vacíos, los campos correspondientes
         en el objeto Autor se establecerán en 0.
         */
    public Autor(DatosAutor datosAutor){
        this.nombre= datosAutor.nombre();
        if (datosAutor.fechaDeNacimiento() != null && !datosAutor.fechaDeNacimiento().isEmpty()) {
            this.fechaDeNacimiento = Integer.parseInt(datosAutor.fechaDeNacimiento());
        } else {
            this.fechaDeNacimiento = 0; // o cualquier otro valor predeterminado que desees utilizar
        }
        if (datosAutor.fechaDeFallecimiento() != null && !datosAutor.fechaDeFallecimiento().isEmpty()) {
            this.fechaDeFallecimiento = Integer.parseInt(datosAutor.fechaDeFallecimiento());
        } else {
            this.fechaDeFallecimiento = 0; // o cualquier otro valor predeterminado que desees utilizar
        }
    }
    public Autor(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(int fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public int getFechaDeFallecimiento() {
        return fechaDeFallecimiento;
    }

    public void setFechaDeFallecimiento(int fechaDeFallecimiento) {
        this.fechaDeFallecimiento = fechaDeFallecimiento;
    }

    public List<Libros> getLibro() {
        return libros;
    }

    public void setLibro(List<Libros> libro) {
        this.libros = libro;
    }

    @Override
    public String toString() {
        return  "nombre= " + getNombre()+
                "\nfechaDeNacimiento= " + getFechaDeNacimiento();
    }
}
