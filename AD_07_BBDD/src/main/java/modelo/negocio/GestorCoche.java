package modelo.negocio;

import modelo.persistencia.DaoImplement;
import modelo.entidad.Coche;

import java.util.List;


public class GestorCoche {

    private DaoImplement dao = DaoImplement.getInstance();

    private static GestorCoche intance;

    private GestorCoche() {
        super();
    }

    public static GestorCoche getInstance() {
        if (intance == null) {
            intance = new GestorCoche();
        }
        return intance;
    }

    /**
     * Comprueba si todos los atributos a excepcion del Id de Coche son validos.
     *
     * @param coche a comprobar.
     * @return true si todos los atributos son validos, false en caso contrario.
     */
    public boolean isCarValid(Coche coche) {
        return coche != null && coche.getMarca() != null && !coche.getMarca().isEmpty() &&
                coche.getModelo() != null && !coche.getModelo().isEmpty() && coche.getMotor() != null &&
                coche.getKilometros() >= 0;
    }



    /**
     * Metodo que agrega un coche a la base de datos.
     *
     * @param coche que será agregado.
     * @return true en caso de que fue agregado correctamente,
     * false en caso contrario.
     */
    public boolean addCoche(Coche coche) {
        if (isCarValid(coche)) {
            int n = dao.insertCoche(coche);
            if (n == 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * Busca el id de un coche en la base de datos.
     *
     * @param id del coche a buscar.
     * @return Objeto de tipo Coche, si existe en la base de datos, null en caso contrario.
     */
    public Coche getCocheById(int id) {
        return dao.getCocheById(id);
    }

    /**
     * Metodo que devuelve un <b>ArrayList</b> de Coches
     *
     * @return Lista de coches si existe almenos 1 coche en la base de datos,
     * <b>null</b> si la base de datos esta vacia o a ocurrido un error.
     */
    public List<Coche> getAllCoches() {
        return dao.getAllCoches();
    }

    /**
     * Actualiza los datos de un coche en la base de datos.
     *
     * @param coche con los datos a actualizar.
     * @return true si los datos se actualiza correctamente, false en caso contrario.
     */
    public boolean updateCoche(Coche coche) {
        if (!isCarValid(coche)) {
            return false;
        }

        Coche c = getCocheById(coche.getId());

        if (c != null) {
            int n = dao.updateCoche(coche);
            if (n == 1) {
                return true;
            }
        }

        return false;
    }

    /**
     * Borra un coche de la base de datos mediante su id.
     *
     * @param id del coche a borrar.
     * @return true si el coche fue borrado, false en caso contrario.
     */
    public boolean deleteCocheById(int id) {

        return dao.deleteCocheById(id);
    }

    /**
     * Retorna todos los coches que tengan la misma Marca guardados en la base de datos.
     *
     * @param marca de los coches a buscar.
     * @return Lista de Coches, si existe en la base de datos, null en caso contrario.
     */
    public List<Coche> getCochesByMarca(String marca) {

        return dao.getCochesByMarca(marca);
    }

}
