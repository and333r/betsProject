package gui;

import java.awt.*;
import java.util.*;

import javax.swing.*;

import businessLogic.*;
import configuration.UtilDate;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class RegistrarGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	////
	private boolean regAdmin= false; //true si se está registrando un administrador.

	public boolean getRegAdmin() {
		return this.regAdmin;
	}

	public void setRegAdmin(boolean regAdmin) {
		this.regAdmin= regAdmin;
	}
	////

	private JLabel lblIntroduceCampos;

	private JPanel panelTextos;

	private JLabel lblNombre;
	private JLabel lblApellido1;
	private JLabel lblApellido2;
	private JLabel lblDni;
	private JLabel lblNombreUsuario;
	private JLabel lblContrasena1;
	private JLabel lblSexo;
	private JLabel lblEmail;
	private JLabel lblFechaNacmiento;
	//
	private JTextField textFName;
	private JTextField textFApellido1;
	private JTextField textFApellido2;
	private JTextField textFDni;
	private JTextField textFEmail;
	private JTextField textFNombreUsuario;
	private JLabel lblNUCogido;


	private JButton btnRegistrar;
	private JPasswordField passwordField;
	private JComboBox<String> comboBoxSexo;
	private JComboBox<String> comboBoxDia;
	private JComboBox<String> comboBoxMes;

	public RegistrarGUI ()
	{
		try
		{
			jbInicializar();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	char sexo = 'p';
	int dia = 0, mes = 0, ano = 0;

	private JTextField textFAno;
	private JLabel lblDia;
	private JLabel lblMes;
	private JLabel lblAno;
	private JLabel lblTelefono;
	private JTextField textFTelefono;
	private JLabel lblSeleccionaGenero;
	private JLabel lblSeleccionFecha;
	private JLabel lblErrorCampos;
	private JButton btnVolver;

	private void jbInicializar() throws Exception {

		int tipo = 0;

		this.getContentPane().setLayout(null);
		setBounds(600, 250,700, 500);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(1);

			}});

		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("RegistrarUsuario"));

		lblIntroduceCampos = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("RellenaCampos")); 
		lblIntroduceCampos.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblIntroduceCampos.setBounds(26, 21, 294, 36);
		getContentPane().add(lblIntroduceCampos);

		panelTextos = new JPanel();
		panelTextos.setBounds(26, 78, 626, 334);
		getContentPane().add(panelTextos);
		panelTextos.setLayout(null);

		//////////////////////////////
		lblNombre = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Nombre"));
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setBounds(10, 10, 116, 13);
		panelTextos.add(lblNombre);

		lblApellido1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Apellido1"));
		lblApellido1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblApellido1.setBounds(10, 41, 116, 13);
		panelTextos.add(lblApellido1);

		lblApellido2 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Apellido2"));
		lblApellido2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblApellido2.setBounds(10, 70, 116, 13);
		panelTextos.add(lblApellido2);

		lblDni = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Dni"));
		lblDni.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDni.setBounds(272, 41, 137, 13);
		panelTextos.add(lblDni);

		lblNombreUsuario = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("NombreUsuario"));
		lblNombreUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombreUsuario.setBounds(10, 128, 116, 13);
		panelTextos.add(lblNombreUsuario);

		lblContrasena1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Contrasena"));
		lblContrasena1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblContrasena1.setBounds(10, 157, 116, 13);
		panelTextos.add(lblContrasena1);

		lblSexo = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Sexo"));
		lblSexo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSexo.setBounds(272, 70, 137, 13);
		panelTextos.add(lblSexo);

		lblEmail = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Email"));
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setBounds(10, 99, 116, 13);
		panelTextos.add(lblEmail);

		lblFechaNacmiento = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("FechaNacimiento"));
		lblFechaNacmiento.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFechaNacmiento.setBounds(10, 275, 116, 13);
		panelTextos.add(lblFechaNacmiento);

		//////////////////////

		textFName = new JTextField();
		textFName.setBounds(136, 7, 148, 19);
		panelTextos.add(textFName);
		textFName.setText((String) null);
		textFName.setColumns(10);

		textFApellido1 = new JTextField();
		textFApellido1.setBounds(136, 38, 148, 19);
		panelTextos.add(textFApellido1);
		textFApellido1.setText((String) null);
		textFApellido1.setColumns(10);

		textFApellido2 = new JTextField();
		textFApellido2.setBounds(136, 67, 148, 19);
		panelTextos.add(textFApellido2);
		textFApellido2.setText((String) null);
		textFApellido2.setColumns(10);

		textFDni = new JTextField();
		textFDni.setText((String) null);
		textFDni.setColumns(10);
		textFDni.setBounds(419, 38, 137, 19);
		panelTextos.add(textFDni);

		textFEmail = new JTextField();
		textFEmail.setText((String) null);
		textFEmail.setColumns(10);
		textFEmail.setBounds(136, 96, 215, 19);
		panelTextos.add(textFEmail);

		textFNombreUsuario = new JTextField();
		textFNombreUsuario.setText((String) null);
		textFNombreUsuario.setColumns(10);
		textFNombreUsuario.setBounds(136, 125, 148, 19);
		panelTextos.add(textFNombreUsuario);


		lblNUCogido = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("NombreUsuarioCogido"));
		lblNUCogido.setVisible(false);
		lblNUCogido.setBackground(Color.BLACK);
		lblNUCogido.setForeground(Color.RED);
		lblNUCogido.setBounds(294, 128, 241, 13);
		panelTextos.add(lblNUCogido);

		passwordField = new JPasswordField();
		passwordField.setText(ResourceBundle.getBundle("Etiquetas").getString("Vacio")); //$NON-NLS-1$ //$NON-NLS-2$
		passwordField.setBounds(136, 154, 148, 19);
		panelTextos.add(passwordField);

		comboBoxSexo = new JComboBox<String>();

		Vector<String> meses = inicializarMeses(0);

		Vector<String> dias = inicializarDias(0);

		String[] sexos = new String[4];

		sexos[0] = ResourceBundle.getBundle("Etiquetas").getString("SeleccionaOpcion");
		sexos[1] = ResourceBundle.getBundle("Etiquetas").getString("Masculino");
		sexos[2] = ResourceBundle.getBundle("Etiquetas").getString("Femenino");
		sexos[3] = ResourceBundle.getBundle("Etiquetas").getString("Otro");

		comboBoxSexo.setModel(new DefaultComboBoxModel<String>(sexos));
		comboBoxSexo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				switch (comboBoxSexo.getSelectedIndex()) {
				case (1):
					sexo = 'M';
				break;
				case (2):
					sexo = 'F';
				break;
				case (3):
					sexo = 'O';
				break;
				case(0): 
				default:
					sexo = 'p';
					break;
				}
			}
		});
		comboBoxSexo.setBounds(419, 66, 137, 21);
		panelTextos.add(comboBoxSexo);

		comboBoxDia = new JComboBox<String>();
		comboBoxDia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dia = comboBoxDia.getSelectedIndex();

				if (dia != 0) {

					int modo = 0;

					if (dia > 29) 
						modo = 1;

					if (dia == 31) 
						modo = 2;

					DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<String>(inicializarMeses(modo));

					if (mes == 0 && ! modelo.equals(comboBoxMes.getModel()))
						comboBoxMes.setModel(modelo);
				}
			}
		});

		comboBoxDia.setModel(new DefaultComboBoxModel<String>(dias));
		comboBoxDia.setBounds(136, 271, 168, 21);
		panelTextos.add(comboBoxDia);

		comboBoxMes = new JComboBox<String>();
		comboBoxMes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String mesecillo = (String) comboBoxMes.getSelectedItem();

				int modo = 0;

				for (int i = 0; i < meses.size(); i++)
					if (meses.get(i).equals(mesecillo))
						mes = i;

				if (mes != 0) {

					if (mes == 2) 
						modo = 2;

					if (mes == 4 || mes == 6 || mes == 9 || mes == 11) 
						modo = 1;

					DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<String>(inicializarDias(modo)); 

					if (dia == 0 && ! comboBoxDia.getModel().equals(modelo))
						comboBoxDia.setModel(modelo);
				}
			}
		});

		comboBoxMes.setModel(new DefaultComboBoxModel<String>(meses));

		comboBoxMes.setBounds(314, 271, 161, 21);
		panelTextos.add(comboBoxMes);

		textFAno = new JTextField();
		textFAno.setBounds(495, 272, 71, 19);
		panelTextos.add(textFAno);
		textFAno.setColumns(10);

		lblDia = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SDia")); 
		lblDia.setBounds(136, 256, 116, 13);
		panelTextos.add(lblDia);

		lblMes = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SMes")); 
		lblMes.setBounds(314, 256, 119, 13);
		panelTextos.add(lblMes);

		lblAno = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SAno")); 
		lblAno.setBounds(495, 256, 105, 13);
		panelTextos.add(lblAno);

		lblTelefono = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Telefono"));
		lblTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelefono.setBounds(272, 10, 137, 13);
		panelTextos.add(lblTelefono);

		textFTelefono = new JTextField();
		textFTelefono.setText((String) null);
		textFTelefono.setColumns(10);
		textFTelefono.setBounds(419, 7, 137, 19);
		panelTextos.add(textFTelefono);

		lblSeleccionaGenero = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("FaltaSexo")); 
		lblSeleccionaGenero.setBackground(Color.GRAY);
		lblSeleccionaGenero.setForeground(Color.RED);
		lblSeleccionaGenero.setBounds(361, 99, 205, 13);
		panelTextos.add(lblSeleccionaGenero);

		lblSeleccionFecha = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("FaltaFecha")); 
		lblSeleccionFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeleccionFecha.setBackground(Color.GRAY);
		lblSeleccionFecha.setForeground(Color.RED);
		lblSeleccionFecha.setBounds(136, 311, 339, 13);
		panelTextos.add(lblSeleccionFecha);

		lblErrorCampos = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Vacio")); 
		lblErrorCampos.setForeground(Color.RED);
		lblErrorCampos.setBackground(Color.GRAY);
		lblErrorCampos.setBounds(136, 183, 480, 63);
		panelTextos.add(lblErrorCampos);

		//////////////

		btnRegistrar = new JButton(ResourceBundle.getBundle("Etiquetas").getString("RegistrarUsuario")); //$NON-NLS-1$ //$NON-NLS-2$
		btnRegistrar.setBounds(234, 422, 170, 31);
		getContentPane().add(btnRegistrar);
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					jbtnRegistrarActionPerformed(e);
				}
				catch (Exception t) {

					lblErrorCampos.setVisible(true);
					lblErrorCampos.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorCampos"));
				}
			}
		});

		btnVolver = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Volver"));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarGUI();
			}
		});
		btnVolver.setBounds(585, 430, 89, 23);
		getContentPane().add(btnVolver);


		this.lblSeleccionaGenero.setVisible(false);
		this.lblSeleccionFecha.setVisible(false);
	}

	private void jbtnRegistrarActionPerformed (ActionEvent e) {

		BLFacadeImplementation facadeIm = (BLFacadeImplementation) IniciarSesionGUI.getBusinessLogic();

		lblErrorCampos.setVisible(false);
		String NombreUsuario = this.textFNombreUsuario.getText();

		if (facadeIm.actorExistente(NombreUsuario)) 
			this.lblNUCogido.setVisible(true);
		else {

			try {
				ano = Integer.parseInt(this.textFAno.getText());

				if (dia == 29 && ((ano % 4) != 0)) {

					this.lblErrorCampos.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorRegistroUsuario2"));
					this.lblErrorCampos.setVisible(true);
				} else 

					if (mes == 0 || dia == 0) {

						this.lblSeleccionFecha.setVisible(true);
					}else {

						if (sexo == 'p') 

							this.lblSeleccionaGenero.setVisible(true);
						else {

							this.lblSeleccionaGenero.setVisible(false);
							this.lblSeleccionFecha.setVisible(false);
							this.lblNUCogido.setVisible(false);

							Date fechaNacimiento = UtilDate.newDate(ano, mes, dia);
							String contrasena = String.copyValueOf(this.passwordField.getPassword());
							//comboBox = (Offer) offerBox.getSelectedItem();

							int resultado = facadeIm.registrarUsuario(this.textFNombreUsuario.getText(), this.textFDni.getText(), this.textFName.getText(), this.textFApellido1.getText(), this.textFApellido2.getText(), fechaNacimiento, contrasena, sexo, this.textFEmail.getText(), this.textFTelefono.getText(), this.regAdmin);

							switch (resultado) {
							case 0 :
								this.regAdmin= false;
								cerrarGUI();
								break;
							case 1:
								this.lblErrorCampos.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorRegistroUsuario1"));
								this.lblErrorCampos.setVisible(true);
								break;
							case 2: 
								this.lblErrorCampos.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorRegistroUsuario2"));
								this.lblErrorCampos.setVisible(true);
								break;
							case 3: 
								this.lblErrorCampos.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorRegistroUsuario3"));
								this.lblErrorCampos.setVisible(true);
								break;
							default:
								this.lblErrorCampos.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorRegistroUsuario-1"));
								this.lblErrorCampos.setVisible(true);
								break;
							}

						}
					}
				
			} catch (NumberFormatException excepcion) {

				this.lblErrorCampos.setVisible(true);
				this.lblErrorCampos.setText(ResourceBundle.getBundle("Etiquetas").getString("AnoValido"));
			}
		}
	}

	/**
	 * Inicializa el vector de String para los meses segun el codigo introducido (0 para mes cualquiera, 1 mes de 30 o 31 dias, 
	 * 2 mes de 31 dias);
	 * @param tipo
	 * @return
	 */
	private Vector<String> inicializarMeses (int tipo) {

		Vector<String> meses = new Vector<String>();

		meses.add(ResourceBundle.getBundle("Etiquetas").getString("SeleccionaOpcion"));
		meses.add(ResourceBundle.getBundle("Etiquetas").getString("Enero"));
		if (tipo == 0)
			meses.add(ResourceBundle.getBundle("Etiquetas").getString("Febrero"));
		meses.add(ResourceBundle.getBundle("Etiquetas").getString("Marzo"));
		if (tipo != 2)
			meses.add(ResourceBundle.getBundle("Etiquetas").getString("Abril"));
		meses.add(ResourceBundle.getBundle("Etiquetas").getString("Mayo"));
		if (tipo != 2)
			meses.add(ResourceBundle.getBundle("Etiquetas").getString("Junio"));
		meses.add(ResourceBundle.getBundle("Etiquetas").getString("Julio"));
		meses.add(ResourceBundle.getBundle("Etiquetas").getString("Agosto"));
		if (tipo != 2)
			meses.add(ResourceBundle.getBundle("Etiquetas").getString("Septiembre"));
		meses.add(ResourceBundle.getBundle("Etiquetas").getString("Octubre"));
		if (tipo != 2)
			meses.add(ResourceBundle.getBundle("Etiquetas").getString("Noviembre"));
		meses.add(ResourceBundle.getBundle("Etiquetas").getString("Diciembre"));

		return meses;
	}

	/**
	 * Inicializa el vector de String para los dias segun el codigo introducido (0 para mes cualquiera, 1 para meses de 30 dias maximo y 
	 * 2 para febrero);
	 * @param modo
	 * @return
	 */
	protected Vector<String> inicializarDias (int modo) {

		Vector<String> dias = new Vector<String>();

		int i = 0;
		if (modo == 0) 
			i = 31;

		if (modo == 1)
			i = 30;

		if (modo == 2)
			i = 29;

		dias.add(ResourceBundle.getBundle("Etiquetas").getString("SeleccionaOpcion"));

		for (int k = 1; k <= i; k++)
			dias.add(Integer.toString(k));

		return dias;
	}

	private void cerrarGUI() {
		this.setVisible(false);
		ApplicationLauncher.inicioSesion().setVisible(true);
	}
}
