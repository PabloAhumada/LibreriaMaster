
package com.libreria.demo.servicios;

import com.libreria.demo.entidades.Autor;
import com.libreria.demo.repositorios.AutorRepositorio;
import com.libreria.demo.errores.ErrorService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AutorServicios {
    
    @Autowired
    private AutorRepositorio autorRepositorio;
    
    @Transactional(propagation = Propagation.NESTED)
    public void cargar (String nombre, Boolean alta) throws ErrorService{
        
        validar(nombre, nombre);
        Autor autor = new Autor();
        autor.setNombre(nombre);
        autor.setAlta(Boolean.TRUE);
        
        autorRepositorio.save(autor);
    }
    
    @Transactional(propagation = Propagation.NESTED)
    public void modificar (String nombre, String id) throws ErrorService{
        
         Optional<Autor> respuesta = autorRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Autor autor =respuesta.get();
            autor.setNombre(nombre);
            
            autorRepositorio.save(autor);
        } else{
            throw new ErrorService("No hay un Autor con ese Id");
        }
    }
    
    @Transactional(propagation = Propagation.NESTED)
    public void eliminar (String id) throws ErrorService{
        
        Optional<Autor> respuesta = autorRepositorio.findById(id);
        if (respuesta.isPresent()) {
           Autor autor =respuesta.get();
           autor.setAlta(Boolean.FALSE);
           
           autorRepositorio.save(autor);
        } else{
            throw new ErrorService("No hay un Autor con ese Id");
        }
    }
    
    public void consultar (String id) throws ErrorService{
        
        Optional<Autor> respuesta = autorRepositorio.findById(id);
        if (respuesta.isPresent()) {
           Autor autor =respuesta.get();
           
        } else{
            throw new ErrorService("No hay un Autor con ese Id");
        }
    }
    
     public void validar(String nombre, String id) throws ErrorService {

        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorService("El Autor debe tener un nombre");
        }
        if (id == null) {
            throw new ErrorService(" El Autor debe tener un Id");
        }
    }
}
