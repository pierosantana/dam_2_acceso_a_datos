package negocio;

import entidad.Email;

public class Productor extends Thread {
	
	private String nombre;
	private Cola cola;
	
	public Productor(String n, Cola c) {
		this.nombre = n;
		this.cola = c;
	}
	
	public void run() {
		GeneradorEmails ge = new GeneradorEmails();
		for(int i = 1;i <= 10;i++){
			
			Email email = ge.generarEmail();
			System.out.println(nombre + " ha producido el email: " + email.getId());
			System.out.println("----------------------------------");
			cola.addEmail(email);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
