package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.SalaEntity;
import com.example.demo.repository.SalaRepository;
import com.example.demo.service.SalaService;

public class SalaServiceImpl  implements SalaService{

	@Autowired
    private SalaRepository salaRepository;

    @Override
    public SalaEntity guardarSala(SalaEntity sala) {
        return salaRepository.save(sala);
    }

    @Override
    public List<SalaEntity> listarSalas() {
        return salaRepository.findAll();
    }

    @Override
    public SalaEntity buscarSalaPorId(Integer id) {
        return salaRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarSala(Integer id) {
        salaRepository.deleteById(id);
    }

}
