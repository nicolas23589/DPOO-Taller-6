package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;

import logica.Combo;
import logica.Producto;
import logica.Ingrediente;
import logica.ProductoAjustado;
import logica.ProductoMenu;
import org.junit.Test;

public class ComboTest {
	private Combo comboCorral;
	
	private ArrayList<Producto> listaProductos= new ArrayList<Producto>() ; 

	
	@Before
	public void setUp() throws Exception {
		
			listaProductos.add(new ProductoMenu ("corral", 14000, 700));
			listaProductos.add(new ProductoMenu ("papas medianas", 5500, 200));
			//La gaseosa se agrega automáticamente en la lógica
		
			double num= 10;
			comboCorral = new Combo(num,"combo corral", listaProductos);
			
	}
	

	@Test
	public void testGetNombre() {
	
		
				assertEquals(("combo corral") , comboCorral.getNombre());
	
	}
	@Test
	public void testGenerarTextoFactura() {
				assertEquals(("combo corral ; 22050$ ; 1300 Calorias") , comboCorral.generarTextoFactura());
	}
	
	@Test
	public  void testGetCalorias() {
			assertEquals(1300, comboCorral.getCalorias());
	}
	

	@Test
	public  void testGetPrecio() {
			assertEquals(22050, comboCorral.getPrecio());
	}
	@Test
	public  void testGetLista() {
			assertEquals(listaProductos, comboCorral.getlista());
	}
	@Test
	public  void testAgregarItem() {
			Combo comboCorral2= comboCorral;
			comboCorral2.agregarItemsACombo(new ProductoMenu ("corral", 14000, 700));
			listaProductos.add(new ProductoMenu ("corral", 14000, 700));
			assertEquals(comboCorral2.getlista(), listaProductos);
	}


}