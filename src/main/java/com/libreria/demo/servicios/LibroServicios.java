
package com.libreria.demo.servicios;

import com.libreria.demo.entidades.Autor;
import com.libreria.demo.entidades.Editorial;
import com.libreria.demo.entidades.Libro;
import com.libreria.demo.repositorios.LibroRepositorio;
import com.libreria.demo.errores.ErrorService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LibroServicios {
 
    @Autowired 
    private LibroRepositorio libroRepositorio;
    
    @Transactional(propagation = Propagation.NESTED)
    public void cargar (Long isbn, String titulo, Boolean alta, Integer anio, Autor autor, Editorial editorial, Integer ejemplares, Integer ejemplaresPrestados, Integer ejemplaresRestantes) throws ErrorService{
        
        validar(titulo, titulo, isbn, anio, autor, editorial);
        Libro libro = new Libro();
        libro.setTitulo(titulo);
        libro.setIsbn(isbn);
        libro.setAnio(anio);
        libro.setEjemplares(ejemplares);
        libro.setEjemplaresPrestados(0);
        libro.setEjemplaresRestantes(libro.getEjemplares());
        libro.setAlta(Boolean.TRUE);
        libro.setAutor(autor);
        libro.setEditorial(editorial);
        
        libroRepositorio.save(libro);
        
    }
    
    @Transactional(propagation = Propagation.NESTED)
    public void modificar (Long isbn, String titulo, Integer anio, Autor autor, Editorial editorial, String id) throws ErrorService{
        
        Optional<Libro> respuesta = libroRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Libro libro =respuesta.get();
            libro.setIsbn(isbn);
            libro.setTitulo(titulo);
            libro.setAnio(anio);
            libro.setAutor(autor);
            libro.setEditorial(editorial);
            
            libroRepositorio.save(libro);
        } else{
            throw new ErrorService("No hay un Libro con ese Id");
        }
  
    }
    
    @Transactional(propagation = Propagation.NESTED)
    public void eliminar (String id) throws ErrorService{
        
        Optional<Libro> respuesta = libroRepositorio.findById(id);
        if (respuesta.isPresent()) {
           Libro libro =respuesta.get();
           libro.setAlta(Boolean.FALSE);
           
           libroRepositorio.save(libro);
        } else{
            throw new ErrorService("No hay un Libro con ese Id");
        }
    }
    
    public void consultar (String id) throws ErrorService{
        
        Optional<Libro> respuesta = libroRepositorio.findById(id);
        if (respuesta.isPresent()) {
           Libro libro =respuesta.get();
           
        } else{
            throw new ErrorService("No hay un Libro con ese Id");
        }
    }
    
    public void validar (String titulo, String id, Long isbn, Integer anio, Autor autor, Editorial editorial) throws ErrorService {

        if (titulo == null || titulo.isEmpty()) {
            throw new ErrorService("El libro debe tener un nombre");
        }
        if (id == null) {
            throw new ErrorService(" El libro debe tener un Id");
        }
        if (isbn == null) {
            throw new ErrorService("El libro debe tener un Isbn");
        }
        if (anio == null) {
            throw new ErrorService("El libro debe tener un año");
        }
        if (autor == null) {
            throw new ErrorService("El libro debe tener un autor");
        }
        if (editorial == null) {
            throw new ErrorService("El libro debe tener una editorial");
        }
    }
 
}
