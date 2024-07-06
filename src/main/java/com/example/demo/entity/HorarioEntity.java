package com.example.demo.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_horario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HorarioEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "horario_id")
	private Integer horarioId;
	
	@ManyToOne
	@JoinColumn(name = "pelicula_id", nullable = false)
	private PeliculaEntity pelicula;
	
	@ManyToOne
	@JoinColumn(name = "sala_id", nullable = false)
	private SalaEntity sala;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_hora_funcion", nullable = false)
	private Date fechaHoraFuncion;
}
