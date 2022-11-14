package userAdapter;

import java.util.Date;

import javax.persistence.OneToOne;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import configuration.UtilDate;
import domain.Pronostico;
import domain.Usuario;

public class UserAdapter extends AbstractTableModel {

	
	private Usuario user;
	
	private String [] atributos= {"Pronostico", "Pregunta", "Fecha", "CantidadApostada"};
	
	
	
			
	public UserAdapter(Usuario user) {
		this.user=user;
	}
	
	public int getColumnCount() {
		return atributos.length;
		
	}
	
	
	public int getRowCount() {
		int size;
		
		if(user.getApuestas()==null) {
			size=0;
		}
		
		else {
			size=user.getApuestas().size();
		}
		return size;
		
	}
	public Object getValueAt(int row, int col) {
		if(col==0) {
			return user.getApuestas().get(row).getPronostico().getRespuesta();
		}
		if(col==1) {
			return user.getApuestas().get(row).getPronostico().getPregunta().getEnunciado();
		}
		if(col==2) {
			return user.getApuestas().get(row).getFecha()[0];
		}
		
		if(col==3) {
			return user.getApuestas().get(row).getCantidadApostada()[0];
		}
		return null;
	}
	
	public String getColumnName(int col) {
		return atributos[col];
	}

	   public Class getColumnClass(int col) {
		         return String.class;
		   }
	
	
}
