package com.example.apinavigation.reserva.entity;

import lombok.Data;

import com.example.apinavigation.reserva.domain.ReservaDTO;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "reserva")
public class Reserva {

    @Id
    private String id;
    private String nome;
    private String numero;
    private int assento;
    private String viagemId;

    public Reserva(String id, String nome, String numero, int assento, String viagemId) {
        this.id = id;
        this.nome = nome;
        this.numero = numero;
        this.assento = assento;
        this.viagemId = viagemId;
    }

    public ReservaDTO toReservaDTO() {
        return new ReservaDTO(
                this.getId(),
                this.getNome(),
                this.getNumero(),
                this.getAssento()
        );
    }

}
