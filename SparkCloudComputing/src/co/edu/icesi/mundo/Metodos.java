package co.edu.icesi.mundo;

import static spark.Spark.*;
import spark.Spark;
import java.util.ArrayList;
import java.util.Properties;
import com.google.gson.Gson;

public class Metodos {

	static ArrayList<Equipo> equipos = new ArrayList<Equipo>();

	public static void main(String[] args) {
	
		//Enable CORS
        Spark.options("/*", (request, response) ->
        {

            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null)
            {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null)
            {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });

        Spark.before((request, response) ->
        {
            response.header("Access-Control-Allow-Origin", "*");
        });
        
        
		inicializarEquipos();
		metodosHTTP();
	}

	public static void inicializarEquipos() {

		Equipo ClubDeportivoCali = new Equipo("Deportivo_Cali", 9, 1912);
		Equipo Nacional = new Equipo("Atletico_Nacional", 15, 1947);
		Equipo SantaFe = new Equipo("Independiente_SantaFe", 9, 1941);
		Equipo Tolima = new Equipo("Deportes_Tolima", 1, 1954);
		Equipo Pasto = new Equipo("Deportivo_Pasto", 1, 1949);
		Equipo Equidad = new Equipo("La_Equidad", 0, 1982);
		Equipo Quindio = new Equipo("Deportes_Quindio", 1, 1951);
		Equipo Junior = new Equipo("Junior_de_Barranquilla", 7, 1924);
		Equipo Medellin = new Equipo("Independiente_Medellin", 6, 1913);
		Equipo OnceCaldas = new Equipo("Once_Caldas", 4, 1947);
		Equipo America = new Equipo("America", 13, 1927);
		Equipo Chico = new Equipo("Chico", 1, 2002);
		Equipo Envigado = new Equipo("Envigado", 1, 1989);
		Equipo Millonarios = new Equipo("Millonarios", 15, 1946);

		equipos.add(ClubDeportivoCali);
		equipos.add(Nacional);
		equipos.add(SantaFe);
		equipos.add(Junior);
		equipos.add(America);
		equipos.add(Quindio);
		equipos.add(Medellin);
		equipos.add(OnceCaldas);
		equipos.add(Tolima);
		equipos.add(Pasto);
		equipos.add(Equidad);
		equipos.add(Chico);
		equipos.add(Envigado);
		equipos.add(Millonarios);

	}

	public static void metodosHTTP() {

		Gson g = new Gson();

		get("/listaEquipos", (request, response) -> equipos, g::toJson);

		// CODIGO ANTES DE SER LLAMADO POR CLIENTE WEB
		/*
		 * String listaEquipos = ""; for (int i = 0; i < equipos.size(); i++) {
		 * listaEquipos += equipos.get(i).getNombreEquipo() + "\n"; } if
		 * (listaEquipos.equalsIgnoreCase("")) { return
		 * "No hay equipos por mostrar"; } else { return
		 * "Los equipos del futbol profesional colombiano son: \n" +
		 * listaEquipos; }
		 */

		get("/informacion/:nombreEquipo", (request, response) -> {

			String nombreEquipo = request.params(":nombreEquipo");
			String informacion = "";

			for (int i = 0; i < equipos.size() && informacion.equalsIgnoreCase(""); i++) {
				if (equipos.get(i).getNombreEquipo().equals(nombreEquipo)) {
					informacion = "Información Equipo" + "\n" + "Nombre " + equipos.get(i).getNombreEquipo() + "\n"
							+ "Cantidad de títulos en la liga colombiana: " + equipos.get(i).getCantidadTitulos() + "\n"
							+ "Año de fundación: " + equipos.get(i).getAñoFundacion();
				}
			}
			if (informacion.equals("")) {
				return "No existe el equipo";
			}
			return informacion;
		});

		post("/crearEquipo", (request, response) -> {

			String respuesta = "No se completo la creacion";
			String body = request.body();

			final Properties p = new Gson().fromJson(body, Properties.class);
			String nombre = p.getProperty("nombre");
			String titulos = p.getProperty("titulos");
			String fundacion = p.getProperty("fundacion");

			int titulosE = Integer.parseInt(titulos);
			int añoFundacion = Integer.parseInt(fundacion);

			if (nombre != null && !nombre.equalsIgnoreCase("")) {
				Equipo encontrado = null;
				for (int i = 0; i < equipos.size() && encontrado == null; i++) {
					if (equipos.get(i).getNombreEquipo().equalsIgnoreCase(nombre)) {
						encontrado = equipos.get(i);
					}
				}
				if (encontrado != null) {
					System.out.println("es null");
					respuesta = "El equipo " + nombre + " ya existe";
				} else {
					Equipo equip = new Equipo(nombre, titulosE, añoFundacion);
					equipos.add(equip);
					respuesta = nombre + " fue agregado como equipo exitosamente";
				}
			}
			return respuesta;
		});

		delete("/borrarEquipo/:nombre", (request, response) -> {

			String nombre = request.params(":nombre");

			for (int i = 0; i < equipos.size(); i++) {
				if (equipos.get(i).getNombreEquipo().equalsIgnoreCase(nombre)) {
					equipos.remove(i);
					return nombre + " fue eliminado de la lista de equipos";
				}
			}
			return "No se pudo borrar el equipo";
		});

		put("/editarEquipo/:nombre", (request, response) -> {

			String respuesta = "No se edito el equipo";

			String nombre = request.params(":nombre");
			String body = request.body();

			final Properties p = new Gson().fromJson(body, Properties.class);
			String nombre2 = p.getProperty("nombre");
			String titulos = p.getProperty("titulos");
			String fundacion = p.getProperty("fundacion");

			int copas = Integer.parseInt(titulos);
			int fun = Integer.parseInt(fundacion);

			Equipo encontrado = null;
			for (int i = 0; i < equipos.size() && encontrado == null; i++) {
				if (equipos.get(i).getNombreEquipo().equalsIgnoreCase(nombre)) {
					encontrado = equipos.get(i);
				}
			}
			if (encontrado != null) {
				encontrado.setNombreEquipo(nombre2);
				encontrado.setCantidadTitulos(copas);
				encontrado.setAñoFundacion(fun);

				respuesta = "El equipo " + nombre + " fue editado exitosamente";
			} else {
				respuesta = "El equipo " + nombre + " no existe";
			}
			return respuesta;
		});
	}

}
