package com.example.demo.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.ClienteEntity;
import com.example.demo.repository.ClienteRepository;

@Controller
public class ClienteController {

	@Autowired
	private ClienteRepository repository;
	
	@GetMapping("/clientes")
    public String listarClientes(Model model) {
        model.addAttribute("clientes", repository.findAll());
        return "listadoCliente";
    }
	
	@GetMapping("/nuevo")
    public String mostrarFormularioNuevoCliente(Model model) {
        model.addAttribute("cliente", new ClienteEntity());
        return "formularioCliente";
    }
	
	@PostMapping("/nuevo")
    public String guardarCliente(@ModelAttribute ClienteEntity cliente) {
        cliente.setFchaCreacionCliente(new Date());
        cliente.setFchaActualizacionCliente(new Date());
        repository.save(cliente);
        return "redirect:/clientes";
    }
	
	@GetMapping("/eliminar/{id}")
	public String eliminarClientes(@PathVariable("id")Integer id) {
		repository.deleteById(id);
		return("redirect:/clientes");
	}
}
