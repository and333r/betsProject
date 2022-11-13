package factory;

import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.UIManager;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import configuration.ConfigXML;
import configuration.UtilDate;
import dataAccess.DataAccess;
import domain.Actor;
import domain.Usuario;

public class BLFactory {
	
	ConfigXML c = ConfigXML.getInstance();

	
	public void BLFactory(){}
	
	
	public BLFacade getBusinessLogicFactory() throws MalformedURLException {
		BLFacade appFacadeInterface ;
		
		if(c.isBusinessLogicLocal()) {
			appFacadeInterface= getLocalBusinessLogicFactory();
		}
		else {
			appFacadeInterface= getWebBusinessLogicFactory();
		}
		return appFacadeInterface;
	}
	

	private BLFacade getWebBusinessLogicFactory() throws MalformedURLException {
		BLFacade appFacadeInterface ;
		String serviceName= "http://"+c.getBusinessLogicNode() +":"+ c.getBusinessLogicPort()+"/ws/"+c.getBusinessLogicName()+"?wsdl";
		 
		//URL url = new URL("http://localhost:9999/ws/ruralHouses?wsdl");
		URL url = new URL(serviceName);
		
        //1st argument refers to wsdl document above
		//2nd argument is service name, refer to wsdl document above
//        QName qname = new QName("http://businessLogic/", "FacadeImplementationWSService");
        QName qname = new QName("http://businessLogic/", "BLFacadeImplementationService");
        Service service = Service.create(url, qname);

        appFacadeInterface = service.getPort(BLFacade.class);
        
        return appFacadeInterface;
        
		
	}


	private BLFacade getLocalBusinessLogicFactory() {
		BLFacade appFacadeInterface ;
		
		DataAccess da= new DataAccess(c.getDataBaseOpenMode().equals("initialize"));
		
		appFacadeInterface =new BLFacadeImplementation(da);
		
		return appFacadeInterface;
		
	}
	
	
}


/*
 * 
		try {
			
			BLFacade appFacadeInterface;
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			
			appFacadeInterface= getBLFactory()
			
			
			else { //If remote
				
				 String serviceName= "http://"+c.getBusinessLogicNode() +":"+ c.getBusinessLogicPort()+"/ws/"+c.getBusinessLogicName()+"?wsdl";
				 
				//URL url = new URL("http://localhost:9999/ws/ruralHouses?wsdl");
				URL url = new URL(serviceName);

		 
		        //1st argument refers to wsdl document above
				//2nd argument is service name, refer to wsdl document above
//		        QName qname = new QName("http://businessLogic/", "FacadeImplementationWSService");
		        QName qname = new QName("http://businessLogic/", "BLFacadeImplementationService");
		 
		        Service service = Service.create(url, qname);

		        appFacadeInterface = service.getPort(BLFacade.class);
			} 
 */
