package domain;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import configuration.UtilDate;

@Entity
public class Apuesta {

	@Id
	private String id;
	private Pronostico pronostico;
	
	private int cantidadDeApuestas;
	@OneToOne
	private Usuario usuario;
	private double cantidadApostada[] = {0,0,0,0,0,0,0,0,0,0};
	private double cuota[] = new double[10];
	private double gananciaTotal = 0;
	private Date fecha[] = new Date[10];
	private boolean abierta;

	public Apuesta(Pronostico pronos, Usuario user, double cant) {

		this.pronostico = pronos;
		this.usuario = user;
		this.cantidadApostada[0] = cant;
		
		for (int i = 1; i < 10; i++)
			this.cantidadApostada[i] = 0;
		
		this.cuota[0] = pronos.getGanancia();
		this.id = this.pronostico.getId()+"_"+this.usuario.getNombreUsuario();
		
		this.cantidadDeApuestas = 1;
		this.fecha[0] = new Date();
		this.abierta = true;
	}

	public Date[] getFecha() {
		return fecha;
	}
	
	public boolean estaAbierta() {
		return this.abierta;
	}
	
	public void cerrarApuesta() {
		this.abierta = false;
	}
	
	public int getCantidadDeApuestas () {
		return this.cantidadDeApuestas;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Pronostico getPronostico() {
		return pronostico;
	}

	public void setPronostico(Pronostico pronostico) {
		this.pronostico = pronostico;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public double[] getCantidadApostada() {
		return cantidadApostada;
	}

	public double[] getCuota() {
		return cuota;
	}

	public void setCuota(double[] cuota) {
		this.cuota = cuota;
	}

	public double calcularGananciaTotal() {
		
		double total = 0;
		for (int i=0;i<this.cantidadDeApuestas;i++) {
			total+=this.cantidadApostada[i]*this.cuota[i];
		}
		this.gananciaTotal = total;
		return this.gananciaTotal;
	}

	public int anadirSubApuesta(double cant, double couta) {

		int i=this.cantidadDeApuestas;
		if (cantidadDeApuestas < 10) {

			this.cantidadApostada[i]=cant;
			this.cuota[i]=couta;
			this.fecha[i] = new Date();
			
			this.calcularGananciaTotal();
			this.cantidadDeApuestas++;
			return 0;
		}
		return 1;//no puede realizar mas apuestas al mismo pronostico
	}

	public boolean puedeAnadirApuesta() {
		
		return this.cantidadDeApuestas < 10;
	}
}
