package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.SwingConstants;

import businessLogic.BLFacadeImplementation;
import domain.*;

import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class ApostarGUI extends JFrame{


	private static final long serialVersionUID = 1L;

	private JFrame ventanaSeguro = new EstasSeguroMismoPronosticoGUI();

	public static String id;
	public static double cantidad;
	public static double cuota;

	private Pronostico pronostico;

	private JLabel lblSlctComp;
	private JLabel lblSlctEvent;
	private JLabel lblSlctPreg;
	private JLabel lblSlctPron;
	private JLabel lblCuotaEs;
	private JLabel lblNumCuota;
	private JLabel lblCantApuesta;
	private JLabel lblErrores;
	private JLabel lblDineros;
	private JLabel lblNoHay;

	private JComboBox<String> comboBoxCompeticiones;
	private JComboBox<String> comboBoxEventos;
	private JComboBox<String> comboBoxPreguntas;
	private JComboBox<String> comboBoxPronosticos;

	private JTextField textFieldCantApuesta;

	private JButton btnApostar;
	private JButton btnVolver;

	private Vector<Competicion> competiciones;
	private Vector<Evento> eventos;
	private Vector<Pregunta> preguntas;
	private Vector<Pronostico> pronosticos;
	BLFacadeImplementation facade = (BLFacadeImplementation) IniciarSesionGUI.getBusinessLogic();
	
	static final String etiquetas = "Etiquetas";
	static final String a = "Tahoma";
	static final String o = "SeleccionaOpcion";
	
	public ApostarGUI() {

		this.setTitle("Hacer apuesta");
		setBounds(600, 250,465, 380);
		getContentPane().setLayout(null);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(1);

			}});

		lblSlctComp = new JLabel(ResourceBundle.getBundle(etiquetas).getString("SeleccionaCompeticion"));
		lblSlctComp.setBounds(10, 28, 185, 19);
		getContentPane().add(lblSlctComp);

		lblSlctEvent = new JLabel(ResourceBundle.getBundle(etiquetas).getString("SeleccionaEvento"));
		lblSlctEvent.setBounds(10, 58, 185, 19);
		getContentPane().add(lblSlctEvent);
		lblSlctEvent.setVisible(false);

		lblSlctPreg = new JLabel(ResourceBundle.getBundle(etiquetas).getString("SeleccionaPregunta"));
		lblSlctPreg.setBounds(10, 88, 185, 19);
		getContentPane().add(lblSlctPreg);
		lblSlctPreg.setVisible(false);

		lblSlctPron = new JLabel(ResourceBundle.getBundle(etiquetas).getString("SeleccionaPronostico"));
		lblSlctPron.setBounds(10, 118, 185, 19);
		getContentPane().add(lblSlctPron);
		lblSlctPron.setVisible(false);

		comboBoxCompeticiones = new JComboBox<String>();
		comboBoxCompeticiones.setBounds(205, 26, 233, 22);
		getContentPane().add(comboBoxCompeticiones);

		comboBoxEventos = new JComboBox<String>();
		comboBoxEventos.setBounds(205, 56, 233, 22);
		getContentPane().add(comboBoxEventos);
		comboBoxEventos.setVisible(false);

		comboBoxPreguntas = new JComboBox<String>();
		comboBoxPreguntas.setBounds(205, 86, 233, 22);
		getContentPane().add(comboBoxPreguntas);
		comboBoxPreguntas.setVisible(false);

		comboBoxPronosticos = new JComboBox<String>();
		comboBoxPronosticos.setBounds(205, 116, 233, 22);
		getContentPane().add(comboBoxPronosticos);
		comboBoxPronosticos.setVisible(false);

		lblCuotaEs = new JLabel(ResourceBundle.getBundle(etiquetas).getString("CuotaEs"));
		lblCuotaEs.setFont(new Font(a, Font.PLAIN, 14));
		lblCuotaEs.setBounds(10, 165, 148, 19);
		getContentPane().add(lblCuotaEs);
		lblCuotaEs.setVisible(false);

		lblNumCuota = new JLabel("New label");
		lblNumCuota.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumCuota.setFont(new Font(a, Font.PLAIN, 14));
		lblNumCuota.setBounds(205, 167, 133, 19);
		getContentPane().add(lblNumCuota);
		lblNumCuota.setVisible(false);

		lblCantApuesta = new JLabel(ResourceBundle.getBundle(etiquetas).getString("IntroduceApuesta"));
		lblCantApuesta.setFont(new Font(a, Font.PLAIN, 14));
		lblCantApuesta.setBounds(10, 214, 275, 19);
		getContentPane().add(lblCantApuesta);
		lblCantApuesta.setVisible(false);

		textFieldCantApuesta = new JTextField();
		textFieldCantApuesta.setBounds(308, 216, 65, 19);
		getContentPane().add(textFieldCantApuesta);
		textFieldCantApuesta.setColumns(10);
		textFieldCantApuesta.setVisible(false);

		btnApostar = new JButton(ResourceBundle.getBundle(etiquetas).getString("Apuesta"));
		btnApostar.setBounds(123, 261, 207, 35);
		getContentPane().add(btnApostar);
		btnApostar.setEnabled(false);

		btnVolver = new JButton(ResourceBundle.getBundle(etiquetas).getString("Volver"));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				IniciarSesionGUI.dispUser().actualizar();
				IniciarSesionGUI.dispUser().volver();
			}
		});
		btnVolver.setBounds(349, 307, 89, 23);
		getContentPane().add(btnVolver);

		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		competiciones = facade.obtenerCompeticiones();
		Vector<String> strings = new Vector<String>();
		strings.add(ResourceBundle.getBundle(etiquetas).getString(o));
		for (Competicion c:competiciones) {
			strings.add(c.getNombre());
		}
		comboBoxCompeticiones.setModel(new DefaultComboBoxModel<String>(strings));

		lblErrores = new JLabel(ResourceBundle.getBundle(etiquetas).getString("Vacio")); 
		lblErrores.setBounds(10, 311, 328, 19);
		getContentPane().add(lblErrores);

		lblDineros = new JLabel(ResourceBundle.getBundle(etiquetas).getString("ApostarGUI.lblNewLabel.text"));
		lblDineros.setVisible(false);
		lblDineros.setBounds(377, 218, 61, 15);
		getContentPane().add(lblDineros);

		lblNoHay = new JLabel(); 
		this.lblNoHay.setVisible(false);
		lblNoHay.setFont(new Font(a, Font.PLAIN, 12));
		lblNoHay.setHorizontalAlignment(SwingConstants.CENTER);
		lblNoHay.setBackground(Color.GRAY);
		lblNoHay.setForeground(Color.RED);
		lblNoHay.setBounds(123, 169, 216, 35);
		getContentPane().add(lblNoHay);

		comboBoxCompeticiones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				comboBoxEventos.setEnabled(false);
				comboBoxPreguntas.setEnabled(false);
				comboBoxPronosticos.setEnabled(false);
				btnApostar.setEnabled(false);
				
				if (!comboBoxCompeticiones.getSelectedItem().equals(ResourceBundle.getBundle(etiquetas).getString(o))) {

					Competicion competicion = (Competicion) competiciones.get(comboBoxCompeticiones.getSelectedIndex() - 1);
					eventos = (Vector<Evento>) facade.obtenerEventosAbiertosPorCompeticion(competicion);

					if (eventos.isEmpty()) {

						lblNoHay.setVisible(true);
						lblNoHay.setText("La competicino seleccionada no tiene eventos abiertos asociados.");

					} else  {
						lblNoHay.setVisible(false);
						Vector<String> strings = new Vector<String>();

						strings.add(ResourceBundle.getBundle(etiquetas).getString(o));

						for (Evento evento : eventos) 
							strings.add(evento.getNombre());

						comboBoxEventos.setEnabled(true);
						comboBoxEventos.setModel(new DefaultComboBoxModel<String>(strings));
						lblSlctEvent.setVisible(true);
						comboBoxEventos.setVisible(true);
					}
				}
			}
		});

		//-------------------------------------------------------------------------------------------------------------------------------------------//

		comboBoxEventos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				comboBoxPreguntas.setEnabled(false);
				comboBoxPronosticos.setEnabled(false);
				btnApostar.setEnabled(false);
				
				if (!comboBoxEventos.getSelectedItem().equals(ResourceBundle.getBundle(etiquetas).getString(o))) {

					Evento evento = (Evento) eventos.get(comboBoxEventos.getSelectedIndex() - 1);
					preguntas = (Vector<Pregunta>) facade.obtenerPreguntasAbiertasPorEvento(evento);

					if (preguntas.isEmpty()){

						lblNoHay.setVisible(true);
						lblNoHay.setText("El evento seleccionado no tiene preguntas abiertas asociadas.");
					} else {
						
						lblNoHay.setVisible(false);
						Vector<String> strings = new Vector<String>();

						strings.add(ResourceBundle.getBundle(etiquetas).getString(o));

						for (Pregunta pregunta : preguntas) 
							strings.add(pregunta.getEnunciado());

						comboBoxPreguntas.setEnabled(true);
						comboBoxPreguntas.setModel(new DefaultComboBoxModel<String>(strings));
						lblSlctPreg.setVisible(true);
						comboBoxPreguntas.setVisible(true);
					}
				}
			}
		});

		//-------------------------------------------------------------------------------------------------------------------------------------------//

		comboBoxPreguntas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				comboBoxPronosticos.setEnabled(false);
				btnApostar.setEnabled(false);
				
				if(!comboBoxPreguntas.getSelectedItem().equals(ResourceBundle.getBundle(etiquetas).getString(o))) {
					Pregunta pregunta = (Pregunta) preguntas.get(comboBoxPreguntas.getSelectedIndex() - 1);
					pronosticos = (Vector<Pronostico>) facade.obtenerPronosticosDePregunta(pregunta);

					if (pronosticos.isEmpty()) {

						lblNoHay.setVisible(true);
						lblNoHay.setText("La pregunta seleccionada no tiene pronosticos asociados.");
					} else {
						lblNoHay.setVisible(false);
						Vector<String> strings = new Vector<String>();

						strings.add(ResourceBundle.getBundle(etiquetas).getString(o));
						for(Pronostico p:pronosticos) 
							strings.add(p.getRespuesta());

						comboBoxPronosticos.setEnabled(true);
						comboBoxPronosticos.setModel(new DefaultComboBoxModel<String>(strings));
						lblSlctPron.setVisible(true);
						comboBoxPronosticos.setVisible(true);
					}
				}
			}
		});

		//-------------------------------------------------------------------------------------------------------------------------------------------//

		comboBoxPronosticos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(!comboBoxPronosticos.getSelectedItem().equals(ResourceBundle.getBundle(etiquetas).getString(o))) {
					lblCuotaEs.setVisible(true);
					pronostico = (Pronostico) pronosticos.get(comboBoxPronosticos.getSelectedIndex() - 1);
					lblNumCuota.setText(Double.toString(pronostico.getGanancia()));
					lblNumCuota.setVisible(true);
					lblCantApuesta.setVisible(true);
					textFieldCantApuesta.setVisible(true);
					btnApostar.setEnabled(true);
					lblDineros.setVisible(true);
					btnApostar.setEnabled(true);
				}
			}
		});

		//--------------------------------------------------------------------------------------------------------------------------------------------//

		btnApostar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int resultado = facade.crearApuesta(pronostico,(Usuario) IniciarSesionGUI.actor(),Double.parseDouble(textFieldCantApuesta.getText()));
				switch(resultado) {
				case 0:
					lblErrores.setText(ResourceBundle.getBundle(etiquetas).getString("NuevaApuesta"));
					lblErrores.setForeground(Color.GREEN);

					break;
				case 1:
					lblErrores.setText(ResourceBundle.getBundle(etiquetas).getString("SaldoInsuficiente"));
					lblErrores.setForeground(Color.RED);
					break;
				case 2:
					guardarInfo(pronostico.getId()+"_"+IniciarSesionGUI.actor().getNombreUsuario(),pronostico.getGanancia(),Double.parseDouble(textFieldCantApuesta.getText())); 
					abrirSeguroGUI();
					break;
				case 3: 
					lblErrores.setText(ResourceBundle.getBundle(etiquetas).getString("NoMasApuestas"));
					lblErrores.setForeground(Color.RED);
					break;
				case 4:
					lblErrores.setText(ResourceBundle.getBundle(etiquetas).getString("CantidadInsuficiente"));
					lblErrores.setForeground(Color.RED);
					break;
				default:
					break;
				}
			}
		});
	}

	private void abrirSeguroGUI() {
		if (ventanaSeguro == null)
			ventanaSeguro = new EstasSeguroMismoPronosticoGUI();
		ventanaSeguro.setVisible(true);
	}

	private void guardarInfo(String id , double c, double cant) {

		ApostarGUI.id=id;
		ApostarGUI.cuota = c;
		ApostarGUI.cantidad=cant;
	}
}
