package logica;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import excepciones.IngredienteRepetidoException;
import excepciones.ProductoRepetidoException;

public class Restaurante {
	//VARIABLES GLOBALES
	public static ArrayList<Ingrediente> listIngredientes= new ArrayList<Ingrediente>();
	public static ArrayList<ProductoMenu> listMenu= new ArrayList<ProductoMenu>();
	public static ArrayList<Combo> listCombos= new ArrayList<Combo>();
	public static ArrayList<Pedido> listPedidos= new ArrayList<Pedido>();
	public static ArrayList<Bebidas> listBebidas= new ArrayList<Bebidas>();
	public static Pedido pedidoEnCurso= null;
	/*metodos */
	public static  void cargarInformacionRestaurante (File archivoIngredientes, File archivoMenu, File archivoCombos, File archibebidas) throws IOException, IngredienteRepetidoException, ProductoRepetidoException {
		cargarIngredientes(archivoIngredientes);
		cargarMenu(archivoMenu);
		cargarCombos(archivoCombos);
		cargarBebidas(archibebidas);
		 }
	
	public static ArrayList<ProductoMenu> getMenuBase () {
		return listMenu;
		}
	
	public static ArrayList<Ingrediente> getIngredientes () {
		return listIngredientes;
		}
	public static ArrayList<Combo> getCombos () {
		return listCombos;
		}
	public static ArrayList<Bebidas> getBebidas () {
		return listBebidas;
		}
	
	public static  Pedido iniciarPedido  (String nombrecliente, String direccionCliente, ArrayList<Producto> listaproductos)
		{
		Pedido pedidoActual= new Pedido(nombrecliente, direccionCliente);
		pedidoEnCurso= pedidoActual;
		return pedidoActual;
		}
	
	public static  void cerrarYGuardarPedido (Pedido pedido) throws FileNotFoundException, UnsupportedEncodingException {
		pedidoEnCurso= null;
		listPedidos.add(pedido);
		pedido.guardarFactura();
		}
	
	public  Pedido getPedidoEnCurso () {
		return this.pedidoEnCurso;
		
		
		}

	//FUNCIONES DE CARGA DE DATOS
	public static  void cargarIngredientes (File archivoIngredientes) throws IOException, IngredienteRepetidoException{
		 BufferedReader lector = new BufferedReader (new FileReader (archivoIngredientes));
		 String linea = lector.readLine() ; 
		 ArrayList<Ingrediente> listaIngredientes= new ArrayList<Ingrediente>();
		 int numLinea=1;
		 while (linea!= null) { 
			 String[] datos =linea.split (";" ) ; 
			 String nombre = datos [ 0 ] ; 
			 String precio = datos [ 1 ] ;
			 String calorias = datos [ 2 ] ;
			 int calorias2= Integer.parseInt(calorias);
			 int precio2= Integer.parseInt(precio);
			 Ingrediente new_ingrediente = new Ingrediente (nombre, precio2, calorias2); 
			
				 for (int i=0; i<listaIngredientes.size(); i++) {
					
					 if (new_ingrediente.getNombre().equals(listaIngredientes.get(i).getNombre())){
						
						 throw new IngredienteRepetidoException ("Hay un ingrediente repetido", numLinea, new_ingrediente);
					 }
				 }
			 
			 listaIngredientes.add(new_ingrediente);
			
			 linea = lector.readLine() ;
			 numLinea+=1;
		 }
		 listIngredientes= listaIngredientes;
		lector.close();
	}
	
	public static  void cargarMenu (File archivoMenu) throws IOException, ProductoRepetidoException {
		 BufferedReader lector = new BufferedReader (new FileReader (archivoMenu));
		 String linea = lector.readLine() ; 
		 ArrayList<ProductoMenu> listaMenu= new ArrayList<ProductoMenu>();

		 while (linea!= null) { 
			 //  procesamiento 
			 int numLinea=1;
			 String[] datos =linea.split (";") ; 
	
			 String nombre = datos [ 0 ] ; 
			 String precio = datos [ 1 ] ;
			 String calorias = datos [ 2 ] ;
			 int calorias2= Integer.parseInt(calorias);
			 int precio2= Integer.parseInt(precio);
			 ProductoMenu new_productomenu = new ProductoMenu (nombre, precio2, calorias2); 
			 
				 for (int i=0; i<listaMenu.size(); i++) {
					 if (new_productomenu.getNombre().equals(listaMenu.get(i).getNombre())){
						 throw new ProductoRepetidoException ("Hay un producto del menú repetido", numLinea, new_productomenu);
					 }
				 }
			 
			 listaMenu.add(new_productomenu);
			 linea = lector.readLine() ;
		 }
		 listMenu= listaMenu;
		lector.close();
		
	}
	public static  void cargarBebidas (File archivoMenu) throws IOException {
		 BufferedReader lector = new BufferedReader (new FileReader (archivoMenu));
		 String linea = lector.readLine() ; 
		 ArrayList<Bebidas> listaMenu= new ArrayList<Bebidas>();

		 while (linea!= null) { 
			 //  procesamiento 
			 String[] datos =linea.split (";") ; 
			 String nombre = datos [ 0 ] ; 
			 String precio = datos [ 1 ] ;
			 String calorias = datos [ 2 ] ;
			 int calorias2= Integer.parseInt(calorias);
			 int precio2= Integer.parseInt(precio);
			 Bebidas new_productomenu = new Bebidas (nombre, precio2, calorias2); 
			 listaMenu.add(new_productomenu);
			 linea = lector.readLine() ;
		 }
		 listBebidas= listaMenu;
		lector.close();
		
	}
	public static  void cargarCombos (File archivoCombos) throws IOException {
		 BufferedReader lector = new BufferedReader (new FileReader (archivoCombos));
		 String linea = lector.readLine() ; 
		 List <Combo> listaCombos= new ArrayList<Combo>();

		 while (linea!= null) { 
			 //  procesamiento 
			 String[] datos =linea.split (";") ; 
			 String nombre = datos [ 0 ] ; 
			 String[] descuento = datos [ 1 ].split("%") ;// lista donde la primera posicion es el numero y la segunda el simbolo a ingnorar
			 String descuento3= descuento[0];//se saca el numero
			 Double descuento2 = Double. parseDouble(descuento3);//se hace el casting
	
			 //Teniendo el str de ingrediente, se itera la lista (atributo) ya cargado para compararlo y agregarlo
			 List<Producto> lista_ingre_combo= new ArrayList<Producto>();
			 for (int i=2;i<datos.length;i++) {
				 String nombre_actual= datos[i];
				 
				 for (int j=0;j<listMenu.size();j++) {
					 ProductoMenu producto_actual= listMenu.get(j);
					 String nombre_producto_act= producto_actual.getNombre();
				
					 if (nombre_producto_act.equals(nombre_actual)){ 
						 lista_ingre_combo.add(producto_actual);
					 	}
					 }
			 }
			 Combo new_combo = new Combo (descuento2, nombre, lista_ingre_combo); 
			 listCombos.add(new_combo);
			 linea= lector.readLine() ;	 
		 }
		 
		lector.close();
		
	}
	/*
	public static void main (String[] args) throws IOException{
		File archicombos= new File("./data/combos.txt");
		File archiingre= new File("./data/ingredientes.txt");
		File archimenu= new File("./data/menu.txt");
		cargarIngredientes(archiingre);
		
		Ingrediente i_1= listIngredientes.get(4);
		int a = i_1.getCostoAdicional();
		System.out.println(a);
		
		cargarMenu(archimenu);
		ProductoMenu i2= listMenu.get(0);
		int b = i2.getPrecio();
		//System.out.println(b);
		
		
		cargarCombos(archicombos);
		Combo i3= listCombos.get(0);
		//System.out.println(listCombos);
		int c = i3.getPrecio();
		System.out.println(c);
		//System.out.println(i3.getlista());
	} */
	
}
