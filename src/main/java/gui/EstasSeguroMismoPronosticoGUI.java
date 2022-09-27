package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.SwingConstants;

import businessLogic.BLFacadeImplementation;

public class EstasSeguroMismoPronosticoGUI extends JFrame{	
	private static final long serialVersionUID = 1L;

	private JLabel lblNewLabel;
	private JButton btnSi;
	private JButton btnNo;
	
	BLFacadeImplementation facade = (BLFacadeImplementation) IniciarSesionGUI.getBusinessLogic();

	public EstasSeguroMismoPronosticoGUI() {
		getContentPane().setLayout(null);
		this.setBounds(600, 250,561, 180);
		
		

		lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Seguro"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 525, 22);
		getContentPane().add(lblNewLabel);

		btnSi = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Si"));
		btnSi.setBounds(154, 65, 118, 36);
		getContentPane().add(btnSi);

		btnNo = new JButton(ResourceBundle.getBundle("Etiquetas").getString("No"));
		btnNo.setBounds(302, 65, 118, 36);
		getContentPane().add(btnNo);
		
		btnSi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				facade.anadirApuesta(ApostarGUI.id,ApostarGUI.cantidad,ApostarGUI.cuota);
				cerrarGUI();
			}
		});
		
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarGUI();
			}
		});
		
		
	}
	
	public void cerrarGUI() {
			this.setVisible(false);
	}
}
