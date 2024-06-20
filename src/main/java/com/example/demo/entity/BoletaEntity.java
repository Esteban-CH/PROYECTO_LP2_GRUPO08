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
@Table(name = "tb_boleta")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BoletaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "boleta_id")
	private Integer boletaId;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false)
	private ClienteEntity cliente;
	
	@ManyToOne
	@JoinColumn(name = "horario_id", nullable = false)
	private HorarioEntity horario;
	
	@Column(name = "cantidad_boleta")
	private Integer cantidadBoleta;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_boleta", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date fechaBoleta;
}
