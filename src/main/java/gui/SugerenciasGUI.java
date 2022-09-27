package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.util.ResourceBundle;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class SugerenciasGUI extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private JFrame ventanaSugerencia;
	
	private JLabel lblTitulo;
	private JButton btnCompeticiones;
	private JButton btnEventos;
	private JButton btnPreguntas;
	private JButton btnVolver;
	private JButton btnOtros;
	
	public SugerenciasGUI() {
		getContentPane().setLayout(null);
		setBounds(600, 250,450, 300);
		this.setTitle("Seleccionar Tipo de Sugerencia");
		
		lblTitulo = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("TituloSugerencias"));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTitulo.setBounds(0, 11, 434, 29);
		getContentPane().add(lblTitulo);
		
		btnCompeticiones = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Competicion"));
		btnCompeticiones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaSugerencia = new SugerirCompeticionGUI();
				cerrarDispersora();
				ventanaSugerencia.setVisible(true);
			}
		});
		btnCompeticiones.setBounds(95, 51, 238, 29);
		getContentPane().add(btnCompeticiones);
		
		btnEventos = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Evento"));
		btnEventos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaSugerencia = new SugerirEventoGUI();
				cerrarDispersora();
				ventanaSugerencia.setVisible(true);
			}
		});
		btnEventos.setBounds(95, 91, 238, 29);
		getContentPane().add(btnEventos);
		
		btnPreguntas = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Pregunta"));
		btnPreguntas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaSugerencia = new SugerirPreguntaGUI();
				cerrarDispersora();
				ventanaSugerencia.setVisible(true);
			}
		});
		btnPreguntas.setBounds(95, 131, 238, 29);
		getContentPane().add(btnPreguntas);
		
		btnVolver = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Volver"));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IniciarSesionGUI.dispUser().volver();
			}
		});
		btnVolver.setBounds(335, 227, 89, 23);
		getContentPane().add(btnVolver);
		
		btnOtros = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Otros"));
		btnOtros.setBounds(95, 171, 238, 29);
		getContentPane().add(btnOtros);
		btnOtros.setEnabled(false);
	}

	public void cerrarDispersora() {
		this.setVisible(false);
	}
	
	public void volver() {
		this.setVisible(true);
		ventanaSugerencia.setVisible(false);
	}
}