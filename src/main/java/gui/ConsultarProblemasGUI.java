package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.event.ListSelectionListener;

import businessLogic.BLFacadeImplementation;
import domain.InformeError;

import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;

public class ConsultarProblemasGUI extends JFrame {

	private JPanel contentPane;
	private JList listInformes;
	private JLabel lblDescripcion;
	private JButton btnResolverProblema;
	private JButton btnVolver;
	private DefaultListModel<String> model= new DefaultListModel<String>();
	private Vector<InformeError> informes;
	private JRadioButton rdbtnNoResueltos;
	private JRadioButton rdbtnResueltos;
	
	BLFacadeImplementation facade = (BLFacadeImplementation) IniciarSesionGUI.getBusinessLogic();
	private JLabel lblErrores;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblInformadoCuando;
	private JLabel lblResueltoCuando;
	private JLabel lblFechaInforme;
	private JLabel lblFechaResolucion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultarProblemasGUI frame = new ConsultarProblemasGUI();
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
	public ConsultarProblemasGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/*informes= facade.obtenerInformes(false);
		for(InformeError ie: informes) {
			model.addElement(ie.getTitulo()+" De:"+ie.getUsuario().getNombreUsuario());
		}*/
		
		listInformes = new JList();
		listInformes.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(!listInformes.isSelectionEmpty()) {
					lblFechaInforme.setText(informes.get(listInformes.getSelectedIndex()).getFechaInforme().toString());
					if(informes.get(listInformes.getSelectedIndex()).getFechaResuelto()!=null) lblFechaResolucion.setText(informes.get(listInformes.getSelectedIndex()).getFechaResuelto().toString());
					lblDescripcion.setText(informes.get(listInformes.getSelectedIndex()).getDescripcion());
				}
			}
		});
		listInformes.setBounds(10, 11, 414, 96);
		//contentPane.add(listInformes);
		listInformes.setModel(model);
		
		JScrollPane scLista= new JScrollPane(listInformes);
		scLista.setBounds(10, 11, 414, 96);
		contentPane.add(scLista);
		
		lblDescripcion = new JLabel("New label");
		lblDescripcion.setBounds(10, 166, 414, 34);
		contentPane.add(lblDescripcion);
		lblDescripcion.setText(ResourceBundle.getBundle("Etiquetas").getString("Vacio"));
		
		btnResolverProblema = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MarcarResuelto"));
		btnResolverProblema.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblDescripcion.setText(ResourceBundle.getBundle("Etiquetas").getString("Vacio"));
				lblFechaInforme.setText(ResourceBundle.getBundle("Etiquetas").getString("Vacio"));
				lblFechaResolucion.setText(ResourceBundle.getBundle("Etiquetas").getString("Vacio"));
				lblErrores.setText(ResourceBundle.getBundle("Etiquetas").getString("Vacio"));
				if(!listInformes.isSelectionEmpty()) {
					facade.resolverProblema(informes.get(listInformes.getSelectedIndex()));
					informes= new Vector<InformeError>();
					informes= facade.obtenerInformes(false);
					model= new DefaultListModel<String>();
					for(InformeError ie: informes) {
						model.addElement(ie.getTitulo()+" De:"+ie.getUsuario().getNombreUsuario());
					}
					listInformes.setModel(model);
				}else lblErrores.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorInformeNoSeleccionado"));
			}
		});
		btnResolverProblema.setBounds(10, 280, 211, 23);
		contentPane.add(btnResolverProblema);
		
		btnVolver = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Volver"));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IniciarSesionGUI.dispAdmin().volver();
			}
		});
		btnVolver.setBounds(335, 280, 89, 23);
		contentPane.add(btnVolver);
		
		lblErrores = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Vacio"));
		lblErrores.setBounds(10, 210, 414, 14);
		contentPane.add(lblErrores);
		lblErrores.setForeground(Color.RED);
		
		rdbtnNoResueltos = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("NoResueltos"));
		rdbtnNoResueltos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				informes= new Vector<InformeError>();
				informes= facade.obtenerInformes(false);
				model= new DefaultListModel<String>();
				for(InformeError ie: informes) {
					model.addElement(ie.getTitulo()+" De:"+ie.getUsuario().getNombreUsuario());
				}
				listInformes.setModel(model);
				btnResolverProblema.setEnabled(true);
			}
		});
		buttonGroup.add(rdbtnNoResueltos);
		rdbtnNoResueltos.setBounds(165, 227, 109, 23);
		contentPane.add(rdbtnNoResueltos);
		
		rdbtnResueltos = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("Resueltos"));
		rdbtnResueltos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				informes= new Vector<InformeError>();
				informes= facade.obtenerInformes(true);
				model= new DefaultListModel<String>();
				for(InformeError ie: informes) {
					model.addElement(ie.getTitulo()+" De:"+ie.getUsuario().getNombreUsuario());
				}
				listInformes.setModel(model);
				btnResolverProblema.setEnabled(false);
			}
		});
		buttonGroup.add(rdbtnResueltos);
		rdbtnResueltos.setBounds(165, 252, 109, 23);
		contentPane.add(rdbtnResueltos);
		
		buttonGroup.add(rdbtnResueltos);
		buttonGroup.add(rdbtnNoResueltos);
		
		lblInformadoCuando = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("InformadoEl"));
		lblInformadoCuando.setBounds(10, 117, 129, 14);
		contentPane.add(lblInformadoCuando);
		
		lblResueltoCuando = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ResueltoEl"));
		lblResueltoCuando.setBounds(10, 144, 129, 12);
		contentPane.add(lblResueltoCuando);
		
		lblFechaInforme = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Vacio"));
		lblFechaInforme.setBounds(153, 120, 271, 14);
		contentPane.add(lblFechaInforme);
		
		lblFechaResolucion = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Vacio"));
		lblFechaResolucion.setBounds(149, 144, 275, 12);
		contentPane.add(lblFechaResolucion);
		
		
	}
}
