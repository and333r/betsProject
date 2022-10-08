package gui;

import javax.swing.JFrame;
import javax.swing.JTextField;

import businessLogic.BLFacadeImplementation;
import domain.Usuario;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class UsarPromocionGUI extends JFrame{
	private JTextField textFieldCode;
	private int aplicada=-1;
	
	public UsarPromocionGUI() {
		
		BLFacadeImplementation facade = (BLFacadeImplementation) IniciarSesionGUI.getBusinessLogic();

		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("UseProm"));
		setBounds(600, 250,465, 327);
		getContentPane().setLayout(null);
		
		getContentPane().setLayout(null);
		
		textFieldCode = new JTextField();
		textFieldCode.setBounds(94, 100, 241, 43);
		getContentPane().add(textFieldCode);
		textFieldCode.setColumns(10);
		JLabel lblNewLabelError = new JLabel("Error");
		lblNewLabelError.setForeground(Color.RED);
		lblNewLabelError.setBackground(Color.LIGHT_GRAY);
		lblNewLabelError.setVisible(false);
		JLabel lblNewLabelCode = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("InsertCode"));
		lblNewLabelCode.setBounds(94, 75, 241, 14);
		getContentPane().add(lblNewLabelCode);
		
		
		JButton btnNewButtonConfirmar = new JButton(ResourceBundle.getBundle("Etiquetas").getString("UseProm"));
		btnNewButtonConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aplicada= facade.aplicarPromocion(textFieldCode.getText(), (Usuario) IniciarSesionGUI.actor());
				if (aplicada==1) {
					lblNewLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("AlreadyUsedCode"));
					lblNewLabelError.setVisible(true);
				}
				else if (aplicada==2) {
					lblNewLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("NoMoreUses"));
					lblNewLabelError.setVisible(true);
				}
				else if (aplicada==0) {
					lblNewLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("Success"));
					lblNewLabelError.setVisible(true);
				}
				else {
					lblNewLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("Prom"));
					lblNewLabelError.setVisible(true);
				}
				
				
			}
		});
		btnNewButtonConfirmar.setBounds(94, 179, 241, 23);
		getContentPane().add(btnNewButtonConfirmar);
		
		
		lblNewLabelError.setBounds(20, 246, 315, 31);
		getContentPane().add(lblNewLabelError);
		
		JButton btnAtras = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Volver"));
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IniciarSesionGUI.dispUser().volver();
			}
		});
		btnAtras.setBounds(346, 246, 95, 31);
		getContentPane().add(btnAtras);
		
	}

}