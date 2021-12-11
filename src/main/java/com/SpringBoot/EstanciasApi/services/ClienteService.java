package com.SpringBoot.EstanciasApi.services;

import com.SpringBoot.EstanciasApi.DTO.ClienteDTO;
import com.SpringBoot.EstanciasApi.entities.Cliente;
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
public class ClienteService {

    @Autowired
    private ClienteRepository cr;

    @Autowired
    private UsuarioRepository ur;

    @Autowired
    private FamiliaRepository fr;

    @Transactional(readOnly = true)
    public List<ClienteDTO> obtenerClientes() {
        try {
            ModelMapper mp = new ModelMapper();
            List<ClienteDTO> dto = new ArrayList();
            List<Cliente> clientes = cr.findAll();
            for (Cliente c : clientes) {
                dto.add(mp.map(c, ClienteDTO.class));
            }
            return dto;
        } catch (Exception e) {
            throw e;
        }
    }

    public void guardar(ClienteDTO dto) throws Exception {
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
            Cliente c = mp.map(dto, Cliente.class);
            if (dto.getUsuario() == null) {
                c.setUsuario(ur.buscar(dto.getCorreo()));
            } else {
                ur.save(dto.getUsuario());
            }

            if (dto.getAlta() != true) {
                c.setAlta(true);
            }
            cr.save(c);
        } catch (Exception e) {
            throw e;
        }

    }

    public ClienteDTO buscarPorId(Integer id) {
        try {
            ModelMapper mp = new ModelMapper();

            Cliente c = cr.findById(id).orElse(null);
            ClienteDTO dto = mp.map(c, ClienteDTO.class);
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
