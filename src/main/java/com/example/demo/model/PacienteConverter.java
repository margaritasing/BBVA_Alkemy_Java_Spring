package com.example.demo.model;

import com.example.demo.dto.PacienteDto;
import com.example.demo.entities.Paciente;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PacienteConverter {

    public PacienteDto converteEntityToDto(Paciente paciente){
        ModelMapper modelMapper = new ModelMapper();
        PacienteDto pacienteDto = modelMapper.map(paciente, PacienteDto.class);
        return  pacienteDto;
    }

    public Paciente convertDtoToEntity(PacienteDto pacienteDto){
        ModelMapper modelMapper = new ModelMapper();
        Paciente paciente = modelMapper.map(pacienteDto, Paciente.class);
        return  paciente;
    }
}
