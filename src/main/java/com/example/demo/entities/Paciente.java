package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "paciente")
public class Paciente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long pacienteId;
	private String nombre;
	private String apellido;
	private String email;
	private int edad;
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;
	private String dni;
	private String telefono;
	@Temporal(TemporalType.DATE)
	private Date fechaTurnoConMedico;
	@JsonIgnore
	@ManyToMany(mappedBy = "pacientes")

	private List<Medico> medicos;
	
	public long getPacienteId() {
		return pacienteId;
	}
	public void setPacienteId(long pacienteId) {
		this.pacienteId = pacienteId;
	}
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
