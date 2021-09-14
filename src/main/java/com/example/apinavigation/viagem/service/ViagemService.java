package com.example.apinavigation.viagem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype. Service;

import java.util.List;
import java.util.Optional;

import com.example.apinavigation.viagem.entity.Viagem;
import com.example.apinavigation.viagem.repository.ViagemRepository;

@Service
public class ViagemService {

    private ViagemRepository repository;

    @Autowired
    public ViagemService(ViagemRepository repository) {
        this.repository = repository;
    }

    public Optional<Viagem> findById(String id) {
        return repository.findById(id);
    }

    public Optional<Viagem> findByNomeMotoristaAndDataViagemAndOrigemAndDestino(Viagem viagem) {
        return repository.findByNomeMotoristaAndDataViagemAndOrigemAndDestino(viagem.getNomeMotorista(),
                viagem.getDataViagem(), viagem.getOrigem(), viagem.getDestino());
    }

    public Viagem save(Viagem viagem) {
        return repository.save(viagem);
    }

    public List<Viagem> findAll() {
        return repository.findAll();
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

}
