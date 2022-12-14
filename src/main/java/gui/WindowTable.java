package gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.BorderLayout;
import domain.Usuario;
import userAdapter.UserAdapter;

public class WindowTable extends JFrame{ 
	
			
	
	private Usuario user;
	private JTable tabla=new JTable();
	
	public WindowTable(Usuario user){
		
		super("Apuestas realizadas por "+ user.getNombreUsuario()+":"); 
		this.setBounds(100, 100, 700, 200);
		this.user = user;
		UserAdapter adapt = new UserAdapter(user);
		tabla = new JTable(adapt); 
		tabla.setAutoCreateRowSorter(true);
		tabla.setPreferredScrollableViewportSize(new Dimension(500, 70)); //Creamos un JscrollPane y le agregamos la JTable
		JScrollPane scrollPane = new JScrollPane(tabla); //Agregamos el JScrollPane al contenedor getContentPane().add(scrollPane, BorderLayout.CENTER);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
	
	} 
	
	}
