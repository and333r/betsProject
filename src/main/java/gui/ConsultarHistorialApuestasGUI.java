package gui;

import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;

import businessLogic.*;
import domain.*;
import java.awt.Color;

public class ConsultarHistorialApuestasGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private int usuario; //1 usuario, 2 administrador
	private BLFacadeImplementation facadeIm = (BLFacadeImplementation) IniciarSesionGUI.getBusinessLogic();

	private JPanel contentPane;

	private JLabel lblUsuario;
	private JLabel lblEvento;
	private JLabel lblFecha;
	private JLabel lblResultado;
	private JLabel lblCantidadApostada;

	private JLabel lblEventoTxt;
	private JLabel lblFechaTxt;
	private JLabel lblResultadoTxt;
	private JLabel lblCantidadApostadaTxt;
	private JLabel lblNoApuestas;

	private JComboBox<String> comboBoxApuestas;

	private JButton btnBuscarApuestas;
	private JButton btnVolver;

	private JTextField textFUsuario;

	Vector<Apuesta> apuestasUsuario = new Vector<Apuesta>();
	Vector<String> apuestas = new Vector<String>();
	Apuesta apuesta = null;
	String nombreUsuario;

	public ConsultarHistorialApuestasGUI(int usuario) {

		this.usuario = usuario;

		this.setBounds(600, 250,600, 390);
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
		this.setBounds(100, 100, 623, 473);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(1);

			}});



		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);


		lblNoApuestas = new JLabel(); 
		lblNoApuestas.setBackground(Color.LIGHT_GRAY);
		lblNoApuestas.setForeground(Color.RED);
		lblNoApuestas.setHorizontalAlignment(SwingConstants.CENTER);
		lblNoApuestas.setBounds(117, 238, 346, 60);
		lblNoApuestas.setVisible(false);
		contentPane.add(lblNoApuestas);

		textFUsuario = new JTextField();
		textFUsuario.setBounds(356, 31, 136, 19);
		contentPane.add(textFUsuario);
		textFUsuario.setColumns(10);

		lblUsuario = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("IntroducirNombreUsuario")); 
		lblUsuario.setBounds(79, 31, 258, 19);
		contentPane.add(lblUsuario);


		btnBuscarApuestas = new JButton(ResourceBundle.getBundle("Etiquetas").getString("BuscarApuestas"));

		btnBuscarApuestas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisibleMultiple(false);

				if (usuario == 1) 
					nombreUsuario = IniciarSesionGUI.actor().getNombreUsuario();

				else if (usuario == 2) 
					nombreUsuario = textFUsuario.getText();

				if (facadeIm.actorExistente(nombreUsuario)) {

					apuestasUsuario = facadeIm.obtenerHistorialApuestasUsuario(nombreUsuario);

					if (! apuestasUsuario.isEmpty()) {

						lblNoApuestas.setVisible(false);

						apuestas = new Vector<String>();
						apuestas.add(ResourceBundle.getBundle("Etiquetas").getString("SeleccionaOpcion"));

						for (Apuesta apu: apuestasUsuario) 
							apuestas.add(apu.getPronostico().getPregunta().getEvento().getNombre() + "-" + apu.getPronostico().getPregunta().getEnunciado() + "-" + apu.getPronostico().getRespuesta());

						comboBoxApuestas.setModel(new DefaultComboBoxModel<String>(apuestas));
						comboBoxApuestas.setVisible(true);
					}
					else {

						lblNoApuestas.setText(ResourceBundle.getBundle("Etiquetas").getString("NoApuestasUsuario"));
						lblNoApuestas.setVisible(true);
					}
				} else {

					lblNoApuestas.setText(ResourceBundle.getBundle("Etiquetas").getString("UsuarioNoExistente"));
					lblNoApuestas.setVisible(true);
				}
			}
		});

		btnBuscarApuestas.setBounds(178, 89, 218, 39);
		contentPane.add(btnBuscarApuestas);

		comboBoxApuestas = new JComboBox<String>();
		comboBoxApuestas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (comboBoxApuestas.getSelectedIndex() != 0) {

					apuesta = apuestasUsuario.get(comboBoxApuestas.getSelectedIndex() - 1);
					setTextMultiple();
				}
			}
		});

		comboBoxApuestas.setBounds(79, 161, 413, 21);
		comboBoxApuestas.setVisible(false);
		contentPane.add(comboBoxApuestas);

		if (usuario == 1) {

			textFUsuario.setVisible(false);
			lblUsuario.setVisible(false);	
		}

		lblEvento = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Event") + ":"); 
		lblEvento. setBounds(57, 215, 96, 13);
		lblEvento.setVisible(false);
		contentPane.add(lblEvento);
		
		lblFecha = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Fecha"));
		lblFecha.setBounds(57, 249, 96, 13);
		lblFecha.setVisible(false);
		contentPane.add(lblFecha);

		lblResultado = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Resultado") + ":");
		lblResultado.setBounds(57, 285, 96, 13);
		lblResultado.setVisible(false);
		contentPane.add(lblResultado);

		lblCantidadApostada = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CantidadApostada") + ":");
		lblCantidadApostada.setBounds(57, 324, 129, 13);
		lblCantidadApostada.setVisible(false);
		contentPane.add(lblCantidadApostada);

		lblEventoTxt = new JLabel();
		lblEventoTxt.setBounds(234, 215, 286, 13);
		contentPane.add(lblEventoTxt);

		lblFechaTxt = new JLabel();
		lblFechaTxt.setBounds(234, 249, 286, 13);
		contentPane.add(lblFechaTxt);

		lblResultadoTxt = new JLabel();
		lblResultadoTxt.setBounds(234, 285, 286, 13);
		contentPane.add(lblResultadoTxt);

		lblCantidadApostadaTxt = new JLabel();
		lblCantidadApostadaTxt.setBounds(234, 324, 286, 13);
		contentPane.add(lblCantidadApostadaTxt);

		btnVolver = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Volver")); 
		btnVolver.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (usuario == 1)
					IniciarSesionGUI.dispUser().volver();
				if (usuario == 2)
					IniciarSesionGUI.dispAdmin().volver();
			}
		});

		btnVolver.setBounds(235, 387, 102, 21);
		contentPane.add(btnVolver);
	}

	public void setVisibleMultiple(boolean valor) {

		this.lblEvento.setVisible(valor);
		this.lblEventoTxt.setVisible(valor);
		this.lblFecha.setVisible(valor);
		this.lblFechaTxt.setVisible(valor);
		this.lblResultado.setVisible(valor);
		this.lblResultadoTxt.setVisible(valor);
		this.lblCantidadApostada.setVisible(valor);
		this.lblCantidadApostadaTxt.setVisible(valor);
	}

	public void setTextMultiple () {

		this.lblEventoTxt.setText(apuesta.getPronostico().getPregunta().getEvento().getNombre() + " -> " + apuesta.getPronostico().getPregunta().getEnunciado());
		this.lblFechaTxt.setText(apuesta.getFecha()[0].toString());		
		if (apuesta.getPronostico().getId().equals(apuesta.getPronostico().getPregunta().getResultado().getId())) {
			this.lblResultadoTxt.setText("Ganada. :D");
			this.lblCantidadApostadaTxt.setText(apuesta.calcularGananciaTotal() + " dineros");
		}
		else {
			this.lblResultadoTxt.setText("Perdida. :(");
			this.lblCantidadApostadaTxt.setText(0 + " dineros");
		}
		setVisibleMultiple(true);
	}
}
