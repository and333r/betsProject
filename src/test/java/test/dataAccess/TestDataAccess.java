package test.dataAccess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import domain.*;
import gui.IniciarSesionGUI;

public class TestDataAccess {
	protected  EntityManager  db;
	protected  EntityManagerFactory emf;

	ConfigXML  c=ConfigXML.getInstance();


	public TestDataAccess()  {
		
		System.out.println("Creating TestDataAccess instance");

		open();
		
	}

	
	public void open(){
		
		System.out.println("Opening TestDataAccess instance ");

		String fileName=c.getDbFilename();
		
		if (c.isDatabaseLocal()) {
			  emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
			  db = emf.createEntityManager();
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
	
	
	public Promocion obtenerPromo (List<Promocion> promociones, String text) {
		
		Promocion resul= new Promocion("0");		
		for(Promocion e: promociones) {
			if(e.getCode().equals(text)) {
			resul= e;
			}
		}	
		return resul;
	}
	
	public boolean promoUsada(Promocion resul, Usuario actor) {
		
		for (Usuario a: resul.getUsuarios()) {
			if (a.getDNI().equals(actor.getDNI())) return true;
		}
		
		return false;
	}

	public int aplicarPromocion(String text, Usuario actor) {
		try {
		
		TypedQuery<Promocion> query = db.createQuery("SELECT p FROM Promocion p",Promocion.class);
		query.setParameter(1, text);
		List<Promocion> resultados = query.getResultList();

		
		Promocion resul = this.obtenerPromo(resultados,text);
		boolean usado = this.promoUsada(resul, actor);		
		if(usado) return 1;
			
			db.getTransaction().begin();
			Promocion mod= db.find(Promocion.class, resul);
			if(mod.getNum_veces()<=0) return 2;
			mod.setNum_veces(mod.getNum_veces()-1);
			Usuario user= db.find(Usuario.class, actor);
			mod.anadirUsuario(user);

			if(mod.getCompeticion()==null && !mod.isTipo()) user.setSaldo(user.getSaldo()+mod.getCant());
			else user.anadirPromo(mod);	

			db.persist(mod);
			db.persist(user);
			db.getTransaction().commit();
			
			
		}catch(NullPointerException e) {
			return 3;
			
		}
		return 0;
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
	
	public void borrarCompeticion(Competicion c2) {
		db.getTransaction().begin();
		Competicion c=db.find(Competicion.class, c2);
		db.remove(c);
		db.getTransaction().commit();
	}
	
	public void borrarPromocion(Promocion p) {
		db.getTransaction().begin();
		Promocion p1=db.find(Promocion.class, p);
		db.remove(p1);
		db.getTransaction().commit();
	}
	
	public Competicion crearCompeticion(String nombre, String deporte, char genero, String temporada, String descripcion, Actor admin) {

		db.getTransaction().begin();
		Administrador admin0= (Administrador) db.find(Actor.class, admin.getNombreUsuario());
		Competicion competicion = new Competicion(nombre,  deporte,  genero,  temporada, descripcion, admin0);
		db.persist(competicion);
		db.getTransaction().commit();

		return competicion;
	}


	public int anadirPromocion(Promocion p) {
		db.getTransaction().begin();
		Promocion pr = p;
		db.persist(pr);
		db.getTransaction().commit();
		return 0;
	}
	
	public void anadirUsuario(Usuario u) {
		db.getTransaction().begin();
		Usuario pr = u;
		db.persist(pr);
		db.getTransaction().commit();
	}

	
	public void borrarUsuario(Usuario u) {
		db.getTransaction().begin();
		Usuario p1=db.find(Usuario.class, u);
		db.remove(p1);
		db.getTransaction().commit();
	}
	
	public int crearPronostico(Pronostico pronos){

		db.getTransaction().begin();
		Pronostico pr=pronos;
		db.persist(pr);	
		db.getTransaction().commit();
		return 0;
	}
	
	public void borrarPronostico(Pronostico u) {
		db.getTransaction().begin();
		Pronostico p1=db.find(Pronostico.class, u);
		db.remove(p1);
		db.getTransaction().commit();
	}
	
	
	public int crearApuesta(Pronostico pronostico, Usuario usuario, double cantidad) {

		this.open();
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


	public void borrarApuesta(Apuesta apuesta) {
		db.getTransaction().begin();
		Apuesta p1=db.find(Apuesta.class, apuesta);
		db.remove(p1);
		db.getTransaction().commit();
		
	}


	public void anadirPregunta(Pregunta pr) {
		db.getTransaction().begin();
		Pregunta pre=pr;
		db.persist(pre);	
		db.getTransaction().commit();
		
	}


	public void borrarPregunta(Pregunta pr) {
		db.getTransaction().begin();
		Pregunta p1=db.find(Pregunta.class, pr);
		db.remove(p1);
		db.getTransaction().commit();
		
		
	}

}