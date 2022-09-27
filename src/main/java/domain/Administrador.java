package domain;

import java.util.ArrayList;
import java.util.Date;
import javax.persistence.*;
/**
 * Información de un administrador de la aplicación.
 * @author Markel Barrena.
 * 05/03/2022
 */

@Entity
public class Administrador extends Actor{
	/*
	@Id
	private String nombreUsuario; //nick del usuario.
	private String DNI;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private Date fechaN; //fecha de nacimiento.
	private String pswd;
	private char sexo; //M, F, O.
	private String email;
	private String numTel;*/
	@OneToMany
	private ArrayList<Pronostico> pronosticos; //pronosticos formulados por el administrador.
	@OneToMany
	private ArrayList<Pregunta> preguntas; //preguntas formuladas por el administrador.
	@OneToMany
	private ArrayList<Evento> eventos; //eventos creados por el administrador.
	@OneToMany
	private ArrayList<Competicion> competiciones; //competiciones creadas por el administrador.
	
	public Administrador(String nombreUsuario, String DNI, String nombre, String apellido1, String apellido2, Date fechaN, String pswd, char Sexo, String email, String numTel) {
	
		super(nombreUsuario,  DNI,  nombre,  apellido1,  apellido2,  fechaN,  pswd,  Sexo,  email,  numTel);
/*
		this.nombreUsuario= nombreUsuario;
		this.DNI= DNI;
		this.nombre= nombre;
		this.apellido1= apellido1;
		this.apellido2= apellido2;
		this.fechaN= fechaN;
		this.pswd= pswd;
		this.email= email;
		this.numTel= numTel;*/
		this.competiciones = new ArrayList<Competicion>();
		this.eventos = new ArrayList<Evento>();
		this.preguntas = new ArrayList<Pregunta>();
		this.pronosticos = new ArrayList<Pronostico>();	
	}
	
	//getters y setters:
	/*
	public String getNombreUsuario() {
		return this.nombreUsuario;
	}
	
	public String getDNI() {
		return this.DNI;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellido1() {
		return apellido1;
	}
	
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	
	public String getApellido2() {
		return apellido2;
	}
	
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	
	public String getPswd() {
		return pswd;
	}
	
	public void setPswd(String contraseña) {
		this.pswd = contraseña;
	}
	
	public char getSexo() {
		return sexo;
	}
	
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Date getFechaN() {
		return this.fechaN;
	}
	
	public String getNumTel() {
		return this.numTel;
	}
	
	public void setNumTel(String numTel) {
		this.numTel= numTel;
	}
	*/
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
	 * Devuelve el pronóstico que coincida con la respuesta de entrada.
	 * @param respuesta la respuesta del pronóstico a buscar.
	 * @return el pronóstico con el mismo enunciado.
	 */
	public Pronostico buscarPronosticoPorRespuesta(String respuesta) {
		for(Pronostico p: pronosticos) {
			if(p.getRespuesta().equals(respuesta)) return p;
		}
		return null;
	}
	
	/**
	 * Añade un pronóstico a la lista de pronósticos creados por el administrador.
	 * @param pronóstico el pronóstico a añadir.
	 */
	public void anadirPronostico(Pronostico pronostico) {
		pronosticos.add(pronostico);
	}
	
	/**
	 * Elimina el pronóstico de entrada.
	 * @param pronostico el pronóstico a eliminar.
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
			if(p.getEnunciado().equals(enunciado)) return p;
		}
		return null;
	}
	
	/**
	 * Añade una pregunta a la lista de preguntas creadas por el administrador..
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
	 * Añade un evento a la lista de eventos creados por el administrador.
	 * @param evento el evento a añadir.
	 */
	public void anadirEvento(Evento evento) {
		this.eventos.add(evento);
	}
	
	/**
	 * Devuelve la competición con el id que se introduce.
	 * @param nombre(String): el nombre de la competición a buscar.
	 * @return la competición de la bd que tenga ese nombre.
	 */
	public Competicion buscarCompeticionPorId(String nombre) {
		
		for(Competicion c: competiciones) {
			if(c.getNombre().equals(nombre)) return c;
		}
		return null;
	}
	
	/**
	 * Añade una competición a la lista de competiciones creadas por el administrador.
	 * @param competicion la competicion a añadir.
	 */
	public void anadirCompeticion(Competicion competicion) {
		this.competiciones.add(competicion);
	}
}
