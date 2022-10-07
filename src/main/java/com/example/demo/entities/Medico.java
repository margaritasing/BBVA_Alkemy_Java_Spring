package com.example.demo.entities;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "medico")
public class Medico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long medicoId;

	private String nombre;
	private String apellido;
	private String email;
	private String telefono;

	@ManyToOne
	@JoinColumn(name = "clinica_id", nullable = false)
	private Clinica clinicaDondeTrabaja;

	@Enumerated(EnumType.STRING)
	private DiaSemanaEnum diaSemanaDisponible;

	private boolean trabajaFinesSemanasYFeriados;

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(
			name = "medico_paciente",
			joinColumns = @JoinColumn(name = "medico_id"),
			inverseJoinColumns = @JoinColumn(name = "paciente_id"))

	private List<Paciente> pacientes;
	
	public long getMedicoId() {
		return medicoId;
	}
	public void setMedicoId(long medicoId) {
		this.medicoId = medicoId;
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
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public Clinica getClinicaDondeTrabaja() {
		return clinicaDondeTrabaja;
	}
	public void setClinicaDondeTrabaja(Clinica clinicaDondeTrabaja) {
		this.clinicaDondeTrabaja = clinicaDondeTrabaja;
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

}
