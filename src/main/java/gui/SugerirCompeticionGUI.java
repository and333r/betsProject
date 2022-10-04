package gui;

import java.awt.event.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import businessLogic.*;
import domain.Usuario;

public class SugerirCompeticionGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	
	private JLabel lblIntroduceDatos;
	private JLabel lblNombreCompeticion;
	private JLabel lblTemporada;
	private JLabel lblDescripcion;		
	private JLabel lblGenero;
	private JLabel lblDeporte;
	private JLabel lblErrores;
	private JButton btnCrearCompeticion;
	private JButton btnVolver;
	
	private JTextField textFDescripcion;
	private JTextField textFNombre;
	private JTextField textFDeporte;
	
	private JComboBox<String> comboBoxSexo;
	private JComboBox<String> comboBoxTemporada;
	
	Vector<String> sexos = new Vector<String>();
	Vector<String> temporadas = new Vector<String>();
	
	BLFacadeImplementation facade = (BLFacadeImplementation) IniciarSesionGUI.getBusinessLogic();
	
	public SugerirCompeticionGUI() {
		
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("Sugerir") + " " + ResourceBundle.getBundle("Etiquetas").getString("Competicion"));
		setBounds(600, 250,550, 385);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(1);

			}});
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblIntroduceDatos = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("TituloSugerencias"));
		lblIntroduceDatos.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIntroduceDatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblIntroduceDatos.setBounds(116, 10, 310, 41);
		contentPane.add(lblIntroduceDatos);
		
		lblNombreCompeticion = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("NombreCompeticion"));
		lblNombreCompeticion.setBounds(37, 61, 249, 27);
		contentPane.add(lblNombreCompeticion);
		
		lblTemporada = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("TemporadaCompeticion"));
		lblTemporada.setBounds(37, 176, 249, 27);
		contentPane.add(lblTemporada);
		
		lblDescripcion = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Descripcion") + " " + ResourceBundle.getBundle("Etiquetas").getString("Competicion"));
		lblDescripcion.setBounds(37, 213, 286, 27);
		contentPane.add(lblDescripcion);
	
		lblDeporte = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("DeporteCompeticion"));
		lblDeporte.setBounds(37, 102, 249, 27);
		contentPane.add(lblDeporte);
		
		lblGenero = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("GeneroCompeticion"));
		lblGenero.setBounds(37, 139, 249, 27);
		contentPane.add(lblGenero);
		
		comboBoxSexo = new JComboBox<String>();
		
		sexos.add(ResourceBundle.getBundle("Etiquetas").getString("SeleccionaOpcion"));
		sexos.add(ResourceBundle.getBundle("Etiquetas").getString("Masculino"));
		sexos.add(ResourceBundle.getBundle("Etiquetas").getString("Femenino"));
		sexos.add(ResourceBundle.getBundle("Etiquetas").getString("Mixto"));
		
		comboBoxSexo.setModel(new DefaultComboBoxModel<String>(sexos));
		comboBoxSexo.setBounds(333, 140, 176, 24);
		contentPane.add(comboBoxSexo);

		comboBoxTemporada = new JComboBox<String>();
		
		temporadas.add(ResourceBundle.getBundle("Etiquetas").getString("SeleccionaOpcion"));
		temporadas.add("2021-2022");
		temporadas.add("2022-2023");
		temporadas.add("2023-2024");
		
		comboBoxTemporada.setModel(new DefaultComboBoxModel<String>(temporadas));
		comboBoxTemporada.setBounds(333, 177, 176, 24);
		contentPane.add(comboBoxTemporada);
		
		textFDescripcion = new JTextField();
		textFDescripcion.setText(ResourceBundle.getBundle("Etiquetas").getString("Vacio")); 
		textFDescripcion.setBounds(37, 250, 286, 72);
		contentPane.add(textFDescripcion);
		textFDescripcion.setColumns(10);
		
		textFNombre = new JTextField(ResourceBundle.getBundle("Etiquetas").getString("Vacio"));
		textFNombre.setColumns(10);
		textFNombre.setBounds(291, 61, 218, 27);
		contentPane.add(textFNombre);
		
		textFDeporte = new JTextField(ResourceBundle.getBundle("Etiquetas").getString("Vacio"));
		textFDeporte.setColumns(10);
		textFDeporte.setBounds(291, 102, 218, 27);
		contentPane.add(textFDeporte);
		
		btnCrearCompeticion = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Sugerir") + " " + ResourceBundle.getBundle("Etiquetas").getString("Competicion"));
		btnCrearCompeticion.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				pulsadoSugerirCompeticion();
				cerrarGUI();
			}
		});
		
		btnCrearCompeticion.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCrearCompeticion.setBounds(333, 250, 176, 27);
		contentPane.add(btnCrearCompeticion);
		
		lblErrores = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Vacio"));
		lblErrores.setVisible(false);
		lblErrores.setHorizontalAlignment(SwingConstants.CENTER);
		lblErrores.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblErrores.setBackground(Color.GRAY);
		lblErrores.setForeground(Color.RED);
		lblErrores.setBounds(333, 220, 176, 65);
		contentPane.add(lblErrores);
		
		btnVolver = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Volver"));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarGUI();
			}
		});
		
		btnVolver.setBounds(420, 312, 89, 23);
		contentPane.add(btnVolver);
	}
	
	private void pulsadoSugerirCompeticion() {
		
		if (this.textFNombre.getText().equals("") || this.textFDeporte.getText().equals("")) {
			
			this.lblErrores.setText("Por favor, rellene todos los campos necesarios.");//TODO para agus: cambiar errores a etiquetas, tmbn en crear competicion de vidal -agus
		}else
		
		if (this.comboBoxSexo.getSelectedIndex() * this.comboBoxTemporada.getSelectedIndex() == 0) {
			
			this.lblErrores.setText("Por favor, seleccione tanto el genero como la temporada.");//TODO para agus: cambiar errores a etiquetas, tmbn en crear competicion de vidal -agus
		}else
		{
			char sexo = 'N';
			switch (this.comboBoxSexo.getSelectedIndex()){
				
			case 1 :
				sexo = 'M';
				break;
			case 2 : 
				sexo = 'F';
				break;
			case 3 : 
				sexo = 'A';
				break;
			default:
				break;
			}
			
			String temporada = (String) this.comboBoxTemporada.getSelectedItem();
			
			facade.sugerirCompeticion(this.textFNombre.getText(), this.textFDeporte.getText(), sexo, temporada, this.textFDescripcion.getText(), (Usuario) IniciarSesionGUI.actor());
		}
		
	}
	
	public void cerrarGUI() {
		this.setVisible(false);
		IniciarSesionGUI.dispUser().setVisible(true);
	}
}