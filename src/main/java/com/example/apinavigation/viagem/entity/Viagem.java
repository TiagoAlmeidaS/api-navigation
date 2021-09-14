package com.example.apinavigation.viagem.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

import com.example.apinavigation.viagem.domain.ViagemDTO;

@Data
@Document(collection = "viagem")
public class Viagem {

    @Id
    private String id;
    private String nomeMotorista;
    private String origem;
    private String destino;
    private Long dataViagem;
    private Map<Integer, String> assentos;

    public Viagem(String id, String nomeMotorista, String origem, String destino, Long dataViagem, Map<Integer, String> assentos) {
        this.id = id;
        this.nomeMotorista = nomeMotorista;
        this.origem = origem;
        this.destino = destino;
        this.dataViagem = dataViagem;
        this.assentos = assentos;
    }

    public static Viagem parseViagem(ViagemDTO viagemDTO) {
        return new Viagem(viagemDTO.getId(),viagemDTO.getNomeMotorista(), viagemDTO.getOrigem(), viagemDTO.getDestino(), viagemDTO.getDataViagem().toEpochDay(), viagemDTO.getAssentos());
    }

    public ViagemDTO toViagemDTO() {
        return new ViagemDTO(
                this.getId(),
                this.getNomeMotorista(),
                this.getOrigem(), this.getDestino(),
                Instant.ofEpochMilli(this.getDataViagem()).atZone(ZoneId.systemDefault()).toLocalDate(),
                this.getAssentos());
    }
}
