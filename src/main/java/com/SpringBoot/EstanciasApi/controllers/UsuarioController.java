package com.SpringBoot.EstanciasApi.controllers;

import com.SpringBoot.EstanciasApi.DTO.UsuarioDTO;
import com.SpringBoot.EstanciasApi.services.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    
 @Autowired
 private UsuarioService us;
 
 @GetMapping()
   public List<UsuarioDTO> obtenerUsuarios(){
       return us.buscarTodos();
   }
    
    @PostMapping()
    public void crear(@RequestBody UsuarioDTO dto) throws Exception{
        us.guardarUsuario(dto);
     } 
    
    @GetMapping("/{id}")
    public UsuarioDTO buscarPorId(@PathVariable("id") Integer id){
    return this.us.buscarPorId(id);
    }
    
    @PostMapping("/baja/{id}")
    public void bajaAlta(@PathVariable("id") Integer id){
      us.baja(id);
    }
}
