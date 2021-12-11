package com.SpringBoot.EstanciasApi.services;

import com.SpringBoot.EstanciasApi.DTO.ReservaDTO;
import com.SpringBoot.EstanciasApi.entities.Reserva;
import com.SpringBoot.EstanciasApi.repositories.CasaRepository;
import com.SpringBoot.EstanciasApi.repositories.ClienteRepository;
import com.SpringBoot.EstanciasApi.repositories.ReservaRepository;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository rr;

    @Autowired
    private CasaRepository casaR;

    @Autowired
    private ClienteRepository cr;

    @Transactional(readOnly = true)
    public List<ReservaDTO> obtenerReservas() {
        try {
            ModelMapper mp = new ModelMapper();
            List<ReservaDTO> dto = new ArrayList();
            List<Reserva> reservas = rr.findAll();
            for (Reserva r : reservas) {
                dto.add(mp.map(r, ReservaDTO.class));
            }
            return dto;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<ReservaDTO> reservasPorCasas(Integer id) {
        try {
            ModelMapper mp = new ModelMapper();
            List<ReservaDTO> dto = new ArrayList();
            List<Reserva> reservas = rr.reservasPorCasa(id);
            for (Reserva r : reservas) {
                dto.add(mp.map(r, ReservaDTO.class));
            }
            return dto;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<ReservaDTO> reservasPorClientes(Integer id) {
        try {
            ModelMapper mp = new ModelMapper();
            List<ReservaDTO> dto = new ArrayList();
            List<Reserva> reservas = rr.reservasPorCliente(id);
            for (Reserva r : reservas) {
                dto.add(mp.map(r, ReservaDTO.class));
            }
            return dto;
        } catch (Exception e) {
            throw e;
        }

    }

    public void guardar(ReservaDTO dto) throws Exception {
        try {
           if (dto.getFechaDesde().isAfter(dto.getFechaHasta())) {
                throw new Exception ("La fecha de finalizacion debe ser posterior a la de inicio");
            }
            ModelMapper mp = new ModelMapper();
            Reserva r = mp.map(dto, Reserva.class);
            
            if (dto.getCasa() == null) {
                r.setCasa(casaR.findById(dto.getIdCasa()).orElse(null));
            } else {
                casaR.save(dto.getCasa());
            }
            if ((dto.getFechaDesde().isBefore(r.getCasa().getFechaDesde())) 
                 || (dto.getFechaDesde().isAfter(r.getCasa().getFechaHasta())) 
                 || (dto.getFechaHasta().isAfter(r.getCasa().getFechaHasta()))) {
                throw new Exception ("La fecha no esta entre los dias disponibles de la casa");
            }
            
            List<Reserva> reservas = rr.reservasPorCasa(r.getCasa().getId());
            for (Reserva re : reservas) {
                if ((dto.getFechaDesde().isAfter(re.getFechaDesde()) || dto.getFechaDesde().equals(re.getFechaDesde()))
                     && (dto.getFechaDesde().isBefore(re.getFechaHasta()) || dto.getFechaDesde().equals(re.getFechaHasta()))) {
                    throw new Exception ("Esa fecha se encuentra reservada");                  
                }
            }
            
            if (dto.getCliente() == null) {
                r.setCliente(cr.findById(dto.getIdCliente()).orElse(null));
            } else {
                cr.save(dto.getCliente());
            }

            if (dto.getAlta() != true) {
                r.setAlta(true);
            }
            rr.save(r);
        } catch (Exception e) {
            throw e;
        }
    }

    public ReservaDTO buscarPorId(Integer id) {
        try {
            ModelMapper mp = new ModelMapper();

            Reserva r = rr.findById(id).orElse(null);
            ReservaDTO dto = mp.map(r, ReservaDTO.class);
            return dto;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional
    public void baja(Integer id) {
        try {
            rr.baja(id, false);
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional
    public void alta(Integer id) {
        try {
            rr.baja(id, true);
        } catch (Exception e) {
            throw e;
        }
    }
}
