package com.SpringBoot.EstanciasApi.DTO;

import com.SpringBoot.EstanciasApi.entities.Usuario;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FamiliaDTO implements Serializable {

    private Integer id;
    private String nombre;
    private Integer edadMin;
    private Integer edadMax;
    private Integer numHijos;
    private Boolean alta;
    private String correo;
    private Usuario usuario;

}
