package co.edu.icesi.mundo;

public class Equipo {

	
	private String nombreEquipo;
	private int cantidadTitulos;
	private int a�oFundacion;


	public Equipo(String nombre, int titulos,int fundacion){
		this.nombreEquipo=nombre;
		this.cantidadTitulos=titulos;
		this.a�oFundacion=fundacion;
	}

	public String getNombreEquipo() {
		return nombreEquipo;
	}

	public void setNombreEquipo(String nombreEquipo) {
		this.nombreEquipo = nombreEquipo;
	}

	public int getCantidadTitulos() {
		return cantidadTitulos;
	}

	public void setCantidadTitulos(int cantidadTitulos) {
		this.cantidadTitulos = cantidadTitulos;
	}

	public int getA�oFundacion() {
		return a�oFundacion;
	}

	public void setA�oFundacion(int a�oFundacion) {
		this.a�oFundacion = a�oFundacion;
	}
	
}
