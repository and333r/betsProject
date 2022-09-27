package domain;

import java.util.*;

import javax.persistence.*;

import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import gui.IniciarSesionGUI;
/**
 * Un evento deportivo, sobre el que se pueden hacer apuestas.
 * @author Markel Barrena.
 * 26/02/2022
 */
@Entity
public class Evento {
	
	@Id
	private String id;
	
	private String nombre;
	private Date fecha;
	private String descripcion; //descripción opcional.
	@OneToOne 
	private Competicion comp;
	@OneToMany (cascade =CascadeType.PERSIST)
	private Vector<Pregunta> preguntas; //preguntas sobre el evento.
	private boolean sugerencia; //true en caso de ser una sugerencia, false en caso de ser un evento definitivo
	private boolean estado; //Abierto--> True, Cerrado --> False
	
	@OneToOne
	private Actor actor;
	private String nombreActor;
	
	public Evento(String nombre, Date fecha, Competicion comp, String descripcion, Administrador admin) {
		
		this.nombre= nombre;
		this.fecha= fecha;
		this.comp= comp;
		this.descripcion=descripcion;
		this.preguntas= new Vector<Pregunta>();
		this.comp.anadirEvento(this); 
		this.id = comp.getId()+"Evento:"+nombre+"Fecha:"+fecha.toString();
		this.actor=  admin;
		this.nombreActor= admin.getNombreUsuario();
		this.sugerencia=false;
		this.estado=true;
	}
	
	public Evento(String nombre, Date fecha, Competicion comp, String descripcion, Usuario user) {

		this.id = comp.getId()+"Evento:"+nombre+"Fecha:"+fecha.toString();
		this.nombre= nombre;
		this.fecha= fecha;
		this.comp= comp;
		this.descripcion=descripcion;
		this.actor=  user;
		this.nombreActor= user.getNombreUsuario();
		this.sugerencia=true;
		this.estado=true;
	}
	
	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	//getters y setters:
	public void setPreguntas() {
		this.preguntas = new Vector<Pregunta>();
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
	
	public String getId(){
		
		return this.id;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public Date getFecha() {
		return fecha;
	}

	public Competicion getComp() {
		return comp;
	}
	
	public Vector<Pregunta> getPreguntas() {
		return this.preguntas;
	}
	
	//fin getters y setters.
	
	/* ****de esto se encarga la lógica de negocio****
	public boolean eventoExpirado() {
		Date now= new Date();
		return fecha.after(now);
	}*/
	
	/**
	 * Devuelve la pregunta que coincida con el enunciado de entrada.
	 * @param enunciado el enunciado de la pregunta a buscar.
	 * @return la pregunta con el mismo enunciado.
	 */
	public Pregunta buscarPreguntaPorEnunciado(String enunciado) {
		for(Pregunta p: preguntas) {
			if(p.getEnunciado().equals(enunciado)) return p;
		}
		return null;
	}
	
	/**
	 * Añade una pregunta al evento.
	 * @param pregunta la pregunta a añadir.
	 */
	public void anadirPregunta(Pregunta pregunta) {
		
		preguntas.add(pregunta);
	}
	
	/**
	 * Elimina la pregunta de entrada.
	 * @param pregunta la pregunta a eliminar.
	 */
	public void eliminarPregunta(Pregunta pregunta) {
		preguntas.remove(pregunta);
	}

	@Override
	/**
	 * Comprueba que la instacia que llama tieme el mismo nombre, corresponde a la misma competicion y tiene la misma fecha que el objeto que se le da como parametro
	 */
	public boolean equals(Object obj) {
		
		if (!(obj instanceof Evento))
			return false;
		Evento evento = (Evento) obj;
		return (evento.nombre.equals(this.nombre) && evento.getComp().equals(this.comp) && evento.getFecha().equals(this.fecha));
	}
	
	public void comprobarAbierto() {
		
		boolean abierto = false;
		for (Pregunta pregunta: this.preguntas)
			if (pregunta.getEstado())
				abierto = true;
		
		if (!abierto)
			this.estado = false;
	}
}
