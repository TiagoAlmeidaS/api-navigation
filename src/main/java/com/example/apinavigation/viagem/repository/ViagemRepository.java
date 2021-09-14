package com.example.apinavigation.viagem.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.example.apinavigation.viagem.entity.Viagem;

@Repository
public interface ViagemRepository extends MongoRepository<Viagem, String> {

    Optional<Viagem> findByNomeMotoristaAndDataViagemAndOrigemAndDestino(String nomeMotorista, Long dataViagem, String origem, String destino);
}
