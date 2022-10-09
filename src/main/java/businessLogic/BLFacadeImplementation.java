package businessLogic;



import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;

import configuration.ConfigXML;
import configuration.UtilDate;
import dataAccess.DataAccess;
import domain.*;
import exceptions.EventoNoExistenteException;
import exceptions.PreguntaNoExistenteException;
import gui.IniciarSesionGUI;

/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation  implements BLFacade {

	DataAccess dbManager;
	final static String INI = "initialize";

	public BLFacadeImplementation()  {		
		System.out.println("Creating BLFacadeImplementation instance");
		ConfigXML c=ConfigXML.getInstance();

		if (c.getDataBaseOpenMode().equals(INI)) {
			dbManager=new DataAccess(c.getDataBaseOpenMode().equals(INI));
			dbManager.initializeDB();
		} else
			dbManager=new DataAccess();
		dbManager.close();


	}

	public BLFacadeImplementation(DataAccess da)  {

		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		ConfigXML c=ConfigXML.getInstance();

		if (c.getDataBaseOpenMode().equals(INI)) {
			da.open(true);
			da.initializeDB();
			da.close();

		}
		dbManager=da;		
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//REGISTER ZONE

	/**
	 * 
	 * @param id
	 * @param DNI
	 * @param Nombre
	 * @param Apellido1
	 * @param Apellido2
	 * @param fechaN
	 * @param contrasena
	 * @param sexo
	 * @param email
	 * @param saldo
	 * @param admin :especifica si la bd tiene que crear un actor usuario o un actor admin (true: admin)
	 * @return 0: Todx correcto; 1: Fecha es incorrecta; 2: Saldo es negativo
	 */
	public int registrarUsuario(String id, String DNI, String Nombre, String Apellido1, String Apellido2, Date fechaN, String contrasena, char sexo, String email,String tlfn, boolean admin) {


		dbManager.open(false);

		Date fechaHoy = UtilDate.currentDate();
		Date fechaHace18 = UtilDate.fechaMayorEdad();

		if (dbManager.actorExistente(id)) {

			dbManager.close();
			return 1; //El usuario que intenta a�adir ya existe
		}
		else 

			if (fechaHoy.before(fechaN)) {
				dbManager.close();
				return 2; //Comprueba que la fecha es anterior al momento actual (Para que no se pueda poner que has nacido en 2025). Devuelve 2 si la fecha esta mal.
			}

			else 
				if (fechaN.before(fechaHace18))
					dbManager.registrarUsuario(id, DNI, Nombre, Apellido1, Apellido2, fechaN, contrasena, sexo, email,tlfn, admin);
				else 
					return 3;

		dbManager.close();
		return 0;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * 
	 * @param id
	 * @param DNI
	 * @param Nombre
	 * @param Apellido1
	 * @param Apellido2
	 * @param fechaN
	 * @param contrasena
	 * @param sexo
	 * @param email
	 * @param saldo
	 * @return 0: Todo correcto; 1: Fecha es incorrecta; 2: Saldo es negativo
	 */

	/*public int registrarAdmin(String id, String DNI, String Nombre, String Apellido1, String Apellido2, Date fechaN, String contrasena, char sexo, String email,String tlfn) {
		//Solo se comprueba que este correcta la fecha, que el usuario no exista, y que el saldo sea positivo.
		dbManager.open(false);
		if (dbManager.actorExistente(id)) {
			dbManager.close();
			return 1; //El usuario que intenta a�adir ya existe
		}
		else if (fechaN.before(fechaN)) {
			dbManager.close();
			return 2; //Comprueba que la fecha es anterior al momento actual (Para que no se pueda poner que has nacido en 2025). Devuelve 2 si la fecha esta mal.
		}
		else {
			dbManager.registrarAdmin(id, DNI, Nombre, Apellido1, Apellido2, fechaN, contrasena, sexo, email, tlfn);
		}
		dbManager.close();
		return 0;
	}*/

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	/**
	 * 
	 * @param id
	 * @param nombre
	 * @param deporte
	 * @param genero
	 * @param temporada
	 * @param descripcion
	 * @return 1: La competicion esta a�adida; 0: To ok
	 */

	public Competicion crearCompeticion(String nombre, String deporte, char genero, String temporada, String descripcion, Actor admin) {

		dbManager.open(false);

		Competicion competicion = null;
		if (!dbManager.existeCompeticion("Competicion:" + nombre)) {
			competicion = dbManager.crearCompeticion(nombre, deporte, genero, temporada, descripcion, admin);
		}
		//Si la competicion ya est� a�adida.
		dbManager.close();
		return competicion;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 
	 * @param nombre
	 * @param fecha
	 * @param comp
	 * @return 1:  El evento es un acontecimiento que ya ha pasado.; 2:El evento que queremos a�adir ya existe; 0: To ok
	 * @throws EventoNoExistenteException 
	 */
	public int crearEvento(String nombre, Date fecha, Competicion comp, String descripcion, Actor admin) throws EventoNoExistenteException {

		dbManager.open(false);

		Date now = new Date();
		if (fecha.before(now)) return 1; //Queremos crear un evento en un acontecimiento ya pasado.
		else if (dbManager.existeEvento(nombre, fecha, comp)) return 2; //El evento que queremos a�adir ya existe.
		else {
			dbManager.crearEvento(nombre, fecha, comp, descripcion, admin);
		}
		dbManager.close();
		new Date();
		return 0;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * 
	 * @param enunciado
	 * @param evento
	 * @return La pregunta si la ha creado, null si no
	 */
	public Pregunta crearPregunta(String enunciado, Evento evento, double minBet, boolean estado, Actor admin) {

		dbManager.open(false);

		Pregunta p = null;

		if (! dbManager.existePregunta(enunciado, evento)) //Si la Pregunta ya existe, saltara la creacion

			p = dbManager.crearPregunta (enunciado, evento, minBet, estado, admin);

		dbManager.close();

		return p;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * 
	 * @param respuesta
	 * @param tasa
	 * @param pregunta
	 * @return 1: El pronostico ya existe; 2: Tasa es incorrecta; 0: To ok
	 * @throws PreguntaNoExistenteException 
	 */
	public int crearPronostico(String respuesta, Pregunta pregunta, double ganancia, Actor admin) {

		dbManager.open(false);

		/*for (Pronostico p : pregunta.getPronosticos())
			if (p.getRespuesta().equals(respuesta))
				return 1;//ya esta*/

		if (dbManager.existePronostico(respuesta, pregunta)) {
			return 1; // el pronostico ya existe
		}else {
			if (ganancia>=1.00) {
				dbManager.crearPronostico(respuesta, pregunta, ganancia, admin);
				dbManager.close();
				return 0;
			}else return 2; //la cuota no es valida
		}
	}

	public int crearApuesta(Pronostico pronostico, Usuario usuario, double cantidad) { 
		
		dbManager.open(false);
		
		if(pronostico==null || usuario==null ||cantidad<0) return -1; //Algo ha salido mal
		
		if (!this.existeApuesta(pronostico.getId()+"_"+usuario.getNombreUsuario())) {//comprobacion de existenca 
			switch(dbManager.crearApuesta(pronostico, usuario, cantidad)) {
			case 0:
				dbManager.close();//La apuesta se ha realizado bien
				return 0;
			case 1:
				dbManager.close();
				return 4;//la cantidad apostada no supra la apuesta minima
			case 2:
				dbManager.close();
				return 1; //saldo insuficiente
			default:
				return -1; //Algo ha salido mal
			}

		}else {
			if(dbManager.puedeAnadirApuesta(pronostico.getId()+"_"+usuario.getNombreUsuario())) {
				dbManager.close();
				return 2; //nueva apuesta al mismo pronostico
			}else {
				dbManager.close();
				return 3;//no se pueden realizar mas apuestas al mismo pronostico
			}
		}
	}

	public void close() {
		DataAccess dB4oManager=new DataAccess(false);

		dB4oManager.close();

	}

	/**
	 * This method invokes the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	@WebMethod	
	public void initializeBD(){
		dbManager.open(false);
		dbManager.initializeDB();
		dbManager.close();
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Este metodo es para comprobar que un actor (usuario o administrador) ya existe.
	 * @param user (El usuario que la Intefaz pasa para comprobar si el usuario existe)
	 * @return True si existe el usuario, False si no.
	 */
	public boolean actorExistente (String nombreUsuario) {

		boolean existe=false;
		dbManager.open(false);
		existe = dbManager.actorExistente(nombreUsuario);
		dbManager.close();
		return existe;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * @param respuesta
	 * @param p
	 * @return
	 */
	public boolean existePronostico (String respuesta, Pregunta p) {

		dbManager.open(false);

		boolean salida = dbManager.existePronostico(respuesta, p);

		dbManager.close();

		return salida; //Comprobar que la pregunta exista (Por si lo necesitais en algun momento)
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Dado el parametro identificador de una pregunta y el evento al que corresponde, comprueba que esa pregunta ya esta en la base de datos
	 * 
	 * @param event El evento que queremos comprobar si existe o no
	 * @return True si existe, False si no
	 */
	public boolean existeLaPregunta (String enunciado, Evento evento) {

		dbManager.open(false);

		boolean salida = dbManager.existePregunta(enunciado, evento);

		dbManager.close();

		return salida; //Comprobar que la pregunta exista (Por si lo necesitais en algun momento)
	}

	public boolean existeApuesta(String string) {
		dbManager.open(false);
		boolean resultado = dbManager.existeApuesta(string);
		dbManager.close();
		return resultado;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Dado un usuario y una contresena comprueba que la contrasena dada y la asiganda al usuario coinciden
	 * 
	 * @param user El usuario del cual queremos combrobar la contrasena
	 * @param pwd La contrasena qie debemos comprobar
	 * @return 0: Si el nombre de usuario no esta registrado; 1: La pwd es correcta y es user; 2: La pwd es correcta y es admin; 3: contrase�a erronea
	 */
	public int comprobarContrasena (String user, String pwd) {

		dbManager.open(false);

		if (dbManager.actorExistente(user)) {

			if (dbManager.comprobarContrasena(user, pwd)) { //si el usuario existe, y la pwd es correcta, entramos a comprobar si es admin o user

				if (dbManager.esAdmin(user)) {
					dbManager.close();
					return 2; //2 si es admin
				}
				else {
					dbManager.close();
					return 1; //1 si es user
				}
			}
			else
				return 3; //si no coinciden nombre y contrase�a
		}
		dbManager.close();
		return 0; //0 si el nombre de usuario no esta registrado
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 
	 * @param OGpwd La contrase�a original
	 * @param newPwd La contrase�a nueva
	 * @param newPwd2 La contrase�a nueva repetida
	 * @return 0: Contrase�a cambiada satisfactoriamente; 1: Si la OGPwd esta mal; 2: Si las contrase�as nuevas son distintas; 3: Algo salga mal
	 */
	public boolean cambiarContrasena (String nombreUsuario, String nuevaContrasena) {
		
		dbManager.open(false);
		
		boolean resultado = dbManager.cambiarContrasena(nombreUsuario, nuevaContrasena);
			
		dbManager.close();
		return  resultado; //No deberia pasar por aqui, entonces is hace return 3, algo va mal
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Dada una competicion, se devuelve un vector que contiene todos los eventos correspondientes a dicha competicion
	 * @param fecha
	 * @return
	 */

	public Vector<Evento> obtenerEventosPorCompeticion (Competicion competicion){

		dbManager.open(false);
		Vector<Evento> eventos= dbManager.obtenerEventosPorCompeticion(competicion);
		dbManager.close();
		return eventos;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public Vector<Pregunta> obtenerPreguntasAbiertasPorEvento (Evento evento){
		dbManager.open(false);
		Vector<Pregunta> preguntas = dbManager.obtenerPreguntasAbiertasPorEvento(evento);
		dbManager.close();
		return preguntas;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public Vector<Pregunta> obtenerPreguntasPorEvento (Evento evento){
		dbManager.open(false);
		Vector<Pregunta> preguntas = dbManager.obtenerPreguntasPorEvento(evento);
		dbManager.close();
		return preguntas;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Dada una pregunta devuelve todos los pronosticos asociados a esa pregunta
	 * 
	 * @param event El evento del que queremos obtener los pronosticos
	 * @return La colecci�n de pronosticos del evento pasado como parametro
	 */
	public Vector<Pronostico> obtenerPronosticosDePregunta (Pregunta p){
		dbManager.open(false);
		Vector<Pronostico> pronostico = dbManager.obtenerPronosticosDePregunta(p); //Si el evento existe, hace return de los pronosticos.
		dbManager.close();
		return pronostico;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Dado el parametro clave de usurario manda a la clase DataAccess buscar el usuario correspondiente
	 * 
	 * @param user Nombre del usuario que queremos obtener
	 * @return Null si el user no existe, el objeto Usuario al que corresponda ese nombre
	 */
	public Actor obtenerActor (String user) {

		Actor usuario = null;
		dbManager.open(false);
		if (dbManager.actorExistente(user)) { //Comprobar si el usuario existe
			usuario= dbManager.obtenerActor (user); //Pillar el user de la BD
		}
		dbManager.close();
		return usuario;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Devuelve todas las Competiciones presentes en la base de datos
	 * @return (Vector<Competicion>): vector con todas las Comepticiones presentes en la base de datos
	 */
	public Vector<Competicion> obtenerCompeticiones() {

		Vector<Competicion> competis=new Vector<Competicion>();
		dbManager.open(false);
		competis= dbManager.obtenerCompeticiones();
		dbManager.close();
		return competis;
	}

	public boolean anadirApuesta(String id, double cantidad, double cuota) {
		
		dbManager.open(false);
		int resultado = dbManager.anadirApuesta(id, cantidad, cuota);
		dbManager.close();
		
		return (resultado == 0);

	}

	public Vector<Usuario> obtenerGanadores(Pronostico p) {
		ArrayList<Apuesta> apuestas= dbManager.obtenerApuestas(p);
		//		ArrayList<Apuesta> apuestasGanadoras= new ArrayList<Apuesta>();
		Vector<Usuario> ganadores= new Vector<Usuario>();
		for (Apuesta a: apuestas) {
			if (a.getPronostico().getId().equals(p.getId()))
				ganadores.add(a.getUsuario());
		}
		return ganadores;
	}

	public void actualizarSaldos(Vector<Usuario> ganadores, Pronostico p) {
		dbManager.actualizarSaldos(ganadores, p);
	}


	public void cerrarApuesta(String idPregunta, String idPronostico) {

		
		dbManager.cerrarApuesta(idPregunta, idPronostico);
	
	}

	public Vector<Apuesta> obtenerApuestasAbiertasUsuario(String nombreUsuario) {

		Vector<Apuesta> apuestas =  new Vector<Apuesta>();
		dbManager.open(false);

		if (dbManager.actorExistente(nombreUsuario)) 
			apuestas = dbManager.obtenerApuestasAbiertasUsuario(nombreUsuario);

		dbManager.close();
		return apuestas;
	}
	
	public Apuesta obtenerApuesta(String id) {
		Apuesta apuesta = null;
		dbManager.open(false);

		apuesta = dbManager.obtenerApuesta(id);

		dbManager.close();
		return apuesta;
	}


	public Vector<Competicion> obtenerCompeticionesAdmin(String nAdmin) {
		dbManager.open(false);
		Vector<Competicion> competiciones= dbManager.obtenerCompeticionesAdmin(nAdmin);
		dbManager.close();
		return competiciones;
	}

	public Vector<Evento> obtenerEventosAdmin(String nAdmin) {
		dbManager.open(false);
		Vector<Evento> eventos= dbManager.obtenerEventosAdmin(nAdmin);
		dbManager.close();
		return eventos;
	}

	public Vector<Pregunta> obtenerPreguntasAdmin(String nAdmin) {
		dbManager.open(false);
		Vector<Pregunta> preguntas= dbManager.obtenerPreguntasAdmin(nAdmin);
		dbManager.close();
		return preguntas;
	}

	public Vector<Pronostico> obtenerPronosticosAdmin(String nAdmin) {
		dbManager.open(false);
		Vector<Pronostico> pronosticos= dbManager.obtenerPronosticosAdmin(nAdmin);
		dbManager.close();
		return pronosticos;
	}

	public Vector<Apuesta> obtenerApuestasDePronostico(String id) {

		Vector<Apuesta> apuestas = new Vector<Apuesta>();

		dbManager.open(false);

		apuestas = dbManager.obtenerApuestasDePronostico(id);

		dbManager.close();

		return apuestas;
	}

	public void depositar(double deposito) {
		dbManager.open(false);
		dbManager.depositar(IniciarSesionGUI.actor(),deposito);
		dbManager.close();
		
	}

	public int sugerirEvento(String nombreEvento, Date fechaEvento, Competicion competicion, String descripcion, Actor user) {

		dbManager.open(false);

		Date now = new Date();
		if (fechaEvento.before(now)) return 1; //Queremos crear un evento en un acontecimiento ya pasado.
		else if (dbManager.existeEvento(nombreEvento, fechaEvento, competicion)) return 2; //El evento que queremos a�adir ya existe.
		else {
			dbManager.sugerirEvento(nombreEvento, fechaEvento, competicion, descripcion, user);
		}
		dbManager.close();
		return 0;
	}

	public void sugerirCompeticion(String nombre, String deporte, char genero, String temporada, String descripcion, Usuario user) {
		dbManager.open(false);
		
		if (!dbManager.existeCompeticion("Competicion:" + nombre)) {
			dbManager.sugerirCompeticion(nombre, deporte, genero, temporada, descripcion, user);
		}
		//Si la competicion ya est� a�adida.
		dbManager.close();
	}

	public void sugerirPregunta(String enunciadoPregunta, Evento eventSelected, double minBet, Actor user) {
		dbManager.open(false);

		if (! dbManager.existePregunta(enunciadoPregunta, eventSelected)) //Si la Pregunta ya existe, saltara la creacion

			dbManager.sugerirPregunta(enunciadoPregunta, eventSelected, minBet, user);

		dbManager.close();
	}

	//devuelve 1 si existe el un informe con el mismo t�tulo, 0 si se ha a�adido correctamente.
	public void informarError(String titulo, String descripcion, Usuario usuario) {
		dbManager.open(false);
		dbManager.informarError(titulo, descripcion, usuario);
		dbManager.close();
	}

	//resuelto: determina si se quieren obtener informes resueltos o no.
	public Vector<InformeError> obtenerInformes(boolean resuelto) {
		dbManager.open(false);
		Vector<InformeError> informes;
		if(resuelto) informes= dbManager.obtenerInformesResueltos();
		else informes= dbManager.obtenerInformesNoResueltos();
		dbManager.close();
		return informes;
	}

	public void resolverProblema(InformeError problema) {
		dbManager.open(false);
		dbManager.resolverProblema(problema);
		dbManager.close();
	}

	public Competicion obtenerCompeticionPorNombre(String selectedItem) {
		dbManager.open(false);
		for (Competicion e: dbManager.obtenerCompeticiones()) {
			if (e.getNombre().equals(selectedItem)) return e;
		}
		dbManager.close();
		return null;
		
	}


	public void anadirPromocion(String code, Administrador actor, int cant, int num_veces, boolean tipo) {
		dbManager.open(false);		
	    dbManager.anadirPromocion(code, actor, cant, num_veces, tipo);
		dbManager.close();
		
	}

	public void anadirPromocionConComp(String code, Administrador actor, int cant, int num_veces, boolean tipo, Competicion comp) {
		dbManager.open(false);		
		dbManager.anadirPromocionConComp(code, actor, cant, num_veces, tipo, comp);
		dbManager.close();
		
		
	}

	public Vector<Apuesta> obtenerHistorialApuestasUsuario(String nombreUsuario) {
		
		dbManager.open(false);
		Vector<Apuesta> apuestas = dbManager.obtenerHistorialApuestasUsuario(nombreUsuario);
		dbManager.close();
		return apuestas;
	}
	
	public int aplicarPromocion(String text, Usuario actor) {
		int aplicada=0;
		dbManager.open(false);
		aplicada= dbManager.aplicarPromocion(text, actor);
		dbManager.close();
		return aplicada;
		
	}

	public Vector<Competicion> obtenerSugerenciasCompeticiones() {
		dbManager.open(false);
		Vector<Competicion> comps =dbManager.obtenerSugerenciasCompeticiones();
		dbManager.close();
		return comps;
	}

	public Vector<Evento> obtenerSugerenciasEventos() {
		dbManager.open(false);
		Vector<Evento> evs =dbManager.obtenerSugerenciasEventos();
		dbManager.close();
		return evs;
	}

	public Vector<Pregunta> obtenerSugerenciasPreguntas() {
		dbManager.open(false);
		Vector<Pregunta> pregs =dbManager.obtenerSugerenciasPreguntas();
		dbManager.close();
		return pregs;
	}

	public void hacerCompDefinitiva(Competicion c) {
		dbManager.open(false);
		dbManager.hacerCompDefinitiva(c);
		dbManager.close();
	}

	public void hacerEventoDfinitivo(Evento ev) {
		dbManager.open(false);
		dbManager.hacerEventoDfinitivo(ev);
		dbManager.close();
	}

	public void hacerPreguntaDefinitiva(Pregunta p) {
		dbManager.open(false);
		dbManager.hacerPreguntaDefinitiva(p);
		dbManager.close();
	}

	public void borrarCompeticion(Competicion c) {
		dbManager.open(false);
		dbManager.borrarCompeticion(c);
		dbManager.close();
	}

	public void borrarEvento(Evento ev) {
		dbManager.open(false);
		dbManager.borrarEvento(ev);
		dbManager.close();
	}

	public void borrarPregunta(Pregunta p) {
		dbManager.open(false);
		dbManager.borrarPregunta(p);
		dbManager.close();
	}

	public boolean cambiarCorreoE(String nombreUsuario, String nuevoCorreoElectronico) {
		dbManager.open(false);
		boolean resultado = dbManager.cambiarCorreoE(nombreUsuario,  nuevoCorreoElectronico);
		dbManager.close();
		return resultado;
	}

	public Vector<Evento> obtenerEventosAbiertosPorCompeticion(Competicion competicion) {
		
		dbManager.open(false);
		Vector<Evento> eventos= dbManager.obtenerEventosAbiertosPorCompeticion(competicion);
		dbManager.close();
		return eventos;
	}
}