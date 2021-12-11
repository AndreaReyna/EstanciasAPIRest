package com.SpringBoot.EstanciasApi.DTO;

import com.SpringBoot.EstanciasApi.entities.Usuario;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO implements Serializable {

    private Integer id;
    private String nombre;
    private String calle;
    private Integer numero;
    private Integer cp;
    private String ciudad;
    private String pais;
    private Boolean alta;
    private Usuario usuario;
    private String correo;
}
