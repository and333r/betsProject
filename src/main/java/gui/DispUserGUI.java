package gui;

import javax.swing.*;

import domain.Usuario;

import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class DispUserGUI extends JFrame{//TODO para vidal: mirar lo de los idiomas, el cambio de idioma de inicio sesion a dispersora -agus

	private static final long serialVersionUID = 1L;

	private JFrame ventanaNueva;
	
	private JLabel seleccionarAccion;
	
	private JButton consultarPregunta;
	private JButton btnApostar;
	private JButton btnVerApuestas;
	private JButton btnCerrarSesion;
	private JLabel lblNewLabel;
	private JLabel lblDineros;
	private JLabel lblCantSaldo;
	private JButton btnDepositar;
	private JButton btnHacerSugerencias;
	private JButton btnInformarProblema;
	private JButton btnNewButton;
	private JButton btnVerApuestasHistorial;
	private JButton btnCambiarValores;

	public DispUserGUI() {

		this.getContentPane().setLayout(null);
		setBounds(600, 250,496, 430);
		this.setTitle("Dispersora de Usuario");
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(1);

			}});

		consultarPregunta = new JButton();
		consultarPregunta.setFont(new Font("Tahoma", Font.BOLD, 10));
		consultarPregunta.setText(ResourceBundle.getBundle("Etiquetas").getString("ConsultarElemn"));

		consultarPregunta.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				cerrarDispersora();
				ventanaNueva = new ConsultarGUI(1);
				ventanaNueva.setVisible(true);
			}
		});

		consultarPregunta.setBounds(35, 67, 201, 37);
		this.getContentPane().add(consultarPregunta);

		seleccionarAccion = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("seleccionarAccion"));
		seleccionarAccion.setHorizontalAlignment(SwingConstants.CENTER);
		seleccionarAccion.setFont(new Font("Tahoma", Font.BOLD, 14));
		seleccionarAccion.setBounds(116, 29, 185, 28);
		this.getContentPane().add(seleccionarAccion);

		btnApostar = new JButton();
		btnApostar.setText(ResourceBundle.getBundle("Etiquetas").getString("Apuesta"));
		btnApostar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnApostar.setBounds(255, 68, 201, 37);
		getContentPane().add(btnApostar);
		
		btnCerrarSesion = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CerrarSesion")); 
		btnCerrarSesion.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cerrarSesion();
			}
		});
		btnCerrarSesion.setBounds(165, 315, 145, 21);
		getContentPane().add(btnCerrarSesion);
		
		
		btnApostar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarDispersora();
				ventanaNueva= new ApostarGUI();
				ventanaNueva.setVisible(true);
			}
		});
		
		btnVerApuestas = new JButton(ResourceBundle.getBundle("Etiquetas").getString("VerApuestasAbiertas"));
		btnVerApuestas.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnVerApuestas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cerrarDispersora();
				ventanaNueva = new ConsultarApuestasAbiertasGUI(1);
				ventanaNueva.setVisible(true);
			}
		});
		btnVerApuestas.setBounds(35, 116, 201, 41);
		getContentPane().add(btnVerApuestas);
		
		lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SaldoActual")); 
		lblNewLabel.setBounds(25, 346, 136, 14);
		getContentPane().add(lblNewLabel);
		
		lblDineros = new JLabel("Dineros"); 
		lblDineros.setBounds(376, 346, 46, 14);
		getContentPane().add(lblDineros);
		
		lblCantSaldo = new JLabel(); 
		if (IniciarSesionGUI.actor() != null)
			lblCantSaldo.setText(Double.toString(((Usuario) IniciarSesionGUI.actor()).getSaldo()));
		
		lblCantSaldo.setBounds(258, 346, 94, 14);
		getContentPane().add(lblCantSaldo);
		
		btnDepositar = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Depositar")); 
		btnDepositar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnDepositar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarDispersora();
				ventanaNueva= new DepositarGUI();
				ventanaNueva.setVisible(true);
				
			}
		});
		
		btnDepositar.setBounds(255, 117, 201, 41);
		getContentPane().add(btnDepositar);
		
		btnHacerSugerencias = new JButton(ResourceBundle.getBundle("Etiquetas").getString("HacerSugerencias"));
		btnHacerSugerencias.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnHacerSugerencias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaNueva = new SugerenciasGUI();
				cerrarDispersora();
				ventanaNueva.setVisible(true);
			}
		});
		btnHacerSugerencias.setBounds(255, 215, 201, 41);
		getContentPane().add(btnHacerSugerencias);
		
		btnInformarProblema = new JButton(ResourceBundle.getBundle("Etiquetas").getString("InformarProblema"));
		this.btnInformarProblema.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnInformarProblema.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaNueva = new InformarProblemaGUI();
				cerrarDispersora();
				ventanaNueva.setVisible(true);
			}
		});
		btnInformarProblema.setBounds(255, 168, 201, 37);
		getContentPane().add(btnInformarProblema);
		
		btnNewButton = new JButton("Aplicar Promoción"); 
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaNueva = new UsarPromocionGUI();
				cerrarDispersora();
				ventanaNueva.setVisible(true);
			}
		});
		btnNewButton.setBounds(35, 266, 421, 39);
		getContentPane().add(btnNewButton);
		
		btnVerApuestasHistorial = new JButton(ResourceBundle.getBundle("Etiquetas").getString("VerApuestasHistorial"));
		btnVerApuestasHistorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cerrarDispersora();
				ventanaNueva = new ConsultarHistorialApuestasGUI(1);
				ventanaNueva.setVisible(true);
			}
		});
		btnVerApuestasHistorial.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnVerApuestasHistorial.setBounds(35, 167, 201, 41);
		getContentPane().add(btnVerApuestasHistorial);
		
		btnCambiarValores = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CambiarValoresUsuario"));
		btnCambiarValores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cerrarDispersora();
				ventanaNueva = new CambiarDatosGUI();
				ventanaNueva.setVisible(true);
			}
		});
		btnCambiarValores.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnCambiarValores.setBounds(35, 214, 201, 41);
		getContentPane().add(btnCambiarValores);
	}
	
	
	/**
	 * Cierra la ventana de la interfaz desplegada desde la dispersora y vuelve a mostrar la dispersora
	 */
	public void volver() {
		
		ventanaNueva.setVisible(false);
		this.setVisible(true);
	}

	/**
	 * Cierra la ventana de la interfaz dispersora
	 */
	private void cerrarDispersora() {
		this.setVisible(false);
	}

	/**
	 * @Vidal
	 */
	public void cerrarSesion() {
		
		IniciarSesionGUI.eliminarActor();
		this.setVisible(false);
		ApplicationLauncher.inicioSesion().remostrar();
	}

	public void actualizar() {
		
		lblCantSaldo.setText(Double.toString(((Usuario) IniciarSesionGUI.actor()).getSaldo()));
	}
}
