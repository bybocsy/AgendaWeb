package com.ginasiouniforagenda.AgendamentoWeb.repository;

import com.ginasiouniforagenda.AgendamentoWeb.domain.event.Agendamento;
import com.ginasiouniforagenda.AgendamentoWeb.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface AgendamentoRepository extends JpaRepository<Agendamento, UUID> {
    List<Agendamento> findByPlaceContainingIgnoreCase(String place);
    List<Agendamento> findByDateTime(LocalDateTime dateTime);
    boolean existsByDateTimeAndPlace(LocalDateTime dateTime, String place);
}
