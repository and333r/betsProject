
import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dataAccess.DataAccess;
import domain.Promocion;
import domain.Usuario;
import test.dataAccess.TestDataAccess;

public class AplicarPromocionDABTest {
	
	//sut:system under test
	 static DataAccess sut = new DataAccess();
	 
	 //additional operations needed to execute the test 
	 static TestDataAccess testDA = new TestDataAccess();
	
	 
	 @Test	 //Caso donde nos salta la excepcion cuando nos llega un parametro nulo.
	 public void test1() {
		 Usuario u1 = new Usuario("Lonbas","12351789N", new ArrayList<Promocion>());
		 try {  
				
				String text = null;
				testDA.open();
				testDA.anadirUsuario(u1);
				testDA.close();
				
				int resul = sut.aplicarPromocion(text, u1);
				assertEquals(3,resul);
				
				testDA.open();
				testDA.borrarUsuario(u1);
				testDA.close();
				
			}catch(NullPointerException e ) {
				testDA.open();
				testDA.borrarUsuario(u1);
				testDA.close();
				e.printStackTrace();
				fail();
			} 
	 }
	 
	 @Test	 //Caso donde nos salta la excepcion cuando nos llega un parametro nulo.
	 public void test2() {
			
		 try {
				
				Usuario u1 = null;
				String text = "a";
				
				
				int resul = sut.aplicarPromocion(text, u1);
				assertEquals(3,resul);
				
				
			}catch(NullPointerException e ) {
				e.printStackTrace();
				fail();
			} 
	 }
	 
	 @Test //Caso donde ya se han utilizado toda la cantidad de veces la promocion, devuelve 2
	 public void test3() {
		 	
		 	Promocion p = new Promocion("LaLiga ofertas",new ArrayList<Usuario>(),0);
			Usuario u1 = new Usuario("Ander", "12356789N", new ArrayList<Promocion>());
			Usuario u2 = new Usuario("ale","12356789a", new ArrayList<Promocion>());
			p.anadirUsuario(u2);
		 
		try {
		 
		 String text = "LaLiga ofertas";
		 testDA.open();
		 int comp = testDA.anadirPromocion(p);
		 System.out.println(comp);
		 testDA.anadirUsuario(u1);
		 testDA.anadirUsuario(u2);
		 testDA.close();
		 
		 int resul = sut.aplicarPromocion(text, u1);
		 System.out.println(resul);
		 
		 assertEquals(2,resul);
			
			
			testDA.open();
			testDA.borrarUsuario(u1);
			testDA.borrarUsuario(u2);
			testDA.borrarPromocion(p);
			testDA.close();
			
		 } catch(Exception e) {
			 	testDA.open();
				testDA.borrarUsuario(u1);
				testDA.borrarUsuario(u2);
				testDA.borrarPromocion(p);
				testDA.close();
			 e.printStackTrace();
			 fail();
		 } 
		 	 
	 }

	 
	 @Test //Caso donde el usuario ya ha utilizado el codigo, devuelve 1
	 public void test4() {
		 Promocion p = new Promocion("LaLiga offer",new ArrayList<Usuario>(),0);
			Usuario u1 = new Usuario("Ander", "12356789h", new ArrayList<Promocion>());
			p.anadirUsuario(u1);
		 
		try {
		 
		 String text = "LaLiga offer";
		 testDA.open();
		 int comp = testDA.anadirPromocion(p);
		 System.out.println(comp);
		 testDA.anadirUsuario(u1);
		 int resul = sut.aplicarPromocion(text, u1);
		 testDA.close();
		 
		 System.out.println(resul);
		 
		 assertEquals(1,resul);
			
			
			testDA.open();
			testDA.borrarUsuario(u1);
			testDA.borrarPromocion(p);
			testDA.close();
			
		 } catch(Exception e) {
			 	testDA.open();
				testDA.borrarUsuario(u1);
				testDA.borrarPromocion(p);
				testDA.close();
			 e.printStackTrace();
			 fail();
		 } 
		 	
	 }
	 
	 @Test //Caso donde todo va bien, devuelve 0
	 public void test5() {

		 Promocion p = new Promocion("LaLiga offers",new ArrayList<Usuario>(),3);
		 	Promocion p2 = new Promocion("LaLiga ofertas",new ArrayList<Usuario>(),0);
			Usuario u1 = new Usuario("AnderTest1", "12376789h", new ArrayList<Promocion>());
			Usuario u2 = new Usuario("MikelTest1", "11396789h", new ArrayList<Promocion>());
			u1.setSaldo(100);
			u1.anadirPromo(p2);
			u2.anadirPromo(p2);
			p.anadirUsuario(u2);
			
		try {
		 String text = "LaLiga offers";
		 testDA.open();
		 testDA.anadirUsuario(u1);
		 testDA.anadirUsuario(u2);
		 int comp = testDA.anadirPromocion(p);
		 System.out.println(comp);
		 
		 
		 int resul = sut.aplicarPromocion(text, u1);
		 System.out.println(resul);
		 testDA.close();
		 
		 assertEquals(0,resul);
			 
			
			testDA.open();
			testDA.borrarPromocion(p);
			testDA.borrarUsuario(u1);
			testDA.borrarUsuario(u2);
			testDA.close();
			
		 } catch(Exception e) {
			 e.printStackTrace();
			 	testDA.open();
				testDA.borrarPromocion(p);
				testDA.borrarUsuario(u1);
				testDA.borrarUsuario(u2);
				testDA.close();
			 fail();
		 } 
		 	 
	 }
}
