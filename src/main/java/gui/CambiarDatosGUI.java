package gui;

import javax.swing.*;

import businessLogic.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.ResourceBundle;
import java.awt.*;

public class CambiarDatosGUI extends JFrame {

	private BLFacadeImplementation facadeIm = (BLFacadeImplementation) IniciarSesionGUI.getBusinessLogic();

	private final ButtonGroup buttonGroup = new ButtonGroup();

	private JRadioButton rdbtnEmail;
	private JRadioButton rdbtnPassword;

	private JPanel botonesSeleccion;

	private boolean esContrasena = false; 
	private boolean esEmail= false; 

	private JLabel lblSelecciona;
	private JLabel lblIntroduceNuevo;
	private JLabel lblIntroduceContrasena;
	private JLabel lblErrores;
	
	private JTextField textFieldVariable;
	private JTextField textFieldContraseña;
	private JButton btnNewButton;
	

	public CambiarDatosGUI() {

		this.setBounds(600, 250, 440, 473);
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
		this.setBounds(100, 100, 623, 473);

		getContentPane().setLayout(null);

		botonesSeleccion = new JPanel();
		botonesSeleccion.setBounds(174, 62, 257, 36);
		getContentPane().add(botonesSeleccion);
		botonesSeleccion.setLayout(null);



		rdbtnPassword = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("Contrasena"));
		rdbtnPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				esContrasena = true;
				esEmail = false;

				lblIntroduceNuevo.setText(ResourceBundle.getBundle("Etiquetas").getString("IntroduceNuevo") + " " + ResourceBundle.getBundle("Etiquetas").getString("Contrasena"));
				lblIntroduceNuevo.setVisible(true);

				lblIntroduceContrasena.setText(ResourceBundle.getBundle("Etiquetas").getString("ConfirmaContra"));
				lblIntroduceContrasena.setVisible(true);
				
				textFieldContraseña.setText((String) null);
				textFieldVariable.setText((String) null);
				lblErrores.setVisible(false);
			}
		});
		rdbtnPassword.setBounds(23, 6, 82, 21);
		buttonGroup.add(rdbtnPassword);
		botonesSeleccion.add(rdbtnPassword);

		rdbtnEmail = new JRadioButton("Email:");
		rdbtnEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				esContrasena = false;
				esEmail = true;

				lblIntroduceNuevo.setText(ResourceBundle.getBundle("Etiquetas").getString("IntroduceNuevo") + " " + ResourceBundle.getBundle("Etiquetas").getString("Email"));
				lblIntroduceNuevo.setVisible(true);

				lblIntroduceContrasena.setText(ResourceBundle.getBundle("Etiquetas").getString("ConfirmaContra"));
				lblIntroduceContrasena.setVisible(true);
				
				textFieldContraseña.setText((String) null);
				textFieldVariable.setText((String) null);
				lblErrores.setVisible(false);
			}
		});
		rdbtnEmail.setBounds(151, 6, 75, 21);
		buttonGroup.add(rdbtnEmail);
		botonesSeleccion.add(rdbtnEmail);

		lblSelecciona = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SeleccionaCambio")); 
		lblSelecciona.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelecciona.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSelecciona.setBounds(102, 25, 402, 23);
		getContentPane().add(lblSelecciona);

		lblIntroduceNuevo = new JLabel();
		lblIntroduceNuevo.setVisible(false);
		lblIntroduceNuevo.setHorizontalAlignment(SwingConstants.CENTER);
		lblIntroduceNuevo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblIntroduceNuevo.setBounds(102, 122, 402, 23);
		getContentPane().add(lblIntroduceNuevo);

		lblIntroduceContrasena = new JLabel();
		lblIntroduceContrasena.setVisible(false);
		lblIntroduceContrasena.setHorizontalAlignment(SwingConstants.CENTER);
		lblIntroduceContrasena.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblIntroduceContrasena.setBounds(102, 227, 402, 23);
		getContentPane().add(lblIntroduceContrasena);

		textFieldVariable = new JTextField();
		textFieldVariable.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldVariable.setText((String) null); 
		textFieldVariable.setBounds(175, 164, 256, 36);
		getContentPane().add(textFieldVariable);
		textFieldVariable.setColumns(10);

		textFieldContraseña = new JTextField();
		textFieldContraseña.setText((String) null);
		textFieldContraseña.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldContraseña.setColumns(10);
		textFieldContraseña.setBounds(174, 266, 256, 36);
		getContentPane().add(textFieldContraseña);

		btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Cambiar")); 
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ApplicationLauncher.inicioSesion();
				int resultado = facadeIm.comprobarContrasena(IniciarSesionGUI.actor().getNombreUsuario(), textFieldContraseña.getText());
				if (resultado == 1) {
					if (esContrasena)
						if (facadeIm.cambiarContrasena(IniciarSesionGUI.actor().getNombreUsuario(), textFieldVariable.getText())) {

							lblErrores.setVisible(true);
							lblErrores.setText(ResourceBundle.getBundle("Etiquetas").getString("ContrasenaCambiada"));
							lblErrores.setForeground(Color.GREEN);
						} else {

							lblErrores.setVisible(true);
							lblErrores.setText(ResourceBundle.getBundle("Etiquetas").getString("ContrasenaNoCambiada"));
							lblErrores.setForeground(Color.RED);
						}
					if (esEmail)
						if (facadeIm.cambiarCorreoE(IniciarSesionGUI.actor().getNombreUsuario(), textFieldVariable.getText())) {
							
							lblErrores.setVisible(true);
							lblErrores.setText(ResourceBundle.getBundle("Etiquetas").getString("CorreoCambiado"));
							lblErrores.setForeground(Color.GREEN);
						} else {
							
							lblErrores.setVisible(true);
							lblErrores.setText(ResourceBundle.getBundle("Etiquetas").getString("CorreoNoCambiada"));
							lblErrores.setForeground(Color.RED);
						}
				} else {
					if (resultado == 3) {
						lblErrores.setVisible(true);
						lblErrores.setText(ResourceBundle.getBundle("Etiquetas").getString("ContrasenaErronea"));
						lblErrores.setForeground(Color.RED);
					}
				}
			}
		});
		btnNewButton.setBounds(232, 377, 131, 36);
		getContentPane().add(btnNewButton);

		lblErrores = new JLabel();
		lblErrores.setBackground(Color.LIGHT_GRAY);
		lblErrores.setVisible(false);

		lblErrores.setHorizontalAlignment(SwingConstants.CENTER);
		lblErrores.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblErrores.setBounds(102, 323, 402, 44);
		getContentPane().add(lblErrores);


	}
}
