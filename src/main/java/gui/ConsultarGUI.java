package gui;


import java.util.*;

import javax.swing.*;

import businessLogic.*;
import domain.*;

import java.awt.event.*;
import java.awt.Font;

public class ConsultarGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private int llamada; //0 iniciarSesion, 1 dispUsuario, 2 dispAdministrador
	private int seleccionado = 0; //0 nada, 1 competicion, 2 evento, 3 pregunta, 4 pronostico, 5 apuesta

	private JLabel lblCompeticiones;
	private JLabel lblEventos;
	private JLabel lblPreguntas;
	private JLabel lblPronosticos;

	private Competicion competicion = null;
	private Evento evento;
	private Pregunta pregunta;
	private Pronostico pronostico;
	private Apuesta apuesta;

	private JComboBox<String> comboBoxCompeticiones;
	private JComboBox<String> comboBoxEventos;
	private JComboBox<String> comboBoxPreguntas;
	private JComboBox<String> comboBoxPronosticos;

	private JButton btnVolver;

	Vector<String> etiquetas;
	Vector<Competicion> competiciones = new Vector<Competicion>();
	Vector<Evento> eventos;
	Vector<Pregunta> preguntas;
	Vector<Pronostico> pronosticos;
	Vector<Apuesta> apuestas;

	BLFacadeImplementation facade = (BLFacadeImplementation)IniciarSesionGUI.getBusinessLogic();
	private JButton btnConsultar;
	private JLabel lblMostrando;
	private JLabel lblInformacion1;
	private JLabel lblInformacion2;
	private JLabel lblInformacion3;
	private JLabel lblInformacion4;
	private JLabel lblInformacion5;
	private JLabel lblInformacion6;

	private JLabel lblContenido1;
	private JLabel lblContenido2;
	private JLabel lblContenido3;
	private JLabel lblContenido4;
	private JLabel lblContenido5;
	private JLabel lblContenido6;

	public ConsultarGUI(int llamada) {

		this.llamada = llamada;

		this.getContentPane().setLayout(null);
		this.setBounds(600, 250,722, 570);
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("Consultar"));

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(1);

			}});

		lblCompeticiones = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SeleccionaCompeticion"));
		lblCompeticiones.setBounds(49, 41, 212, 13);
		getContentPane().add(lblCompeticiones);

		lblEventos = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SeleccionaEvento"));
		lblEventos.setBounds(49, 81, 212, 13);
		getContentPane().add(lblEventos);

		lblPreguntas = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SeleccionaPregunta"));
		lblPreguntas.setBounds(49, 121, 212, 13);
		getContentPane().add(lblPreguntas);

		lblPronosticos = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SeleccionaPronostico"));
		lblPronosticos.setBounds(49, 163, 212, 13);
		getContentPane().add(lblPronosticos);

		comboBoxCompeticiones = new JComboBox<String>();

		competiciones = facade.obtenerCompeticiones();

		etiquetas = new Vector<String>();
		etiquetas.add(ResourceBundle.getBundle("Etiquetas").getString("SeleccionaOpcion"));
		for (Competicion c: competiciones)
			etiquetas.add(c.getNombre());

		comboBoxCompeticiones.setModel(new DefaultComboBoxModel<String>(etiquetas));
		comboBoxCompeticiones.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (!comboBoxCompeticiones.getSelectedItem().equals(ResourceBundle.getBundle("Etiquetas").getString("SeleccionaOpcion"))) {

					seleccionado = 1;
					btnConsultar.setEnabled(true);

					competicion = (Competicion) competiciones.get(comboBoxCompeticiones.getSelectedIndex() - 1);

					evento = null;
					pregunta = null;
					pronostico = null;
					apuesta = null;

					eventos = (Vector<Evento>) facade.obtenerEventosPorCompeticion(competicion);

					etiquetas = new Vector<String>();
					etiquetas.add(ResourceBundle.getBundle("Etiquetas").getString("SeleccionaOpcion"));

					for (Evento evento : eventos) 
							etiquetas.add(evento.getNombre());
	
					

					comboBoxEventos.setModel(new DefaultComboBoxModel<String>(etiquetas));
				}

			}
		});
		comboBoxCompeticiones.setBounds(271, 34, 254, 26);
		getContentPane().add(comboBoxCompeticiones);

		comboBoxEventos = new JComboBox<String>();
		comboBoxEventos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!comboBoxEventos.getSelectedItem().equals(ResourceBundle.getBundle("Etiquetas").getString("SeleccionaOpcion"))) {

					seleccionado = 2;
					btnConsultar.setEnabled(true);

					evento = eventos.get(comboBoxEventos.getSelectedIndex() - 1);

					pregunta = null;
					pronostico = null;
					apuesta = null;

					preguntas = (Vector<Pregunta>) facade.obtenerPreguntasPorEvento(evento);

					etiquetas = new Vector<String>();
					etiquetas.add(ResourceBundle.getBundle("Etiquetas").getString("SeleccionaOpcion"));

					for (Pregunta pregunta : preguntas)
						etiquetas.add(pregunta.getEnunciado());

					comboBoxPreguntas.setModel(new DefaultComboBoxModel<String>(etiquetas));
				}
			}
		});
		comboBoxEventos.setBounds(271, 74, 254, 26);
		getContentPane().add(comboBoxEventos);

		comboBoxPreguntas = new JComboBox<String>();
		comboBoxPreguntas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!comboBoxPreguntas.getSelectedItem().equals(ResourceBundle.getBundle("Etiquetas").getString("SeleccionaOpcion"))) {

					pregunta = preguntas.get(comboBoxPreguntas.getSelectedIndex() - 1);

					pronostico = null;
					apuesta = null;

					seleccionado = 3;

					btnConsultar.setEnabled(true);
					pronosticos = (Vector<Pronostico>) facade.obtenerPronosticosDePregunta(pregunta);

					etiquetas = new Vector<String>();
					etiquetas.add(ResourceBundle.getBundle("Etiquetas").getString("SeleccionaOpcion"));

					for (Pronostico pronostico : pronosticos)
						etiquetas.add(pronostico.getRespuesta());

					comboBoxPronosticos.setModel(new DefaultComboBoxModel<String>(etiquetas));
				}
			}
		});

		comboBoxPreguntas.setBounds(271, 114, 254, 26);
		getContentPane().add(comboBoxPreguntas);

		comboBoxPronosticos = new JComboBox<String>();
		comboBoxPronosticos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!comboBoxPronosticos.getSelectedItem().equals(ResourceBundle.getBundle("Etiquetas").getString("SeleccionaOpcion"))) {

					seleccionado = 4;
					pronostico = pronosticos.get(comboBoxPronosticos.getSelectedIndex() - 1);
					btnConsultar.setEnabled(false);
					mostrar();
				}
			}
		});
		comboBoxPronosticos.setBounds(271, 156, 254, 26);
		getContentPane().add(comboBoxPronosticos);


		btnVolver = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Volver"));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (llamada == 2)
					IniciarSesionGUI.dispAdmin().volver();
				if (llamada == 1)
					IniciarSesionGUI.dispUser().volver();
				if (llamada == 0)
					ApplicationLauncher.inicioSesion().volver();
			}
		});
		btnVolver.setBounds(301, 490, 105, 33);
		getContentPane().add(btnVolver);

		btnConsultar = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Consultar"));
		btnConsultar.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				mostrar();
			}
		});

		btnConsultar.setBounds(535, 93, 147, 40);
		btnConsultar.setEnabled(false);
		getContentPane().add(btnConsultar);

		lblMostrando = new JLabel(); 
		lblMostrando.setHorizontalAlignment(SwingConstants.CENTER);
		lblMostrando.setBounds(49, 219, 605, 26);
		getContentPane().add(lblMostrando);

		lblInformacion1 = new JLabel(); 
		lblInformacion1.setBounds(49, 270, 156, 26);
		getContentPane().add(lblInformacion1);

		lblInformacion2 = new JLabel();
		lblInformacion2.setBounds(49, 306, 156, 26);
		getContentPane().add(lblInformacion2);

		lblInformacion3 = new JLabel();
		lblInformacion3.setBounds(49, 342, 156, 26);
		getContentPane().add(lblInformacion3);

		lblInformacion4 = new JLabel();
		lblInformacion4.setBounds(49, 378, 156, 26);
		getContentPane().add(lblInformacion4);

		lblInformacion5 = new JLabel();
		lblInformacion5.setBounds(49, 414, 156, 24);
		getContentPane().add(lblInformacion5);

		lblInformacion6 = new JLabel();
		lblInformacion6.setBounds(49, 448, 156, 25);
		getContentPane().add(lblInformacion6);

		lblContenido1 = new JLabel(); 
		lblContenido1.setBounds(271, 270, 383, 26);
		getContentPane().add(lblContenido1);

		lblContenido2 = new JLabel();
		lblContenido2.setBounds(271, 305, 383, 26);
		getContentPane().add(lblContenido2);

		lblContenido3 = new JLabel();
		lblContenido3.setBounds(271, 342, 383, 26);
		getContentPane().add(lblContenido3);

		lblContenido4 = new JLabel();
		lblContenido4.setBounds(271, 378, 383, 26);
		getContentPane().add(lblContenido4);

		lblContenido5 = new JLabel();
		lblContenido5.setBounds(271, 414, 383, 24);
		getContentPane().add(lblContenido5);

		lblContenido6 = new JLabel();
		lblContenido6.setBounds(271, 448, 383, 25);
		getContentPane().add(lblContenido6);
	}

	public void mostrar() {

		lblInformacion1.setText(ResourceBundle.getBundle("Etiquetas").getString("Identificador"));
		lblInformacion2.setText(ResourceBundle.getBundle("Etiquetas").getString("NombreAdmin"));//TODO para cualquiera:cambiar etquetas a nombre de creador -agus/vidal
		switch (seleccionado) {
		case (1):

			lblMostrando.setText(ResourceBundle.getBundle("Etiquetas").getString("Mostrando") + " " + ResourceBundle.getBundle("Etiquetas").getString("Competicion") + " " + competicion.getNombre());
		lblContenido1.setText(competicion.getId());
		lblContenido2.setText(competicion.getNombreActor());

		lblInformacion3.setText(ResourceBundle.getBundle("Etiquetas").getString("Deporte") + ":");
		lblContenido3.setText(competicion.getDeporte());
		lblInformacion4.setText(ResourceBundle.getBundle("Etiquetas").getString("Genero") + ":");

		String genero = new String();
		if (competicion.getGenero() == 'M')
			genero = ResourceBundle.getBundle("Etiquetas").getString("Masculino");
		if (competicion.getGenero() == 'F')
			genero = ResourceBundle.getBundle("Etiquetas").getString("Femenino");
		if (competicion.getGenero() == 'O')
			genero = ResourceBundle.getBundle("Etiquetas").getString("Otro");

		lblContenido4.setText(genero);
		lblInformacion5.setText(ResourceBundle.getBundle("Etiquetas").getString("Temporada") + ":");
		lblContenido5.setText(competicion.getTemporada());
		lblInformacion6.setText(ResourceBundle.getBundle("Etiquetas").getString("Descripcio") + ":");
		lblContenido6.setText(competicion.getDescripcion());

		break;
		case (2):

			lblMostrando.setText(ResourceBundle.getBundle("Etiquetas").getString("Mostrando") + " " + ResourceBundle.getBundle("Etiquetas").getString("Evento") + " " + evento.getNombre());
		lblContenido1.setText(evento.getId());
		lblContenido2.setText(evento.getNombreActor());

		lblInformacion3.setText(ResourceBundle.getBundle("Etiquetas").getString("NombreCompeticio") + ":");
		lblContenido3.setText(evento.getComp().getNombre());
		lblInformacion4.setText(ResourceBundle.getBundle("Etiquetas").getString("Fecha") + ":");
		lblContenido4.setText(evento.getFecha().toString());
		lblInformacion5.setText(ResourceBundle.getBundle("Etiquetas").getString("Descripcio") + ":");
		lblContenido5.setText(evento.getDescripcion());
		lblInformacion6.setText("");
		lblContenido6.setText("");

		break;
		case (3):

			lblMostrando.setText(ResourceBundle.getBundle("Etiquetas").getString("Mostrando") + " " + ResourceBundle.getBundle("Etiquetas").getString("Pregunta") + " " + pregunta.getEnunciado());
		lblContenido1.setText(pregunta.getId());
		lblContenido2.setText(pregunta.getNombreActor());

		lblInformacion3.setText(ResourceBundle.getBundle("Etiquetas").getString("NombreEvento") + ":");
		lblContenido3.setText(pregunta.getEvento().getNombre());
		lblInformacion4.setText(ResourceBundle.getBundle("Etiquetas").getString("Estado") + ":");
		String estado = new String();

		if (pregunta.getEstado())
			estado = "Abierta";
		else
			estado = "Cerrada";

		lblContenido4.setText(estado);
		lblInformacion5.setText(ResourceBundle.getBundle("Etiquetas").getString("Resultado") + ":");
		lblContenido5.setText(pregunta.getResultado().getRespuesta()); //Modificacion Vidal 10/05
		lblInformacion6.setText(ResourceBundle.getBundle("Etiquetas").getString("ApuestaMinima") + ":");
		lblContenido6.setText(Double.toString(pregunta.getMinBet()));

		break;
		case (4):

			lblMostrando.setText(ResourceBundle.getBundle("Etiquetas").getString("Mostrando") + " " + ResourceBundle.getBundle("Etiquetas").getString("Pronostico") + " " + pronostico.getRespuesta());
		lblContenido1.setText(pronostico.getId());
		lblContenido2.setText(pronostico.getNombreAdmin());

		lblInformacion3.setText(ResourceBundle.getBundle("Etiquetas").getString("NombrePregunta") + ":");
		lblContenido3.setText(pronostico.getPregunta().getEnunciado());
		lblInformacion4.setText(ResourceBundle.getBundle("Etiquetas").getString("Cuota") + ":");
		lblContenido4.setText(Double.toString(pronostico.getGanancia()));
		lblInformacion5.setText("");
		lblContenido5.setText("");
		lblInformacion6.setText("");
		lblContenido6.setText("");

		break;
		default:
			break;
		}
	}
}

