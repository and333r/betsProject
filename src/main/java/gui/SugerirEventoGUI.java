package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacadeImplementation;
import domain.*;
import exceptions.EventoNoExistenteException;

import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;

public class SugerirEventoGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField txtFieldNombreEvento;
	
	private Vector<Competicion> competiciones;
	private JComboBox<String> cmbBoxCompeticiones;
	
	private JLabel lblSelectComp;
	private JLabel lblNombreEvent;
	private JLabel lblFechaEvent;
	
	private int dia = 0, mes = 0, ano = 0;
	Competicion competicion;
	
	BLFacadeImplementation facade = (BLFacadeImplementation) IniciarSesionGUI.getBusinessLogic();
	
	private JComboBox<String> comboBoxDia0;
	private JComboBox<String> comboBoxMes0;
	private JLabel lblDia0;
	private JLabel lblMes0;
	private JLabel lblAno0;
	private JTextField textFAno0;
	private JLabel lblErrores;
	
	private JButton btnAnadirEvento;
	private JButton btnCerrarVentana;
	private JLabel lblTitulo;
	private JLabel lblDescripcion;
	private JTextField textFieldDescripcion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SugerirEventoGUI frame = new SugerirEventoGUI();
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
	public SugerirEventoGUI() {
		
		setTitle("Crear evento");
		setBounds(600, 250,450, 360);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(1);

			}});
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblSelectComp = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SeleccionaCompeticion"));
		lblSelectComp.setBounds(10, 55, 179, 27);
		contentPane.add(lblSelectComp);
		
		lblNombreEvent = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("IntroducirEvento"));
		lblNombreEvent.setBounds(10, 93, 179, 27);
		contentPane.add(lblNombreEvent);
		
		lblFechaEvent = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("FechaEvento"));
		lblFechaEvent.setBounds(10, 131, 179, 27);
		contentPane.add(lblFechaEvent);
		
		
		//Competiciones:
		cmbBoxCompeticiones = new JComboBox<String>();
		
		competiciones = facade.obtenerCompeticiones();

		Vector<String> strings = new Vector<String>();
		strings.add(ResourceBundle.getBundle("Etiquetas").getString("SeleccionaOpcion"));
		for (Competicion c: competiciones) strings.add(c.getNombre());
		
		cmbBoxCompeticiones.setModel(new DefaultComboBoxModel<String>(strings));
		
		cmbBoxCompeticiones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!cmbBoxCompeticiones.getSelectedItem().equals(ResourceBundle.getBundle("Etiquetas").getString("SeleccionaOpcion"))) {
					competicion = (Competicion) competiciones.get(cmbBoxCompeticiones.getSelectedIndex() - 1);
				}
			}
		});
		
		cmbBoxCompeticiones.setBounds(199, 55, 225, 27);
		contentPane.add(cmbBoxCompeticiones);
		///////////
		
		//Nombre del evento:
		txtFieldNombreEvento = new JTextField();
		txtFieldNombreEvento.setText(ResourceBundle.getBundle("Etiquetas").getString("Vacio"));
		txtFieldNombreEvento.setBounds(199, 93, 225, 27);
		contentPane.add(txtFieldNombreEvento);
		txtFieldNombreEvento.setColumns(10);
		//////////
		
		//Fecha del evento:
		comboBoxDia0 = new JComboBox<String>();
		comboBoxDia0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dia = comboBoxDia0.getSelectedIndex() + 1;
			}
		});

		String[] dias = new String[31];
		dias[0] = "1";
		dias[1] = "2";
		dias[2] = "3";
		dias[3] = "4";
		dias[4] = "5";
		dias[5] = "6";
		dias[6] = "7";
		dias[7] = "8";
		dias[8] = "9";
		dias[9] = "10";
		dias[10] = "11";
		dias[11] = "12";
		dias[12] = "13";
		dias[13] = "14";
		dias[14] = "15";
		dias[15] = "16";
		dias[16] = "17";
		dias[17] = "18";
		dias[18] = "19";
		dias[19] = "20";
		dias[20] = "21";
		dias[21] = "22";
		dias[22] = "23";
		dias[23] = "24";
		dias[24] = "25";
		dias[25] = "26";
		dias[26] = "27";
		dias[27] = "28";
		dias[28] = "29";
		dias[29] = "30";
		dias[30] = "31";

		comboBoxDia0.setModel(new DefaultComboBoxModel<String>(dias));
		comboBoxDia0.setBounds(55, 169, 104, 21);
		contentPane.add(comboBoxDia0);

		comboBoxMes0 = new JComboBox<String>();
		comboBoxMes0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				mes = comboBoxMes0.getSelectedIndex() + 1;
			}
		});
		String[] meses = new String[12];
		meses[0] = ResourceBundle.getBundle("Etiquetas").getString("Enero");
		meses[1] = ResourceBundle.getBundle("Etiquetas").getString("Febrero");
		meses[2] = ResourceBundle.getBundle("Etiquetas").getString("Marzo");
		meses[3] = ResourceBundle.getBundle("Etiquetas").getString("Abril");
		meses[4] = ResourceBundle.getBundle("Etiquetas").getString("Mayo");
		meses[5] = ResourceBundle.getBundle("Etiquetas").getString("Junio");
		meses[6] = ResourceBundle.getBundle("Etiquetas").getString("Julio");
		meses[7] = ResourceBundle.getBundle("Etiquetas").getString("Agosto");
		meses[8] = ResourceBundle.getBundle("Etiquetas").getString("Septiembre");
		meses[9] = ResourceBundle.getBundle("Etiquetas").getString("Octubre");
		meses[10] = ResourceBundle.getBundle("Etiquetas").getString("Noviembre");
		meses[11] = ResourceBundle.getBundle("Etiquetas").getString("Diciembre");

		comboBoxMes0.setModel(new DefaultComboBoxModel<String>(meses));

		comboBoxMes0.setBounds(199, 169, 104, 21);
		contentPane.add(comboBoxMes0);

		textFAno0 = new JTextField();
		textFAno0.setText(ResourceBundle.getBundle("Etiquetas").getString("Vacio")); //$NON-NLS-1$ //$NON-NLS-2$
		textFAno0.setBounds(320, 169, 104, 21);
		contentPane.add(textFAno0);
		textFAno0.setColumns(10);
		
		lblDia0 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SDia")); //$NON-NLS-1$ //$NON-NLS-2$
		lblDia0.setBounds(55, 152, 89, 14);
		contentPane.add(lblDia0);
		
		lblMes0 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SMes")); //$NON-NLS-1$ //$NON-NLS-2$
		lblMes0.setBounds(199, 152, 89, 14);
		contentPane.add(lblMes0);
		
		lblAno0 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SAno")); //$NON-NLS-1$ //$NON-NLS-2$
		lblAno0.setBounds(320, 152, 104, 14);
		contentPane.add(lblAno0);
		
		///////////////////
		
		lblErrores = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Vacio")); //$NON-NLS-1$ //$NON-NLS-2$
		lblErrores.setVisible(false);
		lblErrores.setBackground(Color.GRAY);
		lblErrores.setForeground(Color.RED);
		lblErrores.setHorizontalAlignment(SwingConstants.CENTER);
		lblErrores.setBounds(10, 157, 381, 27);
		contentPane.add(lblErrores);
		
		////
		
		btnAnadirEvento = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Sugerir")+" "+ResourceBundle.getBundle("Etiquetas").getString("Evento")); 
		btnAnadirEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean camposInCompletos= (cmbBoxCompeticiones.getSelectedItem()==null || txtFieldNombreEvento.getText().equals("") ||
						comboBoxDia0.getSelectedItem()==null || comboBoxMes0.getSelectedItem()==null ||
						textFAno0.getText().equals("")); //determina si todo esta rellenado.
				if(camposInCompletos) {
					lblErrores.setVisible(true);
					lblErrores.setText(ResourceBundle.getBundle("Etiquetas").getString("RellenaTCampos")); //muestra error de campos vacios
				}else {
					ano= Integer.parseInt(textFAno0.getText());
					String nombreEvento= txtFieldNombreEvento.getText();
					String descripcion=textFieldDescripcion.getText();
					Date fechaEvento= new Date(ano, mes, dia);
						int c= facade.sugerirEvento(nombreEvento, fechaEvento, competicion, descripcion, IniciarSesionGUI.actor());
						switch(c) {
						case 0:
							cerrarGUI();
							break;
						case 1:
							lblErrores.setVisible(true);
							lblErrores.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorFechaExpirada"));
							break;
						case 2:
							lblErrores.setVisible(true);
							lblErrores.setText(ResourceBundle.getBundle("Etiquetas").getString("EventoYaExiste"));
							break;
						}
				}
			}
		});
		btnAnadirEvento.setBounds(125, 266, 200, 44);
		contentPane.add(btnAnadirEvento);
		
		btnCerrarVentana = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Volver")); 
		btnCerrarVentana.setBounds(335, 287, 89, 23);
		contentPane.add(btnCerrarVentana);
		
		lblTitulo = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("TituloSugerencias")); 
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTitulo.setBounds(55, 11, 334, 33);
		contentPane.add(lblTitulo);
		
		lblDescripcion = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Descripcion") + ResourceBundle.getBundle("Etiquetas").getString("Evento")); 
		lblDescripcion.setBounds(10, 195, 200, 27);
		contentPane.add(lblDescripcion);
		
		textFieldDescripcion = new JTextField();
		textFieldDescripcion.setBounds(10, 228, 414, 27);
		contentPane.add(textFieldDescripcion);
		textFieldDescripcion.setColumns(10);
		btnCerrarVentana.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarGUI();
			}
		});
		
		
	}
	
	public void cerrarGUI() {
		this.setVisible(false);
		IniciarSesionGUI.dispUser().setVisible(true);
	}
}