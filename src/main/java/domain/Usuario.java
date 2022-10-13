package domain;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.*;

/**
 * Informaci�n de un usuario de la aplicaci�n.
 * @author Markel Barrena.
 * 23/02/2022
 */

@Entity
public class Usuario extends Actor{
	
	private double saldo; //unidades monetarias ingresadas en la aplicaci�n.
	
	@OneToMany (cascade = CascadeType.PERSIST)
	private ArrayList<Apuesta> apuestas;
	private ArrayList<Promocion> promos_abiertas; //Para llevar un registro de promociones utilizadas por el usuario
	@OneToMany
	private ArrayList<InformeError> informes; //errores informados por este usuario.
	
	private ArrayList<String> antiguasContrasenas;
	private ArrayList<String> antiguosCorreosElectronicos;
	
	public Usuario(String nombreUsuario, String DNI, String nombre, String apellido1, String apellido2, Date fechaN, String pswd, char Sexo, String email, String numTel) {
		
		super(nombreUsuario,  DNI,  nombre,  apellido1,  apellido2,  fechaN,  pswd,  Sexo,  email,  numTel);
		this.saldo = 0;
		this.apuestas=new ArrayList<Apuesta>();
		this.promos_abiertas= new ArrayList<Promocion>();
		this.antiguasContrasenas = new ArrayList<String>();
		this.antiguosCorreosElectronicos = new ArrayList<String>();
	}

	public Usuario(String string, ArrayList<Promocion> arrayList) {
		super(string,"12345678N");
		this.promos_abiertas = new ArrayList<Promocion>();
		this.apuestas=new ArrayList<Apuesta>();
		this.antiguasContrasenas = new ArrayList<String>();
		this.antiguosCorreosElectronicos = new ArrayList<String>();
	}

	public Usuario(String string) {
		super(string, "12345678N");
	}

	public Usuario(String string, String string2, ArrayList<Promocion> arrayList) {
		super(string,string2);
		this.promos_abiertas = arrayList;
	}

	public void anadirPromo (Promocion user) {
		this.promos_abiertas.add(user);
	}
	//getters y setters:

	public ArrayList<Promocion> getPromos_abiertas() {
		return this.promos_abiertas;
	}

	public void setPromos_abiertas(ArrayList<Promocion> e) {
		this.promos_abiertas=e;
	}
	
	public double getSaldo() {
		return saldo;
	}
	
	public void setSaldo(double saldo) {
		this.saldo= saldo;
	}
	
	public ArrayList<Apuesta> getApuestas() {
		return apuestas;
	}

	public void setApuestas(ArrayList<Apuesta> apuestas) {
		this.apuestas = apuestas;
	}
	
	//fin getters y setters.
	
	/**
	 * Actualiza el saldo sum�ndole la cantidad de entrada.
	 * @param cantidad la cantidad que se le suma (negativa si se va a sustraer).
	 */
	public void actualizarSaldo(double cantidad) {
		
		this.saldo = this.saldo + cantidad;
	}
	
	public void anadirApuesta(Apuesta a) {
		this.apuestas.add(a);
	}
	
	public void anadirInforme(InformeError ie) {
		this.informes.add(ie);
	}
	
	/**
	 * Devuelve la informaci�n del usuario en un string.
	 */
	public String toString() {
		return (this.getNombreUsuario()+": "+this.getNombre()+", "+this.getApellido1()+", "+this.getApellido2()+
				".\n\tDni: "+this.getDNI()+"\n\tFecha de nacimiento: "+this.getFechaN().toString()+"\n\tEmail: "+
				this.getEmail()+"\n\tN�mero de tel�fono: "+this.getNumTel()+"\n\tSaldo: "+this.saldo+"\n");
	}
	
	public ArrayList<String> getContrasenasAntiguas() {
		return this.antiguasContrasenas;
	}
	
	public ArrayList<String> getCorreosAntiguos() {
		return this.antiguosCorreosElectronicos;
	}

	public boolean cambiarContrasena(String nuevaContrasena) {
		
		for(String c : this.antiguasContrasenas)
			if (c.equals(nuevaContrasena))
				return false;
		
		if (this.getPswd().equals(nuevaContrasena))
			return false;
		
		this.antiguasContrasenas.add(this.getPswd());
		this.setPswd(nuevaContrasena);
		return true;
	}
	
	public boolean cambiarCorreoElectronico (String nuevoCorreoE) {
		
		for(String c : this.antiguosCorreosElectronicos)
			if (c.equals(nuevoCorreoE))
				return false;
		
		if (this.getEmail().equals(nuevoCorreoE))
			return false;
		
		this.antiguosCorreosElectronicos.add(this.getEmail());
		this.setEmail(nuevoCorreoE);
		return true;
	}
}
