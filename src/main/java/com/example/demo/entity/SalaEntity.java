package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_sala")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SalaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sala_id")
	private Integer salaId;
	
	@Column(name = "numero_sala", nullable = false)
	private String numeroSala;
	
	@Column(name = "capacidad_sala", nullable = false)
	private Integer capadidadSala;
}
