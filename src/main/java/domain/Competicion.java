package domain;

import java.util.ArrayList;
import java.util.Vector;

import javax.persistence.*;


/**
 * Una competición que puede englobar un conjunto de eventos.
 * @author Markel Barrena.
 * 26/02/2022
 */
@Entity
public class Competicion {

	@Id
	private String id;
	

	private String nombre;
	private String deporte;
	private char genero; //género de los deportistas ('M' de masculino, 'F' de femenino, 'A' de mixto/Unisex).
	private String temporada;
	private String descripcion; //descripción opcional.
	@OneToMany (cascade =CascadeType.PERSIST)
	private Vector<Evento> eventos; //eventos de la competición.
	private boolean sugerencia; //true en caso de ser una sugerencia de un usuario, y false en caso de ser algo definitivo
	
	@OneToOne
	private Actor actor; //lo definimos como actor ya que en las sugerencias estara ligado a un usuario mientras que cuando sea definitivo estara ligado a un administrador
	private String nombreActor;
	
	

	/**
	 * 
	 * @param nombre
	 * @param deporte
	 * @param genero
	 * @param temporada
	 * @param descripcion
	 */
	public Competicion(String nombre, String deporte, char genero, String temporada, String descripcion, Administrador admin) {
		
		this.id= "Competicion:"+nombre;
		this.nombre= nombre;
		this.deporte= deporte;
		this.genero= genero;
		this.temporada= temporada;
		this.eventos = new Vector<Evento>();
		this.descripcion = descripcion;
		this.actor= admin;
		this.nombreActor= admin.getNombreUsuario();
		this.sugerencia=false;
	}
	
	public Competicion(String nombre, String deporte, char genero, String temporada, String descripcion, Usuario user) {
		this.id="Competicion:"+nombre;
		this.nombre=nombre;
		this.deporte=deporte;
		this.genero=genero;
		this.temporada=temporada;
		this.descripcion=descripcion;
		this.actor=user;
		this.nombreActor=user.getNombreUsuario();
		this.sugerencia=true;
		
		
	}
	
	//getters y setters:
	public void setEventos() {
		this.eventos = new Vector<Evento>();
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
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDeporte() {
		return deporte;
	}

	public char getGenero() {
		return genero;
	}

	public String getTemporada() {
		return temporada;
	}
	
	public Vector<Evento> getEventos() {
		
		return this.eventos;
	}
	
	/**
	 * Devuelve el evento con el nombre que se introduce.
	 * @param nombre el nombre del evento a buscar.
	 * @return el evento con el mismo nombre.
	 */
	public Evento buscarEventoPorNombre(String nombre) {
		for(Evento e: eventos) {
			if(e.getNombre().equals(nombre)) return e;
		}
		return null;
	}
	
	/**
	 * Añade un evento a la lista de eventos de esta competición.
	 * @param e el evento a añadir.
	 */
	public void anadirEvento(Evento evento) {
		
		this.eventos.add(evento);
	}
	
	@Override
	/**
	 * Comprueba si la instacia que llama al metodo tiene el mismo nombre, es del mismo deporte, es de la misma temporara y del mismo genero que la competicio que se le da como parametro
	 */
	public boolean equals(Object obj) {
		
		if (!(obj instanceof Competicion))
			return false;
		Competicion competicion = (Competicion) obj;
		
		return (this.nombre.equals(competicion.getNombre()) && competicion.getDeporte().equals(this.deporte) && (competicion.getTemporada() == this.temporada) && (competicion.getGenero() == this.genero));
	}
}
