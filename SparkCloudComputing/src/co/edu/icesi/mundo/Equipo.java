package co.edu.icesi.mundo;

public class Equipo {

	
	private String nombreEquipo;
	private int cantidadTitulos;
	private int aņoFundacion;


	public Equipo(String nombre, int titulos,int fundacion){
		this.nombreEquipo=nombre;
		this.cantidadTitulos=titulos;
		this.aņoFundacion=fundacion;
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

	public int getAņoFundacion() {
		return aņoFundacion;
	}

	public void setAņoFundacion(int aņoFundacion) {
		this.aņoFundacion = aņoFundacion;
	}
	
}
