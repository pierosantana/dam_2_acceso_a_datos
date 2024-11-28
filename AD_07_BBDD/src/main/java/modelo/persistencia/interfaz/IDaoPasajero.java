package modelo.persistencia.interfaz;

import java.util.List;

import modelo.entidad.Pasajero;


public interface IDaoPasajero {
	
	int insertPasajero(Pasajero pasajero);

	Pasajero getPasajeroById(int id);
	
	List<Pasajero> getAllPasajeros();

	boolean deletePasajeroById(int id);

	int updatePasajero(Pasajero pasajero);

}
