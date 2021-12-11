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
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String calle;
    private int numero;
    private int cp;
    @Column(nullable = false)
    private String ciudad;
    @Column(nullable = false)
    private String pais;
    private Boolean alta;

    @OneToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;

}
