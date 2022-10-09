

import domain.*;

import org.junit.Test;


import java.util.ArrayList;
import dataAccess.DataAccess;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import businessLogic.BLFacadeImplementation;

@RunWith(MockitoJUnitRunner.class)
public class AplicarPromocionMockIntTest {
	
	
	DataAccess db = Mockito.mock(DataAccess.class);
	
	
	BLFacadeImplementation bl = new BLFacadeImplementation(db);
	
	@Test //Test para el funcionamiento correcto.
	public void test0() {
	try
	{
		Usuario u1 = new Usuario("Mikel", new ArrayList<Promocion>());
		
		Mockito.doReturn(0).when(db).aplicarPromocion("a",u1);
		//Cuando forumDAO llama al metodo elegido, el mockito hace return del valor esperado (mirarlo bien)
		
	//System.out.println("forumDAO.addUserDAO(\"1234\",\"Jon\",\"654478722\")" +forumDAO.addUserDAO("1234","Jon","654478722"));
	System.out.println("db.aplicarPromocion(a,u1) --> 0");
	}
	catch
	(Exception e) {
	
	e.printStackTrace();
	}
	}
	
	@Test //test para cuando uno de los argumentos no esta en la bd.
	public void test1() {
	try
	{
		Usuario u1 = new Usuario("a", new ArrayList<Promocion>()); //Usuario no almacenado en la base de datos.
		String text = "prueba"; //Texto de la promocion que no esta almacenado en la bd
		
		Mockito.doReturn(false).when(db).aplicarPromocion("a",u1); //U1 no esta en la bd luego devuelve false.
		
		Mockito.doReturn(false).when(db).aplicarPromocion(text,u1); //text no esta en la bd.
	
	System.out.println("db.aplicarPromocion(a,u1) --> 0");
	}
	catch
	(Exception e) {
	
	e.printStackTrace();
	}
	}
	
	@Test //Test cuando el texto de la promocion es null
	public void test2() {
	try
	{
		Usuario u1 = new Usuario("Mikel", new ArrayList<Promocion>());
		
		Mockito.doReturn(3).when(db).aplicarPromocion(null,u1);
		
		
	System.out.println("db.aplicarPromocion(null,u1) --> 3");
	}
	catch
	(Exception e) {
	
	e.printStackTrace();
	}
	}
	
	@Test  //Test cuando el usuario es null
	public void test3() {
	try
	{	
		Mockito.doReturn(3).when(db).aplicarPromocion("a",null);
		
	System.out.println("db.aplicarPromocion(a,null) --> 3");
	}
	catch
	(Exception e) {
	
	e.printStackTrace();
	}
	}
}
