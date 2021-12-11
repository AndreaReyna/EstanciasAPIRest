package com.SpringBoot.EstanciasApi.DTO;

import com.SpringBoot.EstanciasApi.entities.Casa;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComentarioDTO implements Serializable {

    private Integer id;
    private String comentario;
    private Boolean alta;
    private Integer idCasa;
    private Casa casa;
}
