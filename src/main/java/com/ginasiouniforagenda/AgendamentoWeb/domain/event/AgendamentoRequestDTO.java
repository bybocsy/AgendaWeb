package com.ginasiouniforagenda.AgendamentoWeb.domain.event;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record AgendamentoRequestDTO(
        @NotBlank
        String place,

        @NotNull
        LocalDateTime dateTime,

        @NotNull
        Boolean isFixed,

        String description,

        @NotBlank
        String responsible
) {}

