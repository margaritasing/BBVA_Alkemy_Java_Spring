package com.example.demo.services.imple;

import com.example.demo.dto.ClinicaDto;
import com.example.demo.dto.MedicoDto;
import com.example.demo.dto.PacienteDto;
import com.example.demo.entities.*;

import java.util.Date;
import java.util.List;

public interface ClinicaService {


    List<Clinica> obtenerClinicas();
    Clinica guardarClinica(Clinica nuevaClinica);
    Clinica getCLinicaById(Long clinicaId);
    void eliminarClinica (Long idClinica);
    ClinicaDto addClinica(ClinicaDto clinicaDto);









}
