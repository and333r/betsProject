package userAdapter;

import java.util.Date;

import javax.persistence.OneToOne;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import domain.Pronostico;
import domain.Usuario;

public class UserAdapter extends AbstractTableModel {

	
	private Usuario user;
	
	private String [] atributos= {"Pronostico", "Pregunta", "Fecha", "CantidadApostada"};
	
	
	String[] columnNames = {"First Name",
            "Last Name",
            "Sport",
            "# of Years",
            "Vegetarian"};

	Object[][] data = {
				{"Kathy", "Smith",
						"Snowboarding", new Integer(5), new Boolean(false)},
					{"John", "Doe",
								"Rowing", new Integer(3), new Boolean(true)},
						{"Sue", "Black",
									"Knitting", new Integer(2), new Boolean(false)},
						{"Jane", "White",
										"Speed reading", new Integer(20), new Boolean(true)},
						{"Joe", "Brown",
											"Pool", new Integer(10), new Boolean(false)}
	};
			
			
	public UserAdapter(Usuario user) {
		this.user=user;
		JTable prueba= new JTable (columnNames, data);
	}
	
	public int getColumnCount() {
		return 4;
		
	}
	
	
	public int getRowCount() {
		return 8;
		
	}
	public Object getValueAt(int row, int col) {
		if(col==0) {
			return user.getApuestas().get(row).getPronostico().getRespuesta();
		}
		if(col==1) {
			return user.getApuestas().get(row).getPronostico().getPregunta();
		}
		if(col==2) {
			return user.getApuestas().get(row).getFecha();
		}
		
		if(col==3) {
			return user.getApuestas().get(row).getCantidadApostada();
		}
		return null;
	}
	

	
	
}
