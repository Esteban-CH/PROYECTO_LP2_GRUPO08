package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

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
        model.addAttribute("user", session.getAttribute("user"));
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
