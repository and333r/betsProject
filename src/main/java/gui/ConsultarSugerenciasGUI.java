package gui;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.SwingConstants;

import businessLogic.BLFacadeImplementation;
import domain.*;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class ConsultarSugerenciasGUI extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	private JList<String> listSugerencias;
	private JLabel lblTitulo;
	private JRadioButton rdbtnCompeticiones;
	private JRadioButton rdbtnEventos;
	private JRadioButton rdbtnPreguntas;
	private JLabel lblID;
	private JLabel lblTituloS;
	private JLabel lblUsuario;
	private JLabel lblDescripcion;
	private JLabel lblIDSug;
	private JLabel lblTituloSSug;
	private JLabel lblUsuarioSug;
	private JLabel lblDescripcionSug;
	private JButton btnVolver;
	private JButton btnAceptar;
	private JButton btnRechazar;
	
	private DefaultListModel<String> model= new DefaultListModel<String>();
	private Vector<Competicion> tempComp = new Vector<Competicion>();
	private Vector<Evento> tempEv = new Vector<Evento>();
	private Vector<Pregunta> tempPreg = new Vector<Pregunta>();
	BLFacadeImplementation facade = (BLFacadeImplementation) IniciarSesionGUI.getBusinessLogic();
	
	private Competicion c;
	private Evento ev;
	private Pregunta p;
	private JLabel lblFeedback;
	
	
	public ConsultarSugerenciasGUI() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(1);

			}});
		
		getContentPane().setLayout(null);

		setBounds(600, 250,450, 450);
		this.setTitle("Consultar Sugerencias");
		listSugerencias = new JList<String>();
		listSugerencias.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				lblID.setVisible(true);
				lblTituloS.setVisible(true);
				lblUsuario.setVisible(true);
				lblDescripcion.setVisible(true);
				lblIDSug.setVisible(true);
				lblTituloSSug.setVisible(true);
				lblUsuarioSug.setVisible(true);
				lblDescripcionSug.setVisible(true);
				if (!listSugerencias.isSelectionEmpty()) {
					if (rdbtnCompeticiones.isSelected()) {
						c = tempComp.get(listSugerencias.getSelectedIndex());

						lblIDSug.setText(c.getId());
						lblTituloSSug.setText(c.getNombre());
						lblUsuarioSug.setText(c.getNombreActor());
						lblDescripcionSug.setText(c.getDescripcion());
					} else if (rdbtnEventos.isSelected()) {
						ev = tempEv.get(listSugerencias.getSelectedIndex());

						lblIDSug.setText(ev.getId());
						lblTituloSSug.setText(ev.getNombre());
						lblUsuarioSug.setText(ev.getNombreActor());
						lblDescripcionSug.setText(ev.getDescripcion());
					} else if (rdbtnPreguntas.isSelected()) {
						p = tempPreg.get(listSugerencias.getSelectedIndex());

						lblIDSug.setText(p.getId());
						lblTituloSSug.setText(p.getEnunciado());
						lblUsuarioSug.setText(p.getNombreActor());
						lblDescripcion.setVisible(false);
						lblDescripcionSug.setVisible(false);
						lblDescripcionSug.setText(ResourceBundle.getBundle("Etiquetas").getString("Vacio"));
					} 
				}
				
			}
		});
		listSugerencias.setBounds(23, 53, 388, 72);
		getContentPane().add(listSugerencias);
		
		lblTitulo = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("TituloConsultarSugerencias"));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTitulo.setBounds(10, 11, 414, 27);
		getContentPane().add(lblTitulo);
		
		rdbtnCompeticiones = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("Competicion"));
		rdbtnCompeticiones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tempComp=null;
				model= new DefaultListModel<String>();
				tempComp=facade.obtenerSugerenciasCompeticiones();
				for(Competicion c:tempComp) {
					model.addElement(c.getId());
				}
				listSugerencias.setModel(model);
			}
		});
		buttonGroup.add(rdbtnCompeticiones);
		rdbtnCompeticiones.setBounds(23, 132, 109, 23);
		getContentPane().add(rdbtnCompeticiones);
		
		rdbtnEventos = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("Evento"));
		rdbtnEventos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tempEv=null;
				model= new DefaultListModel<String>();
				tempEv=facade.obtenerSugerenciasEventos();
				for(Evento ev:tempEv){
					model.addElement(ev.getId());
				}
				listSugerencias.setModel(model);
			}
		});
		buttonGroup.add(rdbtnEventos);
		rdbtnEventos.setBounds(166, 132, 109, 23);
		getContentPane().add(rdbtnEventos);
		
		rdbtnPreguntas = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("Pregunta"));
		rdbtnPreguntas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tempPreg=null;
				model= new DefaultListModel<String>();
				tempPreg=facade.obtenerSugerenciasPreguntas();
				for(Pregunta p:tempPreg) {
					model.addElement(p.getId());
				}
				listSugerencias.setModel(model);
			}
		});
		buttonGroup.add(rdbtnPreguntas);
		rdbtnPreguntas.setBounds(302, 132, 109, 23);
		getContentPane().add(rdbtnPreguntas);
		
		lblID = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("IDSug"));
		lblID.setBounds(10, 162, 89, 23);
		getContentPane().add(lblID);
		lblID.setVisible(false);
		
		lblTituloS = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("TituloDeSug"));
		lblTituloS.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTituloS.setBounds(10, 196, 89, 23);
		getContentPane().add(lblTituloS);
		lblTituloS.setVisible(false);
		
		lblUsuario = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("UsuarioSug"));
		lblUsuario.setBounds(10, 230, 89, 23);
		getContentPane().add(lblUsuario);
		lblUsuario.setVisible(false);
		
		lblDescripcion = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("DescSug"));
		lblDescripcion.setBounds(10, 264, 89, 23);
		getContentPane().add(lblDescripcion);
		lblDescripcion.setVisible(false);
		
		lblIDSug = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Vacio"));
		lblIDSug.setBounds(109, 162, 315, 23);
		getContentPane().add(lblIDSug);
		lblIDSug.setVisible(false);
		
		lblTituloSSug = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Vacio"));
		lblTituloSSug.setBounds(109, 200, 315, 23);
		getContentPane().add(lblTituloSSug);
		lblTituloSSug.setVisible(false);
		
		lblUsuarioSug = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Vacio"));
		lblUsuarioSug.setBounds(109, 234, 315, 23);
		getContentPane().add(lblUsuarioSug);
		lblUsuarioSug.setVisible(false);
		
		lblDescripcionSug = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Vacio"));
		lblDescripcionSug.setVerticalAlignment(SwingConstants.TOP);
		lblDescripcionSug.setBounds(109, 264, 315, 58);
		getContentPane().add(lblDescripcionSug);
		lblDescripcionSug.setVisible(false);
		
		btnVolver = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Volver"));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IniciarSesionGUI.dispAdmin().volver();
			}
		});
		btnVolver.setBounds(335, 380, 89, 23);
		getContentPane().add(btnVolver);
		
		btnAceptar = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Accept"));
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnCompeticiones.isSelected()) {
					facade.hacerCompDefinitiva(c);
					tempComp.remove(c);
					model= new DefaultListModel<String>();
					for(Competicion comp:tempComp) {
						model.addElement(comp.getId());
					}
					listSugerencias.setModel(model);
				}else if(rdbtnEventos.isSelected()) {
					facade.hacerEventoDfinitivo(ev);
					tempEv.remove(ev);
					model= new DefaultListModel<String>();
					for(Evento evento:tempEv) {
						model.addElement(evento.getId());
					}
					listSugerencias.setModel(model);
				}else if(rdbtnPreguntas.isSelected()) {
					facade.hacerPreguntaDefinitiva(p);
					tempPreg.remove(p);
					model= new DefaultListModel<String>();
					for(Pregunta preg:tempPreg) {
						model.addElement(preg.getId());
					}
					listSugerencias.setModel(model);
				}
				lblFeedback.setText("Se ha hecho definitivo/a");
			}
		});
		btnAceptar.setBounds(50, 333, 139, 27);
		getContentPane().add(btnAceptar);
		
		btnRechazar = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Denegar"));
		btnRechazar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnCompeticiones.isSelected()) {
					facade.borrarCompeticion(c);
					tempComp.remove(c);
					model= new DefaultListModel<String>();
					for(Competicion comp:tempComp) {
						model.addElement(comp.getId());
					}
					listSugerencias.setModel(model);
				}else if(rdbtnEventos.isSelected()) {
					facade.borrarEvento(ev);
					tempEv.remove(ev);
					model= new DefaultListModel<String>();
					for(Evento evento:tempEv) {
						model.addElement(evento.getId());
					}
					listSugerencias.setModel(model);
				}else if(rdbtnPreguntas.isSelected()) {
					facade.borrarPregunta(p);
					tempPreg.remove(p);
					model= new DefaultListModel<String>();
					for(Pregunta preg:tempPreg) {
						model.addElement(preg.getId());
					}
					listSugerencias.setModel(model);
				}
				lblFeedback.setText("Se ha eliminado correctamente");
			}
		});
		btnRechazar.setBounds(225, 333, 139, 27);
		getContentPane().add(btnRechazar);
		
		lblFeedback = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Vacio")); 
		lblFeedback.setBounds(10, 371, 265, 23);
		getContentPane().add(lblFeedback);
	}

	
}
