

import domain.*;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import dataAccess.DataAccess;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
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
		
		Usuario u1 = new Usuario("Lonbas","12351789N", new ArrayList<Promocion>());
		 try { 
				
				String text = null;
				
//				int resul = sut.aplicarPromocion(text, u1);
//				assertEquals(3,resul);
//				
//				testDA.open();
//				testDA.borrarUsuario(u1);
//				testDA.close();
				
			}catch(NullPointerException e ) {
//				testDA.open();
//				testDA.borrarUsuario(u1);
//				testDA.close();
				e.printStackTrace();
				fail();
			} 
	}
	
	@Test  //Test cuando el usuario es null
	public void test3() {
		try {
			
			Usuario u1 = null;
			String text = "a";
			
			Mockito.when(db.aplicarPromocion(text, u1)).thenReturn(3);
			int resul = bl.aplicarPromocion(text, u1);
			assertEquals(3,resul);
			
		}catch(NullPointerException e ) {
			e.printStackTrace();
			fail();
		}
	}
}
