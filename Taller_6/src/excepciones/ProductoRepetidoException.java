package excepciones;

import logica.Ingrediente;
import logica.ProductoMenu;

public class ProductoRepetidoException extends HamburguesaException{

	private ProductoMenu productoMenu;
	private int linea;
	public ProductoRepetidoException(String mensaje, int linea, ProductoMenu productoMenu) {
		super(mensaje);
		this.productoMenu= productoMenu;
		this.linea= linea;
		// TODO Auto-generated constructor stub
	}

	@Override
public String darNombre() {
		return productoMenu.getNombre();
	}
	public void darInfo() {
		// Información adicional que no se pidió, pero puede llamarse en cualquier lugar del codigo :)
		System.out.println("A continuación encuentra información del producto repetido:");
		System.out.println("Nombre: " + productoMenu.getNombre() );
		System.out.println("Costo: " + productoMenu.getPrecio() );
		System.out.println("Calorias: " + productoMenu.getCalorias() );
		System.out.println("Linea del archivo en la que se ubica: " + String.valueOf(linea) );
	}

}
