package com.example.demo.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.demo.entity.EmpleadoEntity;
import com.example.demo.repository.EmpleadoRepository;
import com.example.demo.service.EmpleadoService;

@Service
public class EmpleadoServiceImpl implements EmpleadoService{

	@Autowired
	private EmpleadoRepository empleadoRepository;

	@Override
	public EmpleadoEntity insertarEmpleado(EmpleadoEntity empleado) {
		empleado.setFchaCreacionEmpleado(new Date());
		empleado.setFchaActualizacionEmpleado(new Date());
		return empleadoRepository.save(empleado);
	}

	@Override
	public List<EmpleadoEntity> listarEmpleados() {
		return empleadoRepository.findAll();
	}

	@Override
	public EmpleadoEntity obtenerEmpleadoPorId(Integer id) {
		return empleadoRepository.findById(id).get();
	}

	@Override
	public EmpleadoEntity actualizarEmpleado(EmpleadoEntity empleado) {
		EmpleadoEntity empleadoBuscarId = obtenerEmpleadoPorId(empleado.getEmpleadoId());
		if(empleadoBuscarId != null) {
			empleadoBuscarId.setNombreEmpleado(empleado.getNombreEmpleado());
			empleadoBuscarId.setApellidoEmpleado(empleado.getApellidoEmpleado());
			empleadoBuscarId.setEmailEmpleado(empleado.getEmailEmpleado());
			empleadoBuscarId.setPasswordEmpleado(empleado.getPasswordEmpleado());
			empleadoBuscarId.setTelefonoEmpleado(empleado.getTelefonoEmpleado());
			empleadoBuscarId.setFchaActualizacionEmpleado(new Date());
			empleado.setFchaCreacionEmpleado(empleado.getFchaCreacionEmpleado());
			return empleadoRepository.save(empleadoBuscarId);
		}
		return null;
	}

	@Override
	public void eliminarEmpleado(Integer id) {
		empleadoRepository.deleteById(id);
		
	}
	
	

}
