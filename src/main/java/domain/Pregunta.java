package domain;

import java.util.*;
import javax.persistence.*;
/**
 * Una pregunta de un determinado evento, se puede apostar por diferentes respuestas.
 * @author Markel Barrena.
 * 26/02/2022
 */
@Entity
public class Pregunta {//TODO consultar el bests que nos daban y volver a poner los atributos que quitamos -agus/vidal

	@Id
	private String id;

	private String enunciado; //enunciado de la pregunta.
	private boolean estado; //Para saber si esta pregunta sigue abierta o no.
	@OneToOne 
	private Evento evento; 
	@OneToMany (cascade = CascadeType.PERSIST)
	private Vector<Pronostico> pronosticos; //pronxsticos sobre la pregunta.
	private Pronostico resultado; //pronxstico correcto: que coincide con el resultado real del evento.
	
	private double minBet; //apuesta minima a esta pregunta.
	private boolean sugerencia; //true si es una segurencia, false si es un objeto definitivo

	@OneToOne
	private Actor actor;
	private String nombreActor;


	public Pregunta(String enunciado, Evento evento, double minBet, boolean estado, Administrador admin) {

		this.id = evento.getId() + "Pregunta:" + enunciado;
		this.enunciado = enunciado;
		this.evento = evento;
		pronosticos= new Vector<Pronostico>();
		this.minBet = minBet;
		this.evento.anadirPregunta(this);
		this.estado=estado;
		this.actor=  admin;
		this.nombreActor= admin.getNombreUsuario();
		this.resultado = null;
		this.sugerencia=false;
	}

	public Pregunta(String enunciado, Evento evento, double minBet, Usuario admin) {

		this.id = evento.getId() + "Pregunta:" + enunciado;
		this.enunciado = enunciado;
		this.evento = evento;
		this.minBet = minBet;
		this.actor=  admin;
		this.nombreActor= admin.getNombreUsuario();
		this.resultado = null;
		this.sugerencia=true;
	}
	
	public Pregunta(String id) {
		this.id=id;
	}

	//getters y setters.
	public void setResultado(Pronostico resultado) {
		this.resultado = resultado;
	}
	public void setMinBet(double resultado) {
		this.minBet = resultado;
	}
	
	public void setPronosticos() {
		this.pronosticos = new Vector<Pronostico>();
	}

	public void setSugerencia(boolean sugerencia) {
		this.sugerencia = sugerencia;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

	public void setNombreActor(String nombreActor) {
		this.nombreActor = nombreActor;
	}

	public String getNombreActor() {
		return nombreActor;
	}

	public boolean getEstado() {
		return this.estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public String getId(){

		return this.id;
	}

	public Vector<Pronostico> getPronosticos() {
		return pronosticos;
	}

	public double getMinBet() {

		return this.minBet;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public Evento getEvento() {
		return evento;
	}

	public Pronostico getResultado() {
		return resultado;
	}

	public boolean fijarResultado(Pronostico resultado) {

		if (this.resultado == null) {
			this.resultado= resultado;
			return true;
		}
		return false;
	}

	//fin getters y setters.

	/**
	 * Devuelve el pronxstico que coincida con la respuesta de entrada.
	 * @param respuesta la respuesta del pronxstico a buscar.
	 * @return el pronxstico con el mismo enunciado.
	 */
	public Pronostico buscarPronosticoPorRespuesta(String respuesta) {
		for(Pronostico p: pronosticos) {
			if(p.getRespuesta().equals(respuesta)) return p;
		}
		return null;
	}

	/**
	 * Axade un pronxstico al evento.
	 * @param pronxstico el pronxstico a axadir.
	 */
	public void anadirPronostico(Pronostico pronostico) {

		pronosticos.add(pronostico);
	}

	/**
	 * Elimina el pronxstico de entrada.
	 * @param pronostico el pronxstico a eliminar.
	 */
	public void eliminarPronostico(Pronostico pronostico) {

		pronosticos.remove(pronostico);
	}

	@Override
	/**
	 * Comprueba si la instancia que llama al metodo corresponde al mismo evento, tiene el mismo enunciado y la misma apuesta minima que el objeto que se la da como parametro
	 */
	public boolean equals(Object obj) {

		if (!(obj instanceof Pregunta))
			return false;

		Pregunta pregunta = (Pregunta) obj;

		return (pregunta.getEvento().equals(this.evento) && pregunta.getEnunciado().equals(this.enunciado) && (pregunta.minBet == this.minBet));
	}
}
