package com.SpringBoot.EstanciasApi.services;

import com.SpringBoot.EstanciasApi.DTO.FamiliaDTO;
import com.SpringBoot.EstanciasApi.entities.Familia;
import com.SpringBoot.EstanciasApi.repositories.ClienteRepository;
import com.SpringBoot.EstanciasApi.repositories.FamiliaRepository;
import com.SpringBoot.EstanciasApi.repositories.UsuarioRepository;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FamiliaService {

    @Autowired
    private FamiliaRepository fr;

    @Autowired
    private UsuarioRepository ur;

    @Autowired
    private ClienteRepository cr;

    @Transactional(readOnly = true)
    public List<FamiliaDTO> obtenerFamilias() {
        try {
            ModelMapper mp = new ModelMapper();
            List<FamiliaDTO> dto = new ArrayList();
            List<Familia> familias = fr.findAll();
            for (Familia f : familias) {
                dto.add(mp.map(f, FamiliaDTO.class));
            }
            return dto;
        } catch (Exception e) {
            throw e;
        }
    }

    public void guardar(FamiliaDTO dto) throws Exception {
        try {
            ModelMapper mp = new ModelMapper();
            if (dto.getCorreo() == null && dto.getUsuario() == null) {
                throw new Exception("Debe estar asociado a un usuario registrado");
            }
            if (!ur.existsUsuarioByCorreo(dto.getCorreo())) {
                throw new Exception("El correo no se encuentra registrado");
            }
            if (!(fr.buscarPorUsuario((ur.buscar(dto.getCorreo())).getId()).isEmpty()) || !(cr.buscarPorUsuario((ur.buscar(dto.getCorreo())).getId()).isEmpty())) {
                throw new Exception("Ya existe una familia o cliente asociada a ese usuario");
            }
            Familia f = mp.map(dto, Familia.class);
            if (dto.getUsuario() == null) {
                f.setUsuario(ur.buscar(dto.getCorreo()));
            } else {
                ur.save(dto.getUsuario());
            }

            if (dto.getAlta() != true) {
                f.setAlta(true);
            }
            fr.save(f);
        } catch (Exception e) {
            throw e;
        }
    }

    public FamiliaDTO buscarPorId(Integer id) {
        try {
            ModelMapper mp = new ModelMapper();
            Familia f = fr.findById(id).orElse(null);
            FamiliaDTO dto = mp.map(f, FamiliaDTO.class);
            return dto;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional
    public void baja(Integer id) {
        try {
            fr.baja(id, false);
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional
    public void alta(Integer id) {
        try {
            fr.baja(id, true);
        } catch (Exception e) {
            throw e;
        }
    }

}
