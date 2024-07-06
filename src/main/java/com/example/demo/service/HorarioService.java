package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.HorarioEntity;

public interface HorarioService {

	HorarioEntity guardarHorario(HorarioEntity horario);
    List<HorarioEntity> listarHorarios();
    HorarioEntity buscarHorarioPorId(Integer id);
    void eliminarHorario(Integer id);
    
    List<HorarioEntity> buscarHorariosPorPelicula(Integer peliculaId);
    HorarioEntity buscarHorarioPorPeliculaYSala(Integer peliculaId, Integer salaId);
}
