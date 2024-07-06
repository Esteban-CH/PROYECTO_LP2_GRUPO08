package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.BoletaEntity;

@Repository
public interface BoletaRepository extends JpaRepository<BoletaEntity, Integer>{

}
