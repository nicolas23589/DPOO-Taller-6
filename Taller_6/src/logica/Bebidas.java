package logica;

public class Bebidas implements Producto {
	//ATRIBUTOS
		private String nombre;
		private int precioBase;
		private int calorias;
	//CONSTRUCTORES
	
	public Bebidas(String nombre, int precioBase, int calorias) {
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
	public String generarTextoFactura() {
		// TODO Auto-generated method stub
		String textoFactura= this.nombre + " ; " + Integer.toString(precioBase)
		+ "$ ; " + this.calorias + " Calorías" ;
		return textoFactura;
	}

	@Override
	public int getCalorias() {
		// TODO Auto-generated method stub
		return calorias;
	}
	

	
	
}
