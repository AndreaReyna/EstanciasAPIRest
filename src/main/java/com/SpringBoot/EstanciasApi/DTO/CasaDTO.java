package com.SpringBoot.EstanciasApi.DTO;

import com.SpringBoot.EstanciasApi.entities.Familia;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CasaDTO implements Serializable {

    private Integer id;
    private String calle;
    private Integer num;
    private String codPostal;
    private String ciudad;
    private String pais;
    private LocalDate fechaDesde;
    private LocalDate fechaHasta;
    private Integer minDias;
    private Integer maxDias;
    private Double precio;
    private String tipoVivienda;
    private Boolean alta;
    private Integer idFamilia;
    private Familia familia;
}
