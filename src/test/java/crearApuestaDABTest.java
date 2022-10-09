import static org.junit.Assert.*;


import java.util.ArrayList;

import org.junit.Test;

import dataAccess.DataAccess;
import domain.*;
import test.dataAccess.TestDataAccess;

public class crearApuestaDABTest {
	
	DataAccess sut = new DataAccess();
	TestDataAccess testDA = new TestDataAccess();

	
	private ArrayList<Promocion> promos = new ArrayList<Promocion>();



	@Test
	public void test1() { //User sin promos apuesta más de lo que tiene
		try {
			
			 ArrayList<Promocion> promos = new ArrayList<Promocion>();
			 Usuario u3 = new Usuario("Ander1", promos);
			 Pronostico p3= new Pronostico("111111", "La Liga Santander");
			 
			testDA.open();
			u3.setSaldo(0);
			testDA.anadirUsuario(u3);
			testDA.crearPronostico(p3);
			testDA.close();
			
			int res= sut.crearApuesta(p3, u3, 500);
			
			assertEquals(2,res);
			
			testDA.open();
			testDA.borrarUsuario(u3);
			testDA.borrarPronostico(p3);
			testDA.close();
			
			
		}
		catch(Exception e){
			e.printStackTrace();
			fail();
			
		}
	}
	
	@Test
	public void test2() { //User sin promos apuesta menos del minimo
		try {
			
			 ArrayList<Promocion> promos = new ArrayList<Promocion>();
			 Usuario u4 = new Usuario("Ander2", promos);
			 Pronostico p4= new Pronostico("222222", "La Liga Santander");
			
			testDA.open();
			u4.setSaldo(500);
			//testDA.borrarUsuario(u4);
			//testDA.borrarPronostico(p4);
			testDA.anadirUsuario(u4);
			p4.setMinBet(2);
			Pregunta pr= new Pregunta("Pregunta1");
			p4.setPregunta(pr);
			//testDA.borrarPregunta(pr);
			testDA.anadirPregunta(pr);
			p4.getPregunta().setMinBet(500);
			testDA.crearPronostico(p4);
			Apuesta apuesta = new Apuesta (p4,u4,500);
			//testDA.borrarApuesta(apuesta);
			testDA.close();

			int res= sut.crearApuesta(p4, u4, 1);
			
			assertEquals(1,res);
			
			testDA.open();
			testDA.borrarUsuario(u4);
			testDA.borrarPregunta(pr);
			testDA.borrarPronostico(p4);
			//testDA.borrarApuesta(apuesta);
			testDA.close();
			
			
		}
		catch(Exception e){
			e.printStackTrace();
			fail();
		}
	}

	
	@Test
	public void test3() { //User sin promos apuesta bien
		try {
			
			ArrayList<Promocion> promos = new ArrayList<Promocion>();
			 Usuario u5 = new Usuario("Ander3", promos);
			 Pronostico p5= new Pronostico("333333", "La Liga Santander");
			
			testDA.open();
			u5.setSaldo(500);
			//testDA.borrarUsuario(u5);
			testDA.anadirUsuario(u5);
			p5.setMinBet(2);
			Pregunta pr= new Pregunta("Pregunta2");
			p5.setPregunta(pr);
			//testDA.borrarPregunta(pr);
			testDA.anadirPregunta(pr);
			p5.getPregunta().setMinBet(1);
			//testDA.borrarPronostico(p5);
			testDA.crearPronostico(p5);
			Apuesta apuesta = new Apuesta (p5,u5,500);
						
			
			
			int res= sut.crearApuesta(p5, u5, 500);
			
			testDA.close();
			assertEquals(0,res);
			
			testDA.open();
			testDA.borrarUsuario(u5);
			testDA.borrarPregunta(pr);
			testDA.borrarPronostico(p5);
			testDA.borrarApuesta(apuesta);
			testDA.close();
			
			
		}
		catch(Exception e){
			e.printStackTrace();
			fail();
		}
	}
	
	
	@Test
	public void test4() { //User sin promociones sobre el pronóstico apuesta mas de lo que tiene
		try {
			
			 ArrayList<Promocion> promos = new ArrayList<Promocion>();
			 Usuario u0 = new Usuario("Ander4", promos);
			 
			 Pronostico p0= new Pronostico("444444", "La Liga Santander");
			
			testDA.open();
			u0.setSaldo(0);
			//testDA.borrarUsuario(u0);
			//testDA.borrarPronostico(p0);
			
			Promocion promoPremier= new Promocion("222", "Premier League");
			u0.anadirPromo(promoPremier);

			testDA.anadirUsuario(u0);
			testDA.crearPronostico(p0);
			testDA.close();
			
			int res= sut.crearApuesta(p0, u0, 500);
			
			assertEquals(2,res);
			
			testDA.open();
			testDA.borrarUsuario(u0);
			testDA.borrarPronostico(p0);
			testDA.close();
			
			
		}
		catch(Exception e){
			e.printStackTrace();
			fail();
			
		}
	}
	
	@Test
	public void test5() { //User con promociones sobre el pronóstico tiene prmocion de tipo porcentual
		try {
			
			 ArrayList<Promocion> promos = new ArrayList<Promocion>();
			 Usuario u6 = new Usuario("Ander5", promos);
			 
			 Pronostico p6= new Pronostico("555555", "La Liga Santander");
			
			testDA.open();
			u6.setSaldo(1000);
			Pregunta pr= new Pregunta("Pregunta3");
			p6.setPregunta(pr);
			testDA.anadirPregunta(pr);
			p6.getPregunta().setMinBet(1);

			Promocion promoPremier= new Promocion("22772", "La Liga Santander");
			promoPremier.setTipo(true);
			u6.anadirPromo(promoPremier);

			testDA.anadirUsuario(u6);
			testDA.crearPronostico(p6);
			testDA.close();
			
			int res= sut.crearApuesta(p6, u6, 500);
			
			assertEquals(0,res);
			
			testDA.open();
			testDA.borrarUsuario(u6);
			testDA.borrarPronostico(p6);
			testDA.borrarPregunta(pr);
			Apuesta apuesta = new Apuesta (p6,u6,500);
			testDA.borrarApuesta(apuesta);
			testDA.close();
			
			
		}
		catch(Exception e){
			e.printStackTrace();
			fail();
			
		}
	}
	
	@Test
	public void test6() { //User con promociones sobre el pronóstico tiene prmocion de tipo entero
		try {
			
			 ArrayList<Promocion> promos = new ArrayList<Promocion>();
			 Usuario u6 = new Usuario("Ander666666", promos);
			 
			 Pronostico p6= new Pronostico("666666", "La Liga Santander");
			
			testDA.open();
			u6.setSaldo(1000);
			Pregunta pr= new Pregunta("Pregunta4");
			p6.setPregunta(pr);
			testDA.anadirPregunta(pr);
			p6.getPregunta().setMinBet(1);
			Promocion promoPremier= new Promocion("2882772", "La Liga Santander");
			promoPremier.setTipo(false);
			u6.anadirPromo(promoPremier);

			testDA.anadirUsuario(u6);
			testDA.crearPronostico(p6);
			
			
			testDA.close();
			int res= sut.crearApuesta(p6, u6, 500);
			
			assertEquals(0,res);
			
			testDA.open();
			testDA.borrarUsuario(u6);
			testDA.borrarPronostico(p6);
			testDA.borrarPregunta(pr);
			Apuesta apuesta = new Apuesta (p6,u6,500);
			testDA.borrarApuesta(apuesta);
			testDA.close();
			
			
		}
		catch(Exception e){
			e.printStackTrace();
			fail();
			
		}
	}
	
	@Test
	public void test7() { //Cantidad negativa
		
		try {
		
		ArrayList<Promocion> promos = new ArrayList<Promocion>();
		Usuario u7 = new Usuario("Ander7", promos);
		Pronostico p7= new Pronostico("777777", "La Liga Santander");
		testDA.open();
		testDA.anadirUsuario(u7);
		testDA.crearPronostico(p7);
		testDA.close();
		
		int res = sut.crearApuesta(p7, u7,-47);
		
		assertEquals(res,4);
		
		testDA.open();
		testDA.borrarUsuario(u7);
		testDA.borrarPronostico(p7);
		testDA.close();
		}
		
		catch(Exception e){
			fail();
		}
		
		
		
	}
	
	@Test
	public void test8() { //Usuario null
		try {
		Usuario u8 = null;
		Pronostico p8= new Pronostico("888888", "La Liga Santander");
		testDA.open();
		testDA.crearPronostico(p8);
		testDA.close();
		
		int res = sut.crearApuesta(p8, u8,-47);
		
		assertEquals(res,4);
		
		testDA.open();
		testDA.borrarPronostico(p8);
		testDA.close();
		}
		catch(Exception e) {
			assertTrue(true);
		}
		
	}
	
	@Test
	public void test9() { //Pronostico nullc
		try {
		Usuario u9 = new Usuario("Ander9", promos);;
		Pronostico p9= null;
		testDA.open();
		testDA.crearPronostico(p9);
		testDA.close();
		
		int res = sut.crearApuesta(p9, u9,-47);
		
		assertEquals(res,4);
		
		testDA.open();
		testDA.borrarPronostico(p9);
		testDA.close();
		}
		catch(Exception e) {
			assertTrue(true);
		}
		
	}
	
		
}
