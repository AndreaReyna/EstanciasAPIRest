package com.SpringBoot.EstanciasApi.controllers;

import com.SpringBoot.EstanciasApi.DTO.ComentarioDTO;
import com.SpringBoot.EstanciasApi.services.ComentarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioService cs;

    @GetMapping()
    public List<ComentarioDTO> comentarios() {
        return cs.obtenerComentarios();
    }

    @GetMapping("/casa/{id}")
    public List<ComentarioDTO> comentariosPorCasa(@PathVariable("id") Integer id) {
        return cs.comentariosPorCasa(id);
    }

    @PostMapping()
    public void crear(@RequestBody ComentarioDTO dto) throws Exception {
        cs.guardar(dto);
    }

    @GetMapping("/{id}")
    public ComentarioDTO buscarPorId(@PathVariable("id") Integer id) {
        return this.cs.buscarPorId(id);
    }

    @PostMapping("/baja/{id}")
    public void bajaAlta(@PathVariable("id") Integer id) {
        cs.baja(id);
    }
}
