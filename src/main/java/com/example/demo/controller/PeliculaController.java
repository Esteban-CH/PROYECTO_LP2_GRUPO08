package com.example.demo.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.PeliculaEntity;
import com.example.demo.repository.PeliculaRepository;

@Controller
public class PeliculaController {

	@Autowired
	private PeliculaRepository repository;
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
	
	@GetMapping("/listar_pelicula")
    public String listarPeliculas(Model model) {
        model.addAttribute("peliculas", repository.findAll());
        return "peliculas/listar";
    }
	
	//REGISTRAR
	@GetMapping("/registrar_pelicula")
    public String mostrarFormularioNuevaPelicula(Model model) {
        model.addAttribute("pelicula", new PeliculaEntity());
        return "peliculas/formularioPelicula";
    }
	
	@PostMapping("/registrar_pelicula")
    public String guardarPelicula(@ModelAttribute PeliculaEntity pelicula, @RequestAttribute("imagen") MultipartFile imagen) {
        if (!imagen.isEmpty()) {
            try {
                pelicula.setImagenPelicula(imagen.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        pelicula.setFchaCreacionPelicula(new Date());
        pelicula.setFchaActualizacionPelicula(new Date());
        repository.save(pelicula);
        return "redirect:/peliculas";
    }
	
	//EDITAR
	@GetMapping("/editar_pelicula/{id}")
	public String mostrarFormularioEditarPelicula(@PathVariable("id") Integer id, Model model) {
	    PeliculaEntity pelicula = repository.findById(id)
	            .orElseThrow(() -> new IllegalArgumentException("Id de película no válido:" + id));
	    model.addAttribute("pelicula", pelicula);
	    return "peliculas/editarPelicula";
	}

	@PostMapping("/editar_pelicula/{id}")
	public String guardarCambiosPelicula(@PathVariable("id") Integer id,
	                                     @ModelAttribute("pelicula") PeliculaEntity pelicula,
	                                     @RequestParam("imagen") MultipartFile imagen) throws IOException {
	    // Lógica para guardar los cambios de la película
	    if (!imagen.isEmpty()) {
	        pelicula.setImagenPelicula(imagen.getBytes());
	    }
	    pelicula.setFchaActualizacionPelicula(new Date());
	    pelicula.setFchaCreacionPelicula(new Date());
	    repository.save(pelicula);
	    return "redirect:/peliculas";
	}

    //ELIMINAR
    @GetMapping("/eliminar_pelicula/{id}")
    public String eliminarPelicula(@PathVariable("id") Integer id) {
        PeliculaEntity pelicula = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id de película no válido:" + id));
        repository.delete(pelicula);
        return "redirect:/listar_pelicula";
    }
}
