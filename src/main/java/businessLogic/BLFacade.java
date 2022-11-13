package businessLogic;




import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebService;

import domain.Actor;
import domain.Competicion;
import domain.Evento;
import extendedIterator.ExtendedIterator;

/**
 * Interface that specifies the business logic.
 */
@WebService
public interface BLFacade  {

	/**
	 * This method calls the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	@WebMethod public void initializeBD();
	
	public ExtendedIterator<Evento> getEventsIterator(Date date);
	
	public Actor obtenerActor (String user);
	

	
}
