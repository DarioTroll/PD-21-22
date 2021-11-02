import java.io.Serializable;

public class RecordRegistro implements Serializable {

	private static final long serialVersionUID = 1L;

	public RecordRegistro (String n, String i) {
		nome = n;
		indirizzo = i;
	}
	
	public String getNome () {
		return nome;
	}
	
	public String getIndirizzo () {
		return indirizzo;
	}
	
	private String nome;
	private String indirizzo;
}
