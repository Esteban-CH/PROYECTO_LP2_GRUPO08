package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.HorarioEntity;
import com.example.demo.repository.HorarioRepository;
import com.example.demo.service.HorarioService;

public class HorarioServiceImpl implements HorarioService{

	@Autowired
    private HorarioRepository horarioRepository;

    @Override
    public HorarioEntity guardarHorario(HorarioEntity horario) {
        return horarioRepository.save(horario);
    }

    @Override
    public List<HorarioEntity> listarHorarios() {
        return horarioRepository.findAll();
    }

    @Override
    public HorarioEntity buscarHorarioPorId(Integer id) {
        return horarioRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarHorario(Integer id) {
        horarioRepository.deleteById(id);
    }
    
    @Override
    public List<HorarioEntity> buscarHorariosPorPelicula(Integer peliculaId) {
        return horarioRepository.findByPelicula_PeliculaId(peliculaId);
    }

    @Override
    public HorarioEntity buscarHorarioPorPeliculaYSala(Integer peliculaId, Integer salaId) {
        return horarioRepository.findByPelicula_PeliculaIdAndSala_SalaId(peliculaId, salaId);
    }
}
