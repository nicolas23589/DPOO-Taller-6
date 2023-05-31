package logica;

public class ProductoMenu implements Producto {
	//ATRIBUTOS
		private String nombre;
		private int precioBase;
		private int calorias;
	//CONSTRUCTORES
	
	public ProductoMenu(String nombre, int precioBase, int calorias) {
		super();
		this.nombre = nombre;
		this.precioBase = precioBase;
		this.calorias= calorias;
	}

	//getters (mismos metodos que la interfaz)
	@Override
	
	public int getPrecio() {
		// TODO Auto-generated method stub
		return precioBase;
	}
	
	public String getNombre() {
		// TODO Auto-generated method stub
		return nombre;
	}
	public int getCalorias() {
		// TODO Auto-generated method stub
		return calorias;
	}
	public String generarTextoFactura() {
		// TODO Auto-generated method stub
		String textoFactura= this.nombre + " ; " + Integer.toString(precioBase)
		+ "$ ; " + this.calorias + " Calorías" ;
		return textoFactura;
	}
	

	
	
}
