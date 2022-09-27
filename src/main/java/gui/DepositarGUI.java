package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import businessLogic.BLFacadeImplementation;

public class DepositarGUI extends JFrame{
	private static final long serialVersionUID = 1L;

	private JLabel lblPreguntaDeposito;
	private JButton btnDepositar;
	private JButton btnVolver;
	private JTextField textField;
	BLFacadeImplementation facade = (BLFacadeImplementation) IniciarSesionGUI.getBusinessLogic();

	public DepositarGUI() {
		getContentPane().setLayout(null);
		setBounds(600, 250,450, 200);
		this.setTitle("Depositar");

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(1);

			}});

		lblPreguntaDeposito = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("PreguntaDeposito"));
		lblPreguntaDeposito.setHorizontalAlignment(SwingConstants.CENTER);
		lblPreguntaDeposito.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPreguntaDeposito.setBounds(82, 11, 280, 35);
		getContentPane().add(lblPreguntaDeposito);

		textField = new JTextField();
		textField.setBounds(133, 55, 176, 20);
		getContentPane().add(textField);
		textField.setColumns(10);

		btnDepositar = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Depositar"));
		btnDepositar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				facade.depositar(Double.parseDouble(textField.getText()));
				cerrarGUI();
			}
		});
		
		btnDepositar.setBounds(143, 86, 157, 20);
		getContentPane().add(btnDepositar);

		btnVolver = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Volver"));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cerrarGUI();
			}
		});
		btnVolver.setBounds(335, 125, 89, 23);
		getContentPane().add(btnVolver);
	}

	private void cerrarGUI() {
		IniciarSesionGUI.dispUser().actualizar();
		IniciarSesionGUI.dispUser().volver();
	}


}
