package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import businessLogic.BLFacadeImplementation;
import domain.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class CrearPronosticoGUI extends JFrame{
	private static final long serialVersionUID = 1L;

	private JTextField textFieldNombrePronostico;
	private JLabel lblSeleccionaComp;
	private JLabel lblSeleccionaEvento;
	private JLabel lblSeleccionaPreg;
	private JLabel lblNombrePronostico;
	private JComboBox<String> comboBoxCompeticiones;
	private JComboBox<String> comboBoxEventos;
	private JComboBox<String> comboBoxPreguntas;
	private JButton btnAnadirPronostico;
	private JButton btnCerrarPestana;
	private JLabel lblCuotaGanancia;
	private JTextField textFieldCuotaGanancia;
	private JLabel lblErrores;

	Vector<Competicion> competiciones;
	Vector<Evento> eventos;
	Vector<Pregunta> preguntas;
	Pregunta selec = null;
	BLFacadeImplementation facade = (BLFacadeImplementation) IniciarSesionGUI.getBusinessLogic();

	public CrearPronosticoGUI() {
		this.setTitle("Añadir Pronostico");
		setBounds(600, 250,430, 360);
		getContentPane().setLayout(null);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(1);

			}});

		lblSeleccionaComp = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SeleccionaCompeticion"));
		lblSeleccionaComp.setBounds(22, 11, 186, 23);
		getContentPane().add(lblSeleccionaComp);

		lblSeleccionaEvento = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SeleccionaEvento"));
		lblSeleccionaEvento.setBounds(22, 45, 186, 23);
		getContentPane().add(lblSeleccionaEvento);

		lblSeleccionaPreg = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SeleccionaPregunta"));
		lblSeleccionaPreg.setBounds(22, 79, 186, 23);
		getContentPane().add(lblSeleccionaPreg);

		lblNombrePronostico = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("IntroducePronostico"));
		lblNombrePronostico.setVisible(false);
		lblNombrePronostico.setBounds(22, 113, 363, 23);
		getContentPane().add(lblNombrePronostico);
		
		

		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		

		
		comboBoxCompeticiones = new JComboBox<String>();
		competiciones = facade.obtenerCompeticiones();

		Vector<String> strings = new Vector<String>();
		strings.add(ResourceBundle.getBundle("Etiquetas").getString("SeleccionaOpcion"));
		for (Competicion c: competiciones)
			strings.add(c.getNombre());

		comboBoxCompeticiones.setModel(new DefaultComboBoxModel<String>(strings));

		comboBoxCompeticiones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!comboBoxCompeticiones.getSelectedItem().equals(ResourceBundle.getBundle("Etiquetas").getString("SeleccionaOpcion"))) {
					Competicion competicion = (Competicion) competiciones.get(comboBoxCompeticiones.getSelectedIndex() - 1);
					eventos = (Vector<Evento>) facade.obtenerEventosPorCompeticion(competicion);
					Vector<String> strings = new Vector<String>();
					strings.add(ResourceBundle.getBundle("Etiquetas").getString("SeleccionaOpcion"));
					for (Evento evento : eventos) {
						if(evento.isEstado()) {
							strings.add(evento.getNombre());
						}
					}
					comboBoxEventos.setModel(new DefaultComboBoxModel<String>(strings));
				}
			}
		});

		comboBoxCompeticiones.setBounds(218, 11, 186, 23);
		getContentPane().add(comboBoxCompeticiones);

		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		comboBoxEventos = new JComboBox<String>();

		comboBoxEventos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!comboBoxEventos.getSelectedItem().equals(ResourceBundle.getBundle("Etiquetas").getString("SeleccionaOpcion"))) {
					Evento evento = (Evento) eventos.get(comboBoxEventos.getSelectedIndex() - 1);
					preguntas = (Vector<Pregunta>) facade.obtenerPreguntasPorEvento(evento);
					Vector<String> strings = new Vector<String>();
					strings.add(ResourceBundle.getBundle("Etiquetas").getString("SeleccionaOpcion"));
					for (Pregunta pregunta : preguntas)
						if (pregunta.getEvento().getNombre().equals(evento.getNombre()))
							strings.add(pregunta.getEnunciado());
					comboBoxPreguntas.setModel(new DefaultComboBoxModel<String>(strings));
				}
			}
		});

		comboBoxEventos.setBounds(218, 45, 186, 23);
		getContentPane().add(comboBoxEventos);

		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		comboBoxPreguntas = new JComboBox<String>();

		comboBoxPreguntas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!comboBoxPreguntas.getSelectedItem().equals(ResourceBundle.getBundle("Etiquetas").getString("SeleccionaOpcion"))) {
					selec = preguntas.get(comboBoxPreguntas.getSelectedIndex() - 1);
					btnAnadirPronostico.setEnabled(true);
					lblNombrePronostico.setVisible(true);
					lblCuotaGanancia.setVisible(true);
					textFieldNombrePronostico.setVisible(true);
					textFieldCuotaGanancia.setVisible(true);
				}
			}
		});
		comboBoxPreguntas.setBounds(218, 79, 186, 23);
		getContentPane().add(comboBoxPreguntas);

		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		textFieldNombrePronostico = new JTextField();
		textFieldNombrePronostico.setVisible(false);
		textFieldNombrePronostico.setBounds(22, 135, 186, 23);
		getContentPane().add(textFieldNombrePronostico);
		textFieldNombrePronostico.setColumns(10);

		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		btnAnadirPronostico = new JButton(ResourceBundle.getBundle("Etiquetas").getString("AnadirPronostico"));
		btnAnadirPronostico.setEnabled(false);
		btnAnadirPronostico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblErrores.setVisible(false);
				String respuesta = textFieldNombrePronostico.getText(); //obtener los campos rellenados por el actor
				String sGanancia = textFieldCuotaGanancia.getText();

				if (respuesta.equals("") || sGanancia.equals("")) { //comprobacion de que el actor no ha rellenado los campos
					lblErrores.setVisible(true);
					lblErrores.setText(ResourceBundle.getBundle("Etiquetas").getString("RellenaTCampos")); //muestra error de campos vacios
				}else { // ha rellenado los campos
					double ganancia = Double.parseDouble(sGanancia);
					int n = facade.crearPronostico(respuesta, selec, ganancia, IniciarSesionGUI.actor());

					switch (n) {
					case 0:
						//lblErrores.setForeground(Color.black);//TODO: para agus: mirar el cambio de colores -agus
						lblErrores.setVisible(true);
						lblErrores.setText(ResourceBundle.getBundle("Etiquetas").getString("PronosticoAnadidoCorrectamente"));
						//lblErrores.setForeground(Color.RED);
						break;
					case 1:
						lblErrores.setVisible(true);
						lblErrores.setText(ResourceBundle.getBundle("Etiquetas").getString("PronosticoYaExistente"));
						break;
					case 2:
						lblErrores.setVisible(true);
						lblErrores.setText(ResourceBundle.getBundle("Etiquetas").getString("GananciaInvalida"));
						break;
					}

				}
			}
		});
		btnAnadirPronostico.setBounds(108, 261, 179, 30);
		getContentPane().add(btnAnadirPronostico);

		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		btnCerrarPestana = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Volver"));
		btnCerrarPestana.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarGUI();
				IniciarSesionGUI.dispAdmin().setVisible(true);
			}
		});

		btnCerrarPestana.setBounds(315, 287, 89, 23);
		getContentPane().add(btnCerrarPestana);

		lblCuotaGanancia = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("IntroduceGanancia"));
		lblCuotaGanancia.setVisible(false);
		lblCuotaGanancia.setBounds(22, 169, 382, 23);
		getContentPane().add(lblCuotaGanancia);

		textFieldCuotaGanancia = new JTextField();
		textFieldCuotaGanancia.setVisible(false);
		textFieldCuotaGanancia.setColumns(10);
		textFieldCuotaGanancia.setBounds(22, 188, 186, 23);
		getContentPane().add(textFieldCuotaGanancia);

		lblErrores = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Vacio"));
		lblErrores.setVisible(false);
		lblErrores.setBackground(Color.GRAY);
		lblErrores.setForeground(Color.RED);
		lblErrores.setHorizontalAlignment(SwingConstants.CENTER);
		lblErrores.setBounds(22, 225, 363, 30);
		getContentPane().add(lblErrores);
	}

	public void cerrarGUI() {
		this.setVisible(false);
		
	}


}
