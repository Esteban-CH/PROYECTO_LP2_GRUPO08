package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.HorarioEntity;

@Repository
public interface HorarioRepository extends JpaRepository<HorarioEntity, Integer>{
	
	List<HorarioEntity> findByPelicula_PeliculaId(Integer peliculaId);
    HorarioEntity findByPelicula_PeliculaIdAndSala_SalaId(Integer peliculaId, Integer salaId);
}
