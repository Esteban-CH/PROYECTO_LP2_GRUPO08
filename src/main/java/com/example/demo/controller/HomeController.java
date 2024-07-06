package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.EmpleadoRepository;
import com.example.demo.repository.PeliculaRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	@Autowired
	private PeliculaRepository peliculaRepository;

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

	    // Obtener el usuario y los datos relevantes (número de registros)
	    // Aquí se simulan los datos, reemplázalos con tu lógica de negocio real
	    // Supongamos que estos datos vienen de tu base de datos o repositorios
	    long numClientes = clienteRepository.count();
	    long numEmpleados = empleadoRepository.count();
	    long numPeliculas = peliculaRepository.count();

	    model.addAttribute("user", session.getAttribute("user")); // Usuario actual
	    model.addAttribute("numClientes", numClientes); // Número de clientes registrados
	    model.addAttribute("numEmpleados", numEmpleados); // Número de empleados registrados
	    model.addAttribute("numPeliculas", numPeliculas); // Número de películas registradas

	    return "admin";
	}
	
	@GetMapping("/cliente")
    public String cliente(HttpSession session, Model model) {
        if (!"CLIENTE".equals(session.getAttribute("role"))) {
            return "redirect:/login";
        }
        model.addAttribute("user", session.getAttribute("user"));
        return "cliente";
    }
}
