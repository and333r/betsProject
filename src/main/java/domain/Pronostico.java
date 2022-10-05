package domain;


import java.util.ArrayList;
import java.util.Vector;

import javax.persistence.*;
/**
 * Posibles pronosticos a una pregunta sobre un evento, se puede apostar sobre �l.
 * @author Markel Barrena.
 * 26/02/2022
 */
@Entity
public class Pronostico {

	@Id
	private String id;
	private String respuesta; //el pron�stico a la pregunta.
	@OneToOne 
	private Pregunta pregunta;
	private double ganancia;
	@OneToMany 
	private Vector<Apuesta> apuestas ;
	
	@OneToOne
	private Administrador admin;
	private String nombreAdmin;

	

	public Pronostico(String respuesta, Pregunta pregunta, double ganancia, Actor admin) {

		this.id = pregunta.getId() + "Pronostico:" + respuesta;
		this.respuesta = respuesta;
		this.pregunta = pregunta;
		this.pregunta.anadirPronostico(this);
		this.ganancia = ganancia;
		this.apuestas = new Vector<Apuesta>();
		this.admin= (Administrador) admin;
		this.nombreAdmin= admin.getNombreUsuario();
	}

	//getters y setters:

	public String getNombreAdmin() {
		return nombreAdmin;
	}
	
	public String getId(){

		return this.id;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public Pregunta getPregunta() {
		return pregunta;
	}
	
	public double getGanancia() {
		return ganancia;
	}

	public void setGanancia(double ganancia) {
		this.ganancia = ganancia;
	}

	/**
	 * Determina si este pron�stico coincide con el resultado.
	 * @return true si es correcto.
	 */
	public boolean esCorrecto() {
		return this.pregunta.getResultado().respuesta.equals(this.respuesta);
		//return ganador;
	}
	/*
	public void haGanado(){

		this.ganador = true;
	}*/
	
	@Override
	/**
	 * Comprueba que la instancia que llama al metodo corresponda a la misma pregunta y tenga la misma respuesta que el objeto que se le da como parametro
	 */
	public boolean equals(Object obj) {
		
		if(!(obj instanceof Pronostico))
			return false;
		
		Pronostico pronostico = (Pronostico) obj;
		
		return (this.pregunta.equals(pronostico.getPregunta()) && pronostico.getRespuesta().equals(this.respuesta));
	}

	public void anadirApuesta(Apuesta apuesta) {
		this.apuestas.add(apuesta);
		
	}
}
