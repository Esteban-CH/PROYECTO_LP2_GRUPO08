package com.example.demo.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.ClienteEntity;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.service.ClienteService;

@Controller
public class ClienteController {

	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping("/listar_cliente")
    public String listarClientes(Model model) {
        //model.addAttribute("cliente", repository.findAll());
        List<ClienteEntity> cliente = clienteService.listarClientes();
        model.addAttribute("cliente", cliente);
		return "/clientes/listar";
    }
	
	@GetMapping("/registrar_cliente")
    public String mostrarFormularioNuevoCliente(Model model) {
        model.addAttribute("cliente", new ClienteEntity());
        return "/clientes/registrar";
    }
	
	@PostMapping("/registrar_cliente")
    public String guardarCliente(@ModelAttribute ClienteEntity cliente) {
        //cliente.setFchaCreacionCliente(new Date());
        //cliente.setFchaActualizacionCliente(new Date());
        //repository.save(cliente);
        clienteService.insertarCliente(cliente);
		return "redirect:/listar_cliente";
    }
	
	@GetMapping("/editar_cliente/{id}")
	public String mostrarVistaEditarCliente(@PathVariable("id")Integer id, Model model) {
		//ClienteEntity cliente = repository.findById(id).get();
		ClienteEntity cliente = clienteService.obtenerClientePorId(id);
		model.addAttribute("cliente", cliente);
		return("/clientes/editar");
	}
	
	@PostMapping("/editar_cliente/{id}")
	public String editarCLiente(@ModelAttribute ClienteEntity cliente, Model model, @PathVariable("id")Integer id) {
		cliente.setClienteId(id);
	    // Actualizar la contraseña solo si se proporciona una nueva
	    /*
		if (cliente.getPasswordCliente() != null && !cliente.getPasswordCliente().isEmpty()) {
	        cliente.setPasswordCliente(cliente.getPasswordCliente());
	    }
	    cliente.setFchaCreacionCliente(cliente.getFchaCreacionCliente());
	    cliente.setFchaActualizacionCliente(new Date());
	    */
	    //repository.save(cliente);
	    clienteService.actualizarCliente(cliente);
		return("redirect:/listar_cliente");
	}
	
	@GetMapping("/detalle_cliente/{id}")
	public String verCliente(@PathVariable("id")Integer id, Model model) {
		//ClienteEntity cliente = repository.findById(id).get();
		ClienteEntity cliente = clienteService.obtenerClientePorId(id);
		model.addAttribute("cliente", cliente);
		return("/clientes/detalle");
	}
	
	@GetMapping("/eliminar_cliente/{id}")
	public String eliminarClientes(@PathVariable("id")Integer id) {
		//repository.deleteById(id);
		clienteService.eliminarCliente(id);
		return("redirect:/listar_cliente");
	}
	
	@GetMapping("/verificar_correo")
	public ResponseEntity<Map<String, Boolean>> verificarCorreo(@RequestParam String email) {
	    boolean exists = clienteService.existsByEmail(email);
	    Map<String, Boolean> response = new HashMap<>();
	    response.put("exists", exists);
	    return ResponseEntity.ok(response);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
    public String handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        // Aquí puedes definir el mensaje que quieres devolver a la vista
        return "El correo electrónico ya está en uso.";
    }
	
}
