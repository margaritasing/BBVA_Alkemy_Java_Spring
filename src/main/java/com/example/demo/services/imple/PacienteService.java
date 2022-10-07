package com.example.demo.services.imple;

import com.example.demo.entities.*;
import com.example.demo.dto.PacienteDto;
import com.example.demo.entities.CambioTurno;
import com.example.demo.entities.Paciente;
import java.util.Date;
import java.util.List;

public interface PacienteService {
    Paciente guardarPaciente(Paciente nuevoPaciente);
    List<Paciente> obtenerPacientes();
    List<Paciente> obtenerPacienteFechaDelMedico(Long id, Date fecha);
    int obtenerCantidadPacientesClinicaFecha(long clinicaId, Date fecha);
    List<Paciente> obtenerPacientesMedico (Long idMedico);
    Paciente updatePaciente(Paciente paciente, Long pacienteId);
    double promedioPacientesAtendidosPorMedico();
    List<Paciente> pacientesEntreFechas(Date fechaDesde, Date fechaHasta);
    PacienteDto updateTurnoPaciente(CambioTurno cambioTurno, Long id);
    PacienteDto addPaciente(PacienteDto pacienteDto);
    PacienteDto savePaciente(PacienteDto pacienteDto);
    PacienteDto actualizarPaciente(PacienteDto pacientedto, Long id);

}
