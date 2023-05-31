package consola;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import excepciones.CostoPedidoException;
import excepciones.IngredienteRepetidoException;
import excepciones.ProductoRepetidoException;
import logica.Restaurante;
import logica.Ingrediente;
import logica.Pedido;
import logica.Producto;
import logica.ProductoAjustado;
import logica.ProductoMenu;

public class Aplicacion {	
	
	public static void imprimirProdcutosMenu() {
		System.out.println("  ");
		System.out.println("PRODUCTOS DEL MENÚ");
		for (int j=0;j<Restaurante.getMenuBase().size();j++) {
			System.out.println("  ");
			System.out.print(j + ". ");
			System.out.print(Restaurante.getMenuBase().get(j).getNombre());
			System.out.print(" -> ");
			System.out.print(Restaurante.getMenuBase().get(j).getPrecio());
		}	
		System.out.println("  ");
	}
	public static void imprimirBebidas() {
		System.out.println("  ");
		System.out.println("BEBIDAS");
		for (int j=0;j<Restaurante.getBebidas().size();j++) {
			System.out.println("  ");
			System.out.print(j + ". ");
			System.out.print(Restaurante.getBebidas().get(j).getNombre());
			System.out.print(" -> ");
			System.out.print(Restaurante.getBebidas().get(j).getPrecio());
		}	
		System.out.println("  ");
	}
	
	public static void imprimirCombos() {
		System.out.println("  ");
		System.out.println("COMBOS");
		for (int j=0;j<Restaurante.getCombos().size();j++) {
			System.out.println("  ");
			System.out.print(j+ ". " );
			System.out.print(Restaurante.getCombos().get(j).getNombre());
			System.out.print(" -> ");
			System.out.print(Restaurante.getCombos().get(j).getPrecio());
		}
		System.out.println("  ");
	}
	
	public static void imprimirIngredientes() {
		System.out.println("  ");
		System.out.println("INGREDIENTES ADICIONALES");
		for (int j=0;j<Restaurante.getIngredientes().size();j++) {
			System.out.println("  ");
			System.out.print(j + ". ");
			System.out.print(Restaurante.getIngredientes().get(j).getNombre());
			System.out.print(" -> ");
			System.out.print(Restaurante.getIngredientes().get(j).getCostoAdicional());
		}
		System.out.println("  ");
	}
	public static void imprimirMenuPrincipal () {
		System.out.println("\n" + "Bienvenido a El Corral");
		System.out.println("Que desea hacer?");
		System.out.println("1. Ver el Menú");
		System.out.println("2. Hacer un nuevo Pedido");
		System.out.println("3. Consultar información de un pedido");
		System.out.println("4. Salir");
	}
	
	
	
	//METODO MAIN

	
	public static void main(String[] args) throws IOException {
		//CARGA DE DATOS
		File archicombos= new File("./data/combos.txt");
		File archiingre= new File("./data/ingredientes.txt");
		File archimenu= new File("./data/menu.txt");
		File archiBebidas= new File("./data/bebidas.txt");
		try {
			Restaurante.cargarInformacionRestaurante(archiingre,archimenu,archicombos, archiBebidas);
		} 
		catch (IngredienteRepetidoException e) {
			
			System.out.println(e.getMessage());
			System.out.println("Nombre: " + e.darNombre());
			e.printStackTrace();
			System.out.println("corrija el error y vuelva a intentarlo");
			System.exit(0);
		} catch (ProductoRepetidoException e) {
			
			System.out.println(e.getMessage());
			System.out.println("Nombre: " + e.darNombre());
			e.printStackTrace();
			System.out.println("corrija el error y vuelva a intentarlo");
			System.exit(0);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		//IMPRESION DEL MENU PRINCIPAL
		
		imprimirMenuPrincipal();
		Scanner scaner = new Scanner (System.in);
		//ELECCION DE OPCION PRINCIPAL
		int opcionPrincipal = scaner.nextInt();
			//IMPRESION DEL PEDIDO
			if (opcionPrincipal== 1) {
				imprimirProdcutosMenu();
				imprimirCombos();
				imprimirIngredientes();
				imprimirBebidas();
				main(args);
				}
			//CREACION DE NUEVO PEDIDO
			else if (opcionPrincipal== 2) 
			{
				
				System.out.println("Digite su nombre");
				String nombreCliente= scaner.next();
				System.out.println("Digite su dirección");
				String direccionCliente= scaner.next();
				Pedido pedidoActual= new Pedido(nombreCliente, direccionCliente);
				
				Boolean continuarPedido= true;
				while (continuarPedido==true)
				{
					System.out.println("Que desea agregar al pedido? (digite un número de 1 a 4)");
					System.out.println("1. Un Producto del menú");
					System.out.println("2. Un Combo");
					System.out.println("3. Un Producto del menú modificado");
					System.out.println("4. Una Bebida");
					int opcionPedido= scaner.nextInt();
					
					if (opcionPedido==1) {
						imprimirProdcutosMenu();
						System.out.println("digite el número del producto que desea (ej: 0, 1, 2, etc.)");
						int opcionProductoMenu= scaner.nextInt();
						try {
							pedidoActual.agregarProducto( (Restaurante.listMenu.get(opcionProductoMenu)));
						} catch (CostoPedidoException e) {
							System.out.println(e.getMessage());
							e.darInfo();
							e.printStackTrace();
							System.out.println("corrija el error y vuelva a intentarlo");
							System.exit(0);
						}
						System.out.print("Desea agregar algo más al pedido?(digite true/false)");
						continuarPedido= scaner.nextBoolean();
						}
					
					else if (opcionPedido==2) {
						imprimirCombos();
						System.out.println("digite el número del combo que desea (ej: 0, 1, 2, etc.)");
						int opcionCombo= scaner.nextInt();
						try {
							pedidoActual.agregarProducto(Restaurante.listCombos.get(opcionCombo));
						} catch (CostoPedidoException e) {
							System.out.println(e.getMessage());
							e.darInfo();
							e.printStackTrace();
							System.out.println("corrija el error y vuelva a intentarlo");
							System.exit(0);
						}
						System.out.print("Desea agregar algo más al pedido?(digite true/false)");
						continuarPedido= scaner.nextBoolean();
						}
					else if (opcionPedido==3) {
						ArrayList<Ingrediente> listAgregados= new ArrayList<Ingrediente>();
						ArrayList<Ingrediente> listEliminados= new ArrayList<Ingrediente>();
						imprimirProdcutosMenu();
						System.out.println("digite el número del producto que desea modificar (ej: 0, 1, 2, etc.)");
						int opcionProductoMenu= scaner.nextInt();
						ProductoMenu base= Restaurante.getMenuBase().get(opcionProductoMenu);
						System.out.println("desea eliminar algún ingrediente?");
						Boolean opcionEliminar= scaner.nextBoolean();
						if (opcionEliminar== true) 
							{
							//ELIMINAR
							Boolean seguirEliminando= true;
							
							while (seguirEliminando==true) 
								{
								imprimirIngredientes();
								System.out.print("Seleccione el número del ingrediente que desea eliminar");
								int numeroEliminado= scaner.nextInt();
								listEliminados.add(Restaurante.listIngredientes.get(numeroEliminado));
								System.out.println("desea eliminar algún otro ingrediente?(digite true/false)");
								seguirEliminando= scaner.nextBoolean();
								}
							}
						System.out.println("desea agregar algún ingrediente?");
						Boolean opcionAgregar= scaner.nextBoolean();
						if (opcionAgregar== true) 
						{
						//AGREGAR
						Boolean seguirAgregando= true;
						
						while (seguirAgregando==true) 
							{
							imprimirIngredientes();
							System.out.println("Seleccione el número del ingrediente que desea agregar");
							int numeroAgregado= scaner.nextInt();
							listAgregados.add(Restaurante.listIngredientes.get(numeroAgregado));
							System.out.println("desea agregar algún otro ingrediente? (digite true/false)");
							seguirAgregando= scaner.nextBoolean();
							}
						}
						ProductoAjustado pAjustado= new ProductoAjustado (base,listAgregados , listEliminados);
						try {
							pedidoActual.agregarProducto(pAjustado);
						} catch (CostoPedidoException e) {
							System.out.println(e.getMessage());
							e.darInfo();
;							e.printStackTrace();
						}
						System.out.println("Desea agregar algo más al pedido?(digite true/false)");
						continuarPedido= scaner.nextBoolean();
						}
					else if (opcionPedido==4) {
						imprimirBebidas();
						System.out.println("digite el número de la que desea (ej: 0, 1, 2, etc.)");
						int opcionProductoMenu= scaner.nextInt();
						try {
							pedidoActual.agregarProducto(Restaurante.listBebidas.get(opcionProductoMenu));
						} catch (CostoPedidoException e) {
							System.out.println(e.getMessage());
							e.darInfo();
;							e.printStackTrace();
						}
						System.out.print("Desea agregar algo más al pedido?(digite true/false)");
						continuarPedido= scaner.nextBoolean();
						}
					}
				
				
				
				for (int i=0; i<Restaurante.listPedidos.size(); i++) {
					System.out.println("a");
					if ((pedidoActual.getPrecioNetoPedido())==(Restaurante.listPedidos.get(i).getPrecioNetoPedido()))
							{
							System.out.println( "notificación: alguien ya hizo el mismo pedido");
							}
					else {
						System.out.print("es el primer pedido, no hay ninguno igual");
							}
				}
				Restaurante.cerrarYGuardarPedido(pedidoActual);
				System.out.println("\n" +"Recuerde el id de su pedido para consultarlo más tarde");
				System.out.println("El id de su pedido es: " + pedidoActual.getIdPedido());
				main(args);
				}
			else if (opcionPrincipal== 3) {
				Boolean encontrado= false;
				System.out.println("escriba el ID del pedido");
				int idpedido= scaner.nextInt();
				for (int i=0; i<Restaurante.listPedidos.size();i++) {
					Pedido pedidoActual=Restaurante.listPedidos.get(i);
					if (idpedido== pedidoActual.getIdPedido())
						{
						encontrado= true;
						System.out.println("Se ha encontrado el pedido que busca, su información se muestra a continuación");
						System.out.println("id -> " + pedidoActual.getIdPedido());
						System.out.println("Nombre -> " + pedidoActual.getNombreCliente());
						System.out.println("Dirección -> " + pedidoActual.getDireccionCliente());
						ArrayList<String> textoFactura = pedidoActual.generarTextoFactura();
						for (int j = 0; j<textoFactura.size(); j++) {
							System.out.println(textoFactura.get(j));
							}
						
						}
					
				}
				if (encontrado==false){
					System.out.println("no se encontró la información, es posible que haya cerrado"
							+ " el programa después de haber hecho su pedido, o que no se haya hecho el pedido");
				}
				main(args);
			}
				
				
			} 
			
	}
	

