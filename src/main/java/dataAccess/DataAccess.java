package dataAccess;


import java.util.*;

import javax.persistence.*;

import configuration.*;
import domain.*;
import gui.IniciarSesionGUI;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccess  {

	protected static EntityManager  db;
	protected static EntityManagerFactory emf;

	ConfigXML c=ConfigXML.getInstance();

	public DataAccess (boolean initializeMode)  {

		System.out.println("Creating DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());
		open(initializeMode);

	}

	public DataAccess()  {	
		this(false);
	}


	/**
	 * This is the data access method that initializes the database with some events and questions.
	 * This method is invoked by the business logic (constructor of BLFacadeImplementation) when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	public void initializeDB(){

		db.getTransaction().begin();
		try {


			Calendar today = Calendar.getInstance();

			int month=today.get(Calendar.MONTH);
			month+=1;
			int year=today.get(Calendar.YEAR);
			if (month==12) { month=0; year+=1;}  


			Administrador admin = new Administrador("Admin1", "99999999T", "admin", "a", "b", UtilDate.nuevaFecha(1999, 2, 28), "admin", 'H', "admin@gmail.com", "999999999");

			Competicion laLigaSantander= new Competicion("La Liga Santander", "Futbol", 'M', "2022-2023", new String(), admin);

			Actor usuario1 = new Usuario("Usuario1", "11111111F", "usuario", "primero", "primero", UtilDate.nuevaFecha(2001,10,25), "usuario", 'M', "usuario@gmail.com", "675253642");
			Actor usuario2 = new Usuario("DemultiplexadosYCompilacionesMartinez", "22222222F", "Paco", "Martinez", "Martinez", UtilDate.nuevaFecha(1975,01,01), "123456", 'M', "DYCM@gmail.com", "675353642");

			Evento ev1 = new Evento("Atl�tico-Athletic", UtilDate.newDate(year,month,17),laLigaSantander,"", admin);
			Evento ev2 = new Evento( "Eibar-Barcelona", UtilDate.newDate(year,month,17),laLigaSantander,"", admin);
			/*			Evento ev3 = new Evento( "Getafe-Celta", UtilDate.newDate(year,month,17),LaLigaSantander,"", admin);
			Evento ev4 = new Evento( "Alav�s-Deportivo", UtilDate.newDate(year,month,17),LaLigaSantander,"", admin);
			Evento ev5 = new Evento("Espa�ol-Villareal", UtilDate.newDate(year,month,17),LaLigaSantander,"", admin);
			Evento ev6 = new Evento( "Las Palmas-Sevilla", UtilDate.newDate(year,month,17),LaLigaSantander,"", admin);
			Evento ev7 = new Evento( "Malaga-Valencia", UtilDate.newDate(year,month,17),LaLigaSantander,"", admin);
			Evento ev8 = new Evento("Girona-Legan�s", UtilDate.newDate(year,month,17),LaLigaSantander,"", admin);
			Evento ev9 = new Evento( "Real Sociedad-Levante", UtilDate.newDate(year,month,17),LaLigaSantander,"", admin);
			Evento ev10 = new Evento( "Betis-Real Madrid", UtilDate.newDate(year,month,17),LaLigaSantander,"", admin);
			 */
			laLigaSantander.anadirEvento(ev1);
			laLigaSantander.anadirEvento(ev2);
			/*			LaLigaSantander.anadirEvento(ev3);
			LaLigaSantander.anadirEvento(ev4);
			LaLigaSantander.anadirEvento(ev5);
			LaLigaSantander.anadirEvento(ev6);
			LaLigaSantander.anadirEvento(ev7);
			LaLigaSantander.anadirEvento(ev8);
			LaLigaSantander.anadirEvento(ev9);
			LaLigaSantander.anadirEvento(ev10);
			 */
			Pregunta p1;
			Pregunta p2;
			Pregunta p3;
			Pregunta p4;



			if (Locale.getDefault().equals(new Locale("es"))) {
				p1 = new Pregunta( "�Qui�n ganar� el partido?",ev1, 3,true, admin);
				p2 = new Pregunta("�Qui�n meter� el primer gol?",ev1, 0,true, admin);
				p3 = new Pregunta("�Qui�n ganar� el partido?",ev2, 0,true, admin);
				//p4 = new Pregunta( "�Cuantos goles se marcar�n?",ev2, 0,true, admin);
				//p5 = new Pregunta("�Qui�n ganar� el partido?",ev2, 0,true, admin);
				p4 = new Pregunta( "�Habra goles en la primera parte?",ev1, 0,true, admin);
			}
			else if (Locale.getDefault().equals(new Locale("en"))) {
				p1 = new Pregunta(  "Who will win the match?",ev1, 3,true, admin);
				p2 = new Pregunta(  "Who will score first?",ev1, 0,true, admin);
				p3 = new Pregunta(  "Who will win the match?",ev2, 0,true, admin);
				//p4 = new Pregunta(  "How many goals will be scored in the match?",ev2, 0,true, admin);
				//p5 = new Pregunta(  "Who will win the match?",ev7, 0,true, admin);
				p4 = new Pregunta(  "Will there be goals in the first half?",ev1, 0,true, admin);
			}			
			else {
				p1 = new Pregunta(  "Zeinek irabaziko du partidua?",ev1, 3,true, admin);
				p2 = new Pregunta(  "Zeinek sartuko du lehenengo gola?",ev1, 0,true, admin);
				p3 = new Pregunta(  "Zeinek irabaziko du partidua?",ev2, 0,true, admin);
				//p4 = new Pregunta(  "Zenbat gol sartuko dira?",ev2, 0,true, admin);
				//p5 = new Pregunta(  "Zeinek irabaziko du partidua?",ev7, 0,true, admin);
				p4 = new Pregunta(  "Golak sartuko dira lehenengo zatian?",ev1, 0,true, admin);

			}

			Pronostico pro1 = new Pronostico("Athletic Club", p1, 1.05, admin);
			Pronostico pro2 = new Pronostico("Athletico de Madrid", p1, 1.05, admin);
			Pronostico pro3 = new Pronostico(ResourceBundle.getBundle("Etiquetas").getString("Empate"), p1, 1.05, admin);
			Pronostico pro4 = new Pronostico("Unai Simon en propia.", p2, 1.05, admin);
			Pronostico pro5 = new Pronostico("Unai Simon a favor.", p2, 2.5, admin);
			Pronostico pro6 = new Pronostico("Toquero (en cualquiera).", p2, 1, admin);
			Pronostico pro7 = new Pronostico("Eibar", p3, 2, admin);
			Pronostico pro8 = new Pronostico("Barcelona", p3, 1.5, admin);
			Pronostico pro9 = new Pronostico(ResourceBundle.getBundle("Etiquetas").getString("Empate"), p3, 1.3, admin);
			Pronostico pro10 = new Pronostico("S�", p4, 1.5, admin);
			Pronostico pro11 = new Pronostico("No", p4, 10, admin);
			Pronostico pro12 = new Pronostico("Nc/Ns", p4, 1, admin);


			p1.anadirPronostico(pro1);
			p1.anadirPronostico(pro2);
			p1.anadirPronostico(pro3);
			p2.anadirPronostico(pro4);
			p2.anadirPronostico(pro5);
			p2.anadirPronostico(pro6);
			p3.anadirPronostico(pro7);
			p3.anadirPronostico(pro8);
			p3.anadirPronostico(pro9); 
			p4.anadirPronostico(pro10);
			p4.anadirPronostico(pro11);
			p4.anadirPronostico(pro12);

			/*
			//Anadir preguntas
			db.persist(p1);
			db.persist(p2);
			db.persist(p3);
			db.persist(p4);
			db.persist(p5);
			db.persist(p6); 

			//Anadir Evetos
			db.persist(ev1);
			db.persist(ev2);
			db.persist(ev3);
			db.persist(ev4);
			db.persist(ev5);
			db.persist(ev6);
			db.persist(ev7);
			db.persist(ev8);
			db.persist(ev9);
			db.persist(ev10);
			db.persist(ev11);
			db.persist(ev12);
			db.persist(ev13);
			db.persist(ev14);
			db.persist(ev15);
			db.persist(ev16);
			db.persist(ev17);
			db.persist(ev18);
			db.persist(ev19);
			db.persist(ev20);	
			 */

			//Anadir competiciones
			db.persist(laLigaSantander);//

			//A�adir usuarios
			db.persist(usuario1);
			db.persist(usuario2);
			db.persist(admin);

			db.getTransaction().commit();
			System.out.println("Db initialized");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 *//*
	public Pregunta createQuestion(Evento event, String question, float betMinimum) throws  QuestionAlreadyExist {

		System.out.println(">> DataAccess: createQuestion=> event= "+event+" question= "+question+" betMinimum="+betMinimum);

		Evento ev;
		try {
			ev = db.find(Evento.class, event.getNombre());
			Pregunta p = db.find(Pregunta.class,question);
			db.getTransaction().begin();
			ev.anadirPregunta(p);
			db.persist(ev);
			db.getTransaction().commit();
			return p;
		} catch (Exception e) {
			throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));
			//e.printStackTrace();
		}



		//if (ev.DoesQuestionExists(question)) throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));


		//Question q = ev.addQuestion(question, betMinimum);
		//db.persist(q);
		//db.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions property of Event class
		// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
		//db.getTransaction().commit();
		//return q;

	}
	  */

	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	/*public Vector<Date> getEventsMonth(Date date) {

		System.out.println(">> DataAccess: getEventsMonth");
		Vector<Date> res = new Vector<Date>();	

		Date firstDayMonthDate= UtilDate.firstDayMonth(date);
		Date lastDayMonthDate= UtilDate.lastDayMonth(date);

		TypedQuery<Date> query = db.createQuery("SELECT DISTINCT ev.eventDate FROM Event ev WHERE ev.eventDate BETWEEN ?1 and ?2",Date.class);
		query.setParameter(1, firstDayMonthDate);
		query.setParameter(2, lastDayMonthDate);
		List<Date> dates = query.getResultList();
		for (Date d:dates){
			System.out.println(d.toString());		 
			res.add(d);
		}
		return res;
	}*/


	public void open(boolean initializeMode){

		System.out.println("Opening DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());

		String fileName=c.getDbFilename();

		if (initializeMode) {
			fileName=fileName+";drop";
			System.out.println("Deleting the DataBase");
			//this.initializeDB();//anadido vidal	
		}

		if (c.isDatabaseLocal()) {
			emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
			db = emf.createEntityManager();

			if (initializeMode)//MAS VIDAL
				this.initializeDB();//VIDAL

		} else {
			Map<String, String> properties = new HashMap<String, String>();
			properties.put("javax.persistence.jdbc.user", c.getUser());
			properties.put("javax.persistence.jdbc.password", c.getPassword());

			emf = Persistence.createEntityManagerFactory("objectdb://"+c.getDatabaseNode()+":"+c.getDatabasePort()+"/"+fileName, properties);

			db = emf.createEntityManager();
		}

	}

	public void close(){

		db.close();
		System.out.println("DataBase closed");
	}

	/**
	 * Comprueba que nombre de usuario recivido corresonde a un usuario en la base de datos
	 * 
	 * @param user Usuario cuya existencia se ha de comprobar
	 * @return TRUE si corresponde y false si no hay ningun usuario que corresponda
	 */
	public boolean actorExistente(String user) {

		Actor u = db.find(Usuario.class, user);
		Actor o = db.find(Administrador.class, user);

		return (u != null || o != null);
	}

	/**
	 * Crea y anade a la base de datos un nuevo usuario a la base de datos con los datos introducidos
	 * 
	 * @param sexo (char): Masculino = 'M'; Femenino = 'F'; Otro = 'O'
	 * @param admin :si es true est� registrando un administrador.
	 */
	public void registrarUsuario(String nombreUsuario, String Dni, String nombre, String apellido1, String apellido2, Date fechaNacimiento, String contrasena, char sexo, String email,String tlfn, boolean admin) {

		db.getTransaction().begin();

		if(!admin) { //se a�ade un usuario
			Actor a = new Usuario(nombreUsuario,Dni,nombre,apellido1,apellido2,fechaNacimiento,contrasena,sexo,email,tlfn);
			db.persist(a);
		}
		else { //se a�ade un administrador
			Actor a = new Administrador(nombreUsuario,Dni,nombre,apellido1,apellido2,fechaNacimiento,contrasena,sexo,email,tlfn);
			db.persist(a);
		}

		db.getTransaction().commit();
	}

	/**
	 * Crea y a�ade a la base de datos un nuevo administrador con los datos introducidos
	 * 
	 * @param sexo (char): Masculino = 'M'; Femenino = 'F'; Otro = 'O'
	 */
	/*public void registrarAdmin(String id, String dNI, String nombre, String apellido1, String apellido2, Date fechaN, String contrasena, char sexo, String email,String tlfn) {

		db.getTransaction().begin();

		Actor a = new Administrador(id,  dNI,  nombre,  apellido1,  apellido2,  fechaN, contrasena,  sexo,  email, tlfn);
		db.persist(a);

		db.getTransaction().commit();
	}*/

	/**
	 * Dado el parametro clave de un usuario se devuelve el objeto correspondiente
	 * 
	 * @param user El nombre de usuario (parametro clave de la clase)
	 * @return El objeto usuario correspondiente al atributo clave
	 */
	public Actor obtenerActor(String user) {

		return db.find(Actor.class, user); 
	}

	/**
	 * Dado el parametro clave de un usuario comprueba si es un administrador
	 * 
	 * @param user El mobre de usuario (parametro clave de la clase)
	 * @return TRUE si es un administrador, FALSE si no lo es
	 */
	public boolean esAdmin(String user) {

		Actor a = db.find(Administrador.class, user); //busca el usuario con en parametro clave deseado sin realizar ninguna comprobacion de existencia, ya que ha sido hecha antes de llamar el metodo
		return a != null;
	}
	/**
	 * Compruba que una contrase�a dada concide con la registrada al usuario dado
	 * 
	 * @param user Usuario al que realizar la comprobacion de contrase�a
	 * @param password Contrase�a a comprobar
	 * @return TRUE si la contrase�a dada coincide con la asociada, FALSE si no
	 */
	public boolean comprobarContrasena(String user, String password) {

		Actor u = db.find(Administrador.class, user); //obtiene el usuario de la base de datos
		if (u==null) {
			u=  db.find(Usuario.class, user);
		}
		return u.checkPswd(password);
	}


	public boolean cambiarContrasena(String nombreUsuario, String nuevaContrasena) {
		db.getTransaction().begin();
		Usuario a = (Usuario) db.find(Actor.class, nombreUsuario);
		boolean resultado = a.cambiarContrasena(nuevaContrasena);
		db.getTransaction().commit();
		return resultado;
	}

	////////////////////////////////////////////////////////////////



	/**
	 * Comprueba que la competicion dada existe
	 * 
	 * @param id Identificador de la competicion
	 * @return TRUE si el objeto esta en la base de datos, FALSE si no
	 */
	public boolean existeCompeticion(String id) {

		Competicion c = db.find(Competicion.class, id);
		return c != null;
	}

	public boolean existeEvento(String nombre, Date fecha, Competicion comp) {

		return (db.find(Evento.class, comp.getId() + "Evento:" + nombre + "Fecha:" + fecha.toString()) != null);
	}

	/**
	 * Comprueba la existercia de una pregunta
	 * @param minBet 
	 * @param evento 
	 * 
	 * @param p El objeto pregunta a comprobar
	 * @return TRUE si esta en la base de datos, FALSE si no
	 */
	public boolean existePregunta(String enunciado, Evento evento) {

		Pregunta pregunta = db.find(Pregunta.class, evento.getId() + "Pregunta:" + enunciado);

		return (pregunta != null);
	}

	public boolean existePronostico(String respuesta, Pregunta pregunta) {

		return (null != db.find(Pronostico.class, pregunta.getId() + "Pronostico:" + respuesta));
	}

	public boolean existeApuesta(String string) {
		return (null != db.find(Apuesta.class,string));
	}

	/**
	 * Crea y a�ade una nueva competicion
	 * 
	 * @param id
	 * @param nombre
	 * @param deporte
	 * @param genero
	 * @param temporada
	 * @param descripcion
	 */

	public Competicion crearCompeticion(String nombre, String deporte, char genero, String temporada, String descripcion, Actor admin) {

		db.getTransaction().begin();
		Administrador admin0= (Administrador) db.find(Actor.class, admin.getNombreUsuario());
		Competicion competicion = new Competicion(nombre,  deporte,  genero,  temporada, descripcion, admin0);
		db.persist(competicion);
		db.getTransaction().commit();

		return competicion;
	}

	public Evento crearEvento(String nombre, Date fecha, Competicion comp,String descripcion, Actor admin) {

		db.getTransaction().begin();

		Administrador admin0= (Administrador) db.find(Actor.class, admin.getNombreUsuario());

		Competicion competicion = this.db.find(Competicion.class, comp.getId());

		Evento evento = new Evento(nombre, fecha, competicion,descripcion, admin0);
		//evento.getComp().anadirEvento(evento); //esto en vez de a�adir el evento en la constructora.
		db.persist(evento);

		db.getTransaction().commit();

		return evento;
	}

	public Pregunta crearPregunta(String enunciado, Evento evento, double minBet, boolean estado, Actor admin) {

		db.getTransaction().begin();

		Administrador admin0= (Administrador) db.find(Actor.class, admin.getNombreUsuario());
		Evento evento0= db.find(Evento.class, evento.getId());

		Pregunta pregunta = new Pregunta(enunciado, evento0, minBet, estado, admin0);

		db.persist(pregunta);
		db.getTransaction().commit();

		return pregunta;
	}

	/**
	 * Crea y a�ade un pronostico a la base de datos
	 * 
	 * @param respuesta
	 * @param tasa
	 * @param pregunta
	 */
	public int crearPronostico(String respuesta, Pregunta pregunta, double ganancia, Actor admin){

		db.getTransaction().begin();
		Actor admin0= (Administrador) db.find(Actor.class, admin.getNombreUsuario());
		Pregunta pregunta0= db.find(Pregunta.class, pregunta.getId());
		Pronostico pr = new Pronostico(respuesta, pregunta0, ganancia, admin0);
		db.persist(pr);	
		db.getTransaction().commit();
		return 0;
	}

	public int crearApuesta(Pronostico pronostico, Usuario usuario, double cantidad) {

		this.open(false);
		ArrayList<Promocion> promo= new ArrayList<Promocion>();
		double cant=cantidad;
		db.getTransaction().begin();


		Usuario u = db.find(Usuario.class, usuario.getNombreUsuario());
		Pronostico p = db.find(Pronostico.class, pronostico.getId());
		
		if(u==null | p==null) return 4;

		if(!u.getPromos_abiertas().isEmpty()) {
			
		for(Promocion e: u.getPromos_abiertas()) {
			if(e.getCompeticion().getId().equals(p.getPregunta().getEvento().getComp().getId())) {
				promo.add(e);
			}
		}
		
		
		for(Promocion b: promo) {
			if(!b.isTipo()) {
				double aux2=b.getCant();
				cant=(cantidad + aux2);	
			}
			else {
				double aux= b.getCant();
				cant =(cantidad + (cantidad*(aux/100.0)));
			}
			int index= u.getPromos_abiertas().indexOf(b);
			u.getPromos_abiertas().remove(index);
		}
		
		}
		
		Apuesta apuesta = new Apuesta (pronostico,usuario,cant);

		if (u.getSaldo() >= cantidad) {
			if (p.getPregunta().getMinBet() < cant) {
				u.anadirApuesta(apuesta);
				p.anadirApuesta(apuesta);
				u.actualizarSaldo(-cantidad);
				db.persist(apuesta);
				db.persist(u);
				IniciarSesionGUI.cambiarActor(u);
				db.getTransaction().commit();
				return 0;
			} else {
				db.getTransaction().rollback();
				return 1;
			} 
		}else {
			db.getTransaction().rollback();
			return 2;
		}


	}


	/**
	 * Devuelve un vector con todas las Competiciones presentes en la base de datos
	 */
	public Vector<Competicion> obtenerCompeticiones() {

		Vector<Competicion> competis = new Vector<Competicion>();

		TypedQuery<Competicion> query = db.createQuery("SELECT c FROM Competicion c WHERE c.sugerencia=false",Competicion.class);   
		List<Competicion> dates = query.getResultList();

		for (Competicion e: dates) {
			competis.add(e);
		}

		return competis;
	}

	////////////////////////////////////////////////////////////////////////////////////

	public Vector<Evento> obtenerEventosPorCompeticion(Competicion competicion) {

		Vector<Evento> eventos = new Vector<Evento>();


		TypedQuery<Evento> query = db.createQuery("SELECT e FROM Evento e WHERE e.comp=?1 and e.sugerencia=false",Evento.class);
		query.setParameter(1, competicion);
		List<Evento> dates = query.getResultList();

		for (Evento evento: dates) 
			eventos.add(evento);

		return eventos;
	}

	//////////////////////////////////////////////////////////////////////////////////////

	/*public Vector<Pregunta> obtenerPreguntas() {

		Vector<Pregunta> preguntas = new Vector<Pregunta>();

		TypedQuery<Pregunta> query = db.createQuery("SELECT e FROM Pregunta e",Pregunta.class);   
		List<Pregunta> dates = query.getResultList();

		preguntas.addAll(dates);

		return preguntas;
	}*/

	public Pregunta obtenerPregunta(String enunciado, Evento evento) {

		Pregunta pregunta = db.find(Pregunta.class, evento.getId() + "Pregunta:" + enunciado);
		if (pregunta.getEnunciado().equals(enunciado))
			return pregunta; 

		return null;
	}


	/*public Vector<Pronostico> obtenerPronosticos() {

		Vector<Pronostico> eventos = new Vector<Pronostico>();

		TypedQuery<Pronostico> query = db.createQuery("SELECT e FROM Pronostico e",Pronostico.class);   
		List<Pronostico> dates = query.getResultList();

		for (Pronostico evento: dates) {
			eventos.add(evento);
		}

		return eventos;
	}*/


	public Vector<Pregunta> obtenerPreguntasPorEvento(Evento evento) {
		Vector<Pregunta> preguntas = new Vector<Pregunta>();

		TypedQuery<Pregunta> query = db.createQuery("SELECT p FROM Pregunta p WHERE p.evento=?1 and p.sugerencia=false",Pregunta.class);   
		query.setParameter(1, evento);
		List<Pregunta> dates = query.getResultList();

		for(Pregunta p:dates) {
			preguntas.add(p);
		}

		return preguntas;
	}

	public Vector<Pregunta> obtenerPreguntasAbiertasPorEvento(Evento evento) {

		Vector<Pregunta> preguntas = new Vector<Pregunta>();

		TypedQuery<Pregunta> query = db.createQuery("SELECT p FROM Pregunta p WHERE p.evento=?1 AND p.estado=true and p.sugerencia=false",Pregunta.class);   
		query.setParameter(1, evento);
		List<Pregunta> dates = query.getResultList();

		for(Pregunta p:dates) 
			preguntas.add(p);

		return preguntas;
	}

	/**
	 * Devuelve una coleccion de pronosticos asiciados a una pregunta
	 * 
	 * @param p La pregunta de la que se quieren saber los pronosticos
	 * @return La coleccion de pronosticos asociados a la pregunta
	 */
	public Vector<Pronostico> obtenerPronosticosDePregunta(Pregunta p) {

		Vector<Pronostico> pronosticos = new Vector<Pronostico>();

		TypedQuery<Pronostico> query = db.createQuery("SELECT p FROM Pronostico p WHERE p.pregunta=?1",Pronostico.class);   
		query.setParameter(1, p);
		List<Pronostico> dates = query.getResultList();

		for(Pronostico pronos:dates) 
			pronosticos.add(pronos);

		return pronosticos;
	}

	public int anadirApuesta(String id, double cant, double cuota) {

		db.getTransaction().begin();
		Apuesta a = db.find(Apuesta.class, id);
		if (a.anadirSubApuesta(cant, cuota)==0) {
			a.getUsuario().actualizarSaldo(-cant);
			db.getTransaction().commit();
			return 0;
		}

		db.getTransaction().rollback();

		return 1; 
	}

	public boolean puedeAnadirApuesta(String id) {
		this.open(false);
		Apuesta a = db.find(Apuesta.class, id);
		return a.puedeAnadirApuesta();
	}

	public ArrayList<Apuesta> obtenerApuestas(Pronostico p) {

		this.open(false);
		ArrayList<Apuesta> apuestas= new ArrayList<Apuesta>();

		TypedQuery<Apuesta> query = db.createQuery("SELECT a FROM Apuesta a WHERE a.pronostico=?1 ", Apuesta.class);   
		query.setParameter(1, p);
		List<Apuesta> apuestasa = query.getResultList();
		for (Apuesta e: apuestasa) {
			apuestas.add(e);
		}
		this.close();
		return apuestas;
	}

	public void actualizarSaldos(Vector<Usuario> ganadores, Pronostico p) {

		this.open(false);
		db.getTransaction().begin();

		for (Usuario e: ganadores) {
			Usuario usuario = db.find(Usuario.class, e.getNombreUsuario());
			for (Apuesta a:usuario.getApuestas()) 
				if (a.getPronostico().getId().equals(p.getId())) 
					usuario.actualizarSaldo(a.calcularGananciaTotal());

		}
		db.getTransaction().commit();
		this.close();
	}

	public void cerrarApuesta(String idPregunta, String idPronostico) {

		this.open(false);
		db.getTransaction().begin();


		Pregunta pregunta = db.find(Pregunta.class, idPregunta);
		Pronostico pronostico = db.find(Pronostico.class, idPronostico);

		Evento evento= db.find(Evento.class, pregunta.getEvento().getId());

		pregunta.setEstado(false);
		pregunta.fijarResultado(pronostico);

		evento.comprobarAbierto();

		TypedQuery<Apuesta> query = db.createQuery("SELECT a FROM Apuesta a WHERE a.pronostico.pregunta=?1 AND a.abierta=?2", Apuesta.class);   
		query.setParameter(1, pregunta);
		query.setParameter(2, true);
		List<Apuesta> apuestas = query.getResultList();

		System.out.println("apuestas cerradas: " + apuestas.size());
		for (Apuesta apuesta: apuestas)
			apuesta.cerrarApuesta();
		
		db.persist(evento);
		db.getTransaction().commit();

		this.close();
	}

	public Vector<Apuesta> obtenerApuestasDePronostico(String id) {

		Vector<Apuesta> apuestas = new Vector<Apuesta>();

		Pronostico pronostico = (Pronostico)db.find(Pronostico.class, id);

		TypedQuery<Apuesta> query = db.createQuery("SELECT a FROM Apuesta a WHERE a.pronostico.id =?1",Apuesta.class);   
		query.setParameter(1, id);

		List<Apuesta> lista =  query.getResultList();

		for (Apuesta apu: lista)
			apuestas.add(apu);

		return apuestas;
	}

	public Vector<Apuesta> obtenerApuestasAbiertasUsuario(String nombreUsuario) {

		Vector<Apuesta> apuestas= new Vector<Apuesta>();
		Actor usuario = db.find(Actor.class, nombreUsuario);

		
		TypedQuery<Apuesta> query = db.createQuery("SELECT a FROM Apuesta a WHERE a.usuario =?1 AND a.abierta = ?2", Apuesta.class);   
		query.setParameter(1, usuario);
		query.setParameter(2, true);

		List<Apuesta> lista =  query.getResultList();
		for (Apuesta apu: lista)
			apuestas.add(apu);

		return apuestas;
	}

	public Apuesta obtenerApuesta(String id) {

		return db.find(Apuesta.class, id);
	}

	public Vector<Competicion> obtenerCompeticionesAdmin(String nAdmin) {
		Vector<Competicion> competiciones = new Vector<Competicion>();

		Actor admin0= (Administrador)db.find(Actor.class, nAdmin);
		TypedQuery<Competicion> query = db.createQuery("SELECT c FROM Competicion c WHERE c.admin=?1",Competicion.class);
		query.setParameter(1, admin0);
		List<Competicion> comps = query.getResultList();

		for (Competicion c: comps) {
			competiciones.add(c);
		}

		return competiciones;
	}

	public Vector<Evento> obtenerEventosAdmin(String nAdmin) {
		Vector<Evento> eventos = new Vector<Evento>();

		Actor admin0= (Administrador)db.find(Actor.class, nAdmin);
		TypedQuery<Evento> query = db.createQuery("SELECT e FROM Evento e WHERE e.admin=?1",Evento.class);
		query.setParameter(1, admin0);
		List<Evento> evnts = query.getResultList();

		for (Evento e: evnts) {
			eventos.add(e);
		}

		return eventos;
	}

	public Vector<Pregunta> obtenerPreguntasAdmin(String nAdmin) {
		Vector<Pregunta> preguntas = new Vector<Pregunta>();

		Actor admin0= (Administrador)db.find(Actor.class, nAdmin);
		TypedQuery<Pregunta> query = db.createQuery("SELECT p FROM Pregunta p WHERE p.admin=?1",Pregunta.class);
		query.setParameter(1, admin0);
		List<Pregunta> pregnts = query.getResultList();

		for (Pregunta p: pregnts) {
			preguntas.add(p);
		}

		return preguntas;
	}

	public Vector<Pronostico> obtenerPronosticosAdmin(String nAdmin) {

		Vector<Pronostico> pronosticos = new Vector<Pronostico>();

		Actor admin0= (Administrador)db.find(Actor.class, nAdmin);
		TypedQuery<Pronostico> query = db.createQuery("SELECT p FROM Pronostico p WHERE p.admin=?1",Pronostico.class);
		query.setParameter(1, admin0);
		List<Pronostico> prontc = query.getResultList();

		for (Pronostico p: prontc) {
			pronosticos.add(p);
		}

		return pronosticos;
	}

	public void depositar(Actor actor, double deposito) {
		db.getTransaction().begin();
		Usuario u= (Usuario) db.find(Actor.class, actor);
		IniciarSesionGUI.cambiarActor(u);
		u.actualizarSaldo(deposito);
		db.getTransaction().commit();
	}

	public void sugerirEvento(String nombreEvento, Date fechaEvento, Competicion competicion, String descripcion, Actor user) {
		db.getTransaction().begin();

		Usuario user0= (Usuario) db.find(Actor.class, user.getNombreUsuario());

		Evento evento = new Evento(nombreEvento, fechaEvento, competicion, descripcion, user0);

		db.persist(evento);

		db.getTransaction().commit();

	}

	public void sugerirCompeticion(String nombre, String deporte, char genero, String temporada, String descripcion, Usuario user) {
		db.getTransaction().begin();
		Usuario user0= (Usuario) db.find(Actor.class, user.getNombreUsuario());
		Competicion competicion = new Competicion(nombre,  deporte,  genero,  temporada, descripcion, user0);
		db.persist(competicion);
		db.getTransaction().commit();
	}

	public void sugerirPregunta(String enunciadoPregunta, Evento eventSelected, double minBet, Actor user) {
		db.getTransaction().begin();

		Usuario user0= (Usuario) db.find(Actor.class, user.getNombreUsuario());

		Pregunta pregunta = new Pregunta(enunciadoPregunta, eventSelected, minBet, user0);

		db.persist(pregunta);
		db.getTransaction().commit();
	}

	public void informarError(String titulo, String descripcion, Usuario usuario) {
		db.getTransaction().begin();
		Usuario usuario0= db.find(Usuario.class, usuario.getNombreUsuario());
		InformeError informe= new InformeError(titulo, usuario0);
		if(!descripcion.equals("")) informe.setDescripcion(descripcion);
		db.persist(informe);
		db.getTransaction().commit();
	}

	public void resolverProblema(InformeError problema) {
		db.getTransaction().begin();
		InformeError problema0= db.find(InformeError.class, problema.getId());
		problema0.setResuelto(true);
		problema0.setFechaResuelto(new Date());
		db.getTransaction().commit();

	}

	public Vector<InformeError> obtenerInformesResueltos() {
		Vector<InformeError> informes = new Vector<InformeError>();

		TypedQuery<InformeError> query = db.createQuery("SELECT i FROM InformeError i WHERE i.resuelto=true",InformeError.class);
		List<InformeError> resultados = query.getResultList();

		for (InformeError ie: resultados) {
			informes.add(ie);
		}
		return informes;
	}

	public Vector<InformeError> obtenerInformesNoResueltos() {
		Vector<InformeError> informes = new Vector<InformeError>();

		TypedQuery<InformeError> query = db.createQuery("SELECT i FROM InformeError i WHERE i.resuelto=false",InformeError.class);
		List<InformeError> resultados = query.getResultList();

		for (InformeError ie: resultados) {
			informes.add(ie);
		}
		return informes;
	}

	public void anadirPromocionConComp(String code, Administrador actor, int cant, int num_veces, boolean tipo, Competicion comp) {
		db.getTransaction().begin();
		Promocion promo = new Promocion (code,  actor,  cant,  num_veces, comp, tipo);
		if (null!= db.find(Promocion.class, promo.getId())) {
			Promocion promocion= db.find(Promocion.class, promo.getId());
			promocion.setNum_veces(promocion.getNum_veces()+num_veces);
			db.persist(promocion);
		}
		else db.persist(promo);

		db.getTransaction().commit();


	}

	public void anadirPromocion(String code, Administrador actor, int cant, int num_veces, boolean tipo) {
		db.getTransaction().begin();
		Promocion promo = new Promocion (code,  actor,  cant,  num_veces, tipo);
		if (null!= db.find(Promocion.class, promo.getId())) {
			Promocion promocion= db.find(Promocion.class, promo.getId());
			promocion.setNum_veces(promocion.getNum_veces()+num_veces);
			db.persist(promocion);
		}
		else db.persist(promo);
		db.getTransaction().commit();

	}

	public Vector<Apuesta> obtenerHistorialApuestasUsuario(String nombreUsuario) {

		Vector<Apuesta> apuestas = new Vector<Apuesta>();

		TypedQuery<Apuesta> query = db.createQuery("SELECT a FROM Apuesta a WHERE a.usuario.nombreUsuario =?1 AND a.abierta = ?2" , Apuesta.class);
		query.setParameter(1, nombreUsuario);
		query.setParameter(2, false);
		List<Apuesta> resultados = query.getResultList();

		for (Apuesta apu: resultados)
			apuestas.add(apu);

		return apuestas;
	}

	public int aplicarPromocion(String text, Usuario actor) {
		try {
		TypedQuery<Promocion> query = db.createQuery("SELECT p FROM Promocion p",Promocion.class);
		query.setParameter(1, text);

		List<Promocion> resultados = query.getResultList();
		Promocion resul= new Promocion("0");
		for(Promocion e: resultados) {
			if(e.getCode().equals(text)) {
				resul= e;
			}
		}
		
			for (Usuario a: resul.getUsuarios()) {
				if (a.getDNI().equals(actor.getDNI())) return 1;
			}
			
			db.getTransaction().begin();
			Promocion mod= db.find(Promocion.class, resul);
			if(mod.getNum_veces()<=0) return 2;
			mod.setNum_veces(mod.getNum_veces()-1);
			Usuario user= db.find(Usuario.class, actor);
			mod.anadirUsuario(user);

			if(mod.getCompeticion()==null && !mod.isTipo()) {
				
				user.setSaldo(user.getSaldo()+mod.getCant());
				
			}else user.anadirPromo(mod);	

			db.persist(mod);
			db.persist(user);
			db.getTransaction().commit();
			
			
		}catch(NullPointerException e) {
			return 3;
			
		}
		return 0;
	}
	

	public Vector<Competicion> obtenerSugerenciasCompeticiones() {
		Vector<Competicion> comps = new Vector<Competicion>();

		TypedQuery<Competicion> query = db.createQuery("SELECT c FROM Competicion c WHERE c.sugerencia=true",Competicion.class);
		List<Competicion> resultados = query.getResultList();

		for(Competicion c:resultados) {
			comps.add(c);
		}
		return comps;
	}

	public Vector<Evento> obtenerSugerenciasEventos() {
		Vector<Evento> evs = new Vector<Evento>();

		TypedQuery<Evento> query = db.createQuery("SELECT e FROM Evento e WHERE e.sugerencia=true",Evento.class);
		List<Evento> resultados = query.getResultList();

		for(Evento c:resultados) {
			evs.add(c);
		}
		return evs;
	}

	public Vector<Pregunta> obtenerSugerenciasPreguntas() {
		Vector<Pregunta> pregs = new Vector<Pregunta>();

		TypedQuery<Pregunta> query = db.createQuery("SELECT p FROM Pregunta p WHERE p.sugerencia=true",Pregunta.class);
		List<Pregunta> resultados = query.getResultList();

		for(Pregunta c:resultados) {
			pregs.add(c);
		}
		return pregs;
	}

	public void hacerCompDefinitiva(Competicion c) {
		db.getTransaction().begin();
		Competicion comp=db.find(Competicion.class, c);
		comp.setActor(IniciarSesionGUI.actor());
		comp.setNombreActor(IniciarSesionGUI.actor().getNombreUsuario());
		comp.setSugerencia(false);
		comp.setEventos();
		db.getTransaction().commit();
	}

	public void hacerEventoDfinitivo(Evento ev) {
		db.getTransaction().begin();
		Evento evento=db.find(Evento.class, ev);
		Competicion comp=db.find(Competicion.class, evento.getComp().getId());
		evento.setActor(IniciarSesionGUI.actor());
		evento.setNombreActor(IniciarSesionGUI.actor().getNombreUsuario());
		evento.setSugerencia(false);
		evento.setPreguntas();
		comp.anadirEvento(evento);
		db.getTransaction().commit();
	}

	public void hacerPreguntaDefinitiva(Pregunta p) {
		db.getTransaction().begin();
		Pregunta preg=db.find(Pregunta.class, p);
		Evento ev=db.find(Evento.class, p.getEvento().getId());
		preg.setActor(IniciarSesionGUI.actor());
		preg.setNombreActor(IniciarSesionGUI.actor().getNombreUsuario());
		preg.setSugerencia(false);
		preg.setPronosticos();
		preg.setEstado(true);
		preg.setResultado(null);
		ev.anadirPregunta(preg);
		db.getTransaction().commit();
	}

	public void borrarCompeticion(Competicion c2) {
		db.getTransaction().begin();
		Competicion c=db.find(Competicion.class, c2);
		db.remove(c);
		db.getTransaction().commit();
	}

	public void borrarEvento(Evento ev) {
		db.getTransaction().begin();
		Evento c=db.find(Evento.class, ev);
		db.remove(c);
		db.getTransaction().commit();
	}

	public void borrarPregunta(Pregunta p) {
		db.getTransaction().begin();
		Pregunta preg=db.find(Pregunta.class, p);
		db.remove(preg);
		db.getTransaction().commit();
	}

	public boolean cambiarCorreoE(String nombreUsuario, String nuevoCorreoElectronico) {

		db.getTransaction().begin();
		Usuario usuario = (Usuario) db.find(Actor.class, nombreUsuario);
		boolean resultado = usuario.cambiarCorreoElectronico(nuevoCorreoElectronico);
		db.getTransaction().commit();
		return resultado;
	}

	public Vector<Evento> obtenerEventosAbiertosPorCompeticion(Competicion competicion) {

		Vector<Evento> eventos = new Vector<Evento>();

		Competicion competi = db.find(Competicion.class, competicion.getId());

		TypedQuery<Evento> query = db.createQuery("SELECT e FROM Evento e WHERE e.comp=?1 and e.sugerencia=false and e.estado=true",Evento.class);
		query.setParameter(1, competi);
		List<Evento> listEventos = query.getResultList();

		for (Evento evento: listEventos) 
			eventos.add(evento);

		return eventos;
	}

	/*
	 * public Vector<Evento> obtenerEventosPorCompeticion(Competicion competicion) {

		Vector<Evento> eventos = new Vector<Evento>();


		TypedQuery<Evento> query = db.createQuery("SELECT e FROM Evento e WHERE e.comp=?1 and e.sugerencia=false",Evento.class);
		query.setParameter(1, competicion);
		List<Evento> dates = query.getResultList();

		for (Evento evento: dates) 
			eventos.add(evento);

		return eventos;
	}
	 * 
	 */
}
