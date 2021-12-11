package com.SpringBoot.EstanciasApi.services;

import com.SpringBoot.EstanciasApi.DTO.UsuarioDTO;
import com.SpringBoot.EstanciasApi.entities.Rol;
import com.SpringBoot.EstanciasApi.entities.Usuario;
import com.SpringBoot.EstanciasApi.repositories.RolRepository;
import com.SpringBoot.EstanciasApi.repositories.UsuarioRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private RolRepository rr;

    @Autowired
    private UsuarioRepository ur;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @PostMapping
    public void guardarUsuario(UsuarioDTO dto) throws Exception {
        try {
            validaciones(dto);
            ModelMapper mp = new ModelMapper();
            Usuario u = mp.map(dto, Usuario.class);
            u.setClave(encoder.encode(dto.getClave()));
            if (ur.findAll().isEmpty()) {
                Rol r1 = new Rol("ADMIN");
                Rol r2 = new Rol("CLIENTE");
                rr.save(r1);
                rr.save(r2);
                u.setRol(r1);
            } else {
                u.setRol(rr.buscarRol("CLIENTE"));
            }
            ur.save(u);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        try {
        Usuario usuario = ur.findByCorreo(correo).orElseThrow(() -> new UsernameNotFoundException("No existe un usuario asociado al correo ingresado"));
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().getNombre());

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attributes.getRequest().getSession(true);

        session.setAttribute("idUsuario", usuario.getId());
        session.setAttribute("correo", usuario.getCorreo());

        return new User(usuario.getCorreo(), usuario.getClave(), Collections.singletonList(authority));
        } catch (IllegalStateException e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<UsuarioDTO> buscarTodos() {
        try {
            ModelMapper mp = new ModelMapper();
            List<UsuarioDTO> dto = new ArrayList();
            List<Usuario> usuarios = ur.findAll();
            for (Usuario u : usuarios) {
                dto.add(mp.map(u, UsuarioDTO.class));
            }
            return dto;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public UsuarioDTO buscarPorId(Integer id) {
        try {
            ModelMapper mp = new ModelMapper();
            Usuario u = ur.findById(id).orElse(null);
            UsuarioDTO dto = mp.map(u, UsuarioDTO.class);
            return dto;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public UsuarioDTO buscarPorCorreo(String correo) {
        try {
            ModelMapper mp = new ModelMapper();
            Usuario u = ur.buscar(correo);
            UsuarioDTO dto = mp.map(u, UsuarioDTO.class);
            return dto;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional
    public void baja(Integer id) {
        try {
            ur.baja(id, false);
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional
    public void alta(Integer id) {
        try {
            ur.baja(id, true);
        } catch (Exception e) {
            throw e;
        }
    }

    public void validaciones(UsuarioDTO dto) throws Exception {
        if (dto.getClave().length() < 8) {
            throw new Exception("La clave debe tener al menos 8 caracteres");
        }
        if (ur.existsUsuarioByCorreo(dto.getCorreo())) {
            throw new Exception("El correo ya se encuentra registrado");
        }
        if (!(dto.getCorreo().contains("@") && dto.getCorreo().contains(".com"))) {
            throw new Exception("Debe ingresar un formato de correo valido.");
        }
    }
}
