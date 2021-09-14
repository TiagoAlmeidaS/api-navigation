package com.example.apinavigation.reserva.repository;

import com.example.apinavigation.reserva.entity.Reserva;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends MongoRepository<Reserva, String> {

    Reserva findByViagemId(String viagemId);


}
