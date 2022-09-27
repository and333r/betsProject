package gui;

/**
 * @author Software Engineering teachers
 */


import javax.swing.*;
import businessLogic.*;
import domain.Actor;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class IniciarSesionGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private static Actor actor;
	
	private static DispAdminGUI dispAdmin;
	private static DispUserGUI dispUser;
	
	private static BLFacade appFacadeInterface;
	
	private JFrame ventanaNueva;

	protected JLabel jLabelInicioSesion; // Cuadro que pone Log in/ Iniciar sesion //

	private JPanel jContentPane = null; // display de los demas elementos

	private JTextField textNombreUsuario;// donde se introduce el nombre de usuario
	private JPasswordField passwordField;// donde se introduce la contrasena

	//textos que aparecen en pantalla de inicio sesion
	
	private JLabel textPane; //texto de introducir nombre usuario
	private JLabel textPane_1; //texto de introducir contrasena
	private JLabel textPane_2; //texto de todavia no tienes una cuenta?
	private JLabel textProblemasInicioSesion; //texto de errores durante inicio sesion

	private JButton btnRegistrarUsuario; // boton de registrar usuario
	private JButton btnIniciarSesion; // boton de iniciar sesion

	
	private JPanel panel_2;//Panel de los botones de idioma
		
	private final ButtonGroup buttonGroup = new ButtonGroup();//Conjunto de botones de idioma

	// Botones Idioma
	private JRadioButton rdbtnRBCastellano;
	private JRadioButton rdbtnRBEnglish;
	private JRadioButton rdbtnRBEuskara;

	private JButton btnCOnsultar;

	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}

	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface = afi;
	}
	
	public static Actor actor() {
		return actor;
	}
	
	public static void eliminarActor() {
		actor = null;
	}
	
	public static void cambiarActor(Actor nuevoActor) {
		actor = nuevoActor;
	}
	
	public static DispUserGUI dispUser() {
		return dispUser;
	}
	
	public static DispAdminGUI dispAdmin() {
		return dispAdmin;
	}


	/**
	 * This is the default constructor
	 */
	public IniciarSesionGUI() {

		super();
		
		dispAdmin = new DispAdminGUI();
		dispUser = new DispUserGUI();

		addWindowListener(new WindowAdapter() { //TODO para Ander: por favor, copie este metodo a todas las otras interfaces. SAludos cordiales
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					//if (ConfigXML.getInstance().isBusinessLogicLocal()) facade.close();
				} catch (Exception e1) {
					System.out.println("Error: "+e1.toString()+" , probably problems with Business Logic or Database");
				}
				System.exit(1);
			}
		});

		initialize();
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}



	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		// this.setSize(271, 295);
		setBounds(600, 250,726, 400);
		this.setContentPane(getJContentPaneInicioSesion());
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}

	/**
	 * This method initializes jContentPane 
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPaneInicioSesion() {

		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getLblNewLabel());
			jContentPane.add(getPanel_2());
			jContentPane.add(getbtnIniciarSesion());
			jContentPane.add(getTextNombreUsuario());
			jContentPane.add(getPasswordField());
			jContentPane.add(getTextPane());
			jContentPane.add(getTextPane_2());
			jContentPane.add(getTextPane_1());
			jContentPane.add(getbtnRegistrarUsuario());
			jContentPane.add(getTextProblemasInicioSesion());
			jContentPane.add(getBtnCOnsultar());

		}
		return jContentPane;
	}		

	private JTextField getTextNombreUsuario() {

		if (textNombreUsuario == null){
			textNombreUsuario = new JTextField();
			textNombreUsuario.setText(ResourceBundle.getBundle("Etiquetas").getString("Vacio")); //$NON-NLS-1$ //$NON-NLS-2$
			textNombreUsuario.setBounds(372, 73, 175, 45);
			textNombreUsuario.setColumns(10);
		}
		return textNombreUsuario;
	}

	private JPasswordField getPasswordField() {

		if(passwordField == null){

			passwordField = new JPasswordField();
			passwordField.setText(ResourceBundle.getBundle("Etiquetas").getString("Vacio")); //$NON-NLS-1$ //$NON-NLS-2$
			passwordField.setBounds(372, 143, 175, 49);
		}
		return passwordField;
	}

	private JLabel getTextPane(){

		if(textPane == null){

			textPane = new JLabel();
			textPane.setFont(new Font("Tahoma", Font.PLAIN, 12));
			textPane.setText(ResourceBundle.getBundle("Etiquetas").getString("IntroducirNombreUsuario"));
			textPane.setBounds(98, 85, 203, 19);
		}
		return textPane;
	}

	private JLabel getTextPane_1(){

		if(textPane_1 == null){

			textPane_1 = new JLabel();
			textPane_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
			textPane_1.setText(ResourceBundle.getBundle("Etiquetas").getString("IntroducirContrasena"));
			textPane_1.setBounds(99, 144, 238, 26);
		}
		return textPane_1;
	}

	private JLabel getTextPane_2(){

		if(textPane_2 == null){

			textPane_2 = new JLabel();
			textPane_2.setHorizontalAlignment(SwingConstants.RIGHT);
			textPane_2.setForeground(Color.BLUE);
			textPane_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
			textPane_2.setText(ResourceBundle.getBundle("Etiquetas").getString("TodaviaNoCuenta"));
			textPane_2.setBounds(143, 293, 238, 19);
		}
		return textPane_2;
	}

	private JButton getbtnRegistrarUsuario() {

		if (btnRegistrarUsuario == null) {

			btnRegistrarUsuario = new JButton(ResourceBundle.getBundle("Etiquetas").getString("BotonRegistrarUsuario"));
			btnRegistrarUsuario.setFont(new Font("Tahoma", Font.BOLD, 11));
			btnRegistrarUsuario.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					JFrame a = new RegistrarGUI();
					a.setVisible(true);
					ApplicationLauncher.inicioSesion().setVisible(false);
				}
			});
			btnRegistrarUsuario.setBounds(384, 291, 163, 21);
		}
		return btnRegistrarUsuario;
	}

	private JLabel getTextProblemasInicioSesion(){

		if(textProblemasInicioSesion == null){

			textProblemasInicioSesion = new JLabel();
			textProblemasInicioSesion.setFont(new Font("Consolas", Font.PLAIN, 14));
			textProblemasInicioSesion.setEnabled(true);
			textProblemasInicioSesion.setVisible(true);
			textProblemasInicioSesion.setBackground(Color.LIGHT_GRAY);
			textProblemasInicioSesion.setForeground(Color.RED);
			textProblemasInicioSesion.setText(ResourceBundle.getBundle("Etiquetas").getString("Vacio"));
			textProblemasInicioSesion.setBounds(115, 202, 509, 31);
		}
		return textProblemasInicioSesion;
	}
	
	private JButton getbtnIniciarSesion() {

		if (btnIniciarSesion == null) {

			btnIniciarSesion = new JButton(ResourceBundle.getBundle("Etiquetas").getString("IniciarSesion")); 
			btnIniciarSesion.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {//action performer de intento de inicio de sesion

					BLFacadeImplementation facadeIm = (BLFacadeImplementation) IniciarSesionGUI.getBusinessLogic();

					String nombreUsuario = textNombreUsuario.getText();
					String contrasena = new String(passwordField.getPassword());
					
					int comprobacion = facadeIm.comprobarContrasena(nombreUsuario, contrasena);
				
					System.out.println("Intento de inicio de sesion con usuario: " + nombreUsuario);
					
					if (comprobacion == 0) {
						
						textProblemasInicioSesion.setVisible(true);
						textProblemasInicioSesion.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorInicioSesion1"));
					}

					if (comprobacion==2) {
						
						textProblemasInicioSesion.setVisible(false);
						System.out.println(ResourceBundle.getBundle("Etiquetas").getString("SesionIniciadaAdmin") + nombreUsuario + ".");
						actor = facadeIm.obtenerActor(nombreUsuario);
						IniciarSesionGUI.dispAdmin().setVisible(true);
						ApplicationLauncher.inicioSesion().setVisible(false);//Ponemos en no visible la ventana de IniciarSesion
					}

					if (comprobacion==1) {

						textProblemasInicioSesion.setVisible(false);
						System.out.println(ResourceBundle.getBundle("Etiquetas").getString("SesionIniciadaAdmin") + nombreUsuario + ".");
						actor = facadeIm.obtenerActor(nombreUsuario);
						IniciarSesionGUI.dispUser().actualizar();
						IniciarSesionGUI.dispUser().setVisible(true);
						ApplicationLauncher.inicioSesion().setVisible(false);//Ponemos en no visible la ventana de IniciarSesion
					}

					if (comprobacion==3) {

						textProblemasInicioSesion.setVisible(true);
						textProblemasInicioSesion.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorInicioSesion2"));
						
					}

				}
			});
			
			btnIniciarSesion.setFont(new Font("Tahoma", Font.BOLD, 12));
			btnIniciarSesion.setText(ResourceBundle.getBundle("Etiquetas").getString("IniciarSesion"));
			btnIniciarSesion.setBounds(372, 244, 175, 38);

		}
		return btnIniciarSesion;
	}

	private JLabel getLblNewLabel() {

		if (jLabelInicioSesion == null) {

			jLabelInicioSesion = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("IniciarSesion"));
			jLabelInicioSesion.setBounds(115, 0, 481, 49);
			jLabelInicioSesion.setFont(new Font("Tahoma", Font.BOLD, 13));
			jLabelInicioSesion.setForeground(Color.BLACK);
			jLabelInicioSesion.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return jLabelInicioSesion;
	}

	private JPanel getPanel_2() {

		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setBounds(143, 322, 481, 31);
			panel_2.add(getRdbtnRBCastellano());
			panel_2.add(getRdbtnRBEnglish());
			panel_2.add(getRdbtnRBEuskara());
		}
		return panel_2;
	}
	private JRadioButton getRdbtnRBCastellano() {

		if (rdbtnRBCastellano == null) {
			rdbtnRBCastellano = new JRadioButton("Castellano");
			rdbtnRBCastellano.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("es"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();				}
			});
			buttonGroup.add(rdbtnRBCastellano);
		}

		return rdbtnRBCastellano;
	}

	private JRadioButton getRdbtnRBEnglish() {

		if (rdbtnRBEnglish == null) {
			rdbtnRBEnglish = new JRadioButton("English");
			rdbtnRBEnglish.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("en"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();				}
			});
			buttonGroup.add(rdbtnRBEnglish);
		}
		return rdbtnRBEnglish;
	}

	private JRadioButton getRdbtnRBEuskara() {

		if (rdbtnRBEuskara == null) {
			rdbtnRBEuskara = new JRadioButton("Euskara");
			rdbtnRBEuskara.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("eus"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();				}
			});
			buttonGroup.add(rdbtnRBEuskara);
		}
		return rdbtnRBEuskara;
	}

	/**
	 * Vuelve a mostrar el JFrame de IniciarSesionGUI para que se pueda iniciar sesion con otros usuarios. Deja todos los
	 * campos y seleccionables limpios.
	 * 
	 * @author Vidal del Rincon
	 */
	protected void remostrar() {
		
		//Locale.setDefault(getLocale());
		this.redibujar();
		this.textNombreUsuario.setText(ResourceBundle.getBundle("Etiquetas").getString("Vacio"));
		this.passwordField.setText(ResourceBundle.getBundle("Etiquetas").getString("Vacio"));
		this.buttonGroup.clearSelection();
		this.setVisible(true);
	}

	/**
	 * Metodo que cambia los textos de los items del JFrame a los del lenguaje actual de la interfaz, ya sea el seleccionado
	 * o el predeterminado. Solo reasigna los necesarios, deja como estaban tanto el campo de Nombre de Usuario como el de 
	 * Contrasena.
	 * 
	 * @author Vidal del Rincon
	 */
	private void redibujar() {

		jLabelInicioSesion.setText(ResourceBundle.getBundle("Etiquetas").getString("IniciarSesion"));
		textPane.setText(ResourceBundle.getBundle("Etiquetas").getString("IntroducirNombreUsuario"));
		textPane_1.setText(ResourceBundle.getBundle("Etiquetas").getString("IntroducirContrasena"));
		textPane_2.setText(ResourceBundle.getBundle("Etiquetas").getString("TodaviaNoCuenta"));
		textProblemasInicioSesion.setText(ResourceBundle.getBundle("Etiquetas").getString("Vacio"));
		btnRegistrarUsuario.setText(ResourceBundle.getBundle("Etiquetas").getString("BotonRegistrarUsuario"));
		btnIniciarSesion.setText(ResourceBundle.getBundle("Etiquetas").getString("IniciarSesion"));
		btnCOnsultar.setText(ResourceBundle.getBundle("Etiquetas").getString("ConsultarElementos"));

		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}
	private JButton getBtnCOnsultar() {
		if (btnCOnsultar == null) {
			btnCOnsultar = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ConsultarElementos")); 
			btnCOnsultar.setBackground(Color.ORANGE);
			btnCOnsultar.setFont(new Font("Tahoma", Font.PLAIN, 9));
			btnCOnsultar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					ventanaNueva = new ConsultarGUI(0);
					ventanaNueva.setVisible(true);
				}
			});
			btnCOnsultar.setBounds(98, 245, 180, 38);
		}
		return btnCOnsultar;
	}
	
	public void volver() {
		
		this.setVisible(true);
		this.ventanaNueva.setVisible(false);
		this.ventanaNueva = null;
	}
} 

