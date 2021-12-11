package com.SpringBoot.EstanciasApi.controllers;

import com.SpringBoot.EstanciasApi.DTO.ReservaDTO;
import com.SpringBoot.EstanciasApi.services.ReservaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService rs;

    @GetMapping()
    public List<ReservaDTO> ObtenerReservas() {
        return rs.obtenerReservas();
    }

    @GetMapping("/casas/{id}")
    public List<ReservaDTO> reservasPorCasas(@PathVariable("id") Integer id) {
        return rs.reservasPorCasas(id);
    }

    @GetMapping("/clientes/{id}")
    public List<ReservaDTO> reservasPorClientes(@PathVariable("id") Integer id) {
        return rs.reservasPorClientes(id);
    }

    @PostMapping()
    public void crear(@RequestBody ReservaDTO dto) throws Exception {
        rs.guardar(dto);
    }

    @GetMapping("/{id}")
    public ReservaDTO buscarPorId(@PathVariable("id") Integer id) {
        return this.rs.buscarPorId(id);
    }

    @PostMapping("/baja/{id}")
    public void bajaAlta(@PathVariable("id") Integer id) {
        rs.baja(id);
    }
}
