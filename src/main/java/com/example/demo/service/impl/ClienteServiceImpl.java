package com.example.demo.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ClienteEntity;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.service.ClienteService;
import com.example.demo.utils.Utilitarios;


@Service
public class ClienteServiceImpl implements ClienteService{

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public ClienteEntity insertarCliente(ClienteEntity cliente) {
		cliente.setFchaCreacionCliente(new Date());
		cliente.setFchaActualizacionCliente(new Date());
		String passwordHash = Utilitarios.extraerHash(cliente.getPasswordCliente());
		cliente.setPasswordCliente(passwordHash);
		return clienteRepository.save(cliente);
	}

	@Override
	public List<ClienteEntity> listarClientes() {
		return clienteRepository.findAll();
	}

	@Override
	public ClienteEntity obtenerClientePorId(Integer id) {
		return clienteRepository.findById(id).get();
	}

	@Override
	public ClienteEntity actualizarCliente(ClienteEntity cliente) {
		ClienteEntity clienteBuscarId = obtenerClientePorId(cliente.getClienteId());
		if(clienteBuscarId != null) {
			clienteBuscarId.setNombreCliente(cliente.getNombreCliente());
			clienteBuscarId.setApellidoCliente(cliente.getApellidoCliente());
			clienteBuscarId.setEmailCliente(cliente.getEmailCliente());
			clienteBuscarId.setPasswordCliente(cliente.getPasswordCliente());
			clienteBuscarId.setTelefonoCliente(cliente.getTelefonoCliente());
			clienteBuscarId.setFchaActualizacionCliente(new Date());
			cliente.setFchaCreacionCliente(cliente.getFchaCreacionCliente());
			return clienteRepository.save(clienteBuscarId);
		}
		return null; 
	}

	@Override
	public void eliminarCliente(Integer id) {
		clienteRepository.deleteById(id);
	}
	
	@Override
    public boolean existsByEmail(String email) {
        return clienteRepository.existsByEmailCliente(email);
    }

}
