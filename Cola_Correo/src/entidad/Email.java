package entidad;

public class Email {
	private int id;
	private String destinatario;
	private String remitente;
	private String asunto;
	private String cuerpo;
	
	
	
	
	
	public Email() {
		super();
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	public String getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
	public String getRemitente() {
		return remitente;
	}
	public void setRemitente(String remitente) {
		this.remitente = remitente;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getCuerpo() {
		return cuerpo;
	}
	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}


	@Override
	public String toString() {
	    return "Email {" +
	           "\n  Destinatario: " + destinatario +
	           "\n  Remitente: " + remitente +
	           "\n  Asunto: " + asunto +
	           "\n  Cuerpo: " + cuerpo +
	           "\n  ID: " + getId() +
	           "\n}";
	}
	
	
	

}
