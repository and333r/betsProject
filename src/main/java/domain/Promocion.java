package domain;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity

public class Promocion {

	@Id
	private String id;
	private String code; //El c�digo que hay que meter.
	private int cant; //La cantidad de la promocion (Ya sea el porcentaje o el int)
	private int num_veces; //El n�mero de veces que se puede aplicar esta promoci�n --> En caso de ser -1, no tiene l�mite (Se puede aplicar de manera infinita para distinos usuarios).
	private boolean tipo; //Si es true significa que la cifra es un porcentaje, si es false, significa que es un integer normal
	private boolean restricciones; //Si es true signiofica que la promocion es valida solo en ciertas competiciones. Se puede ampliar a solo en ciertos eventos y preguntas tambi�n.

	@OneToOne
	private Administrador admin; //El administrador que ha creado la promoción. 
	@OneToOne
	private Competicion competicion;
	private String nombreComp;

	@OneToMany (cascade =CascadeType.PERSIST)
	private ArrayList<Usuario> usuarios; //Los usuarios que han aplicado la promoci�n --> Para evitar que se use dos veces.


	public Promocion (String codes, Administrador admin, int cant, int num_veces, boolean tipo) {
		this.id= new String() + code +  cant + tipo; // En caso de que haya dos promociones con el mismo id, es decir, la misma cantidad y el mismo numero de veces, lo que se har� ser� actualizar 
		//la ya existente, añadiendo el numero de veces deseado. Por eso en el id no est� el admin.
		this.code=codes;
		this.admin=admin;
		this.cant=cant;
		this.num_veces=num_veces;
		this.restricciones=false;
		this.tipo=tipo;
		this.competicion=null;
		usuarios= new ArrayList<Usuario>();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Promocion (String code, Administrador admin, int cant, int num_veces, Competicion competi, boolean tipo) {
		
		this.id= new String () + code + cant + competi.getId() + tipo; //Aqu� pasa lo mismo que antes.
		this.code=code;
		this.admin=admin;
		this.cant=cant;
		this.num_veces=num_veces;
		this.competicion=competi;
		this.restricciones=true;
		this.tipo=tipo;
		usuarios= new ArrayList<Usuario>();
	}

	public Promocion(String string) {
		this.id = string;
	}
	
	public Promocion(String string, String comp) {
		this.id = string;
		this.nombreComp=comp;
		this.competicion= new Competicion("Competicion:"+comp,comp);
	}


	public Promocion(String text, ArrayList<Usuario> users) {
		this.code = "1";
		this.code = text;
		this.usuarios = users;
		this.cant = 1;
	}
	
	public Promocion(String text,int cant) {
		this.id = "1";
		this.code = text;
		this.cant = cant;
	}

	public Promocion(String string, ArrayList<Usuario> arrayList, int i) {
		this.id = string + i;
		this.code = string;
		this.usuarios = arrayList;
		this.cant = i;
		this.num_veces = i;
	}

	public String getId() {
		return id;
	}
	
	public String getNombreComo() {
		return nombreComp;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getCant() {
		return cant;
	}

	public void setCant(int cant) {
		this.cant = cant;
	}

	public int getNum_veces() {
		return num_veces;
	}
	
	
	
	public void setNum_veces(int num_veces) {
		this.num_veces = num_veces;
	}

	public boolean isTipo() {
		return tipo;
	}

	public void setTipo(boolean tipo) {
		this.tipo = tipo;
	}

	public boolean isRestricciones() {
		return restricciones;
	}

	public void setRestricciones(boolean restricciones) {
		this.restricciones = restricciones;
	}

	public Administrador getAdmin() {
		return admin;
	}

	public void setAdmin(Administrador admin) {
		this.admin = admin;
	}

	public Competicion getCompeticion() {
		return competicion;
	}

	public void setCompeticion(Competicion competicion) {
		this.competicion = competicion;
	}

	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public void anadirUsuario (Usuario user) {
		this.usuarios.add(user);
	}
}
