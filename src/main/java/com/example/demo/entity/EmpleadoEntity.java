package com.example.demo.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_empleado")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class EmpleadoEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "empleado_id")
    private Integer empleadoId;

	@Column(name = "nombre_empleado", nullable = false)
    private String nombreEmpleado;
	
	@Column(name = "apellido_empleado", nullable = false)
    private String apellidoEmpleado;
	
	@Column(name = "email_empleado", nullable = false, unique = true)
    private String emailEmpleado;
	
	@Column(name = "telefono_empleado", nullable = false)
    private String telefonoEmpleado;
	
	@Column(name = "password_empleado", nullable = false)
	private String passwordEmpleado;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_creacion_empleado", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date fchaCreacionEmpleado;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_actualizacion_empleado", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private Date fchaActualizacionEmpleado;
}
