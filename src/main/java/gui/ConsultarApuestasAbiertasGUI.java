package gui;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

import java.util.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import businessLogic.*;
import domain.*;

public class ConsultarApuestasAbiertasGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private int usuario; //1 usuario, 2 administrador
	private BLFacadeImplementation facadeIm = (BLFacadeImplementation) IniciarSesionGUI.getBusinessLogic();

	private JTextField textFNombreUsuario;

	private JPanel panel;
	private JPanel contentPane;

	private JComboBox<String> comboBoxEventos;
	private JComboBox<String> comboBoxPregunPron;

	Vector<Apuesta> apuestas;
	Vector<Apuesta> apuestasEvento;

	Vector<String> eventos = new Vector<String>();

	int maximo = 0;
	int seleccionado = 0;

	Apuesta seleccionada;

	private JLabel lblFIntroducirNombreUsuario;
	private JLabel lblErrores;
	private JLabel lblFecha;
	private JLabel lblCantidad;
	private JLabel lblCuota;

	private JButton btnBuscarApuestas;
	private JButton btnSiguiente;
	private JButton btnVolver;
	private JButton btnAnterior;

	/**
	 * Create the frame.
	 */
	public ConsultarApuestasAbiertasGUI(int usuario) {

		this.usuario = usuario;

		this.setBounds(600, 250,620, 480);
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

		lblFIntroducirNombreUsuario = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("IntroducirNombreUsuario"));
		lblFIntroducirNombreUsuario.setBounds(50, 25, 254, 26);
		contentPane.add(lblFIntroducirNombreUsuario);

		textFNombreUsuario = new JTextField();

		textFNombreUsuario.setBounds(325, 26, 198, 26);
		contentPane.add(textFNombreUsuario);
		textFNombreUsuario.setColumns(10);

		if (usuario == 1) {
			this.textFNombreUsuario.setText(IniciarSesionGUI.actor().getNombreUsuario());
			textFNombreUsuario.setEditable(false);
			this.lblFIntroducirNombreUsuario.setText(ResourceBundle.getBundle("Etiquetas").getString("NombreUsuario"));
		}


		btnBuscarApuestas = new JButton(ResourceBundle.getBundle("Etiquetas").getString("BuscarApuestas"));
		btnBuscarApuestas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String nombreUsuario= textFNombreUsuario.getText();

				panel.setVisible(false);

				eventos = new Vector<String>();

				btnSiguiente.setEnabled(false);
				btnAnterior.setEnabled(false);

				apuestas = new Vector<Apuesta>();
				apuestasEvento = new Vector<Apuesta>();

				if (nombreUsuario.equals(new String())) {

					lblErrores.setEnabled(true);
					lblErrores.setText(ResourceBundle.getBundle("Etiquetas").getString("RellenaCampo"));
					lblErrores.setForeground(Color.RED);
				} else 
					if (!facadeIm.actorExistente(nombreUsuario)) { 

						lblErrores.setEnabled(true);
						lblErrores.setText(ResourceBundle.getBundle("Etiquetas").getString("UsuarioNoExistente"));
						lblErrores.setForeground(Color.RED);
					}
					else {
						apuestas = facadeIm.obtenerApuestasAbiertasUsuario(nombreUsuario);

						if (apuestas.isEmpty()) {

							lblErrores.setEnabled(true);
							lblErrores.setText(ResourceBundle.getBundle("Etiquetas").getString("NoApuestas"));
							lblErrores.setForeground(Color.BLACK);
						}
						else {
							String buff;
							//comboBoxEventos
							eventos.add(ResourceBundle.getBundle("Etiquetas").getString("SeleccionaOpcion"));
							for (Apuesta apu: apuestas) {
								buff = apu.getPronostico().getPregunta().getEvento().getNombre();
								if (! eventos.contains(buff))
									eventos.add(buff);
							}

							lblErrores.setEnabled(true);
							lblErrores.setText(ResourceBundle.getBundle("Etiquetas").getString("EstasApuestas"));
							lblErrores.setForeground(Color.GREEN);

							comboBoxEventos.setModel(new DefaultComboBoxModel<String>(eventos));
						}
					}
			}
		});

		btnBuscarApuestas.setBounds(218, 73, 198, 26);
		contentPane.add(btnBuscarApuestas);

		lblErrores = new JLabel(""); 
		lblErrores.setHorizontalAlignment(SwingConstants.CENTER);
		lblErrores.setBackground(Color.DARK_GRAY);
		lblErrores.setEnabled(false);
		lblErrores.setBounds(10, 120, 587, 35);
		contentPane.add(lblErrores);

		comboBoxEventos = new JComboBox<String>();
		comboBoxEventos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String eventoSeleccionado = (String)comboBoxEventos.getSelectedItem();
				Vector<String> preguntasPronostico = new Vector<String>();
				apuestasEvento= new Vector<Apuesta>();
				for (Apuesta apu: apuestas) 
					if (apu.getPronostico().getPregunta().getEvento().getNombre().equals(eventoSeleccionado)) 
						apuestasEvento.add(apu);

				preguntasPronostico.add(ResourceBundle.getBundle("Etiquetas").getString("SeleccionaOpcion"));
				for (Apuesta apu: apuestasEvento) 
					preguntasPronostico.add(apu.getPronostico().getPregunta().getEnunciado() + " : " + apu.getPronostico().getRespuesta());

				comboBoxPregunPron.setModel(new DefaultComboBoxModel<String>(preguntasPronostico));
			}
		});

		comboBoxEventos.setBounds(106, 173, 417, 21);
		contentPane.add(comboBoxEventos);

		comboBoxPregunPron = new JComboBox<String>();
		comboBoxPregunPron.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String pronostico = (String) comboBoxPregunPron.getSelectedItem();

				seleccionada = null;

				for (Apuesta apu: apuestasEvento) 
					if (pronostico.equals(apu.getPronostico().getPregunta().getEnunciado() + " : " + apu.getPronostico().getRespuesta()))
						seleccionada = apu;

				if (seleccionada != null) {
					seleccionada = facadeIm.obtenerApuesta(seleccionada.getId());

					panel.setVisible(true);
					maximo = seleccionada.getCantidadDeApuestas();
					seleccionado = 0;

					btnAnterior.setEnabled(false);
					btnSiguiente.setEnabled(maximo != 1);

					lblFecha.setText(ResourceBundle.getBundle("Etiquetas").getString("FechaCreacion") + " " + seleccionada.getFecha()[seleccionado].toString());
					lblCantidad.setText(ResourceBundle.getBundle("Etiquetas").getString("CantidadApostada") + " " + seleccionada.getCantidadApostada()[seleccionado] + " Dineros");
					lblCuota.setText(ResourceBundle.getBundle("Etiquetas").getString("Cuota") + " " + seleccionada.getCuota()[seleccionado] + "");
				}
			}
		});
		comboBoxPregunPron.setBounds(106, 212, 417, 21);
		contentPane.add(comboBoxPregunPron);

		btnVolver = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Volver"));
		btnVolver.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (usuario == 1)
					IniciarSesionGUI.dispUser().volver();
				if (usuario == 2)
					IniciarSesionGUI.dispAdmin().volver();
			}
		});
		btnVolver.setBounds(244, 400, 111, 26);
		btnVolver.setEnabled(true);
		contentPane.add(btnVolver);

		panel = new JPanel();
		panel.setVisible(false);
		panel.setBounds(90, 255, 433, 135);
		contentPane.add(panel);
		panel.setLayout(null);

		lblCantidad = new JLabel();
		lblCantidad.setBounds(20, 60, 342, 21);
		panel.add(lblCantidad);

		lblCuota = new JLabel();
		lblCuota.setBounds(20, 91, 342, 21);
		panel.add(lblCuota);

		lblFecha = new JLabel();
		lblFecha.setBounds(20, 29, 342, 21);
		panel.add(lblFecha);

		btnSiguiente = new JButton(">");
		btnSiguiente.setBounds(372, 18, 51, 42);
		panel.add(btnSiguiente);
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				seleccionado++;

				btnSiguiente.setEnabled(seleccionado != (maximo -1));
				btnAnterior.setEnabled(true);

				lblFecha.setText(ResourceBundle.getBundle("Etiquetas").getString("FechaCreacion") + " " + seleccionada.getFecha()[seleccionado].toString());
				lblCantidad.setText(ResourceBundle.getBundle("Etiquetas").getString("CantidadApostada") + " " + seleccionada.getCantidadApostada()[seleccionado] + " Dineros");
				lblCuota.setText(ResourceBundle.getBundle("Etiquetas").getString("Cuota") + " " + seleccionada.getCuota()[seleccionado] + "");
			}
		});
		btnAnterior = new JButton("<");
		btnAnterior.setBounds(372, 80, 51, 42);
		panel.add(btnAnterior);
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				seleccionado--;

				btnAnterior.setEnabled(seleccionado != (0));
				btnSiguiente.setEnabled(true);

				lblFecha.setText(ResourceBundle.getBundle("Etiquetas").getString("FechaCreacion") + " " + seleccionada.getFecha()[seleccionado].toString());
				lblCantidad.setText(ResourceBundle.getBundle("Etiquetas").getString("CantidadApostada") + " " + seleccionada.getCantidadApostada()[seleccionado] + " Dineros");
				lblCuota.setText(ResourceBundle.getBundle("Etiquetas").getString("Cuota") + " " + seleccionada.getCuota()[seleccionado] + "");
			}
		});

	}
}
