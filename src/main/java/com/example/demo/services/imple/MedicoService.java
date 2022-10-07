package com.example.demo.services.imple;

import com.example.demo.dto.MedicoDto;
import com.example.demo.entities.DiaSemanaEnum;
import com.example.demo.entities.Medico;

import java.util.List;

public interface MedicoService {

    Medico guardarMedico(Medico nuevoMedico);
    List<Medico> obtenerMedicos();
    List<Medico> obtenerMedicosQueTrabajanDiaSemana(DiaSemanaEnum diaSemana);
    List<Medico> obtenerMedicosPaciente (Long idPaciente);
    List<Medico> medicosQueTrabajanDiasNoLaborables();
    List<Medico> medicosQueTrabajanDiasNoLaborablesClinica(Long clinicaId);
    MedicoDto addMedico(MedicoDto medicoDto);


}
