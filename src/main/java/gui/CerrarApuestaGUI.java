package gui;

import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;

import businessLogic.BLFacadeImplementation;
import domain.Competicion;
import domain.Evento;
import domain.Pregunta;
import domain.Pronostico;
import domain.Usuario;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.Color;
import java.awt.Font;

public class CerrarApuestaGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private JComboBox<String> comboBoxCompetis;
	private JComboBox<String> comboBoxEventos;
	private JComboBox<String> comboBoxPreguntas;
	private JComboBox<String> comboBoxPronosticos;

	private JLabel lblSlctComp;
	private JLabel lblSlctEvent;
	private JLabel lblSlctPreg;
	private JLabel lblSlctPron;
	private JLabel lblSlctGanador;

	private JButton btnNewButton;

	BLFacadeImplementation facade = (BLFacadeImplementation) IniciarSesionGUI.getBusinessLogic();
	Vector<Competicion> competiciones;
	Vector <Evento> eventos;
	Vector<Pregunta> preguntas;
	Vector<Pronostico> pronosticos;
	Pregunta pregunta;
	Pronostico pronostico;
	Vector<Usuario> ganadores;

	private DefaultListModel<String> winners;
	private JList<String> list;
	private JScrollPane scrollBar;
	private JLabel lblNoHay;


	public CerrarApuestaGUI() {

		this.setTitle("Cerrar apuesta");
		setBounds(600, 250,465, 380);
		getContentPane().setLayout(null);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(1);

			}});

		comboBoxCompetis = new JComboBox<String>();
		comboBoxEventos = new JComboBox<String>();
		comboBoxPreguntas = new JComboBox<String>();
		comboBoxPronosticos = new JComboBox<String>();

		winners= new DefaultListModel<String>();


		lblSlctComp = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SeleccionaCompeticion"));
		lblSlctComp.setBounds(10, 24, 119, 14);
		getContentPane().add(lblSlctComp);


		lblSlctEvent = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SeleccionaEvento"));
		lblSlctEvent.setBounds(10, 57, 118, 14);
		getContentPane().add(lblSlctEvent);
		lblSlctEvent.setVisible(false);

		lblSlctPreg = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SeleccionaPregunta"));
		lblSlctPreg.setBounds(11, 90, 119, 14);
		getContentPane().add(lblSlctPreg);
		lblSlctPreg.setVisible(false);

		lblSlctPron = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SeleccionaPronostico"));
		lblSlctPron.setBounds(7, 123, 121, 14);
		getContentPane().add(lblSlctPron);
		lblSlctPron.setVisible(false);

		lblSlctGanador = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Ganadores"));
		lblSlctGanador.setBounds(8, 155, 119, 14);
		getContentPane().add(lblSlctGanador);
		lblSlctGanador.setVisible(false);


		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		competiciones = facade.obtenerCompeticiones();
		Vector<String> strings = new Vector<String>();
		strings.add(ResourceBundle.getBundle("Etiquetas").getString("SeleccionaOpcion"));
		for (Competicion c:competiciones) {
			strings.add(c.getNombre());
		}
		comboBoxCompetis.setModel(new DefaultComboBoxModel<String>(strings));
		//Competiciones
		comboBoxCompetis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				btnNewButton.setEnabled(false);
				if (!comboBoxCompetis.getSelectedItem().equals(ResourceBundle.getBundle("Etiquetas").getString("SeleccionaOpcion"))) {

					Competicion competicion = (Competicion) competiciones.get(comboBoxCompetis.getSelectedIndex() - 1);
					eventos = (Vector<Evento>) facade.obtenerEventosAbiertosPorCompeticion(competicion);

					if (eventos.isEmpty()) {

						comboBoxEventos.setEnabled(false);
						lblNoHay.setVisible(true);
						lblNoHay.setText("La competicion seleccionada no tiene eventos abiertos asociados.");

					} else {

						lblNoHay.setVisible(false);
						comboBoxEventos.setEnabled(true);
						Vector<String> strings = new Vector<String>();
						strings.add(ResourceBundle.getBundle("Etiquetas").getString("SeleccionaOpcion"));

						for (Evento evento : eventos) 
							if(evento.isEstado()) 
								strings.add(evento.getNombre());

						comboBoxEventos.setModel(new DefaultComboBoxModel<String>(strings));
						lblSlctEvent.setVisible(true);
						comboBoxEventos.setVisible(true);
					}
				}
			}
		});

		comboBoxCompetis.setBounds(134, 18, 271, 22);
		getContentPane().add(comboBoxCompetis);


		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	

		//Eventos
		comboBoxEventos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				btnNewButton.setEnabled(false);
				if (!comboBoxEventos.getSelectedItem().equals(ResourceBundle.getBundle("Etiquetas").getString("SeleccionaOpcion"))) {
					Evento evento = (Evento) eventos.get(comboBoxEventos.getSelectedIndex() - 1);
					preguntas = (Vector<Pregunta>) facade.obtenerPreguntasAbiertasPorEvento(evento);

					if (preguntas.isEmpty()) {

						comboBoxPreguntas.setEnabled(false);
						lblNoHay.setVisible(true);
						lblNoHay.setText("El evento seleccionado no tiene preguntas abiertas asociadas.");
					} else {
						
						lblNoHay.setVisible(false);
						comboBoxPreguntas.setEnabled(true);
						Vector<String> strings = new Vector<String>();

						strings.add(ResourceBundle.getBundle("Etiquetas").getString("SeleccionaOpcion"));				

						for (Pregunta pregunta : preguntas)
							if (pregunta.getEstado()) 
								strings.add(pregunta.getEnunciado());

						comboBoxPreguntas.setModel(new DefaultComboBoxModel<String>(strings));
						lblSlctPreg.setVisible(true);
						comboBoxPreguntas.setVisible(true);
					}
				}

			}
		});
		comboBoxEventos.setBounds(134, 52, 271, 22);
		comboBoxEventos.setVisible(false);
		getContentPane().add(comboBoxEventos);


		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//Preguntas
		comboBoxPreguntas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				btnNewButton.setEnabled(false);
				if(!comboBoxPreguntas.getSelectedItem().equals(ResourceBundle.getBundle("Etiquetas").getString("SeleccionaOpcion"))) {
					pregunta = (Pregunta) preguntas.get(comboBoxPreguntas.getSelectedIndex() - 1);
					pronosticos = (Vector<Pronostico>) facade.obtenerPronosticosDePregunta(pregunta);
					Vector<String> strings = new Vector<String>();
					strings.add(ResourceBundle.getBundle("Etiquetas").getString("SeleccionaOpcion"));

					for(Pronostico p:pronosticos) {
						strings.add(p.getRespuesta());
					}
					comboBoxPronosticos.setModel(new DefaultComboBoxModel<String>(strings));
					lblSlctPron.setVisible(true);
					comboBoxPronosticos.setVisible(true);
				}
			}
		});
		comboBoxPreguntas.setBounds(134, 84, 271, 22);
		comboBoxPreguntas.setVisible(false);
		getContentPane().add(comboBoxPreguntas);


		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//Pronósticos
		comboBoxPronosticos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!comboBoxPronosticos.getSelectedItem().equals(ResourceBundle.getBundle("Etiquetas").getString("SeleccionaOpcion"))) {
					pronostico= (Pronostico) pronosticos.get(comboBoxPronosticos.getSelectedIndex()-1);
					winners.clear();
					ganadores= new Vector<Usuario>();
					ganadores = (Vector<Usuario>) facade.obtenerGanadores(pronostico);
					for (Usuario t: ganadores) {
						winners.addElement("Usuario: " + t.getNombreUsuario() + " - Cuota: " + pronostico.getGanancia());
					}
					list.setModel(winners);
					list.setVisible(true);
					scrollBar.setVisible(true);
					lblSlctGanador.setVisible(true);;
					
					btnNewButton.setEnabled(true);
				}
			}
		});
		comboBoxPronosticos.setBounds(133, 119, 271, 22);
		comboBoxPronosticos.setVisible(false);
		getContentPane().add(comboBoxPronosticos);
		getContentPane().setLayout(null);



		btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CerrarApuesta"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				facade.cerrarApuesta(pregunta.getId(), pronostico.getId());
				facade.actualizarSaldos(ganadores, pronostico);
				IniciarSesionGUI.dispAdmin().volver();
			}
		});
		btnNewButton.setBounds(122, 244, 197, 37);
		btnNewButton.setEnabled(false);
		getContentPane().add(btnNewButton);



		list = new JList<String>();
		list.setBounds(134, 154, 271, 79);
		list.setVisible(false);

		scrollBar = new JScrollPane(list);
		scrollBar.setBounds(134, 154, 271, 79);
		getContentPane().add(scrollBar);
		scrollBar.setVisible(false);


		JButton btnVolver = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Volver"));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IniciarSesionGUI.dispAdmin().volver();
			}
		});
		btnVolver.setBounds(349, 307, 89, 23);
		getContentPane().add(btnVolver);
		
		lblNoHay = new JLabel(""); 
		lblNoHay.setVisible(false);
		lblNoHay.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNoHay.setForeground(Color.RED);
		lblNoHay.setBackground(Color.PINK);
		lblNoHay.setBounds(97, 186, 271, 37);
		getContentPane().add(lblNoHay);
	}

}
