package domain;

import java.util.ArrayList;
import java.util.Date;
import javax.persistence.*;
/**
 * Informaci�n de un administrador de la aplicaci�n.
 * @author Markel Barrena.
 * 05/03/2022
 */

@Entity
public class Administrador extends Actor{

	@OneToMany
	private ArrayList<Pronostico> pronosticos; //pronosticos formulados por el administrador.
	@OneToMany
	private ArrayList<Pregunta> preguntas; //preguntas formuladas por el administrador.
	@OneToMany
	private ArrayList<Evento> eventos; //eventos creados por el administrador.
	@OneToMany
	private ArrayList<Competicion> competiciones; //competiciones creadas por el administrador.
	
	public Administrador(String nombreUsuario, 
			String DNI, String nombre, String apellido1,
			String apellido2, Date fechaN, String pswd, char Sexo, String email, String numTel) {
	
		super(nombreUsuario,  DNI,  nombre,  apellido1,  apellido2,  fechaN,  pswd,  Sexo,  email,  numTel);

		this.competiciones = new ArrayList<Competicion>();
		this.eventos = new ArrayList<Evento>();
		this.preguntas = new ArrayList<Pregunta>();
		this.pronosticos = new ArrayList<Pronostico>();	
	}
	

	public ArrayList<Pronostico> getPronosticos() {
		return pronosticos;
	}


	public void setPronosticos(ArrayList<Pronostico> pronosticos) {
		this.pronosticos = pronosticos;
	}


	public ArrayList<Pregunta> getPreguntas() {
		return preguntas;
	}


	public void setPreguntas(ArrayList<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}



	public ArrayList<Evento> getEventos() {
		return eventos;
	}



	public void setEventos(ArrayList<Evento> eventos) {
		this.eventos = eventos;
	}



	public ArrayList<Competicion> getCompeticiones() {
		return competiciones;
	}



	public void setCompeticiones(ArrayList<Competicion> competiciones) {
		this.competiciones = competiciones;
	}
	
	//fin getters y setters.

	
	/**
	 * Devuelve el pron�stico que coincida con la respuesta de entrada.
	 * @param respuesta la respuesta del pron�stico a buscar.
	 * @return el pron�stico con el mismo enunciado.
	 */
	public Pronostico buscarPronosticoPorRespuesta(String respuesta) {
		for(Pronostico p: pronosticos) {
			if(p.getRespuesta().equals(respuesta)) {
				return p;
			}
		}
		return null;
	}
	
	/**
	 * A�ade un pron�stico a la lista de pron�sticos creados por el administrador.
	 * @param pron�stico el pron�stico a a�adir.
	 */
	public void anadirPronostico(Pronostico pronostico) {
		pronosticos.add(pronostico);
	}
	
	/**
	 * Elimina el pron�stico de entrada.
	 * @param pronostico el pron�stico a eliminar.
	 */
	public void eliminarPronostico(Pronostico pronostico) {
		pronosticos.remove(pronostico);
	}
	
	/**
	 * Devuelve la pregunta que coincida con el enunciado de entrada.
	 * @param enunciado el enunciado de la pregunta a buscar.
	 * @return la pregunta con el mismo enunciado.
	 */
	public Pregunta buscarPreguntaPorEnunciado(String enunciado) {
		for(Pregunta p: preguntas) {
			if(p.getEnunciado().equals(enunciado)) {
				return p;
			}
		}
		return null;
	}
	
	/**
	 * A�ade una pregunta a la lista de preguntas creadas por el administrador..
	 * @param pregunta la pregunta a a�adir.
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
	
	/**
	 * Devuelve el evento con el nombre que se introduce.
	 * @param nombre el nombre del evento a buscar.
	 * @return el evento con el mismo nombre.
	 */
	public Evento buscarEventoPorNombre(String nombre) {
		for(Evento e: eventos) {
			if(e.getNombre().equals(nombre)) {
				return e;
			}
		}
		return null;
	}
	
	/**
	 * A�ade un evento a la lista de eventos creados por el administrador.
	 * @param evento el evento a a�adir.
	 */
	public void anadirEvento(Evento evento) {
		this.eventos.add(evento);
	}
	
	/**
	 * Devuelve la competici�n con el id que se introduce.
	 * @param nombre(String): el nombre de la competici�n a buscar.
	 * @return la competici�n de la bd que tenga ese nombre.
	 */
	public Competicion buscarCompeticionPorId(String nombre) {
		
		for(Competicion c: competiciones) {
			if(c.getNombre().equals(nombre)) {
				return c;
			}
		}
		return null;
	}
	
	/**
	 * A�ade una competici�n a la lista de competiciones creadas por el administrador.
	 * @param competicion la competicion a a�adir.
	 */
	public void anadirCompeticion(Competicion competicion) {
		this.competiciones.add(competicion);
	}
}
