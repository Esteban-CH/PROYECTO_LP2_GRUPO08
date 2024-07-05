package com.example.demo.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_pelicula")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PeliculaEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pelicula_id")
    private Integer peliculaId;

	@Column(name = "titulo_pelicula")
    private String tituloPelicula;
	
	@Column(name = "descripcipn_pelicula", columnDefinition = "TEXT")
    private String descripcionPelicula;
	
	@Column(name = "director_pelicula")
    private String directorPelicula;
	
	@Column(name = "fecha_estreno_pelicula")
	@Temporal(TemporalType.DATE)
    private Date fechaEstrenoPelicula;
	
	@Column(name = "duracion_pelicula")
    private int duracionPelicula;
	
	@Column(name = "url_imagen", columnDefinition = "TEXT")
    private String urlImagenPelicula;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_creacion_pelicula", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date fchaCreacionPelicula;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_actualizacion_pelicula", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private Date fchaActualizacionPelicula;
}
