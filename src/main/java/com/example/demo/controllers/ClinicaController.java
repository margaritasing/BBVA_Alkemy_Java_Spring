package com.example.demo.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.example.demo.dto.ClinicaDto;
import com.example.demo.dto.MedicoDto;
import com.example.demo.dto.PacienteDto;
import com.example.demo.entities.*;
import com.example.demo.model.PacienteConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import com.example.demo.services.imple.ClinicaService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/clinica")
public class ClinicaController {
	
	@Autowired
	private ClinicaService service;


	@Autowired
	private PacienteConverter pacienteConverter;

	@Autowired
	private MessageSource messageSource;

	
	@ApiOperation(value = "Endpoint de prueba para saber que funciona las API", response = String.class, tags = "Endpoint prueba API")
	@GetMapping("/hello")
	public String hello() {
		return "Funciona el controlador";
	}



	@ApiOperation(value = "Endpoint para poder agregar una clinica a la lista de clinicas", response = Clinica.class, tags = "Agregar clinica")
	@PostMapping("/add/clinica")
    public Clinica cargarClinica(@RequestBody Clinica clinica) {

		return service.guardarClinica(clinica);
    }


	@ApiOperation(value = "Endpoint para poder agregar un medico a la lista de medicos", response = ClinicaDto.class, tags = "Agregar clinica usando DTO")
	@PostMapping("/add/clinicadto")
	public ResponseEntity<ClinicaDto> addCLinica(@RequestBody ClinicaDto clinicaDto) {
		clinicaDto = service.addClinica(clinicaDto);
		ResponseEntity<ClinicaDto> responseEntity = new ResponseEntity<>(clinicaDto, HttpStatus.CREATED);
		return responseEntity;
	}




	@ApiOperation(value = "Endpoint para poder obtener todas los clínicas", response = Clinica.class, tags = "Todas los clínicas")
	@GetMapping("/get/clinicas")
	public ResponseEntity<List<Clinica>> obtenerClinicas () {
		return ResponseEntity.ok().body(service.obtenerClinicas());
	}


	@ApiOperation(value = "Endpoint para eliminar una clínica", tags = "Eliminar clínica")
	@DeleteMapping("/delete/clinica/{clinicaId}")
	public ResponseEntity<String> eliminarClinica (@PathVariable("clinicaId") Long clinicaId) {
		try {
			service.eliminarClinica(clinicaId);
			String idExist = messageSource.getMessage("success.delete", new String[]{"Clinica"}, Locale.US);
			return ResponseEntity.ok().body(idExist);
		}
		catch (Exception exception) {
			String idNotExist = messageSource.getMessage("bad.id", new String[]{"Clinica"}, Locale.US);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(idNotExist);
		}
	}
  


}
