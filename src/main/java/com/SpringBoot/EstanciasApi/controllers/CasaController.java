package com.SpringBoot.EstanciasApi.controllers;

import com.SpringBoot.EstanciasApi.DTO.CasaDTO;
import com.SpringBoot.EstanciasApi.services.CasaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/casas")
public class CasaController {

    @Autowired
    private CasaService cs;

    @GetMapping()
    public List<CasaDTO> obtenerComentarios() {
        return cs.obtenerCasas();
    }

    @PostMapping()
    public void crear(@RequestBody CasaDTO dto) throws Exception {
        cs.guardar(dto);
    }

    @GetMapping("/{id}")
    public CasaDTO buscarPorId(@PathVariable("id") Integer id) {
        return this.cs.buscarPorId(id);
    }

    @PostMapping("/baja/{id}")
    public void bajaAlta(@PathVariable("id") Integer id) {
        cs.baja(id);
    }
}
