package logica;

public class Ingrediente {

	//ATRIBUTOS
		private String nombre;
		private int costoAdicional;
		private int calorias;
		
	//Constructores
		public Ingrediente(String nombre, int costoAdicional, int calorias) {
			super();
			this.nombre = nombre;
			this.costoAdicional = costoAdicional;
			this.calorias= calorias;
		}		
	
	//Getters
		public String getNombre() {
			return nombre;
		}
		public int getCostoAdicional() {
			return costoAdicional;
		}

		public int getCalorias() {
			// TODO Auto-generated method stub
			return this.calorias;
		}
		
}
