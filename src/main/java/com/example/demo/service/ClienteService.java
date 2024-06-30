package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.ClienteEntity;

public interface ClienteService {

	ClienteEntity insertarCliente(ClienteEntity cliente);
	
	List<ClienteEntity> listarClientes();

	ClienteEntity obtenerClientePorId(Integer id);

	ClienteEntity actualizarCliente(ClienteEntity cliente);

    void eliminarCliente(Integer id);
	
}
