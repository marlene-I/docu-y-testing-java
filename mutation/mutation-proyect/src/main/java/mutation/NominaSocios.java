package mutation;

import java.util.ArrayList;

/**
 * @file NominaSocios.java
 * @brief La clase NominaSocios representa la lista de socios registrados en el
 *        club.
 * @date 2023-12-18
 * 
 * @details La nómina es estática, para permitir centralizar y compartir
 *          globalmente la lista de socios,
 *          y datos asociados.
 */
public class NominaSocios {

	/**
	 * @brief Lista que almacena a todos los socios registrados en el club.
	 */
	static ArrayList<Socio> nomina = new ArrayList<>();

	/**
	 * @brief Índice que lleva el seguimiento del próximo identificador de socio a
	 *        asignar.
	 */
	static int proxSocio;

	/***
	 * 
	 * @param p Persona a asociar. Si la persona ya existe, no se efectiviza el alta
	 *          y se lanza una excepcion
	 * @author oscar
	 * @throws EdadInsuficienteException
	 */
	public static void Asociar(Persona p) throws YaExisteSocioException, EdadInsuficienteException {
		Socio s = new Socio(p);
		if (nomina == null)
			nomina = new ArrayList<Socio>();
		if (p.getEdad() < 16)
			throw new EdadInsuficienteException();
		if (!NominaSocios.YaExisteEnNomina(p)) {
			s.setIdSocio(proxSocio);
			proxSocio++;
			nomina.add(s);
		} else
			throw new YaExisteSocioException();
	}

	/**
	 * Obtiene el id Autonumérico del próximo socio
	 */
	public static int getNextID() {
		return proxSocio;
	}

	/**
	 * Indica si la persona existe en la nómina según su valor autonumérico
	 * 
	 * @param p Persona a buscar
	 */
	public static boolean YaExisteEnNomina(Persona p) {
		for (int i = 0; i < nomina.size(); i++)
			if (p.getDni() == nomina.get(i).getPersona().getDni())
				return true;
		return false;
	}

	/***
	 * Da de baja un socio específíco
	 * 
	 * @param s Socio a dar de baja
	 * @throws NoExisteSocioException
	 */
	public static void Baja(Socio s) throws NoExisteSocioException {

		if (nomina.contains(s))
			nomina.remove(s);
		else
			throw new NoExisteSocioException();
	}

	/***
	 * Cuenta la cantidad de socios en la nómina
	 * 
	 * @return cantidad d socios
	 */
	public static int ContarSocios() {
		return proxSocio;
	}

	public static ArrayList<Socio> GetNomina() {
		return nomina;
	}

	public static void limpiarNomina(){
		nomina = new ArrayList<>();
		proxSocio = 0;
	}
}
