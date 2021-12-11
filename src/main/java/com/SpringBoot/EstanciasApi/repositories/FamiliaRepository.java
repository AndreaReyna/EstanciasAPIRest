package com.SpringBoot.EstanciasApi.repositories;

import com.SpringBoot.EstanciasApi.entities.Familia;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FamiliaRepository extends JpaRepository<Familia, Integer> {

    @Modifying
    @Query("UPDATE Familia a SET a.alta = :alta WHERE a.id = :id")
    void baja(@Param("id") Integer id, @Param("alta") Boolean alta);

    @Modifying
    @Query("SELECT a FROM Familia a WHERE a.usuario.id = :idUsuario")
    List<Familia> buscarPorUsuario(@Param("idUsuario") Integer id);
}
