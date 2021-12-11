package com.SpringBoot.EstanciasApi.services;

import com.SpringBoot.EstanciasApi.DTO.ComentarioDTO;
import com.SpringBoot.EstanciasApi.entities.Comentario;
import com.SpringBoot.EstanciasApi.repositories.CasaRepository;
import com.SpringBoot.EstanciasApi.repositories.ComentarioRepository;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository cr;

    @Autowired
    private CasaRepository casaR;

    @Transactional(readOnly = true)
    public List<ComentarioDTO> obtenerComentarios() {
        try {
            ModelMapper mp = new ModelMapper();
            List<ComentarioDTO> dto = new ArrayList();
            List<Comentario> comentarios = cr.findAll();
            for (Comentario c : comentarios) {
                dto.add(mp.map(c, ComentarioDTO.class));
            }
            return dto;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<ComentarioDTO> comentariosPorCasa(Integer idCasa) {
        try {
            ModelMapper mp = new ModelMapper();
            List<ComentarioDTO> dto = new ArrayList();
            List<Comentario> comentarios = cr.comentariosPorCasa(idCasa);
            for (Comentario c : comentarios) {
                dto.add(mp.map(c, ComentarioDTO.class));
            }
            return dto;
        } catch (Exception e) {
            throw e;
        }
    }

    public void guardar(ComentarioDTO dto) throws Exception {
        try {
            ModelMapper mp = new ModelMapper();
            if (dto.getCasa() == null && dto.getIdCasa() == null) {
                throw new Exception("El comentario debe pertenecer a una casa");
            }
            Comentario c = mp.map(dto, Comentario.class);
            if (dto.getCasa() == null) {
                c.setCasa(casaR.findById(dto.getIdCasa()).orElse(null));
            } else {
                casaR.save(dto.getCasa());
            }

            if (dto.getAlta() != true) {
                c.setAlta(true);
            }
            cr.save(c);
        } catch (Exception e) {
            throw e;
        }
    }

    public ComentarioDTO buscarPorId(Integer id) {
        try {
            ModelMapper mp = new ModelMapper();

            Comentario c = cr.findById(id).orElse(null);
            ComentarioDTO dto = mp.map(c, ComentarioDTO.class);
            return dto;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional
    public void baja(Integer id) {
        try {
            cr.baja(id, false);
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional
    public void alta(Integer id) {
        try {
            cr.baja(id, true);
        } catch (Exception e) {
            throw e;
        }
    }
}
