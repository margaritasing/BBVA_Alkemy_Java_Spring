package com.example.demo.services;

import com.example.demo.dto.MedicoDto;
import com.example.demo.entities.Clinica;
import com.example.demo.entities.DiaSemanaEnum;
import com.example.demo.entities.Medico;
import com.example.demo.entities.Paciente;
import com.example.demo.repositories.ClinicaRepository;
import com.example.demo.repositories.MedicoRepository;
import com.example.demo.repositories.PacienteRepository;
import com.example.demo.services.imple.MedicoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicoServiceImpl implements MedicoService {

    @Autowired
    private ClinicaRepository clinicaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public Clinica getCLinicaById(Long clinicaId) {
        return clinicaRepository.findById(clinicaId).orElse(null);

    }


    @Override
    public List<Medico> medicosQueTrabajanDiasNoLaborablesClinica(Long clinicaId) {
        Clinica clinica = getCLinicaById(clinicaId);
        List<Medico> medicos = medicoRepository.findAll();
        List<Medico> medicoList = new ArrayList<>();

        medicos.stream()
                .filter(medico -> medico.getClinicaDondeTrabaja().equals(clinica) &&
                        medico.isTrabajaFinesSemanasYFeriados())
                .forEach(medicoList::add);
        return medicoList;
    }

    public List<Medico> obtenerMedicos() {
        return medicoRepository.findAll();
    }

    public List<Medico> obtenerMedicosPaciente (Long idPaciente) {
        Paciente paciente = pacienteRepository.findById(idPaciente).orElse(null);
        return paciente.getMedicos();
    }


    @Override
    public List<Medico> medicosQueTrabajanDiasNoLaborables() {
        List<Medico> medicos = medicoRepository.findAll();
        return  medicos.stream()
                .filter(Medico::isTrabajaFinesSemanasYFeriados)
                .collect(Collectors.toList());
    }

    @Override
    public List<Medico> obtenerMedicosQueTrabajanDiaSemana(DiaSemanaEnum diaSemana) {
        List<Medico> medicosDia = obtenerMedicos().stream()
                .filter(medico -> medico.getDiaSemanaDisponible().equals(diaSemana))
                .collect(Collectors.toList());

        return medicosDia;
    }

    public Medico guardarMedico(Medico nuevoMedico) {
        return medicoRepository.save(nuevoMedico);

    }

    public MedicoDto addMedico(MedicoDto medicoDto){
        Medico medico = new Medico();
        BeanUtils.copyProperties(medicoDto, medico);
        medico = medicoRepository.save(medico);
        BeanUtils.copyProperties(medico, medicoDto);
        return medicoDto;
    }
}
