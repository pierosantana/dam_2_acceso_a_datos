package negocio;

import java.util.LinkedList;
import java.util.Queue;

import entidad.Email;

public class Cola {

	public final static int MAX_ELEMENTOS = 5;
	
	private Queue<Email> cola = new LinkedList<>();
	
	public synchronized Email getEmail() {
		
		
		while(cola.size() == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		Email e = cola.poll();
		
		notify();
		return e;
	}
	
	public synchronized void addEmail(Email e) {
		
		if(e.getDestinatario().contains("pikachu")) {
			System.out.println("Email descartado: " + e.getDestinatario() + ", Id: " +  e.getId());
			System.out.println("----------------------------------");
			return;
		}
		
		while(cola.size() == MAX_ELEMENTOS) {
			try {
				wait();
			} catch (InterruptedException e2) {
				e2.printStackTrace();
			}
			
		}
		
		cola.offer(e);
		notify();
	}
	
}
