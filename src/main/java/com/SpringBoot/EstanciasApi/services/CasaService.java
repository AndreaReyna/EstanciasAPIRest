package com.SpringBoot.EstanciasApi.services;

import com.SpringBoot.EstanciasApi.DTO.CasaDTO;
import com.SpringBoot.EstanciasApi.entities.Casa;
import com.SpringBoot.EstanciasApi.repositories.CasaRepository;
import com.SpringBoot.EstanciasApi.repositories.FamiliaRepository;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CasaService {

    @Autowired
    private CasaRepository cr;

    @Autowired
    private FamiliaRepository fr;

    @Transactional(readOnly = true)
    public List<CasaDTO> obtenerCasas() {
        try {
            ModelMapper mp = new ModelMapper();
            List<CasaDTO> dto = new ArrayList();
            List<Casa> casas = cr.findAll();
            for (Casa c : casas) {
                dto.add(mp.map(c, CasaDTO.class));
            }
            return dto;
        } catch (Exception e) {
            throw e;
        }
    }

    public void guardar(CasaDTO dto) throws Exception {
        try {
            ModelMapper mp = new ModelMapper();
            if (dto.getFamilia() == null && dto.getIdFamilia() == null) {
                throw new Exception("La casa debe pertenecer a una familia");
            }
            Casa c = mp.map(dto, Casa.class);
            if (dto.getFamilia() == null) {
                c.setFamilia(fr.findById(dto.getIdFamilia()).orElse(null));
            } else {
                fr.save(dto.getFamilia());
            }

            if (dto.getAlta() != true) {
                c.setAlta(true);
            }
            cr.save(c);
        } catch (Exception e) {
            throw e;
        }
    }

    public CasaDTO buscarPorId(Integer id) {
        try {
            ModelMapper mp = new ModelMapper();

            Casa c = cr.findById(id).orElse(null);
            CasaDTO dto = mp.map(c, CasaDTO.class);
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
