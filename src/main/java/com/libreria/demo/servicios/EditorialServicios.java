
package com.libreria.demo.servicios;

import com.libreria.demo.entidades.Editorial;
import org.springframework.stereotype.Service;
import com.libreria.demo.repositorios.EditorialRepositorio;
import com.libreria.demo.errores.ErrorService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Service
public class EditorialServicios {
    
    @Autowired
    private EditorialRepositorio editorialRepositorio;
    
    @Transactional(propagation = Propagation.NESTED)
    public void cargar (String nombre, Boolean alta) throws ErrorService{
        
        validar(nombre, nombre);
        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);
        editorial.setAlta(Boolean.TRUE);
        
        editorialRepositorio.save(editorial);
    }
    
    @Transactional(propagation = Propagation.NESTED)
    public void modificar (String nombre, String id) throws ErrorService{
        
         Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Editorial editorial =respuesta.get();
            editorial.setNombre(nombre);
            
            editorialRepositorio.save(editorial);
        } else{
            throw new ErrorService("No hay una Editorial con ese Id");
        }
    }
    
    @Transactional(propagation = Propagation.NESTED)
    public void eliminar (String id) throws ErrorService{
        
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        if (respuesta.isPresent()) {
           Editorial editorial =respuesta.get();
           editorial.setAlta(Boolean.FALSE);
           
           editorialRepositorio.save(editorial);
        } else{
            throw new ErrorService("No hay una Editorial con ese Id");
        }
    }
    
    public void consultar (String id) throws ErrorService{
        
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        if (respuesta.isPresent()) {
           Editorial editorial =respuesta.get();
           
        } else{
            throw new ErrorService("No hay una Editorial con ese Id");
        }
    }
    
     public void validar(String nombre, String id) throws ErrorService {

        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorService("La Editorial debe tener un nombre");
        }
        if (id == null) {
            throw new ErrorService(" La Editorial debe tener un Id");
        }
    }
}
