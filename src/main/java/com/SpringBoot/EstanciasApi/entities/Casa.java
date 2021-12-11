package com.SpringBoot.EstanciasApi.entities;

import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Casa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String calle;
    @Column(nullable = false)
    private Integer num;
    @Column(nullable = false)
    private String codPostal;
    @Column(nullable = false)
    private String ciudad;
    @Column(nullable = false)
    private String pais;
    private LocalDate fechaDesde;
    private LocalDate fechaHasta;
    private Integer minDias;
    private Integer maxDias;
    @Column(nullable = false)
    private Double precio;
    @Column(nullable = false)
    private String tipoVivienda;
    private Boolean alta;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Familia familia;
}
