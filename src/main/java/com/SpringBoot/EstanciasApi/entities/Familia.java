package com.SpringBoot.EstanciasApi.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Familia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private Integer edadMin;
    @Column(nullable = false)
    private Integer edadMax;
    @Column(nullable = false)
    private Integer numHijos;

    private Boolean alta;

    @OneToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;
}
