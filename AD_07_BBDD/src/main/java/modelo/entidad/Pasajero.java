package modelo.entidad;

public class Pasajero {
	
	private int id;
	private String nombre;
	private int edad;
	private double peso;
	private int idCoche;
	
	
	
	
	@Override
	public String toString() {
	    return """
	    ==========================
	           PASAJERO
	          ----------
	    ID          : %d
	    Nombre      : %s
	    Edad        : %d
	    Peso        : %.1f kg
	    ID del Coche: %d
	    ==========================
	    """.formatted(id, nombre, edad, peso, idCoche);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public int getIdCoche() {
		return idCoche;
	}
	public void setIdCoche(int idCoche) {
		this.idCoche = idCoche;
	}
	
	
	
	
	

}
