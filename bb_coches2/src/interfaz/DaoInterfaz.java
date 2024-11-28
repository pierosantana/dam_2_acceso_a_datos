package interfaz;

import modelo.entidad.Coche;

import java.util.List;

public interface DaoInterfaz {

    int insertCoche(Coche coche);
    List<Coche> getAllCoches();
    boolean deleteCocheById(int id);
    int updateCoche(Coche coche);
    Coche getCocheById(int id);
    List<Coche> getCochesByMarca(String marca);


}
