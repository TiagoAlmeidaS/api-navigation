package com.example.apinavigation.viagem.domain;


import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.apinavigation.viagem.entity.Viagem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Data
public class ViagemDTO {

    private String id;

    @NotBlank(message = "Não pode ser vazio")
    @Length(min = 5, max = 20, message = "Limite excedido: 20")
    private String nomeMotorista;

    @NotBlank(message = "Não pode ser vazio")
    private String origem;

    @NotBlank(message = "Não pode ser vazio")
    private String destino;

    @NotNull(message = "Não pode ser vazio")
    private LocalDate dataViagem;

    @NotNull(message = "Não pdoe ser nulo")
    private Map<Integer, String> assentos;


    public ViagemDTO(String id, String nomeMotorista, String origem, String destino, LocalDate dataViagem, Map<Integer, String> assentos) {
        this.id = id;
        this.nomeMotorista = nomeMotorista;
        this.origem = origem;
        this.destino = destino;
        this.dataViagem = dataViagem;
        this.assentos = assentos;
    }

    public Viagem parseViagem() {
        return new Viagem(
                this.getId(),
                this.getNomeMotorista(),
                this.getOrigem(), this.getDestino(),
                this.getDataViagem().toEpochDay(),
                this.getAssentos());
    }
}
