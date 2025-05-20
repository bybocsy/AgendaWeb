package com.ginasiouniforagenda.AgendamentoWeb.domain.event;

import jakarta.validation.constraints.NotBlank;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Date;

public record AgendamentoRequestDTO(
        @NotBlank
        String place,

        @NotNull
        Date date,

        @NotNull
        Boolean isFixed,

        String description,

        @NotBlank
        String responsible
) {
}
