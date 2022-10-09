package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import dataAccess.DataAccess;
import domain.Promocion;
import domain.Usuario;

public class AplicarPromocionDABTest {
	
	//sut:system under test
	 static DataAccess sut=new DataAccess();
	 
	 //additional operations needed to execute the test 
	 static TestDataAccess testDA=new TestDataAccess();
	 
	 @Test	 //Caso donde nos salta la excepcion cuando nos llega un parametro nulo.
	 public void test1() {
			
		 try {
				
				Usuario u1 = new Usuario("Mikel", new ArrayList<Promocion>());
				String text = null;
				testDA.aplicarPromocion(text, u1);
				
			}catch(NullPointerException e ) {
				assertTrue(true);
			} 
	 }
	 
	 @Test	 //Caso donde nos salta la excepcion cuando nos llega un parametro nulo.
	 public void test2() {
			
		 try {
				
				Usuario u1 = null;
				String text = "a";
				testDA.aplicarPromocion(text, u1);
				
			}catch(NullPointerException e ) {
				assertTrue(true);
			} 
	 }
	 
	 @Test //Caso donde ya se han utilizado toda la cantidad de veces la promocion, devuelve 2
	 public void test3() {
		 
		 try {
		 Promocion p = new Promocion("0");
		 ArrayList<Promocion> promociones = new ArrayList <Promocion>();
		 promociones.add(p);
		 String text = "a";
		 ArrayList<Usuario> users = new ArrayList <Usuario>();
		 Usuario u = new Usuario("0");
		 users.add(u);
		 
		 Promocion p2 = new Promocion(text, 0);
		 Usuario u1 = new Usuario("A", promociones);
		 
		 testDA.open();
		 testDA.anadirPromocion(p2);
		 testDA.anadirUsuario(u1);
		 testDA.close();
		 
		 int resul = sut.aplicarPromocion(text, u1);
		 
		 assertEquals(2,resul);
			assertTrue(true);
			
			testDA.open();
			testDA.borrarPromocion(p2);
			testDA.borrarUsuario(u1);
			testDA.close();
			
		 } catch(Exception e) {
			 System.out.println(e.getMessage());
		 } 
		 	 
	 }
	 
	 @Test //Caso donde el usuario ya ha utilizado el codigo, devuelve 1
	 public void test4() {
		 
		 try {
		 Promocion p = new Promocion("0");
		 ArrayList<Promocion> promociones = new ArrayList <Promocion>();
		 promociones.add(p);
		 String text = "a";
		 ArrayList<Usuario> users = new ArrayList <Usuario>();
		 Usuario u = new Usuario("0");
		 users.add(u);
		 
		 Usuario u1 = new Usuario("A", promociones);
		 users.add(u1);
		 Promocion p2 = new Promocion(text, users);
		 
		 
		 
		 testDA.open();
		 testDA.anadirPromocion(p2);
		 testDA.anadirUsuario(u1);
		 testDA.close();
		 
		 int resul = sut.aplicarPromocion(text, u1);
		 
		 assertEquals(1,resul);
			assertTrue(true);
			
			testDA.open();
			testDA.borrarPromocion(p2);
			testDA.borrarUsuario(u1);
			testDA.close();
			
		 } catch(Exception e) {
			 System.out.println(e.getMessage());
		 } 
		 	 
	 }
	 
	 @Test //Caso donde todo va bien, devuelve 0
	 public void test5() {
		 
		 try {
		 Promocion p = new Promocion("0");
		 ArrayList<Promocion> promociones = new ArrayList <Promocion>();
		 promociones.add(p);
		 String text = "a";
		 ArrayList<Usuario> users = new ArrayList <Usuario>();
		 Usuario u = new Usuario("0");
		 users.add(u);
		 
		 Usuario u1 = new Usuario("A", promociones);
		 Promocion p2 = new Promocion(text, users);
		 
		 
		 
		 testDA.open();
		 testDA.anadirPromocion(p2);
		 testDA.anadirUsuario(u1);
		 testDA.close();
		 
		 int resul = sut.aplicarPromocion(text, u1);
		 
		 assertEquals(0,resul);
			assertTrue(true);
			
			testDA.open();
			testDA.borrarPromocion(p2);
			testDA.borrarUsuario(u1);
			testDA.close();
			
		 } catch(Exception e) {
			 System.out.println(e.getMessage());
		 } 
		 	 
	 }
}
