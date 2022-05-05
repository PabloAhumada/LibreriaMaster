
package com.libreria.demo.repositorios;

import com.libreria.demo.entidades.Libro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, String> {
    
    @Query("SELECT l FROM Libro l WHERE  l.id = :id")
    public List<Libro> buscarPorId(@Param("id") String id);
    
    @Query("SELECT l FROM Libro l WHERE  l.titulo = :titulo")
    public List<Libro> buscarPorTitulo(@Param("titulo") String id);
    
    @Query("SELECT l FROM Libro l WHERE  l.autor.nombre = :nombre")
    public List<Libro> buscarPorNombreAutor(@Param("nombre") String nombre);

    @Query("SELECT l FROM Libro l WHERE  l.autor.id = :idautor")
    public List<Libro> buscarPorIdAutor (@Param("idautor") String id);


}
