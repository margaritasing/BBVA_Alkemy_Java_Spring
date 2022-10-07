package com.example.demo.services;


import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import com.example.demo.dto.ClinicaDto;
import com.example.demo.entities.*;
import com.example.demo.services.imple.ClinicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repositories.ClinicaRepository;

import javax.persistence.EntityNotFoundException;

@Service
public class ClinicaServiceImpl implements ClinicaService {
	
	@Autowired
	private ClinicaRepository clinicaRepository;
	

	/**
	 * Usado para crear una nueva clinica en la tabla de clinicas
	 * @param nuevaClinica
	 * @return
	 */
	public Clinica guardarClinica(Clinica nuevaClinica) {
		return clinicaRepository.save(nuevaClinica);

	}

	/**
	 * Usado para obtener una lista de clinicas de la tabla de clinicas
	 * @return Lista de clinicas
	 */
	public List<Clinica> obtenerClinicas() {
		return clinicaRepository.findAll();
	}


	public Clinica getClinicaById(long clinicaId){
		Optional<Clinica> clinica = obtenerClinicas().stream()
				.filter(clinica1 -> clinica1.getClinicaId() == clinicaId)
				.findFirst();

		return clinica.isPresent() ? clinica.get() : null;
	}


	@Override
	public Clinica getCLinicaById(Long clinicaId) {
		return clinicaRepository.findById(clinicaId).orElse(null);

	}

	@Override
	public void eliminarClinica(Long idClinica) {
		if (clinicaRepository.existsById(idClinica)) {
			clinicaRepository.deleteById(idClinica);
		}
		else {
			throw new EntityNotFoundException();
		}
	}

	@Override
	public ClinicaDto addClinica(ClinicaDto clinicaDto) {
		return null;
	}


}
