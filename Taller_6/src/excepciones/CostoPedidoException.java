package excepciones;

import java.util.ArrayList;

import logica.Pedido;
import logica.Producto;

public class CostoPedidoException extends HamburguesaException{
	private ArrayList<Producto> lista_productos;
	private int precioNeto;
	private double precioIva;
	private double precioTotal;
	
	
	public CostoPedidoException(String mensaje, ArrayList<Producto> lista_productos, int precioNeto, double precioIva, double precioTotal) {
		super(mensaje);
		this.lista_productos= lista_productos;
		this.precioIva= precioIva;
		this.precioNeto= precioNeto;
		this.precioTotal= precioTotal;
	
		// TODO Auto-generated constructor stub
	}

	@Override
	
	public void darInfo() {
		
		System.out.println("A continuación encuentra información de los productos que produjeron el sobrecosto:");
		for (int i=0; i<lista_productos.size(); i++) {
			System.out.println(lista_productos.get(i).getNombre() + ": " + lista_productos.get(i).getPrecio());
		}
		System.out.print("Precio Neto: " + String.valueOf(precioNeto));
		System.out.print("Precio Iva: " + String.valueOf(precioIva));
		System.out.print("Precio Total: " + String.valueOf(precioTotal));

	}

}
