package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.SalaEntity;

public interface SalaService {
	SalaEntity guardarSala(SalaEntity sala);
    List<SalaEntity> listarSalas();
    SalaEntity buscarSalaPorId(Integer id);
    void	 eliminarSala(Integer id);
}
