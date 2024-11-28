package modelo.dao;

import modelo.entidad.Coche;
import modelo.entidad.Motor;
import interfaz.DaoInterfaz;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoImplement implements DaoInterfaz {

    private String cadenaConexion = "jdbc:mysql://localhost:3306/bbdd";
    private String user = "root";
    private String pass = "";

    private static DaoImplement intance;

    private DaoImplement() {
        super();
    }

    public static DaoImplement getInstance() {
        if (intance == null) {
            intance = new DaoImplement();
        }
        return intance;
    }

    /**
     * Metodo que agrega un coche a la base de datos
     * @param coche que será agregado.
     * @return 1 en caso de que fue agregado correctamente,
     * 0 en caso contrario.
     */
    @Override
    public int insertCoche(Coche coche) {
        int rowsAffected = 0;

        try (Connection con = DriverManager.getConnection(cadenaConexion, user, pass);) {
            String sql = "INSERT INTO COCHES (MARCA, MODELO, MOTOR, KILOMETROS) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, coche.getMarca());
            ps.setString(2, coche.getModelo());
            ps.setString(3, coche.getMotor().toString());
            ps.setDouble(4, coche.getKilometros());

            rowsAffected = ps.executeUpdate();

            return rowsAffected;
        } catch (SQLException e) {

        }
        return rowsAffected;
    }


    /**
     * Metodo que devuelve un <b>ArrayList</b> de Coches
     *
     * @return Lista de coches si existe almenos 1 coche en la base de datos,
     * <b>null</b> si la base de datos esta vacia o a ocurrido un error.
     */
    @Override
    public List<Coche> getAllCoches() {
        List<Coche> listaCoches = new ArrayList<Coche>();

        try (Connection con = DriverManager.getConnection(cadenaConexion, user, pass);) {
            String sql = "SELECT * FROM COCHES";

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Coche coche = new Coche();
                coche.setId(rs.getInt("ID"));
                coche.setMarca(rs.getString("MARCA"));
                coche.setModelo(rs.getString("MODELO"));
                coche.setMotor(Motor.valueOf(rs.getString("MOTOR").toString()));
                coche.setKilometros(rs.getDouble("KILOMETROS"));

                listaCoches.add(coche);
            }
            if (!listaCoches.isEmpty()) {
                return listaCoches;
            } else {
                return null;
            }


        } catch (SQLException e) {
            return null;
        }

    }

    /**
     * Borra un coche de la base de datos mediante su id.
     *
     * @param id del coche a borrar.
     * @return true si el coche fue borrado, false en caso contrario.
     */
    @Override
    public boolean deleteCocheById(int id) {
        int rowsAffected = 0;
        boolean isDeleted = false;
        try (Connection con = DriverManager.getConnection(cadenaConexion, user, pass);) {
            String sql = "DELETE FROM COCHES WHERE ID =?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                isDeleted = true;
            }

        } catch (SQLException e) {

        }
        return isDeleted;

    }

    /**
     * Metodo que actualiza un coche en la base de datos.
     *
     * @param coche con sus datos ya modificados.
     * @return 1 en caso de que fue actualizado correctamente,
     * 0 en caso contrario.
     */
    @Override
    public int updateCoche(Coche coche) {
        int rowsAffected = 0;
        try (Connection con = DriverManager.getConnection(cadenaConexion, user, pass);) {
            String sql = "UPDATE COCHES SET MARCA=?, MODELO=?, MOTOR=?, KILOMETROS=? WHERE ID=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, coche.getMarca());
            ps.setString(2, coche.getModelo());
            ps.setString(3, coche.getMotor().toString());
            ps.setDouble(4, coche.getKilometros());
            ps.setInt(5, coche.getId());

            rowsAffected = ps.executeUpdate();

            return rowsAffected;
        } catch (SQLException e) {

        }
        return rowsAffected;
    }

    /**
     * Busca el id de un coche en la base de datos.
     *
     * @param id del coche a buscar.
     * @return Objeto de tipo Coche, si existe en la base de datos, null en caso contrario.
     */
    @Override
    public Coche getCocheById(int id) {
        Coche coche = null;
        try (Connection con = DriverManager.getConnection(cadenaConexion, user, pass);) {
            String sql = "SELECT * FROM COCHES WHERE ID = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                coche = new Coche();
                coche.setId(rs.getInt("ID"));
                coche.setMarca(rs.getString("MARCA"));
                coche.setModelo(rs.getString("MODELO"));
                coche.setMotor(Motor.valueOf(rs.getString("MOTOR").toString()));
                coche.setKilometros(rs.getDouble("KILOMETROS"));
            }
        } catch (SQLException e) {

        }
        return coche;
    }


    /**
     * Retorna todos los coches que tengan la misma Marca guardados en la base de datos.
     *
     * @param marca de los coches a buscar.
     * @return Lista de Coches, si existe almenos 1 en la base de datos, null en caso contrario.
     */
    @Override
    public List<Coche> getCochesByMarca(String marca) {
        List<Coche> listaCoches = getAllCoches();
        List<Coche> listaCochesPorMarca = new ArrayList<>();

        if (listaCoches != null) {
            for (Coche coche : listaCoches) {
                if (coche.getMarca().equalsIgnoreCase(marca)) {
                    listaCochesPorMarca.add(coche);
                }
            }
        } else {
            return null;
        }

        if (listaCochesPorMarca.isEmpty()) {
            return null;
        }

        return listaCochesPorMarca;
    }


}
