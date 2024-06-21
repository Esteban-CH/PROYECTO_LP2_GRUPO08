package com.example.demo.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.EmpleadoEntity;
import com.example.demo.repository.EmpleadoRepository;

@Controller
public class EmpleadoController {

	@Autowired
	private EmpleadoRepository repository;
	
	@GetMapping("/listar_empleado")
	public String listarEmpleados(Model model) {
		model.addAttribute("empleados", repository.findAll());
		return "empleados/listar";
	}
	
	@GetMapping("/registrar_empleado")
	public String mostrarVistaNuevoEmpleado(Model model) {
		model.addAttribute("empleados", new EmpleadoEntity());
		return("empleados/registrar");
	}
	
	@PostMapping("/registrar_empleado")
	public String registrarEmpleado(EmpleadoEntity empleado) {
		empleado.setFchaCreacionEmpleado(new Date());
		empleado.setFchaActualizacionEmpleado(new Date());
		repository.save(empleado);
		return("redirect:/listar_empleado");
	}
	
	@GetMapping("eliminar_empleado/{id}")
	public String eliminarEmpleado(@PathVariable("id")Integer id) {
		repository.deleteById(id);
		return("redirect:/listar_empleado");
	}

}
