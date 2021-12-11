package com.SpringBoot.EstanciasApi.repositories;

import com.SpringBoot.EstanciasApi.entities.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByCorreo(String correo);

    boolean existsUsuarioByCorreo(String correo);

    @Modifying
    @Query("UPDATE Usuario u SET u.correo = :correo, u.clave = :clave WHERE u.id = :id")
    void modificar(@Param("correo") String correo, @Param("clave") String clave, @Param("id") Integer id);

    @Query("SELECT a FROM Usuario a WHERE a.correo = :correo")
    Usuario buscar(@Param("correo") String correo);

    @Modifying
    @Query("UPDATE Usuario a SET a.alta = :alta WHERE a.id = :id")
    void baja(@Param("id") Integer id, @Param("alta") Boolean alta);
}
