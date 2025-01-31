package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.PeliculaEntity;
import com.example.demo.service.PeliculaService;

@Controller
public class PeliculaController {

	@Autowired
	private PeliculaService peliculaService;
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
	
	@GetMapping("/listar_pelicula")
    public String listarPeliculas(Model model) {
        List<PeliculaEntity> pelicula = peliculaService.listarPeliculas();
        model.addAttribute("pelicula", pelicula);
        return "peliculas/listar";
    }
	
	//REGISTRAR
	@GetMapping("/registrar_pelicula")
    public String mostrarFormularioNuevaPelicula(Model model) {
        model.addAttribute("pelicula", new PeliculaEntity());
        return "peliculas/formularioPelicula";
    }
	
	@PostMapping("/registrar_pelicula")
    public String guardarPelicula(@ModelAttribute PeliculaEntity pelicula, Model model, @RequestParam("imagen") MultipartFile foto) {
		peliculaService.insertarPelicula(pelicula, foto);
        return "redirect:/listar_pelicula";
    }
	
	//EDITAR
	@GetMapping("/editar_pelicula/{id}")
    public String mostrarFormularioEditarPelicula(@PathVariable("id") Integer id, Model model) {
        PeliculaEntity pelicula = peliculaService.obtenerPeliculaPorId(id);
        if (pelicula == null) {
            throw new IllegalArgumentException("Id de película no válido: " + id);
        }
        model.addAttribute("pelicula", pelicula);
        return "peliculas/editarPelicula";
    }

    @PostMapping("/editar_pelicula/{id}")
    public String guardarCambiosPelicula(@PathVariable("id") Integer id,
                                         @ModelAttribute("pelicula") PeliculaEntity pelicula,
                                         @RequestParam("imagen") MultipartFile imagen) {
        pelicula.setPeliculaId(id);
        peliculaService.actualizarPelicula(pelicula, imagen);
        return "redirect:/listar_pelicula";
    }

    //ELIMINAR
    @GetMapping("/eliminar_pelicula/{id}")
    public String eliminarPelicula(@PathVariable("id") Integer id) {
    	peliculaService.eliminarPelicula(id);
        return "redirect:/listar_pelicula";
    }
}
