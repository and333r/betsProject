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
	private String code; //El código que hay que meter.
	private int cant; //La cantidad de la promocion (Ya sea el porcentaje o el int)
	private int num_veces; //El número de veces que se puede aplicar esta promoción --> En caso de ser -1, no tiene límite (Se puede aplicar de manera infinita para distinos usuarios).
	private boolean tipo; //Si es true significa que la cifra es un porcentaje, si es false, significa que es un integer normal
	private boolean restricciones; //Si es true signiofica que la promocion es valida solo en ciertas competiciones. Se puede ampliar a solo en ciertos eventos y preguntas también.

	@OneToOne
	private Administrador admin; //El administrador que ha creado la promoción. 
	@OneToOne
	private Competicion competicion;

	@OneToMany (cascade =CascadeType.PERSIST)
	private ArrayList<Usuario> usuarios; //Los usuarios que han aplicado la promoción --> Para evitar que se use dos veces.


	public Promocion (String codes, Administrador admin, int cant, int num_veces, boolean tipo) {
		this.id= new String() + code +  cant + tipo; // En caso de que haya dos promociones con el mismo id, es decir, la misma cantidad y el mismo numero de veces, lo que se hará será actualizar 
		//la ya existente, añadiendo el numero de veces deseado. Por eso en el id no está el admin.
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
		
		this.id= new String () + code + cant + competi.getId() + tipo; //Aquí pasa lo mismo que antes.
		this.code=code;
		this.admin=admin;
		this.cant=cant;
		this.num_veces=num_veces;
		this.competicion=competi;
		this.restricciones=true;
		this.tipo=tipo;
		usuarios= new ArrayList<Usuario>();
	}

	public String getId() {
		return id;
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
