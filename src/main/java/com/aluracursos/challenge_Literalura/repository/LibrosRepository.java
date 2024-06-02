package com.aluracursos.challenge_Literalura.repository;


import com.aluracursos.challenge_Literalura.model.Autor;
import com.aluracursos.challenge_Literalura.model.Libros;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


//Aca tenemos la interface de la entidad libros que necesitaremos para mapear
// y poder trabajar con el CRUD de la base de datos

public interface LibrosRepository extends JpaRepository<Libros, Long>{
    @Query("SELECT a FROM Autor a LEFT JOIN FETCH a.libros")
    List<Autor> encontrarConLibros();


    @Query("SELECT titulo FROM Libros ")
    List<String> mostrarListaTitulos();


    @Query("SELECT a FROM Autor a WHERE :fecha BETWEEN a.fechaDeNacimiento AND a.fechaDeFallecimiento")
    List<Autor> mostrarAutoresYVivos( Integer fecha);

    @Query("SELECT l FROM Libros l WHERE l.idiomas = :idioma")
    List<Libros> buscarPorIdioma( String idioma);

    @Query("SELECT nombre from Autor")
    List<String> mostrarListaAutores();

    @Query("SELECT id  from Autor where nombre = :autor")
    List<Long> mostrarId(String autor);


    @Modifying
    @Query(value="update  Libros l  " +
            "set l.autor_id = (select a.id from Autor a where a.nombre = :autor) " +
            "where l.nombre = :autor", nativeQuery = true)
    void actualizarId(@Param("autor")String autor);

//    @Modifying
//    @Query("UPDATE Libros l SET l.autor = :autor WHERE l.nombre = :nombre")
//    void actualizarId(@Param("autor") Autor autor, @Param("nombre") String nombre);

//    @Query( value = "SELECT a.nombre, a.FECHA_DE_NACIMIENTO, a.FECHA_DE_FALLECIMIENTO, STRING_AGG(b.titulo, ',') AS titulo "  +
//                    "FROM Autor a "  +
//                   "RIGHT JOIN a.libros b "  +
//            "GROUP BY a.nombre, a.FECHA_DE_NACIMIENTO, a.FECHA_DE_FALLECIMIENTO"
//            , nativeQuery = true)
//    List<Autor> mostrarAutoresYLibros();
}

