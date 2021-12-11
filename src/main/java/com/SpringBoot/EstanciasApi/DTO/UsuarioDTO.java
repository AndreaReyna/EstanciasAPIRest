package com.SpringBoot.EstanciasApi.DTO;

import com.SpringBoot.EstanciasApi.entities.Rol;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO implements Serializable {

    private Integer id;
    private String correo;
    private String clave;
    private Boolean alta;
    private Rol rol;
}
