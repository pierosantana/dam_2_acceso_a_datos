package modelo.persistencia.interfaz;

import java.util.List;

import modelo.entidad.Coche;

public interface IDaoCoche {

	int insertCoche(Coche coche);

	Coche getCocheById(int id);
	
	List<Coche> getCochesByMarca(String marca);
	
	List<Coche> getAllCoches();

	boolean deleteCocheById(int id);

	int updateCoche(Coche coche);


}
