package com.example.demo.dto;

import com.example.demo.entities.Clinica;
import com.example.demo.entities.DiaSemanaEnum;
import com.example.demo.entities.Paciente;


import java.util.List;

public class MedicoDto {

    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private DiaSemanaEnum diaSemanaDisponible;
    private boolean trabajaFinesSemanasYFeriados;
    private Clinica clinicaDondeTrabaja;
    private List<Paciente> pacientes;


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public DiaSemanaEnum getDiaSemanaDisponible() {
        return diaSemanaDisponible;
    }

    public void setDiaSemanaDisponible(DiaSemanaEnum diaSemanaDisponible) {
        this.diaSemanaDisponible = diaSemanaDisponible;
    }

    public boolean isTrabajaFinesSemanasYFeriados() {
        return trabajaFinesSemanasYFeriados;
    }

    public void setTrabajaFinesSemanasYFeriados(boolean trabajaFinesSemanasYFeriados) {
        this.trabajaFinesSemanasYFeriados = trabajaFinesSemanasYFeriados;
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    public Clinica getClinicaDondeTrabaja() {
        return clinicaDondeTrabaja;
    }

    public void setClinicaDondeTrabaja(Clinica clinicaDondeTrabaja) {
        this.clinicaDondeTrabaja = clinicaDondeTrabaja;
    }
}

