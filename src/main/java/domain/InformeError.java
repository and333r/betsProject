package domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class InformeError {
	
	@Id
	private String id;
	private String titulo;
	private String descripcion;
	private boolean resuelto; //true si estx resuelto.
	private Date fechaInforme;
	private Date fechaResuelto;


	@OneToOne
	private Usuario usuario;
	
	public InformeError(String titulo, Usuario usuario) {
		this.titulo=titulo;
		this.usuario=usuario;
		this.resuelto=false;
		this.fechaInforme= new Date();
		this.id=titulo+usuario.getNombreUsuario()+this.fechaInforme.toString();
		this.fechaResuelto=null;
	}

	public String getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public String getDescripcion() {
		return this.descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public boolean getResuelto() {
		return this.resuelto;
	}
	
	public void setResuelto(boolean resuelto) {
		this.resuelto=resuelto;
	}
	
	
	public Date getFechaInforme() {
		return fechaInforme;
	}

	public void setFechaInforme(Date fechaInforme) {
		this.fechaInforme = fechaInforme;
	}

	public Date getFechaResuelto() {
		return fechaResuelto;
	}

	public void setFechaResuelto(Date fechaResuelto) {
		this.fechaResuelto = fechaResuelto;
	}

}
