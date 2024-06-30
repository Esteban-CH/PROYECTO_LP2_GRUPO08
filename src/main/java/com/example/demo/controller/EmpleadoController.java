package com.example.demo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.EmpleadoEntity;
import com.example.demo.repository.EmpleadoRepository;
import com.example.demo.service.EmpleadoService;

@Controller
public class EmpleadoController {

	//@Autowired
	//private EmpleadoRepository repository;
	
	@Autowired
	private EmpleadoService empleadoService;
	
	@GetMapping("/listar_empleado")
	public String listarEmpleados(Model model) {
		//model.addAttribute("empleado", repository.findAll());
		List<EmpleadoEntity> empleado = empleadoService.listarEmpleados(); 
		model.addAttribute("empleado", empleado);
		return "empleados/listar";
	}
	
	@GetMapping("/registrar_empleado")
	public String mostrarVistaNuevoEmpleado(Model model) {
		model.addAttribute("empleado", new EmpleadoEntity());
		return("empleados/registrar");
	}
	
	@PostMapping("/registrar_empleado")
	public String registrarEmpleado(EmpleadoEntity empleado) {
		//empleado.setFchaCreacionEmpleado(new Date());
		//empleado.setFchaActualizacionEmpleado(new Date());
		//repository.save(empleado);
		empleadoService.insertarEmpleado(empleado);
		return("redirect:/listar_empleado");
	}
	
	@GetMapping("/editar_empleado/{id}")
	public String mostrarVistaEditarEmpleado(@PathVariable("id")Integer id, Model model) {
		//EmpleadoEntity empleado = repository.findById(id).get();
		EmpleadoEntity empleado = empleadoService.obtenerEmpleadoPorId(id);
		model.addAttribute("empleado", empleado);
		return("/empleados/editar");
	}
	
	@PostMapping("/editar_empleado/{id}")
	public String editarEmpleado(@ModelAttribute EmpleadoEntity empleado, @PathVariable("id")Integer id, Model model) {
		empleado.setEmpleadoId(id);
		// Actualizar la contraseña solo si se proporciona una nueva
        /*
		if (empleado.getPasswordEmpleado() != null && !empleado.getPasswordEmpleado().isEmpty()) {
            empleado.setPasswordEmpleado(empleado.getPasswordEmpleado());
        }
		*/
		//empleado.setFchaCreacionEmpleado(empleado.getFchaCreacionEmpleado());
		//empleado.setFchaActualizacionEmpleado(new Date());
		//repository.save(empleado);
		empleadoService.actualizarEmpleado(empleado);
		return("redirect:/listar_empleado");
	}
	
	@GetMapping("/detalle_empleado/{id}")
	public String verEmpleado(Model model, @PathVariable("id")Integer id) {
		//EmpleadoEntity empleado = repository.findById(id).get();
		EmpleadoEntity empleado = empleadoService.obtenerEmpleadoPorId(id);
		model.addAttribute("empleado", empleado);
		return ("/empleados/detalle");
	}
	
	@GetMapping("eliminar_empleado/{id}")
	public String eliminarEmpleado(@PathVariable("id")Integer id) {
		//repository.deleteById(id);
		empleadoService.eliminarEmpleado(id);
		return("redirect:/listar_empleado");
	}

}
