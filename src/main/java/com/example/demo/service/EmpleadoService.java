package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.EmpleadoEntity;

public interface EmpleadoService {

	EmpleadoEntity insertarEmpleado(EmpleadoEntity empleado);
	
	List<EmpleadoEntity> listarEmpleados();

    EmpleadoEntity obtenerEmpleadoPorId(Integer id);

    EmpleadoEntity actualizarEmpleado(EmpleadoEntity empleado);

    void eliminarEmpleado(Integer id);
}
