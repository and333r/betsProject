package gui;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import businessLogic.BLFacadeImplementation;
import domain.Competicion;
import domain.Evento;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class CreatePreguntaGUI extends JFrame {
	

	private static final long serialVersionUID = 1L;
	
	private JTextField enunciado= new JTextField();;
	private JButton btnCrearPregunta;
	private JLabel lblCompeticion;
	private JLabel lblEvento;
	
	private JComboBox<String> comboBoxCompeticiones = new JComboBox<String>();
	private JComboBox<String>comboBoxEventos = new JComboBox<String>();

	BLFacadeImplementation facade = (BLFacadeImplementation) IniciarSesionGUI.getBusinessLogic();
	private Vector<Competicion> competiciones= new Vector<Competicion> ();
	private Vector<String> compNames= new Vector<String>();
	private Vector<String> eveNames= new Vector<String>();
	private Vector<Evento> eventos= new Vector<Evento>();
	private final JTextField apuesta = new JTextField();
	private final JLabel introducirEnunciado = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("IntroduceEnunciado"));
	private final JLabel introducirApuesta = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("IntroduceMinBet"));
	private Evento eventSelected=null;
	private final JLabel error = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("PreguntaYaExistente"));
	private final JButton btnVolver = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Volver"));
	
	public CreatePreguntaGUI() {
		this.setTitle("Crear Pregunta");
		setBounds(600, 250,485, 320);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(1);

			}});
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		getContentPane().setLayout(null);
		
		//ComboBoxCompetis
		competiciones = facade.obtenerCompeticiones();
		compNames.add(ResourceBundle.getBundle("Etiquetas").getString("SeleccionaOpcion"));
		for (Competicion e: competiciones) {
			compNames.add(e.getNombre());
		}
		
		comboBoxCompeticiones.setModel(new DefaultComboBoxModel<String>(compNames));
		
		
		comboBoxCompeticiones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if (!comboBoxCompeticiones.getSelectedItem().equals(ResourceBundle.getBundle("Etiquetas").getString("SeleccionaOpcion"))) {
				Competicion competicion = (Competicion) competiciones.get(comboBoxCompeticiones.getSelectedIndex()-1); 
				eventos= facade.obtenerEventosPorCompeticion(competicion);
				eveNames.add(ResourceBundle.getBundle("Etiquetas").getString("SeleccionaOpcion"));
				for (Evento evento : eventos) {
					if(evento.isEstado()) {
						eveNames.add(evento.getNombre());
					}
				}
				comboBoxEventos.setModel(new DefaultComboBoxModel<String>(eveNames));
			}
				

				
				
			}
		});
		comboBoxCompeticiones.setBounds(219, 11, 222, 22);
		getContentPane().add(comboBoxCompeticiones);
		
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//ComboBoxEventos
		comboBoxEventos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 if (!comboBoxEventos.getSelectedItem().equals(ResourceBundle.getBundle("Etiquetas").getString("SeleccionaOpcion"))) {
					 eventSelected = (Evento) eventos.get(comboBoxEventos.getSelectedIndex()-1);
					enunciado.setVisible(true);
					apuesta.setVisible(true);
					introducirEnunciado.setVisible(true);
					introducirApuesta.setVisible(true);
				}
			}
		});
		
		comboBoxEventos.setBounds(219, 56, 222, 22);
		getContentPane().add(comboBoxEventos);
		
		
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//MeterEnunciado
		enunciado.setBounds(219, 114, 222, 37);
		enunciado.setVisible(false);
		getContentPane().add(enunciado);
		enunciado.setColumns(10);
		
		
		
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//Meter apuesta minima 
		
		apuesta.setBounds(219, 162, 222, 37);
		apuesta.setVisible(false);
		getContentPane().add(apuesta);
		apuesta.setColumns(10);
		
		
		
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
		//Crear la pregunta
		btnCrearPregunta = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CrearPregunta")); 
		btnCrearPregunta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String enunciadoPregunta= enunciado.getText();
				String bet= apuesta.getText();
				if (!facade.existeLaPregunta(enunciadoPregunta, eventSelected)) {
					facade.crearPregunta(enunciadoPregunta, eventSelected,Double.parseDouble(bet), true, IniciarSesionGUI.actor());
				}
				else error.setVisible(true);
				
				cerrarGUI();
			}
		});
		
		btnCrearPregunta.setBounds(149, 210, 140, 40);
		getContentPane().add(btnCrearPregunta);
		
		
		
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
		//TextoCompeti
		lblCompeticion = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SeleccionaCompeticion")); 
		lblCompeticion.setBounds(10, 11, 199, 22);
		getContentPane().add(lblCompeticion);
		
		
		
		
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
		//Texto evento
		lblEvento = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SeleccionaEvento")); 
		lblEvento.setBounds(10, 56, 199, 22);
		getContentPane().add(lblEvento);
		
		
		
		
		
		introducirEnunciado.setBounds(10, 125, 199, 14);
		introducirEnunciado.setVisible(false);
		getContentPane().add(introducirEnunciado);
		
		
		
		introducirApuesta.setBounds(10, 173, 199, 14);
		introducirApuesta.setVisible(false);
		getContentPane().add(introducirApuesta);
		
		
		
		error.setBounds(219, 100, 222, 14);
		error.setVisible(false);
		getContentPane().add(error);
		
		
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarGUI();
			}
		});
		btnVolver.setBounds(370, 247, 89, 23);
		getContentPane().add(btnVolver);
		
		
	}
	
	public void cerrarGUI() {
		this.setVisible(false);
		IniciarSesionGUI.dispAdmin().setVisible(true);
	}
}