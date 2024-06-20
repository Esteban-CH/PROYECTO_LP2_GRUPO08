package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.demo.entity.ClienteEntity;
import com.example.demo.entity.EmpleadoEntity;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.EmpleadoRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EmpleadoRepository empleadoRepository;

	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@PostMapping("/login")
    public String authenticateUser(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
        Optional<ClienteEntity> clienteOpt = clienteRepository.findByEmailCliente(email);
        Optional<EmpleadoEntity> empleadoOpt = empleadoRepository.findByEmailEmpleado(email);

        if (clienteOpt.isPresent() && clienteOpt.get().getPasswordCliente().equals(password)) {
            session.setAttribute("user", clienteOpt.get());
            session.setAttribute("role", "CLIENTE");
            return "redirect:/cliente";
        } else if (empleadoOpt.isPresent() && empleadoOpt.get().getPasswordEmpleado().equals(password)) {
            session.setAttribute("user", empleadoOpt.get());
            session.setAttribute("role", "ADMIN");
            return "redirect:/admin";
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }
    }
	
	@PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
	
}
