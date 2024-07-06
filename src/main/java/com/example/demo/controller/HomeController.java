package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.ClienteEntity;
import com.example.demo.entity.PeliculaEntity;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.EmpleadoRepository;
import com.example.demo.repository.PeliculaRepository;
import com.example.demo.service.ClienteService;
import com.example.demo.service.PeliculaService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	private PeliculaService peliculaService;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	@Autowired
	private PeliculaRepository peliculaRepository;
	
	@Autowired
    private ClienteService clienteService;

	@GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
	
	@GetMapping("/admin")
	public String admin(HttpSession session, Model model) {
	    if (!"ADMIN".equals(session.getAttribute("role"))) {
	        return "redirect:/login";
	    }

	    long numClientes = clienteRepository.count();
	    long numEmpleados = empleadoRepository.count();
	    long numPeliculas = peliculaRepository.count();

	    model.addAttribute("user", session.getAttribute("user"));
	    model.addAttribute("numClientes", numClientes);
	    model.addAttribute("numEmpleados", numEmpleados);
	    model.addAttribute("numPeliculas", numPeliculas);

	    return "admin";
	}
	
	 @GetMapping("/cliente")
	    public String cliente(HttpSession session, Model model) {
	        if (!"CLIENTE".equals(session.getAttribute("role"))) {
	            return "redirect:/login";
	        }
	        model.addAttribute("user", session.getAttribute("user"));
	        List<PeliculaEntity> peliculas = peliculaService.listarPeliculas();
	        model.addAttribute("peliculas", peliculas);
	        return "cliente";
	    }
	 
	 @GetMapping("/registrar_usuario")
	    public String mostrarFormularioNuevoCliente(Model model) {
	        model.addAttribute("cliente", new ClienteEntity());
	        return "login/registrar_usuario";
	    }

	    @PostMapping("/registrar_usuario")
	    public String guardarCliente(@ModelAttribute ClienteEntity cliente) {
	        clienteService.insertarCliente(cliente);
	        return "redirect:/login";
	    }
}
