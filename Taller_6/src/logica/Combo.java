package logica;
import java.util.List;

public class Combo implements Producto{

	//ATRIBUTOS
	
	private String nombreCombo;
	private Double descuento;
	
	private List<Producto> listaProductos;
	
	//CONSTRUCTORES
	public Combo(Double descuento, String nombreCombo, List<Producto> listaProductos) {
		super();
		this.descuento = descuento;
		this.nombreCombo = nombreCombo;
		this.listaProductos= listaProductos;
	}
	
	//METODOS
	public void agregarItemsACombo(ProductoMenu itemCombo) {
		this.listaProductos.add(itemCombo);

	}
	@Override
	public int getPrecio() {
		// TODO Auto-generated method stub
		int precio_parcial= 0;
		for (int j=0;j<this.listaProductos.size();j++) {

			precio_parcial= precio_parcial + (listaProductos.get(j)).getPrecio();
			
		}
		precio_parcial= precio_parcial+5000;
		int precio_final = (int) (precio_parcial - (Double) (precio_parcial*this.descuento*0.01));
		return precio_final;
	}
	public int getCalorias() {
		// TODO Auto-generated method stub
		int precio_parcial= 0;
		for (int j=0;j<this.listaProductos.size();j++) {
			precio_parcial= precio_parcial + (listaProductos.get(j)).getCalorias();
		}
		precio_parcial+= 400;
		return precio_parcial;
	}

	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return this.nombreCombo;
	}
	public List<Producto> getlista() {
		return this.listaProductos;
	}

	@Override
	public String generarTextoFactura() {
		// TODO Auto-generated method stub

			String textoFactura= this.nombreCombo + " ; " + Integer.toString(getPrecio() )
					+ "$ ; "  + Integer.toString(getCalorias()) + " Calorias";
			return textoFactura;
		}
	}
	

	
	


