package modelo.entidad;

public class Coche {
    //El coche tendrá un id, una marca, un modelo, un tipo de motor y unos kilómetros).
    private int id;
    private String marca;
    private String modelo;
    private Motor motor;
    private double kilometros;
    private int numeroPasajeros;
    
    
    

    @Override
    public String toString() {
        return 
               "==========================\n" +
               "         COCHE       \n" +
               "        -------       \n" +
               "ID:           " + id + "\n" +
               "Marca:        " + marca + "\n" +
               "Modelo:       " + modelo + "\n" +
               "Motor:        " + motor + "\n" +
               "Kilómetros:   " + kilometros + "\n" +
               "Nº Pasajeros: " + numeroPasajeros + "\n" +
               "==========================";
    }

	public double getKilometros() {
        return kilometros;
    }

    public void setKilometros(double kilometros) {
        this.kilometros = kilometros;
    }

    public Motor getMotor() {
        return motor;
    }

    public void setMotor(Motor motor) {
        this.motor = motor;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

	public int getNumeroPasajeros() {
		return numeroPasajeros;
	}

	public void setNumeroPasajeros(int numeroPasajeros) {
		this.numeroPasajeros = numeroPasajeros;
	}
    
    
}
