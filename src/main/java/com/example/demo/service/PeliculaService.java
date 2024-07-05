package com.example.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.PeliculaEntity;

public interface PeliculaService {

	PeliculaEntity insertarPelicula(PeliculaEntity pelicula, MultipartFile file);
	List<PeliculaEntity> listarPeliculas();
	PeliculaEntity obtenerPeliculaPorId(Integer id);
    PeliculaEntity actualizarPelicula(PeliculaEntity pelicula, MultipartFile file);
    void eliminarPelicula(Integer id);
}
