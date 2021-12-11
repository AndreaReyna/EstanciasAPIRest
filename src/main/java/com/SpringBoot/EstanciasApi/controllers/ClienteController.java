package com.SpringBoot.EstanciasApi.controllers;

import com.SpringBoot.EstanciasApi.DTO.ClienteDTO;
import com.SpringBoot.EstanciasApi.services.ClienteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService cs;

    @GetMapping()
    public List<ClienteDTO> obtenerClientes() {
        return cs.obtenerClientes();
    }

    @PostMapping()
    public void crear(@RequestBody ClienteDTO dto) throws Exception {
        cs.guardar(dto);
    }

    @GetMapping("/{id}")
    public ClienteDTO buscarPorId(@PathVariable("id") Integer id) {
        return this.cs.buscarPorId(id);
    }

    @PostMapping("/baja/{id}")
    public void bajaAlta(@PathVariable("id") Integer id) {
        cs.baja(id);
    }
}
