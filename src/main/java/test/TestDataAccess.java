package test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import domain.*;

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


	public void anadirPromocion(Promocion p) {
		db.getTransaction().begin();
		Promocion pr = db.find(Promocion.class, p.getId());
		db.persist(pr);
		db.getTransaction().commit();
	}
	
	public void anadirUsuario(Usuario u) {
		db.getTransaction().begin();
		Usuario pr = db.find(Usuario.class, u.getNombreUsuario());
		db.persist(pr);
		db.getTransaction().commit();
	}
	
	public void borrarUsuario(Usuario u) {
		db.getTransaction().begin();
		Usuario p1=db.find(Usuario.class, u);
		db.remove(p1);
		db.getTransaction().commit();
	}
}