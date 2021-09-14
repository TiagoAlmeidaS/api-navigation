package com.example.apinavigation.reserva.controller;

import com.example.apinavigation.exception.UserNotFoundException;
import com.example.apinavigation.reserva.domain.ReservaDTO;
import com.example.apinavigation.reserva.entity.Reserva;
import com.example.apinavigation.reserva.service.ReservaService;
import com.example.apinavigation.viagem.entity.Viagem;
import com.example.apinavigation.viagem.service.ViagemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/viagem")
public class ReservaController {

    private ReservaService reservaService;
    private ViagemService viagemService;

    @Autowired
    public ReservaController(ReservaService reservaService, ViagemService viagemService) {
        this.reservaService = reservaService;
        this.viagemService = viagemService;
    }

    @GetMapping(value = "/{viagemId}/reserva")
    public ResponseEntity<ReservaDTO> findAll(@PathVariable(value = "viagemId") String viagemId) {
        Reserva reserva = reservaService.findByViagemId(viagemId);
        ReservaDTO reservaDTO = reserva.toReservaDTO();
        return ResponseEntity.ok(reservaDTO);
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/{viagemId}/reserva")
    public ResponseEntity<ReservaDTO> save(@Valid @PathVariable(value = "viagemId") String viagemId, @RequestBody ReservaDTO reservaDTO) throws UserNotFoundException {

        Optional<Viagem> viagem = viagemService.findById(viagemId);
        if(!viagem.isPresent()){
            return  ResponseEntity.notFound().build();
        }

        Reserva reserva = reservaDTO.parseReserva(reservaDTO);
        reserva.setViagemId(viagemId);

        reservaService.save(reserva);
        return ResponseEntity.ok(reservaDTO);
    }

    @DeleteMapping(value = "/{viagemId}/reserva/{id}")
    public ResponseEntity delete(@PathVariable String id, @PathVariable String viagemId){

        //verificação de relacionamento
        Optional<Viagem> viagem = viagemService.findById(viagemId);
        if(!viagem.isPresent()){
            return  ResponseEntity.notFound().build();
        }

        reservaService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
