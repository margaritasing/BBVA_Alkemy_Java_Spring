package com.example.demo.controllers;

import com.example.demo.dto.PacienteDto;
import com.example.demo.entities.CambioTurno;
import com.example.demo.entities.Paciente;
import com.example.demo.model.PacienteConverter;
import com.example.demo.services.imple.PacienteService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/clinica")
public class PacienteController {

    @Autowired
    private PacienteService service;

    @Autowired
    private PacienteConverter pacienteConverter;

    @Autowired
    private MessageSource messageSource;

    @InitBinder
    public void initBinder(WebDataBinder binder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @ApiOperation(value = "Endpoint para poder agregar un paciente a la lista de pacientes", response = Paciente.class, tags = "Agregar paciente")
    @PostMapping("/add/paciente")
    public Paciente cargarPaciente(@RequestBody Paciente paciente) {
        return service.guardarPaciente(paciente);
    }

    /*End Point con Model Mapper*/
    @ApiOperation(value = "Endpoint para poder agregar un paciente a la lista de pacientes", response = PacienteDto.class, tags = "Agregar paciente con ModelMapper")
    @PostMapping("/add/pacienteDto")
    public ResponseEntity<PacienteDto> savePaciente(@RequestBody PacienteDto pacienteDto) {
        pacienteDto = service.savePaciente(pacienteDto);
        ResponseEntity<PacienteDto> responseEntity = new ResponseEntity<>(pacienteDto, HttpStatus.CREATED);
        return  responseEntity;
    }

    @ApiOperation(value = "Endpoint para poder una lista de pacientes filtrando por medico y por fecha de turno con el medico", response = Paciente.class, tags = "Pacientes por medico y fecha")
    @GetMapping("/get/pacientesPorMedicoYFecha/{medicoId}/{fecha}")
    public ResponseEntity<?> pacientesPorMedicoYFecha(@PathVariable("medicoId") Long medicoId, @PathVariable("fecha") Date fecha) {

        try {
            List<Paciente> pacientes = service.obtenerPacienteFechaDelMedico(medicoId, fecha);
            if (pacientes.isEmpty()){
                throw new Exception("No hay pacientes para el Dr. en la fecha indicada");
            }
            return ResponseEntity.status(HttpStatus.OK).body(pacientes);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }

    }

    @ApiOperation(value = "Endpoint para poder obtener una lista de pacientes que tuvo un medico", response = Paciente.class, tags = "Historial de pacientes de un medico")
    @GetMapping("/get/historialPacientesMedico/{medicoId}")
    public ResponseEntity<?> historialPacientesMedico(@PathVariable("medicoId") Long medicoId) {
        try {
            List<Paciente> pacientes = service.obtenerPacientesMedico(medicoId);
            if (pacientes.isEmpty()){
                throw new Exception("El Dr. no ha tenido pacientes aun");
            }
            return ResponseEntity.status(HttpStatus.OK).body(pacientes);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @ApiOperation(value = "Endpoint para poder obtener la cantidad de pacientes que tiene una clinica para una fecha en particular", response = Integer.class, tags = "Cantidad de pacientes de una clinica por fecha")
    @GetMapping("/get/cantidadPacientesClinicaFecha/{clinicaId}/{fecha}")
    public int cantidadPacientesClinicaFecha(@PathVariable("clinicaId") long clinicaId, @PathVariable("fecha") Date fecha) {

        return service.obtenerCantidadPacientesClinicaFecha(clinicaId, fecha);
    }

    @ApiOperation(value = "Endpoint para poder obtener una lista de pacientes en un rango de fechas", response = Paciente.class, tags = "Pacientes por rango de fechas")
    @GetMapping("/get/pacientesEntreFechas/desde/{fechaDesde}/hasta/{fechaHasta}")
    public List<Paciente> pacientesEntreFechas(@PathVariable("fechaDesde") Date fechaDesde, @PathVariable("fechaHasta") Date fechaHasta) {
        return service.pacientesEntreFechas(fechaDesde, fechaHasta);
    }

    @ApiOperation(value = "Endpoint para poder obtener todos los pacientes", response = Paciente.class, tags = "Todos los pacientes")
    @GetMapping("/get/pacientes")
    public ResponseEntity<List<Paciente>> obtenerPacientes () {
        return ResponseEntity.ok().body(service.obtenerPacientes());
    }

    @ApiOperation(value = "Endpoint para poder actualizar un paciente", response = Paciente.class, tags = "Actualizacion de Paciente")
    @PutMapping("/update/paciente/{id}")
    public ResponseEntity<?> actualizarPaciente(@RequestBody Paciente paciente, @PathVariable Long id){
        try {
            if(service.updatePaciente(paciente, id) == null){
                throw new Exception();
            }
            String successMsg = messageSource.getMessage("success.fine.paciente", new String[]{"Paciente"}, Locale.US);
            return ResponseEntity.status(HttpStatus.OK).body(successMsg);
        }catch (Exception e){
            String notFound = messageSource.getMessage("no.records.found.paciente", new String[]{"Paciente"}, Locale.US);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(notFound);
        }
    }

    @ApiOperation(value = "Endpoint para poder actualizar un paciente", response = Paciente.class, tags = "Actualizacion de Paciente")
    @PutMapping("/update/pacientedto/{id}")
    public ResponseEntity<?> updaterPaciente(@RequestBody PacienteDto pacientedto, @PathVariable Long id){
        try {
            if(service.actualizarPaciente(pacientedto, id) == null){
                throw new Exception();
            }
            String successMsg = messageSource.getMessage("success.fine.paciente", new String[]{"Paciente"}, Locale.US);
            return ResponseEntity.status(HttpStatus.OK).body(successMsg);
        }catch (Exception e){
            String notFound = messageSource.getMessage("no.records.found.paciente", new String[]{"Paciente"}, Locale.US);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(notFound);
        }
    }

    @ApiOperation(value = "Endpoint para poder obtener el promedio de pacientes atendidos por todos los medicos", response = Double.class, tags = "Promedio de pacientes de los medico")
    @GetMapping("/get/promedioPacientesAtendidosPorMedico")
    public double promedioPacientesAtendidosPorMedico() {

        return service.promedioPacientesAtendidosPorMedico();

    }

    @ApiOperation(value = "Endpoint para poder actualizar el turno de un paciente", response = Paciente.class, tags = "Actualizacion de turno de un Paciente")
    @PatchMapping("/update/pacienteTurno/{id}")
    public ResponseEntity<?> actualizarTurnoPaciente(@RequestBody CambioTurno cambioTurno, @PathVariable Long id){
        try {
            PacienteDto p = service.updateTurnoPaciente(cambioTurno, id);
            if(p == null){
                throw new Exception("No se pudo actualizar el paciente, verifica el id");
            }

            return ResponseEntity.status(HttpStatus.OK).body(p);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }




}
