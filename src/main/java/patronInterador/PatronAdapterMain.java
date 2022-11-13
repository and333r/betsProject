package patronInterador;

import businessLogic.BLFacade;

import businessLogic.BLFacadeImplementation;
import domain.Usuario;
import gui.WindowTable;

public class PatronAdapterMain {

	public static void main(String[] args) {
		  try {
			  BLFacade blFacade= new BLFacadeImplementation(); //Maintable soilik
			  Usuario user = (Usuario) blFacade.obtenerActor("Usuario1");
			  WindowTable vt = new WindowTable(user); 
			  
			  vt.setVisible(true);
		} 
		  catch (Exception e) { 
			  e.printStackTrace();
		  }
		}
	
		}