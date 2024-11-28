package negocio;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import entidad.Email;

public class GeneradorEmails {
	
	private static int id;

	public Email generarEmail() {
		Email e = new Email();
		e.setDestinatario(generarDestinatario());
		e.setRemitente(generarRemitente());
		e.setAsunto(generarAsunto());
		e.setCuerpo(generarCuerpo());
		e.setId(++id);
		
		return e;
	}

	private String generarCuerpo() {
		List<String> listaMensajes = new ArrayList<String>();
		listaMensajes.add("Hola, solo quería confirmar si recibiste el último archivo enviado. Quedo atento/a a cualquier comentario.");
		listaMensajes.add("Te escribo para coordinar una reunión esta semana. ¿Qué día y hora te queda mejor?");
		listaMensajes.add("Adjunto el documento solicitado. Por favor, avísame si necesitas alguna otra cosa.");
		listaMensajes.add("Un recordatorio de que el plazo de entrega es el próximo lunes. ¿Todo va bien con el avance?");
		listaMensajes.add("Necesito algunos detalles adicionales sobre el proyecto. ¿Podrías enviarme la última actualización?");
		listaMensajes.add("Mensaje de prueba.");
		
		int numero = ThreadLocalRandom.current().nextInt(0, 6);
		
		return listaMensajes.get(numero);
	}

	private String generarAsunto() {
		List<String> listaAsuntos = new ArrayList<String>();
		listaAsuntos.add("Actualización de Proyecto");
		listaAsuntos.add("Confirmación de Reunión");
		listaAsuntos.add("Solicitud de Información");
		listaAsuntos.add("Entrega de Documentos");
		listaAsuntos.add("Recordatorio de Fecha Límite");
		listaAsuntos.add("Sin Asunto");
		
		int numero = ThreadLocalRandom.current().nextInt(0, 6);
		
		return listaAsuntos.get(numero);
	}

	private String generarRemitente() {
		List<String> listaRemitentes = new ArrayList<String>();
		listaRemitentes.add("pierosantana@mail.com");
		listaRemitentes.add("emma.larson23@netpostmail.net");
		listaRemitentes.add("lucas.bennet99@zoonmail.com");
		listaRemitentes.add("livia.rivers@freemailspace.org");
		listaRemitentes.add("ethan.davis07@myinboxservice.net");
		listaRemitentes.add("ava.reed@fastlane.com");
		
		int numero = ThreadLocalRandom.current().nextInt(0, 6);
		
		return listaRemitentes.get(numero);
	}

	private String generarDestinatario() {
		List<String> listaDestinatarios = new ArrayList<String>();
		listaDestinatarios.add("pikachu@mail.com");
		listaDestinatarios.add("alex.brown12@webmail.com");
		listaDestinatarios.add("sophia.jameson88@fastmail.net");
		listaDestinatarios.add("samuel.lee@cloudsend.org");
		listaDestinatarios.add("mia.greenfield@inboxnow.com");
		listaDestinatarios.add("jordan.smith45@liveconnect.io");
		
		int numero = ThreadLocalRandom.current().nextInt(0, 6);
		
		return listaDestinatarios.get(numero);
	}
	
	
}
