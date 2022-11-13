package gui;

import java.awt.Color;
import java.net.URL;
import java.util.Locale;

import javax.swing.UIManager;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import factory.BLFactory;
import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;

public class ApplicationLauncher { 
	
	
	private static IniciarSesionGUI inicioSesion;
	
	public static IniciarSesionGUI inicioSesion() {
		return inicioSesion;
	}
	
	public static void main(String[] args) {

		ConfigXML c = ConfigXML.getInstance();
		
		System.out.println(c.getLocale());
		
		inicioSesion = new IniciarSesionGUI();
		
		Locale.setDefault(new Locale(c.getLocale()));
		
		System.out.println("Locale: "+Locale.getDefault());
		
		inicioSesion.setVisible(true);
		
		try {
			
			BLFacade appFacadeInterface= new BLFactory().getBusinessLogicFactory();
//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			
			
			/*if (c.getDataBaseOpenMode().equals("initialize")) 
				appFacadeInterface.initializeBD();
				*/
			IniciarSesionGUI.setBussinessLogic(appFacadeInterface);

		

			
		}catch (Exception e) {
			
			inicioSesion.jLabelInicioSesion.setText("Error: "+e.toString());
			inicioSesion.jLabelInicioSesion.setForeground(Color.RED);	
			
			System.out.println("Error in ApplicationLauncher: "+e.toString());
		}
	}

}
