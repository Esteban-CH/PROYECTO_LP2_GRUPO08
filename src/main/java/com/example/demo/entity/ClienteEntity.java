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
@Table(name = "tb_cliente")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ClienteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cliente_id")
	private Integer clienteId;
	
	@Column(name = "nombre_cliente" , nullable = false)
	private String nombreCliente;
	
	@Column(name = "apellido_cliente" , nullable = false)
	private String apellidoCliente;
	
	@Column(name = "email_cliente", nullable = false, unique = true)
	private String emailCliente;
	
	@Column(name = "telefono_cliente" , nullable = false)
	private String telefonoCliente;
	
	@Column(name = "password_cliente", nullable = false)
	private String passwordCliente;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_creacion_cliente", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date fchaCreacionCliente;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_actualizacion_cliente", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private Date fchaActualizacionCliente;
}
