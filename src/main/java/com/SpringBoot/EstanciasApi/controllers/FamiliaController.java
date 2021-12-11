package com.SpringBoot.EstanciasApi.controllers;

import com.SpringBoot.EstanciasApi.DTO.FamiliaDTO;
import com.SpringBoot.EstanciasApi.services.FamiliaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/familias")
public class FamiliaController {

    @Autowired
    private FamiliaService fs;

    @GetMapping()
    public List<FamiliaDTO> obtenerFamilias() {
        return fs.obtenerFamilias();
    }

    @PostMapping()
    public void crear(@RequestBody FamiliaDTO dto) throws Exception {
        fs.guardar(dto);
    }

    @GetMapping("/{id}")
    public FamiliaDTO buscarPorId(@PathVariable("id") Integer id) {
        return this.fs.buscarPorId(id);
    }

    @PostMapping("/baja/{id}")
    public void bajaAlta(@PathVariable("id") Integer id) {
        fs.baja(id);
    }
}
