package com.example.demo.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.PeliculaEntity;
import com.example.demo.repository.PeliculaRepository;
import com.example.demo.service.PeliculaService;
import com.example.demo.utils.Utilitarios;

@Service
public class PeliculaServiceImpl implements PeliculaService{
	
	@Autowired
	private PeliculaRepository peliculaRepository;
	
	@Override
	public PeliculaEntity insertarPelicula(PeliculaEntity pelicula, MultipartFile foto) {
		///guardar foto
		String nombreFoto = Utilitarios.guardarImagen(foto); 	
		pelicula.setUrlImagenPelicula(nombreFoto);
		//Registre la fecha y hora del registro y modificacion
		pelicula.setFchaCreacionPelicula(new Date());
		pelicula.setFchaActualizacionPelicula(new Date());
		return peliculaRepository.save(pelicula);
	}

	@Override
	public List<PeliculaEntity> listarPeliculas() {
		return peliculaRepository.findAll();
	}

	@Override
	public PeliculaEntity obtenerPeliculaPorId(Integer id) {
		return peliculaRepository.findById(id).get();
	}

	@Override
    public PeliculaEntity actualizarPelicula(PeliculaEntity pelicula, MultipartFile file) {
        if (pelicula.getPeliculaId() == null) {
            throw new IllegalArgumentException("El ID de la película no puede ser nulo");
        }

        PeliculaEntity peliculaBuscarId = obtenerPeliculaPorId(pelicula.getPeliculaId());
        if (peliculaBuscarId != null) {
            peliculaBuscarId.setTituloPelicula(pelicula.getTituloPelicula());
            peliculaBuscarId.setDescripcionPelicula(pelicula.getDescripcionPelicula());
            peliculaBuscarId.setDirectorPelicula(pelicula.getDirectorPelicula());
            peliculaBuscarId.setFechaEstrenoPelicula(pelicula.getFechaEstrenoPelicula());
            peliculaBuscarId.setDuracionPelicula(pelicula.getDuracionPelicula());
            peliculaBuscarId.setFchaActualizacionPelicula(new Date());
            
            if (file != null && !file.isEmpty()) {
                // Aquí se debe agregar la lógica para manejar la imagen, por ejemplo, guardar la imagen y establecer la URL en peliculaBuscarId
                // peliculaBuscarId.setUrlImagenPelicula(urlDeLaImagenGuardada);
            }

            return peliculaRepository.save(peliculaBuscarId);
        }
        return null;
    }

	@Override
	public void eliminarPelicula(Integer id) {
		peliculaRepository.deleteById(id);
		
	}

}
