package com.example.apinavigation.reserva.domain;

import com.example.apinavigation.reserva.entity.Reserva;
import com.example.apinavigation.viagem.entity.Viagem;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ReservaDTO {

    private String id;

    @NotBlank(message = "Name is required")
    private String nome;

    @NotBlank(message = "Number is required")
    private String numero;

    @NotNull(message = "Assento is required")
    private int assento;

    public ReservaDTO(String id, String nome, String numero, int assento) {
        this.id = id;
        this.nome = nome;
        this.numero = numero;
        this.assento = assento;
    }

    public Reserva parseReserva(ReservaDTO reservaDTO) {
        return new Reserva(
                reservaDTO.getId(),
                reservaDTO.getNome(),
                reservaDTO.getNumero(),
                reservaDTO.getAssento(),
                null
        );
    }
}
