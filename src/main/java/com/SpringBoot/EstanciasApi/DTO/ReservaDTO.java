package com.SpringBoot.EstanciasApi.DTO;

import com.SpringBoot.EstanciasApi.entities.Casa;
import com.SpringBoot.EstanciasApi.entities.Cliente;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservaDTO implements Serializable {

    private Integer id;
    private Integer idCasa;
    private Integer idCliente;
    private LocalDate fechaDesde;
    private LocalDate fechaHasta;
    private Boolean alta;
    private Casa casa;
    private Cliente cliente;
}
