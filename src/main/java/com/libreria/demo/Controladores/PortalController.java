
package com.libreria.demo.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PortalController {
    
    @GetMapping("/")
    public String index() {
        return "index.html";
    }
    
    @GetMapping("/ListarAutor")
    public String ListarAutor() {
        return "ListarAutor.html";
    }
    
    @GetMapping("/CargarAutor")
    public String CargarAutor() {
        return "CargarAutor.html";
    }
    
}

