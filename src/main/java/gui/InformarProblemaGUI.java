package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacadeImplementation;
import domain.Usuario;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class InformarProblemaGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtTituloProblema;
	private JTextField txtDescripcionProblema;
	private JLabel lblTituloProblema;
	private JLabel lblDescripcionProblema;
	private JLabel lblErrores;
	private JButton btnInformarProblema;
	private JButton btnAtras;
	
	BLFacadeImplementation facade = (BLFacadeImplementation) IniciarSesionGUI.getBusinessLogic();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InformarProblemaGUI frame = new InformarProblemaGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InformarProblemaGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 560, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTituloProblema = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("TitularProblema"));
		lblTituloProblema.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTituloProblema.setBounds(10, 11, 201, 31);
		contentPane.add(lblTituloProblema);
		
		lblDescripcionProblema = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Descripcion"));
		lblDescripcionProblema.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDescripcionProblema.setBounds(10, 52, 201, 30);
		contentPane.add(lblDescripcionProblema);
		
		txtTituloProblema = new JTextField();
		txtTituloProblema.setBounds(234, 9, 302, 33);
		contentPane.add(txtTituloProblema);
		txtTituloProblema.setColumns(10);
		
		txtDescripcionProblema = new JTextField();
		txtDescripcionProblema.setBounds(234, 52, 302, 119);
		contentPane.add(txtDescripcionProblema);
		txtDescripcionProblema.setColumns(10);
		
		lblErrores = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Vacio"));
		lblErrores.setBounds(111, 196, 302, 44);
		contentPane.add(lblErrores);
		lblErrores.setForeground(Color.RED);
		
		btnInformarProblema = new JButton(ResourceBundle.getBundle("Etiquetas").getString("EnviarInforme"));
		btnInformarProblema.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblErrores.setText(ResourceBundle.getBundle("Etiquetas").getString("Vacio"));
				if(!txtTituloProblema.getText().equals("")) {
					facade.informarError(txtTituloProblema.getText(), txtDescripcionProblema.getText(), (Usuario) IniciarSesionGUI.actor());
					IniciarSesionGUI.dispUser().volver();
				}else lblErrores.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorTituloVacio"));
			}
		});
		btnInformarProblema.setBounds(170, 275, 201, 44);
		contentPane.add(btnInformarProblema);
		
		btnAtras = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Volver"));
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IniciarSesionGUI.dispUser().volver();
			}
		});
		btnAtras.setBounds(447, 296, 89, 23);
		contentPane.add(btnAtras);
	}
}
