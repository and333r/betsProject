package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacadeImplementation;
import domain.*;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JList;

public class ConsultarObjetosAdministradoresGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;

	BLFacadeImplementation facadeIm = (BLFacadeImplementation) IniciarSesionGUI.getBusinessLogic();
	
	private JPanel contentPane;
	private JTextField txtFNombreAdmin;
	private JLabel lblIntroduceNombreAdmin;
	private JComboBox<String> cmbBoxTipoCreacion;
	private JLabel lblError;
	private JList<String> listCreaciones;
	
	private DefaultListModel<String> model= new DefaultListModel<String>();
	Vector<String> resultados= new Vector<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultarObjetosAdministradoresGUI frame = new ConsultarObjetosAdministradoresGUI();
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
	public ConsultarObjetosAdministradoresGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 463, 379);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(1);

			}});
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblIntroduceNombreAdmin = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("NombreAdmin"));
		lblIntroduceNombreAdmin.setBounds(10, 11, 163, 14);
		contentPane.add(lblIntroduceNombreAdmin);
		
		txtFNombreAdmin = new JTextField();
		txtFNombreAdmin.setBounds(183, 8, 241, 20);
		contentPane.add(txtFNombreAdmin);
		txtFNombreAdmin.setColumns(10);
		
		cmbBoxTipoCreacion = new JComboBox<String>();
		cmbBoxTipoCreacion.setBounds(183, 39, 241, 20);
		contentPane.add(cmbBoxTipoCreacion);
		
		String[] tipos= new String[4];
		tipos[0]=ResourceBundle.getBundle("Etiquetas").getString("Competiciones");
		tipos[1]=ResourceBundle.getBundle("Etiquetas").getString("Eventos");
		tipos[2]=ResourceBundle.getBundle("Etiquetas").getString("Preguntas");
		tipos[3]=ResourceBundle.getBundle("Etiquetas").getString("Pronosticos");
		
		cmbBoxTipoCreacion.setModel(new DefaultComboBoxModel<String>(tipos));
		
		lblError = new JLabel(""); //$NON-NLS-1$ //$NON-NLS-2$
		lblError.setBounds(85, 122, 241, 14);
		lblError.setBackground(Color.GRAY);
		lblError.setForeground(Color.RED);
		lblError.setVisible(false);
		contentPane.add(lblError);
		
		listCreaciones = new JList<String>();
		
//		model= new DefaultListModel<String>();
		model.addElement("");
		listCreaciones.setModel(model);
		listCreaciones.setBounds(36, 135, 339, 91);
		
		JScrollPane scLista= new JScrollPane(listCreaciones);
		scLista.setBounds(0, 135, 434, 115);
		contentPane.add(scLista);
		
		
		JButton btnConfirmarSeleccion = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ConfirmarSeleccion")); //$NON-NLS-1$ //$NON-NLS-2$
		btnConfirmarSeleccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				lblError.setVisible(false);
				
				resultados= new Vector<String>();

				model= new DefaultListModel<String>();
				
				String nAdmin= txtFNombreAdmin.getText();
				String tipoCreacion= (String) cmbBoxTipoCreacion.getSelectedItem();
				int indexCreacion= cmbBoxTipoCreacion.getSelectedIndex();
				
				if(nAdmin.equals(new String()) || tipoCreacion==null) {
					lblError.setText(ResourceBundle.getBundle("Etiquetas").getString("RellenaTCampos"));
					lblError.setVisible(true);
				}else {
					if(!facadeIm.actorExistente(nAdmin)) {
						lblError.setText(ResourceBundle.getBundle("Etiquetas").getString("AdminNoExiste"));
						lblError.setVisible(true);
					}else {
						switch (indexCreacion){
						case 0:
							Vector<Competicion> competiciones= facadeIm.obtenerCompeticionesAdmin(nAdmin);
							for(Competicion comp: competiciones) {
								model.addElement(comp.getNombre()+"\n");
							}
							break;
						case 1:
							Vector<Evento> eventos= facadeIm.obtenerEventosAdmin(nAdmin);
							for(Evento ev: eventos) {
								model.addElement(ev.getComp().getNombre()+": "+ev.getNombre()+"\n");
							}
							break;
						case 2:
							Vector<Pregunta> preguntas= facadeIm.obtenerPreguntasAdmin(nAdmin);
							for(Pregunta pre: preguntas) {
								model.addElement(pre.getEvento().getComp().getNombre()+": "+pre.getEvento().getNombre()+": "+pre.getEnunciado()+"\n");
							}
							break;
						case 3:
							Vector<Pronostico> pronosticos= facadeIm.obtenerPronosticosAdmin(nAdmin);
							for(Pronostico pro: pronosticos) {
								model.addElement(pro.getPregunta().getEvento().getComp().getNombre()+": "+pro.getPregunta().getEvento().getNombre()+": "+pro.getPregunta().getEnunciado()+": "+pro.getRespuesta()+"\n");
							}
							break;
						}

						listCreaciones.setModel(model);
						
					}
				}
			}
		});
		btnConfirmarSeleccion.setBounds(113, 79, 163, 32);
		contentPane.add(btnConfirmarSeleccion);
		
		JButton btnVolver = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Volver"));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IniciarSesionGUI.dispAdmin().volver();
			}
		});
		btnVolver.setBounds(349, 307, 89, 23);
		getContentPane().add(btnVolver);
	}
		
	
}
