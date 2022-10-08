package gui;

import javax.swing.*;

import domain.*;

import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class DispAdminGUI extends JFrame{//TODO para vidal: mirar lo de los idiomas, el cambio de idioma de inicio sesion a dispersora -agus
	
	private static final long serialVersionUID = 1L;
	
	private JFrame ventanaNueva;
	
	private JLabel seleccionarAccion;
	
	private JButton btnCrearPregunta;
	private JButton btnConsultarPregunta;
	private JButton btnAnadirPronostico;
	private JButton btnCrearEvento;
	private JButton btnCrearCompeticion;
	private JButton btnRegistrarAdmin;
	private JButton btnNewButton;
	private JButton btnVerApuestasAbiertas;
	private JButton btnConsultarObjetosAdministradores;
	private JButton btnCerrarSesion;
	private JButton btnConsultarProblemas;
	private JButton btnAnadirPromo;
	private JButton btnVerSugs;
	private JButton btnVerApuestasHistorial;
	
	public DispAdminGUI() {
		
		this.getContentPane().setLayout(null);
		setBounds(600, 250,540, 480);
		this.setTitle("Dispersora de Administrador");
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(1);

			}});

		seleccionarAccion = new JLabel();
		seleccionarAccion.setHorizontalAlignment(SwingConstants.CENTER);
		seleccionarAccion.setFont(new Font("Tahoma", Font.BOLD, 16));
		seleccionarAccion.setText(ResourceBundle.getBundle("Etiquetas").getString("seleccionarAccion"));
		seleccionarAccion.setBounds(138, 28, 277, 41);
		getContentPane().add(seleccionarAccion);
		
		btnCrearPregunta = new JButton();
		this.btnCrearPregunta.setText(ResourceBundle.getBundle("Etiquetas").getString("CrearPregunta"));
		
		btnCrearPregunta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cerrarDispersora();
				ventanaNueva = new CreatePreguntaGUI();
				ventanaNueva.setVisible(true);
			}
		});
		
		btnCrearPregunta.setBounds(10, 198, 238, 41);
		getContentPane().add(btnCrearPregunta);
		
		btnConsultarPregunta = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ConsultarElemn"));
		btnConsultarPregunta.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				cerrarDispersora();
				ventanaNueva = new ConsultarGUI(2);
				ventanaNueva.setVisible(true);
			}
		});
		
		btnConsultarPregunta.setBounds(10, 93, 238, 41);
		getContentPane().add(btnConsultarPregunta);
		
		//Boton de anadir pronostico
		btnAnadirPronostico = new JButton(ResourceBundle.getBundle("Etiquetas").getString("AnadirPronostico"));
		btnAnadirPronostico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cerrarDispersora();
				ventanaNueva = new CrearPronosticoGUI();
				ventanaNueva.setVisible(true);
			}
		});
		btnAnadirPronostico.setBounds(10, 249, 238, 41);
		getContentPane().add(btnAnadirPronostico);
		
		//Boton de crear evento
		btnCrearEvento = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CrearEvento")); //$NON-NLS-1$ //$NON-NLS-2$
		btnCrearEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cerrarDispersora();
				ventanaNueva = new CrearEventoGUI();
				ventanaNueva.setVisible(true);
			}
		});
		btnCrearEvento.setBounds(10, 146, 238, 41);
		getContentPane().add(btnCrearEvento);
		
		//Boton de crear competicion
		btnCrearCompeticion = new JButton("Crear Competicion.");
		btnCrearCompeticion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cerrarDispersora();
				ventanaNueva = new CrearCompeticionGUI();
				ventanaNueva.setVisible(true);
			}
		});
		btnCrearCompeticion.setBounds(278, 93, 238, 41);
		getContentPane().add(btnCrearCompeticion);
		
		btnRegistrarAdmin = new JButton(ResourceBundle.getBundle("Etiquetas").getString("RegistrarAdmin")); //$NON-NLS-1$ //$NON-NLS-2$
		btnRegistrarAdmin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				cerrarDispersora();
				ventanaNueva = new RegistrarGUI();
				ventanaNueva.setVisible(true);
				((RegistrarGUI) ventanaNueva).setRegAdmin(true);
			}
		});
		btnRegistrarAdmin.setBounds(278, 146, 238, 41);
		getContentPane().add(btnRegistrarAdmin);
		
		btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CerrarApuesta")); //$NON-NLS-1$ //$NON-NLS-2$
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cerrarDispersora();
				ventanaNueva = new CerrarApuestaGUI();
				ventanaNueva.setVisible(true);
			}
		});
		btnNewButton.setBounds(278, 198, 238, 41);
		getContentPane().add(btnNewButton);
		
		btnVerApuestasAbiertas = new JButton(ResourceBundle.getBundle("Etiquetas").getString("VerApuestasAbiertas"));
		btnVerApuestasAbiertas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cerrarDispersora();
				ventanaNueva = new ConsultarApuestasAbiertasGUI(2);
				ventanaNueva.setVisible(true);
			}
		});
		btnVerApuestasAbiertas.setBounds(278, 249, 238, 41);
		getContentPane().add(btnVerApuestasAbiertas);
		
		btnConsultarObjetosAdministradores = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ConsultarObjetosAdmin"));
		btnConsultarObjetosAdministradores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaNueva = new ConsultarObjetosAdministradoresGUI();
				cerrarDispersora();
				ventanaNueva.setVisible(true);
			}
		});
		btnConsultarObjetosAdministradores.setBounds(10, 347, 238, 34);
		getContentPane().add(btnConsultarObjetosAdministradores);
		
		btnCerrarSesion = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CerrarSesion")); 
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cerrarSesion();
			}
		});
		btnCerrarSesion.setBounds(314, 401, 162, 21);
		getContentPane().add(btnCerrarSesion);
		
		btnConsultarProblemas = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ConsultarProblemas"));
		btnConsultarProblemas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaNueva= new ConsultarProblemasGUI();
				cerrarDispersora();
				ventanaNueva.setVisible(true);
			}
		});
		btnConsultarProblemas.setBounds(53, 401, 162, 21);
		getContentPane().add(btnConsultarProblemas);
		
		
		
	    btnAnadirPromo = new JButton("Añadir promoción"); //TODO a�adir etiqueta
		btnAnadirPromo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaNueva= new AnadirPromocionGUI();
				cerrarDispersora();
				ventanaNueva.setVisible(true);
			}
		});
		btnAnadirPromo.setBounds(10, 300, 238, 34);
		getContentPane().add(btnAnadirPromo);
		
		btnVerSugs = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ConsultarSugerencias"));
		btnVerSugs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaNueva=new ConsultarSugerenciasGUI();
				cerrarDispersora();
				ventanaNueva.setVisible(true);
			}
		});
		btnVerSugs.setBounds(278, 347, 238, 34);
		getContentPane().add(btnVerSugs);
		
		btnVerApuestasHistorial = new JButton(ResourceBundle.getBundle("Etiquetas").getString("VerApuestasHistorial"));
		btnVerApuestasHistorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cerrarDispersora();
				ventanaNueva = new ConsultarHistorialApuestasGUI(2);
				ventanaNueva.setVisible(true);
			}
		});
		btnVerApuestasHistorial.setBounds(278, 300, 238, 34);
		getContentPane().add(btnVerApuestasHistorial);
		
	}
	
	/**
	 * Deja de mostrar la ventana actual
	 */
	private void cerrarDispersora() {
		
		this.setVisible(false);
	}
		
	/**
	 * Muestra de nuevo la interfaz de seleccion de opciones para los administradores cerrando las que se hayan abierto
	 * desde esta
	 * 
	 * @author Vidal del Rincon
	 */
	public void volver() {
		
		this.ventanaNueva.setVisible(false);
		this.setVisible(true);
	}
	
	/**
	 * @Vidal
	 */
	public void cerrarSesion() {
		
		IniciarSesionGUI.eliminarActor();
		this.setVisible(false);
		ApplicationLauncher.inicioSesion().remostrar();
	}
}
