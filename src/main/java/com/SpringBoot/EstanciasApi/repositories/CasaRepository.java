package com.SpringBoot.EstanciasApi.repositories;

import com.SpringBoot.EstanciasApi.entities.Casa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CasaRepository extends JpaRepository<Casa, Integer> {

    @Modifying
    @Query("UPDATE Casa a SET a.alta = :alta WHERE a.id = :id")
    void baja(@Param("id") Integer id, @Param("alta") Boolean alta);
}
