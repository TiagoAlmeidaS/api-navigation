package com.example.apinavigation.viagem.controller;

import com.example.apinavigation.exception.UserNotFoundException;
import com.example.apinavigation.viagem.domain.ViagemDTO;
import com.example.apinavigation.viagem.entity.Viagem;
import com.example.apinavigation.viagem.service.ViagemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/viagem")
public class ViagemController {

    private ViagemService service;

    @Autowired
    public ViagemController(ViagemService service) {
        this.service = service;
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ViagemDTO> save(@Valid @RequestBody ViagemDTO viagemDTO) throws UserNotFoundException {

        Viagem viagem = Viagem.parseViagem(viagemDTO);

        service.save(viagem);
        return ResponseEntity.ok(viagemDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ViagemDTO> getById(@PathVariable(required = true) String id) {
        Optional<Viagem> optional = service.findById(id);

        if (!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(optional.get().toViagemDTO());
    }

    @GetMapping()
    public ResponseEntity<List<ViagemDTO>> findAll() {
        List<Viagem> viagens = service.findAll();
        List<ViagemDTO> viagensDTO = viagens.stream().map(Viagem::toViagemDTO).collect(Collectors.toList());
        return ResponseEntity.ok(viagensDTO);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ViagemDTO> update(@RequestBody ViagemDTO viagemDTO, @PathVariable String id) {

        Optional<Viagem> viagemOld = service.findById(id);

        if (!viagemOld.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        Viagem viagem = viagemOld.get();

        if (service.findByNomeMotoristaAndDataViagemAndOrigemAndDestino(viagemDTO.parseViagem()).isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        if (viagemDTO.getDataViagem() != null) {
            viagem.setDataViagem(viagemDTO.getDataViagem().toEpochDay());
        }

        viagem.setNomeMotorista(viagemDTO.getNomeMotorista());
        viagem.setDataViagem(viagemDTO.getDataViagem().toEpochDay());
        viagem.setOrigem(viagemDTO.getOrigem());
        viagem.setDestino(viagemDTO.getDestino());

        service.save(viagem);


        return ResponseEntity.ok(viagemDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable String id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
