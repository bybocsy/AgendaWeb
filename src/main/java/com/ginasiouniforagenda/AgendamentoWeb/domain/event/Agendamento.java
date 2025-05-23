package com.ginasiouniforagenda.AgendamentoWeb.domain.event;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Table(name = "event")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String place;

    @Column(nullable = false)
    private boolean isFixed;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    private String description;

    @Column(nullable = false)
    private String responsible;

    public Agendamento(AgendamentoRequestDTO data) {
        this.place = data.place();
        this.isFixed = data.isFixed();
        this.dateTime = data.dateTime();
        this.description = data.description();
        this.responsible = data.responsible();
    }
}
