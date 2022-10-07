package com.example.demo.services;


import com.example.demo.dto.PacienteDto;
import com.example.demo.entities.CambioTurno;
import com.example.demo.entities.Clinica;
import com.example.demo.entities.Medico;
import com.example.demo.entities.Paciente;
import com.example.demo.model.PacienteConverter;
import com.example.demo.repositories.ClinicaRepository;
import com.example.demo.repositories.MedicoRepository;
import com.example.demo.repositories.PacienteRepository;
import com.example.demo.services.imple.PacienteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ClinicaRepository clinicaRepository;

    @Autowired
    PacienteConverter pacienteConverter;

    @Autowired
    private MedicoRepository medicoRepository;


    @Override
    public Paciente guardarPaciente(Paciente nuevoPaciente) {
        return pacienteRepository.save(nuevoPaciente);
    }

    @Override
    public List<Paciente> obtenerPacientes() {
        return pacienteRepository.findAll();
    }

    /**
     * Usado para obtener una lista de medicos de la tabla de medicos
     * @return Lista de medicos
     */
    public List<Medico> obtenerMedicos() {
        return medicoRepository.findAll();
    }

    @Override
    public List<Paciente> obtenerPacienteFechaDelMedico(Long id, Date fecha) {
        List<Medico> medicos = obtenerMedicos();
        Medico med = null;
        for (Medico medico : medicos){
            if(medico.getMedicoId() == id){
                med = medico;
                break;
            }
        }
        List<Paciente> pacientesFecha = new ArrayList<>();
        for (Paciente pacte : med.getPacientes()){
            if(pacte.getFechaTurnoConMedico().equals(fecha)){
                pacientesFecha.add(pacte);
            }
        }

        return pacientesFecha;
    }



    @Override
    public int obtenerCantidadPacientesClinicaFecha(long clinicaId, Date fecha) {
        int cantPacientes = 0;
        Clinica clinica = getClinicaById(clinicaId);

        if (clinica != null) {
            List<Medico> medicosClinica = clinica.getMedicos();

            for (Medico med : medicosClinica) {
                for (Paciente paciente : med.getPacientes()) {
                    if (paciente.getFechaTurnoConMedico().equals(fecha)) {
                        cantPacientes++;
                    }
                }
            }
        }
        return cantPacientes;
    }



        private Clinica getClinicaById(long clinicaId) {
        return clinicaRepository.findById(clinicaId).orElse(null);
    }


    @Override
    public List<Paciente> pacientesEntreFechas(Date fechaDesde, Date fechaHasta) {
        List<Paciente> pacientes = pacienteRepository.findAll();

        List<Paciente> pacientesFecha = new ArrayList<>();
        for (Paciente paciente : pacientes) {
            if(paciente.getFechaTurnoConMedico().after(fechaDesde) && (paciente.getFechaTurnoConMedico().before(fechaHasta))){
                pacientesFecha.add(paciente);
            }
        }

        return pacientesFecha;
    }

    @Override
    public PacienteDto updateTurnoPaciente(CambioTurno cambioTurno, Long id){
        Optional<Paciente> p = pacienteRepository.findById(id);
        PacienteDto pacienteDto = null;
        if(p.isPresent()){
            pacienteDto = pacienteConverter.converteEntityToDto(p.get());
            pacienteDto.setFechaTurnoConMedico(cambioTurno.getFechaTurno());

            pacienteRepository.save(pacienteConverter.convertDtoToEntity(pacienteDto));
        }
        return pacienteDto;
    }


    @Override
    public double promedioPacientesAtendidosPorMedico() {
        double cantidadPacientesAtendidos = pacienteRepository.findAll().size();
        double promedioPacientes = 0;
        int cantMedicos = medicoRepository.findAll().size();

        promedioPacientes = cantidadPacientesAtendidos / cantMedicos;


        return promedioPacientes;
    }


    /*Prueba con BeanUtils*/
    @Override
    public PacienteDto addPaciente(PacienteDto pacienteDto){
        Paciente pacienteEntity = new Paciente();
        BeanUtils.copyProperties(pacienteDto, pacienteEntity); // dto recibe todos los campos del entity
        pacienteEntity = pacienteRepository.save(pacienteEntity);
        BeanUtils.copyProperties(pacienteEntity, pacienteDto); // convierte entidad en el dto
        return  pacienteDto;
    }

    /*Prueba con Model Mapper*/
    @Override
    public PacienteDto  savePaciente(PacienteDto pacienteDto) {
        Paciente paciente = pacienteConverter.convertDtoToEntity(pacienteDto);
        paciente = pacienteRepository.save(paciente);
        return pacienteConverter.converteEntityToDto(paciente);

    }

    @Override
    public List<Paciente> obtenerPacientesMedico (Long idMedico) {
        Medico medico = medicoRepository.findById(idMedico).orElse(null);
        return medico.getPacientes();

    }

    @Override
    public Paciente updatePaciente(Paciente paciente, Long id){
        Optional<Paciente> optPaciente = pacienteRepository.findById(id);
        Paciente p;
        if (optPaciente.isPresent()){
            p = optPaciente.get();
            p.setNombre(paciente.getNombre());
            p.setApellido(paciente.getApellido());
            p.setEmail(paciente.getEmail());
            p.setEdad(paciente.getEdad());
            p.setTelefono(paciente.getTelefono());
            p.setFechaNacimiento(paciente.getFechaNacimiento());
            p.setDni(paciente.getDni());
            p.setFechaTurnoConMedico(paciente.getFechaTurnoConMedico());
            p = pacienteRepository.save(p);

            return p;
        }
        return null;

    }

    /*Actualizar con lamda*/
    @Override
    public PacienteDto actualizarPaciente(PacienteDto pacientedto, Long id){
        pacienteRepository.findById(id).map(paciente -> {
            paciente.setNombre(pacientedto.getNombre());
            paciente.setApellido(pacientedto.getApellido());
            paciente.setDni(pacientedto.getDni());
            paciente.setEdad(pacientedto.getEdad());
            paciente.setFechaNacimiento(pacientedto.getFechaNacimiento());
            paciente.setFechaTurnoConMedico(pacientedto.getFechaTurnoConMedico());
            paciente.setTelefono(pacientedto.getTelefono());
            paciente.setEmail(pacientedto.getEmail());
            paciente.setMedicos(pacientedto.getMedicos());
            return pacienteRepository.save(paciente);
        }).orElseThrow(NullPointerException::new);
        return pacientedto;
    }


}
