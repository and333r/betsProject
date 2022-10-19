/*
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;
import org.mockito.Mockito;

import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import domain.Administrador;
import domain.Evento;
import domain.Pregunta;
import domain.Promocion;
import domain.Pronostico;
import domain.Usuario;

public class crearApuestaMockIntTest {
	
	
	DataAccess dbMock = Mockito.mock(DataAccess.class);
	
	
	BLFacadeImplementation bl = new BLFacadeImplementation(dbMock);

	@Test
	public void test() { //c<0. No hace falta usar Mock
		
		ArrayList<Promocion> promos = new ArrayList<Promocion>();
		Usuario u1 = new Usuario("Ander1", promos);
		Pronostico p1= new Pronostico("11111111", "La Liga Santander");
		
		
		try {
			int ret = bl.crearApuesta(p1, u1, -7);
			assertEquals(-1,ret);
		}
		catch (Exception e) {
			fail();
		}
		
		
		
	}
	
	@Test
	public void test1() { //Hay una apuesta añadida pero se pueden anadir mas
		
		ArrayList<Promocion> promos = new ArrayList<Promocion>();
		Usuario u2 = new Usuario("Ander2", promos);
		Pronostico p2= new Pronostico("22222222", "La Liga Santander");
		
		
		try {
			
			Mockito.when(dbMock.existeApuesta(Matchers.anyObject())).thenReturn(true);
			
			boolean ret1= bl.existeApuesta(p2.getId()+"_" + u2.getNombreUsuario());
			
			assertEquals(true,ret1);
			
			Mockito.when(dbMock.puedeAnadirApuesta(Matchers.anyObject())).thenReturn(true);
			
			int ret2 = bl.crearApuesta(p2, u2, 50);
			
			assertEquals(2,ret2);
			
		}
		catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		

}
	
	@Test
	public void test2() { //No se pueden anadir mas apuestas
		
		ArrayList<Promocion> promos = new ArrayList<Promocion>();
		Usuario u3 = new Usuario("Ander3", promos);
		Pronostico p3= new Pronostico("33333333", "La Liga Santander");
		
		
		try {
			
			Mockito.when(dbMock.existeApuesta(Matchers.anyObject())).thenReturn(true);
			
			boolean ret1= bl.existeApuesta(p3.getId()+"_" + u3.getNombreUsuario());
			
			assertEquals(true,ret1);
			
			Mockito.when(dbMock.puedeAnadirApuesta(Matchers.anyObject())).thenReturn(false);
			
			int ret2 = bl.crearApuesta(p3, u3, 50);
			
			assertEquals(3,ret2);
			
		}
		catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		
	
}
	
	@Test
	public void test3() { //No existe apuesta del usuario a ese p, y la hace bien
		
		try {
			ArrayList<Promocion> promos = new ArrayList<Promocion>();
			Usuario u4 = new Usuario("Ander4", promos);
			u4.setSaldo(1000);
			Pronostico p4= new Pronostico("44444444", "La Liga Santander");
			Mockito.when(dbMock.existeApuesta(Matchers.anyObject())).thenReturn(false);
			
			boolean ret1= bl.existeApuesta(p4.getId()+"_" + u4.getNombreUsuario());
			
			assertEquals(false,ret1);
			
			Mockito.when(dbMock.crearApuesta(p4, u4, 50)).thenReturn(0);
			
			int ret2 = bl.crearApuesta(p4, u4, 50);
			
			assertEquals(0,ret2);
			
		}
		catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		
	
}
	@Test
	public void test4() { //No existe apuesta del usuario a ese p, y la hace bien
		
		try {
			ArrayList<Promocion> promos = new ArrayList<Promocion>();
			Usuario u5 = new Usuario("Ander4", promos);
			u5.setSaldo(1000);
			Pronostico p5= new Pronostico("44444444", "La Liga Santander");
			Mockito.when(dbMock.existeApuesta(Matchers.anyObject())).thenReturn(false);
			
			boolean ret1= bl.existeApuesta(p5.getId()+"_" + u5.getNombreUsuario());
			
			assertEquals(false,ret1);
			
			Mockito.when(dbMock.crearApuesta(p5, u5, 50)).thenReturn(1);
			
			int ret2 = bl.crearApuesta(p5, u5, 50);
	
			assertEquals(4,ret2);
			
		}
		catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
		@Test
		public void test5() { //No existe apuesta del usuario a ese p, y la hace bien
			
			try {
				ArrayList<Promocion> promos = new ArrayList<Promocion>();
				Usuario u4 = new Usuario("Ander4", promos);
				u4.setSaldo(1000);
				Pronostico p4= new Pronostico("44444444", "La Liga Santander");
				Mockito.when(dbMock.existeApuesta(Matchers.anyObject())).thenReturn(false);
				
				boolean ret1= bl.existeApuesta(p4.getId()+"_" + u4.getNombreUsuario());
				
				assertEquals(false,ret1);
				
				Mockito.when(dbMock.crearApuesta(p4, u4, 50)).thenReturn(2);
				
				int ret2 = bl.crearApuesta(p4, u4, 50);
				
				assertEquals(1,ret2);
				
			}
			catch (Exception e) {
				e.printStackTrace();
				fail();
			}
	
}
}
*/