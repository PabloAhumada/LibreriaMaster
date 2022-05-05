
package com.libreria.demo.repositorios;

import com.libreria.demo.entidades.Autor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepositorio extends JpaRepository<Autor, String>{
    
    @Query("SELECT l FROM Autor l WHERE  l.id = :id")
    public List<Autor> buscarPorId(@Param("id") String id);
    
    @Query("SELECT l FROM Autor l WHERE  l.nombre = :nombre")
    public List<Autor> buscarPorNombre(@Param("nombre") String id);
    
}
