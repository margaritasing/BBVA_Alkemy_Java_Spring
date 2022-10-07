package com.example.demo.dto;

import com.example.demo.entities.Medico;



import java.util.Date;
import java.util.List;

public class PacienteDto {


    private String nombre;
    private String apellido;
    private String email;
    private int edad;
    private Date fechaNacimiento;
    private String dni;
    private String telefono;
    private Date fechaTurnoConMedico;
    private List<Medico> medicos;



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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFechaTurnoConMedico() {
        return fechaTurnoConMedico;
    }

    public void setFechaTurnoConMedico(Date fechaTurnoConMedico) {
        this.fechaTurnoConMedico = fechaTurnoConMedico;
    }

    public List<Medico> getMedicos() {
        return medicos;
    }

    public void setMedicos(List<Medico> medicos) {
        this.medicos = medicos;
    }
}
