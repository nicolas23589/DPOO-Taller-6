package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.junit.Before;

import logica.Combo;
import logica.Producto;
import logica.Ingrediente;
import logica.Pedido;
import logica.ProductoAjustado;
import logica.ProductoMenu;
import org.junit.Test;

import excepciones.CostoPedidoException;
import excepciones.ProductoRepetidoException;

public class PedidoTest {
	private String nombreCliente= "McLovin";
	private String direccionCliente="Uniandes";
	
	private Combo comboCorral;
	private ArrayList<Producto> listaProductos= new ArrayList<Producto>() ; 

	ProductoMenu corral;
	ProductoAjustado corralPiña;
	private Pedido pedido;
	
	//Es necesario crear otro pedido que ya esté incializado para evitar conflictos al agregar items
	private Pedido pedido2;
	@Before
	public void setUp() throws Exception {
			//Producto menu
			corral= new ProductoMenu ("corral", 14000, 700);
			
			//Producto ajustado
			ArrayList<Ingrediente> listaAgregados= new ArrayList<Ingrediente>();
			ArrayList<Ingrediente> listaEliminados= new ArrayList<Ingrediente>();
			listaAgregados.add(new Ingrediente ("piña",2500,100));
			corralPiña = new ProductoAjustado(corral,listaAgregados, listaEliminados);
			
			//Combo
			ArrayList<Producto> listaProductosCombo= new ArrayList<Producto>() ; 
			listaProductosCombo.add(new ProductoMenu ("corral", 14000, 700));
			listaProductosCombo.add(new ProductoMenu ("papas medianas", 5500, 200));

			double num= 10;
			comboCorral = new Combo(num,"combo corral", listaProductosCombo);
			
			pedido= new Pedido(nombreCliente, direccionCliente);
			
			pedido2= new Pedido(nombreCliente, direccionCliente);
			
			listaProductos.add(corral);
			listaProductos.add(corralPiña);
			listaProductos.add(comboCorral);
			
			
			try {
				pedido2.agregarProducto(corral);
			} catch (CostoPedidoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				pedido2.agregarProducto(corralPiña);
			} catch (CostoPedidoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				pedido2.agregarProducto(comboCorral);
			} catch (CostoPedidoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	

	@Test
	public void testGetNombreCliente() {
				assertEquals((nombreCliente) , pedido.getNombreCliente());
	}
	@Test
	public void testGetDireccionCliente() {
				assertEquals((direccionCliente) , pedido.getDireccionCliente());
	}
	@Test
	public void testGetIdPedido() {
		//En este método, el objeto decide arbitraria y aleatoriamente su id,
		//por lo tanto es imposible anticiparla
				assertEquals((pedido.getIdPedido()) , pedido.getIdPedido());
	}
	
	@Test
	public void testAgregarproducto() {
				try {
					pedido.agregarProducto(corral);
				} catch (CostoPedidoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					pedido.agregarProducto(corralPiña);
				} catch (CostoPedidoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					pedido.agregarProducto(comboCorral);
				} catch (CostoPedidoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
				assertEquals((listaProductos) , pedido.getlistaProductos());
	}
	@Test
	public  void testGetPrecioNeto() {
			assertEquals(52550, pedido2.getPrecioNetoPedido());
	}
	@Test
	public  void testGetPrecioIVA() {
		
			assertEquals(9984, (int) (pedido2.getPrecioIvaPedido()));   
	}
	@Test
	public  void testGetPrecioTotal() {
		
			assertEquals(62534, (int) (pedido2.getPrecioTotalPedido()));   
	}

	
	@Test
	public void testGenerarTextoFactura() {
		ArrayList<String> txtFacturaPedido= new ArrayList<String>();
		txtFacturaPedido.add("corral ; 14000$ ; 700 Calorías");
		txtFacturaPedido.add("corral (ProductoAjustado) ; 16500$ ; 800 Calorías");
		txtFacturaPedido.add("combo corral ; 22050$ ; 1300 Calorias");
		
				assertEquals(txtFacturaPedido , pedido2.generarTextoFactura());
	}
	
	@Test
	public  void testGuardarFactura() throws IOException {
		File archivoPrueba= new File("./data/FacturaDePrueba");
		try {
			pedido2.guardarFactura();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File archivoGenerado= new File ("./data/"+ pedido2.getIdPedido());
		
		BufferedReader lector1 = new BufferedReader (new FileReader (archivoPrueba));
		 String linea = lector1.readLine() ; 
		 linea = lector1.readLine() ;
		 
		 BufferedReader lector2 = new BufferedReader (new FileReader (archivoGenerado));
		 String linea2 = lector2.readLine() ;
		 linea2 = lector2.readLine() ; 

		 while (linea!= null) { 
			 //  procesamiento 
				assertEquals(linea, linea2);
		
			 linea = lector1.readLine() ;
			 linea2 = lector2.readLine() ;
		 }
		
		lector1.close();
		lector2.close();

	}
	




}