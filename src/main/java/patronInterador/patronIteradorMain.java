package patronInterador;

import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Actor;
import domain.Administrador;
import domain.Competicion;
import domain.Evento;
import domain.Usuario;
import extendedIterator.ExtendedIterator;
import factory.BLFactory;

public class patronIteradorMain {

	public static void main(String[] args) throws MalformedURLException {
		// obtener el objeto Facade local
		

		
		
		int isLocal = 1;
		BLFacade blFacade = new BLFactory().getBusinessLogicFactory();
		
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date;
		date = UtilDate.newDate(2020,12,17); // 17 del mes que viene 
		
		ExtendedIterator<Evento> i = blFacade.getEventsIterator(date); 
		Evento e;
		System.out.println(i.size());
		System.out.println("_____________________"); 
		System.out.println("RECORRIDO HACIA ATRÁS");
		
		i.goLast(); // Hacia atrás 
		while (i.hasPrevious()) {
		e = i.previous();
		System.out.println(e.getNombre());
		
		}
		
		System.out.println(); System.out.println("_____________________");
		System.out.println("RECORRIDO HACIA ADELANTE"); 
		
		i.goFirst(); // Hacia adelante
		while (i.hasNext()) {
			
			e = i.next();
			System.out.println(e.getNombre()); 
				} 
		}
	}