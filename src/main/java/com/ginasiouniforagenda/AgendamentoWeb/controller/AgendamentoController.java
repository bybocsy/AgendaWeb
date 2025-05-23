package com.ginasiouniforagenda.AgendamentoWeb.controller;

import com.ginasiouniforagenda.AgendamentoWeb.domain.event.Agendamento;
import com.ginasiouniforagenda.AgendamentoWeb.domain.event.AgendamentoRequestDTO;
import com.ginasiouniforagenda.AgendamentoWeb.domain.event.AgendamentoResponseDTO;
import com.ginasiouniforagenda.AgendamentoWeb.domain.product.Product;
import com.ginasiouniforagenda.AgendamentoWeb.domain.product.ProductRequestDTO;
import com.ginasiouniforagenda.AgendamentoWeb.domain.product.ProductResponseDTO;
import com.ginasiouniforagenda.AgendamentoWeb.repository.AgendamentoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @PostMapping("/cadastro")
    public ResponseEntity postAgendamento(@RequestBody @Valid AgendamentoRequestDTO body){
        boolean existe = agendamentoRepository.existsByDateTimeAndPlace(body.dateTime(), body.place());

        if (existe) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Já existe um evento cadastrado nesse local e horário.");
        }

        Agendamento agendamento = new Agendamento(body);

        this.agendamentoRepository.save(agendamento);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/lugar/{place}")
    public ResponseEntity<List<AgendamentoResponseDTO>> getAgendamentoByName(@PathVariable("place") String place){
        List<AgendamentoResponseDTO> agendamentoResponseDTOList = agendamentoRepository.findByPlaceContainingIgnoreCase(place)
                .stream()
                .map(AgendamentoResponseDTO::new)
                .toList();
        if (agendamentoResponseDTOList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(agendamentoResponseDTOList);
    }

    @GetMapping("/listagem")
    public ResponseEntity getAllAgendamentos(){
        List<AgendamentoResponseDTO> agendamentoResponseDTOList = this.agendamentoRepository.findAll().stream().map(AgendamentoResponseDTO::new).toList();

        return ResponseEntity.ok(agendamentoResponseDTOList);
    }
    @GetMapping("/dia-hora/{dateTime}")
    public ResponseEntity<List<AgendamentoResponseDTO>> getAgendamentoByDateTime(
            @PathVariable("dateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime) {

        List<AgendamentoResponseDTO> agendamentoResponseDTOList = agendamentoRepository
                .findByDateTime(dateTime)
                .stream()
                .map(AgendamentoResponseDTO::new)
                .toList();

        if (agendamentoResponseDTOList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(agendamentoResponseDTOList);
    }

}
