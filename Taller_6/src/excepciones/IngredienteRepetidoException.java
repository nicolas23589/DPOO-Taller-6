package excepciones;


import logica.Ingrediente;


public class IngredienteRepetidoException extends HamburguesaException{
	private Ingrediente ingrediente;
	private int linea;
	public IngredienteRepetidoException(String mensaje, int linea, Ingrediente ingrediente) {
		super(mensaje);
		this.ingrediente= ingrediente;
		this.linea= linea;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String darNombre() {
		return ingrediente.getNombre();
	}
	
	public void darInfo() {
		// TODO Auto-generated method stub
		System.out.println("A continuación encuentra información del ingrediente repetido:");
		System.out.println("Nombre: " + ingrediente.getNombre() );
		System.out.println("Costo: " + ingrediente.getCostoAdicional() );
		System.out.println("Calorias: " + ingrediente.getCalorias() );
		System.out.println("Linea del archivo en la que se ubica: " + String.valueOf(linea) );
	}

}
