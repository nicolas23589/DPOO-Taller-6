package test;

import static org.junit.Assert.*;

import org.junit.Before;

import logica.ProductoMenu;

import org.junit.Test;

public class ProductoMenuTest {
	private ProductoMenu productoMenu;

	@Before
	public void setUp() throws Exception {
		
			productoMenu = new ProductoMenu("corral",14000,700);
			
	
	}
	

	@Test
	public void testGetNombre() {
	
		
				assertEquals(("corral") , productoMenu.getNombre());
			
		 
	}
	
	@Test
	public void testGetPrecio() {
	
		
				assertEquals((14000) , productoMenu.getPrecio());
			
	
	}
	
	@Test
	public void testGenerarPrecioFactura() {
	
		
				assertEquals(("corral ; 14000$ ; 700 Calorías") , productoMenu.generarTextoFactura());
	
	}
	

	@Test
	public  void testCalorias() {

			assertEquals(700, productoMenu.getCalorias());

	}

}





