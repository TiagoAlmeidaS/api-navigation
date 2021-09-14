package com.example.apinavigation.reserva.service;

import com.example.apinavigation.reserva.entity.Reserva;
import com.example.apinavigation.reserva.repository.ReservaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaService {

    private ReservaRepository repository;

    @Autowired
    public ReservaService(ReservaRepository repository) {
        this.repository = repository;
    }

    public Reserva save(Reserva reserva) {
       return repository.save(reserva);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

    public Reserva findByViagemId(String viagemId) {
        return repository.findByViagemId(viagemId);
    }
}
