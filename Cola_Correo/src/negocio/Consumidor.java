package negocio;

import entidad.Email;

public class Consumidor extends Thread {

	private String nombre;
	private Cola cola;
	
	public Consumidor(String n, Cola c) {
		super();
		this.nombre = n;
		this.cola = c;
	}
	
	public void run() {
		while(true) {
		Email email = cola.getEmail();
		System.out.println(nombre + " ha consumido el email: " + email);
		System.out.println("----------------------------------");
		}
	}
}
