package com.SpringBoot.EstanciasApi.entities;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate fechaDesde;
    private LocalDate fechaHasta;
    private Boolean alta;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Casa casa;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Cliente cliente;
}
