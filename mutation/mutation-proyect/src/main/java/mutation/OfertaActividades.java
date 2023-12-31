package mutation;

import java.util.ArrayList;

/**
 * @file OfertaActividades.java
 * 
 * @brief La clase OfertaActividades gestiona la oferta de actividades en el
 *        club.
 * 
 * @date 2023-12-18
 *
 * @details La clase centraliza las operaciones relacionadas con las
 *          actividades, simplificando su gestión
 *          y proporcionando un punto único de control en la aplicación.
 */
public class OfertaActividades {
	/**
	 * @brief Lista estática que almacena las actividades disponibles en el club.
	 */
	static ArrayList<Actividad> nomina;

	/**
	 * @brief Añade una nueva actividad a la lista de actividades disponibles.
	 * @param act Actividad a ser añadida.
	 */
	public static void NuevaActividad(Actividad act) {
		if (nomina == null)
			nomina = new ArrayList<Actividad>();
		if (nomina.indexOf(act) < 0)
			nomina.add(act);
	}

	/**
	 * @brief Elimina una actividad de la lista de actividades disponibles.
	 * @param act Actividad a ser eliminada.
	 */
	static void BorrarActividad(Actividad act) {

		if (nomina.indexOf(act) < 0)
			nomina.remove(act);
	}

	/**
	 * @brief Obtiene la cantidad de actividades disponibles.
	 * @return Cantidad de actividades disponibles.
	 */
	public static int CantidadActividades() {
		return nomina.size();
	}

	/**
	 * @brief Obtiene la lista de actividades disponibles.
	 * @return Lista de actividades disponibles.
	 */
	public static ArrayList<Actividad> GetNominaActividades() {
		return nomina;
	}

	public static Actividad getActividadPorNombre(String nombreActividad){
		for (Actividad actividad : nomina) {
			if (actividad.getNombre().equals(nombreActividad)){
				return actividad;
			}
		}
		return null;
	}

}