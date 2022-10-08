package gui;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import businessLogic.BLFacadeImplementation;
import domain.Administrador;
import domain.Competicion;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class AnadirPromocionGUI extends JFrame {
	private JTextField textFieldCant;
	private JTextField textFieldNum;
	private JTextField textFieldCode;
	
	
	private String code;
	private Administrador admin;
	private int cant=0;
	private int num_veces=0;
	private boolean tipo=false;
	private Competicion comp;
	
	
	
	public AnadirPromocionGUI() {
		
		
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("AddProm"));
		setBounds(600, 250,600, 340);
		getContentPane().setLayout(null);
		
		   //TODO Etiquetas
			//TODO desde la iteracion 1. TODOS LOS ELEMENTOS DE JFRAME QUE SEAN DISPLAYEABLES TIENEN QUE SER ATRIBUTOS DE LA CLASE, ni caso
		
		JLabel lblSiONo;
		JLabel lblNewLabelCode = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("PromCode"));
		JLabel lblNewLabelComp = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SeleccionaCompeticion"));
		JLabel lblNewLabelErrores = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("FatalError"));
		lblNewLabelErrores.setForeground(Color.RED);
		lblNewLabelErrores.setBackground(Color.LIGHT_GRAY);
		JLabel lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Prctg"));
		JLabel lblNewLabelNum = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Uses"));
		JLabel lblNewLabelCant = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Cant"));
		
		JComboBox<String> comboBoxSiONo = new JComboBox<String>();
		JComboBox<String> comboBoxComp = new JComboBox<String>();
		JComboBox<String> comboBox = new JComboBox<String>();
		
		textFieldCode = new JTextField();
		textFieldNum = new JTextField();
		textFieldCant = new JTextField();
	  
		JButton btnNewButtonAnadir = new JButton(ResourceBundle.getBundle("Etiquetas").getString("AddProm"));
		BLFacadeImplementation facade = (BLFacadeImplementation) IniciarSesionGUI.getBusinessLogic();
		
		
		lblNewLabelErrores.setVisible(false);
		lblNewLabelComp.setVisible(false);
		comboBoxComp.setVisible(false);
		lblNewLabelCant.setVisible(false);
		textFieldCant.setVisible(false);
		textFieldCode.setVisible(false);
		textFieldNum.setVisible(false);
		comboBox.setVisible(false);
		lblNewLabelNum.setVisible(false);
		lblNewLabelErrores.setVisible(false);
		lblNewLabelComp.setVisible(false);
		lblNewLabelCode.setVisible(false);		
		lblNewLabel.setVisible(false);
		
		getContentPane().setLayout(null);
		
		
		
		
		
	    Vector<String> strings = new Vector<String>();
		strings.add(ResourceBundle.getBundle("Etiquetas").getString("SeleccionaOpcion"));
		strings.add("Si"); 
		strings.add("No");
		comboBoxSiONo.setModel(new DefaultComboBoxModel<String>(strings));
		comboBoxSiONo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!comboBoxSiONo.getSelectedItem().equals(ResourceBundle.getBundle("Etiquetas").getString("SeleccionaOpcion"))){
					if(comboBoxSiONo.getSelectedItem().equals("Si")) {
						lblNewLabelComp.setVisible(true);
						comboBoxComp.setVisible(true);
						textFieldCant.setVisible(false);
						lblNewLabelCant.setVisible(false);
						textFieldCode.setVisible(false);
						textFieldNum.setVisible(false);
						comboBox.setVisible(false);
						lblNewLabelNum.setVisible(false);
						lblNewLabelErrores.setVisible(false);
						lblNewLabelCode.setVisible(false);
						lblNewLabel.setVisible(false);
						
					}
					else {
						comp=null;
						lblNewLabelComp.setVisible(false);
						comboBoxComp.setVisible(false);
						textFieldCant.setVisible(true);
						textFieldCode.setVisible(true);
						textFieldNum.setVisible(true);
						comboBox.setVisible(true);
						lblNewLabelNum.setVisible(true);
						lblNewLabelErrores.setVisible(false);
						lblNewLabelComp.setVisible(false);
						lblNewLabelCode.setVisible(true);	
						lblNewLabel.setVisible(true);
						lblNewLabelCant.setVisible(true);

						
						
						
					}
					
				}
				
				
			}
		});
		
		comboBoxSiONo.setBounds(10, 45, 304, 22);
		getContentPane().add(comboBoxSiONo);
		
/////////////////////////////////////////////////////////////////////////////////////////////////////
		 Vector<String> string = new Vector<String>();
			string.add(ResourceBundle.getBundle("Etiquetas").getString("SeleccionaOpcion"));
			string.add("Si"); 
			string.add("No");
			comboBox.setModel(new DefaultComboBoxModel<String>(string));
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!comboBox.getSelectedItem().equals(ResourceBundle.getBundle("Etiquetas").getString("SeleccionaOpcion"))){
					if(comboBox.getSelectedItem().equals("Si")) {
						tipo=true;
					}
					else {
						tipo=false;
					}
				}
			}
		});
		comboBox.setBounds(210, 121, 122, 22);
		getContentPane().add(comboBox);
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		btnNewButtonAnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

					try {
						code= textFieldCode.getText();
						num_veces=Integer.parseInt(textFieldNum.getText());
						cant=Integer.parseInt(textFieldCant.getText()); 
						if(comp!=null) {
							
							    facade.anadirPromocionConComp(code, (Administrador) IniciarSesionGUI.actor(), cant, num_veces, tipo, comp);
								lblNewLabelErrores.setText(ResourceBundle.getBundle("Etiquetas").getString("Success"));
								lblNewLabelErrores.setVisible(true);
							
						}
						else {
							
							facade.anadirPromocion(code, (Administrador) IniciarSesionGUI.actor(), cant, num_veces, tipo);
							lblNewLabelErrores.setText(ResourceBundle.getBundle("Etiquetas").getString("Success"));
							lblNewLabelErrores.setVisible(true);
						}
						
					}
					catch(Exception a) {
						lblNewLabelErrores.setText(ResourceBundle.getBundle("Etiquetas").getString("Valid"));
						lblNewLabelErrores.setVisible(true);
					}
										
				}
		
		});
		btnNewButtonAnadir.setBounds(426, 189, 138, 44);
		getContentPane().add(btnNewButtonAnadir);

       
/////////////////////////////////////////////////////////////////////////////////////////////////////
		JButton btnAtras = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Volver"));
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IniciarSesionGUI.dispAdmin().volver();
			}
		});
		btnAtras.setBounds(426, 243, 138, 23);
		getContentPane().add(btnAtras);
		
		
		Vector<Competicion>competi= facade.obtenerCompeticiones();
		Vector<String> nombre = new Vector<String>();
		nombre.add(ResourceBundle.getBundle("Etiquetas").getString("SeleccionaOpcion"));
		for (Competicion c:competi) {
			nombre.add(c.getNombre());
		}
		comboBoxComp.setModel(new DefaultComboBoxModel<String>(nombre));
		
		comboBoxComp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!comboBoxComp.getSelectedItem().equals(ResourceBundle.getBundle("Etiquetas").getString("SeleccionaOpcion"))) {
					comp= facade.obtenerCompeticionPorNombre(comboBoxComp.getSelectedItem().toString());
				}
				textFieldCant.setVisible(true);
				textFieldCode.setVisible(true);
				textFieldNum.setVisible(true);
				comboBox.setVisible(true);
				lblNewLabelNum.setVisible(true);
				lblNewLabelErrores.setVisible(false);
				lblNewLabelComp.setVisible(false);
				lblNewLabelCode.setVisible(true);	
				lblNewLabel.setVisible(true);
				lblNewLabelCant.setVisible(true);
			}
		});
		comboBoxComp.setBounds(335, 45, 229, 22);
		getContentPane().add(comboBoxComp);
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		
		lblSiONo = new JLabel("¿Esta promocion pertenece a una competicion?."); 
		lblSiONo.setBounds(10, 20, 304, 14);
		getContentPane().add(lblSiONo);
		
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////

	    
		
		
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////

	
		lblNewLabelComp.setBounds(335, 21, 229, 14);
		getContentPane().add(lblNewLabelComp);
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////

		
		
		textFieldCant.setBounds(36, 117, 138, 31);
		getContentPane().add(textFieldCant);
		textFieldCant.setColumns(10);
		
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////

		
		lblNewLabelCant.setBounds(36, 92, 138, 14);
		getContentPane().add(lblNewLabelCant);
		
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////

		
		textFieldNum.setBounds(360, 117, 204, 31);
		getContentPane().add(textFieldNum);
		textFieldNum.setColumns(10);
		
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////

		
		lblNewLabelNum.setBounds(360, 92, 204, 14);
		getContentPane().add(lblNewLabelNum);

		
		/////////////////////////////////////////////////////////////////////////////////////////////////////

		
		lblNewLabelErrores.setBounds(10, 243, 406, 34);
		getContentPane().add(lblNewLabelErrores);
		
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////

		
		textFieldCode.setBounds(35, 203, 241, 20);
		getContentPane().add(textFieldCode);
		textFieldCode.setColumns(10);
	
	
		/////////////////////////////////////////////////////////////////////////////////////////////////////

		
		lblNewLabelCode.setBounds(35, 179, 241, 14);
		getContentPane().add(lblNewLabelCode);
		
		////////////////////////////////////////////////////
		
		
		
		///////////////////////////////////////////////////
		
		lblNewLabel.setBounds(210, 92, 122, 14);
		getContentPane().add(lblNewLabel);
	}
}
