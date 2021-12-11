package com.SpringBoot.EstanciasApi.repositories;

import com.SpringBoot.EstanciasApi.entities.Reserva;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReservaRepository extends JpaRepository<Reserva, Integer> {

    @Modifying
    @Query("UPDATE Reserva a SET a.alta = :alta WHERE a.id = :id")
    void baja(@Param("id") Integer id, @Param("alta") Boolean alta);

    @Query("SELECT s FROM Reserva s WHERE casa.id = :id")
    List<Reserva> reservasPorCasa(@Param("id") Integer id);

    @Query("SELECT s FROM Reserva s WHERE cliente.id = :id")
    List<Reserva> reservasPorCliente(@Param("id") Integer id);
}
