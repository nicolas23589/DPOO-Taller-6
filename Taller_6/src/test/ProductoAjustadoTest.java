package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;

import logica.Ingrediente;
import logica.ProductoAjustado;
import logica.ProductoMenu;
import org.junit.Test;

public class ProductoAjustadoTest {
	private ProductoAjustado corralPiña;
	private ProductoMenu corral;
	private ArrayList<Ingrediente> listaAgregados= new ArrayList<Ingrediente>() ; 
	private ArrayList<Ingrediente> listaEliminados= new ArrayList<Ingrediente>();
	@Before
	public void setUp() throws Exception {
		try {
			corral = new ProductoMenu("corral",14000,700);
			listaAgregados.add(new Ingrediente ("piña",2500,100));
			corralPiña = new ProductoAjustado(corral,listaAgregados, listaEliminados);
			
		}
		catch (Exception e){
			System.out.print("ha ocurrido una excepción al crear el producto ");
		}
	}
	

	@Test
	public void testGetNombre() {
	
		
				assertEquals(("corral") , corralPiña.getNombre());
			
		
	}
	@Test
	public void testGenerarTextoFactura() {
				assertEquals(("corral (ProductoAjustado) ; 16500$ ; 800 Calorías") , corralPiña.generarTextoFactura());
	}
	
	@Test
	public  void testCalcularCalorias() {
			assertEquals(800, corralPiña.calcularcalorias(listaAgregados, 700));
	}
	
	@Test
	public  void testGetCalorias() {
			assertEquals(800, corralPiña.getCalorias());
	}
	
	@Test
	public  void testCalcularPrecio() {
			assertEquals(16500, corralPiña.calcularPrecio(listaAgregados, 14000));
		
	}
	@Test
	public  void testGetPrecio() {
			assertEquals(16500, corralPiña.getPrecio());
		
	}

}