package com.SpringBoot.EstanciasApi.repositories;

import com.SpringBoot.EstanciasApi.entities.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Modifying
    @Query("UPDATE Cliente a SET a.alta = :alta WHERE a.id = :id")
    void baja(@Param("id") Integer id, @Param("alta") Boolean alta);

    @Modifying
    @Query("SELECT a FROM Cliente a WHERE a.usuario.id = :idUsuario")
    List<Cliente> buscarPorUsuario(@Param("idUsuario") Integer id);
}
