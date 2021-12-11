package com.SpringBoot.EstanciasApi.repositories;

import com.SpringBoot.EstanciasApi.entities.Comentario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {

    @Modifying
    @Query("UPDATE Comentario a SET a.alta = :alta WHERE a.id = :id")
    void baja(@Param("id") Integer id, @Param("alta") Boolean alta);

    @Modifying
    @Query("SELECT a FROM Comentario a WHERE a.casa.id = :idCasa")
    List<Comentario> comentariosPorCasa(@Param("idCasa") Integer id);
}
