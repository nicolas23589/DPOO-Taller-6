package excepciones;

public abstract class HamburguesaException extends Exception{
	
	private int linea;
	
	public HamburguesaException (String mensaje) {
		super(mensaje);

	
	}
	abstract void darInfo();
	public String darNombre() {
		// TODO Auto-generated method stub
		return null;
	}

}
