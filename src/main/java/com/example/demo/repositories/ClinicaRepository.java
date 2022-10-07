package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Clinica;

@Repository
public interface ClinicaRepository extends JpaRepository<Clinica, Long> {



}
