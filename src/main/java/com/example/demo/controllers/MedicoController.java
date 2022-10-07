package com.example.demo.controllers;

import com.example.demo.dto.MedicoDto;
import com.example.demo.entities.DiaSemanaEnum;
import com.example.demo.entities.Medico;

import com.example.demo.services.imple.MedicoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/clinica")
public class MedicoController {

    @Autowired
    private MedicoService service;

    @Autowired
    private MessageSource messageSource;



    /*End Point de Medico*/
    @ApiOperation(value = "Endpoint para poder agregar un medico a la lista de medicos", response = Medico.class, tags = "Agregar medico")
    @PostMapping("/add/medico")
    public Medico cargarMedico(@RequestBody Medico medico) {
        return service.guardarMedico(medico);
    }

    @ApiOperation(value = "Endpoint para poder agregar un medico a la lista de medicos", response = Medico.class, tags = "Agregar medico usando DTO")
    @PostMapping("/add/medicodto")
    public ResponseEntity<MedicoDto> addMedico(@RequestBody MedicoDto medicoDto) {
        medicoDto = service.addMedico(medicoDto);
        ResponseEntity<MedicoDto> responseEntity = new ResponseEntity<>(medicoDto, HttpStatus.CREATED);
        return responseEntity;
    }

    @ApiOperation(value = "Endpoint para poder obtener una lista de medicos con los que se atendio un paciente", response = Medico.class, tags = "Historial de medicos de un paciente")
    @GetMapping("/get/historialAtencionesPaciente/{pacienteId}")
    public ResponseEntity<?> historialAtencionesPaciente(@PathVariable("pacienteId") Long pacienteId) {
        try {
            List<Medico> medicos = service.obtenerMedicosPaciente(pacienteId);
            if (medicos.isEmpty()){
                throw new Exception("El paciente no se ha atendido con ningun Dr. aun");
            }
            return ResponseEntity.status(HttpStatus.OK).body(medicos);
        } catch (Exception ex) {
            String noFound = messageSource.getMessage("no.records.found.paciente", new String[]{"Paciente"}, Locale.US);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(noFound);
        }
    }

    @ApiOperation(value = "Endpoint para poder obtener los medicos que trabajan en un dia de la semana", response = Medico.class, tags = "Medicos que trabajan un dia especifico de la semana")
    @GetMapping("/get/medicosQueTrabajanDia/{diaSemana}")
    public ResponseEntity<?> medicosQueTrabajanDia(@PathVariable("diaSemana") DiaSemanaEnum diaSemana) {
        try {
            List<Medico> medicos = service.obtenerMedicosQueTrabajanDiaSemana(diaSemana);
            if (medicos.isEmpty()){
                throw new Exception("No hay medicos que trabajen en ese dia");
            }
            return ResponseEntity.status(HttpStatus.OK).body(medicos);
        } catch (Exception e) {
            String noFound = messageSource.getMessage("no.records.found.medico", new String[]{"Medico"}, Locale.US);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(noFound);
        }
    }

    @ApiOperation(value = "Endpoint para poder obtener los medicos que trabajan los fines de semana y o feriados", response = Medico.class, tags = "Medicos que trabajan dias no laborables")
    @GetMapping("/get/medicosQueTrabajanDiasNoLaborables")
    public ResponseEntity<List<Medico>> medicosQueTrabajanDiasNoLaborables() {
        ResponseEntity responseEntity;
        try {
            List<Medico> medicos = service.medicosQueTrabajanDiasNoLaborables();
            if (medicos.size() == 0) throw new Exception();
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(medicos);
        } catch (Exception e) {
            String noFound = messageSource.getMessage("no.records.found.medico", new String[]{"Medico"}, Locale.US);
            responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).body(noFound);
        }
        return responseEntity;
    }


    @ApiOperation(value = "Endpoint para poder obtener los medicos que trabajan en una clinica en particular", response = Medico.class, tags = "Medicos que trabajan en una clinica")
    @GetMapping("/get/medicosPorClinicaDiaNoLaborable/{clinicaId}")
    public ResponseEntity<List<Medico>> medicosQueTrabajanDiasNoLaborables(@PathVariable("clinicaId") Long clinicaId) {
        ResponseEntity response;
        try {
            List<Medico> medicos = service.medicosQueTrabajanDiasNoLaborablesClinica(clinicaId);
            if (medicos.size() == 0) throw new Exception();
            response = ResponseEntity.status(HttpStatus.OK).body(medicos);
        } catch (Exception exception){
            String noFound = messageSource.getMessage("no.records.found.medico", new String[]{"Medico"}, Locale.US);		;
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(noFound);
        }
        return response;
    }

    @ApiOperation(value = "Endpoint para poder obtener todos los médicos", response = Medico.class, tags = "Todos los médicos")
    @GetMapping("/get/medicos")
    public ResponseEntity<List<Medico>> obtenerMedicos () {
        return ResponseEntity.ok().body(service.obtenerMedicos());
    }






}
