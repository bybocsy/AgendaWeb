package com.ginasiouniforagenda.AgendamentoWeb.controller;

import com.ginasiouniforagenda.AgendamentoWeb.domain.event.Agendamento;
import com.ginasiouniforagenda.AgendamentoWeb.domain.event.AgendamentoRequestDTO;
import com.ginasiouniforagenda.AgendamentoWeb.domain.event.AgendamentoResponseDTO;
import com.ginasiouniforagenda.AgendamentoWeb.repository.AgendamentoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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

    @GetMapping("/mes/{year}/{month}")
    public ResponseEntity<List<AgendamentoResponseDTO>> getAgendamentoByMonth(
            @PathVariable("year") int year,
            @PathVariable("month") int month) {

        if (month < 1 || month > 12) {
            return ResponseEntity.badRequest().build();
        }

        LocalDateTime startDate = LocalDateTime.of(year, month, 1, 0, 0);
        LocalDateTime endDate = startDate.plusMonths(1).minusSeconds(1);

        List<AgendamentoResponseDTO> agendamentoResponseDTOList = agendamentoRepository
                .findByDateTimeBetween(startDate, endDate)
                .stream()
                .map(AgendamentoResponseDTO::new)
                .toList();

        if (agendamentoResponseDTOList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(agendamentoResponseDTOList);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editarAgendamento(
            @PathVariable UUID id,
            @RequestBody @Valid AgendamentoRequestDTO editedAgendamento){

        if(!agendamentoRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }

        if(agendamentoRepository.existsByDateTimeAndPlaceAndIdNot(
                editedAgendamento.dateTime(),
                editedAgendamento.place(),
                id)){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Já existe outro agendamento neste local e horário.");

        }
        Agendamento agendamentoAtualizado = new Agendamento(editedAgendamento);
        agendamentoAtualizado.setId(id);
        agendamentoRepository.save(agendamentoAtualizado);
        return ResponseEntity.ok(new AgendamentoResponseDTO(agendamentoAtualizado));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteAgendamento(@PathVariable("id") UUID id) {
        try{
            if(!agendamentoRepository.existsById(id)){
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Agendamento não encontrado com o ID: " + id);
            }

            agendamentoRepository.deleteById(id);

            return ResponseEntity
                    .ok("Agendamento com ID " + id + " foi deletado com sucesso.");
        } catch(Exception e){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao deletar agendamento" + e.getMessage());
        }
    }
}
