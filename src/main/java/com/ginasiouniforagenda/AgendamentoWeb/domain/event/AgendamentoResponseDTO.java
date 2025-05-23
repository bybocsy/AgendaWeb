package com.ginasiouniforagenda.AgendamentoWeb.domain.event;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public record AgendamentoResponseDTO(UUID id, String place, LocalDateTime dateTime, Boolean isFixed, String description, String responsible) {
    public AgendamentoResponseDTO(Agendamento agendamento){
        this(agendamento.getId(), agendamento.getPlace(), agendamento.getDateTime(), agendamento.isFixed(), agendamento.getDescription(), agendamento.getResponsible());
    }
}
