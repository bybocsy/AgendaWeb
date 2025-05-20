package com.ginasiouniforagenda.AgendamentoWeb.domain.event;

import java.util.Date;
import java.util.UUID;

public record AgendamentoResponseDTO(UUID id, String place, Date date, Boolean isFixed, String description, String responsible) {
    public AgendamentoResponseDTO(Agendamento agendamento){
        this(agendamento.getId(), agendamento.getPlace(), agendamento.getDate(), agendamento.isFixed(), agendamento.getDescription(), agendamento.getResponsible());
    }
}
