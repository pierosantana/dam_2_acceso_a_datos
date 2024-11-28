package modelo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crearbbdd.ConfigLoader;
import modelo.entidad.Coche;
import modelo.entidad.Motor;
import modelo.entidad.Pasajero;
import modelo.persistencia.interfaz.IDaoPasajero;

public class DaoPasajeroImplement implements IDaoPasajero{
	
	ConfigLoader config = new ConfigLoader();
	
	private String cadenaConexion = config.getProperty("cadenaConexion");
	private String user = config.getProperty("user");
	private String pass = config.getProperty("pass");
	

	private static DaoPasajeroImplement intance;
	
	
	private DaoPasajeroImplement() {
		super();
	}

	public static DaoPasajeroImplement getInstance() {
		if (intance == null) {
			intance = new DaoPasajeroImplement();
		}
		return intance;
	}
	
	@Override
	public int insertPasajero(Pasajero pasajero) {
		int rowsAffected = 0;

		try (Connection con = DriverManager.getConnection(cadenaConexion, user, pass);) {
			String sql = "INSERT INTO PASAJEROS (NOMBRE, EDAD, PESO) VALUES (?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, pasajero.getNombre());
			ps.setInt(2, pasajero.getEdad());
			ps.setDouble(3, pasajero.getPeso());
			

			rowsAffected = ps.executeUpdate();

			return rowsAffected;
		} catch (SQLException e){

		}
		return rowsAffected;
	}

	@Override
	public Pasajero getPasajeroById(int id) {
		
		Pasajero pasajero = null;
		
		try (Connection con = DriverManager.getConnection(cadenaConexion, user, pass);) {
			String sql = "SELECT * FROM PASAJEROS WHERE ID = ?";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				pasajero = new Pasajero();
				
				pasajero.setId(rs.getInt("ID"));
				pasajero.setNombre(rs.getString("NOMBRE"));
				pasajero.setEdad(rs.getInt("EDAD"));
				pasajero.setPeso(rs.getDouble("PESO"));
				pasajero.setIdCoche(rs.getInt("IDCOCHE"));
			}
		} catch (SQLException e) {

		}
		return pasajero;
	}

	@Override
	public List<Pasajero> getAllPasajeros() {
		List<Pasajero> listaPasajero = new ArrayList<Pasajero>();

		try (Connection con = DriverManager.getConnection(cadenaConexion, user, pass);) {
			String sql = "SELECT * FROM PASAJEROS";

			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Pasajero pasajero = new Pasajero();
				pasajero.setId(rs.getInt("ID"));
				pasajero.setNombre(rs.getString("NOMBRE"));
				pasajero.setEdad(rs.getInt("EDAD"));
				pasajero.setPeso(rs.getDouble("PESO"));
				pasajero.setIdCoche(rs.getInt("IDCOCHE"));
				

				listaPasajero.add(pasajero);
			}
			if (!listaPasajero.isEmpty()) {
				return listaPasajero;
			} else {
				return null;
			}

		} catch (SQLException e) {
			return null;
		}

	}

	@Override
	public boolean deletePasajeroById(int id) {
		int rowsAffected = 0;
		boolean isDeleted = false;
		try (Connection con = DriverManager.getConnection(cadenaConexion, user, pass);) {
			String sql = "DELETE FROM PASAJEROS WHERE ID =?";

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
	

	public int updatePasajero(Pasajero pasajero) {
		int rowsAffected = 0;
		try (Connection con = DriverManager.getConnection(cadenaConexion, user, pass);) {
			String sql = "UPDATE PASAJEROS SET NOMBRE=?, EDAD=?, PESO=?, IDCOCHE=? WHERE ID=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, pasajero.getNombre());
			ps.setInt(2, pasajero.getEdad());
			ps.setDouble(3, pasajero.getPeso());
			ps.setInt(4, pasajero.getIdCoche());
			ps.setInt(5, pasajero.getId());

			rowsAffected = ps.executeUpdate();

			return rowsAffected;
		} catch (SQLException e) {

		}
		return rowsAffected;
	}

}
