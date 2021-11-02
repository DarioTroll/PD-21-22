import java.io.Serializable;

public class Pippo implements Serializable {

    public static final long serialVersionUID = 42L;

    private String nome;

    public Pippo () {
        nome = "Ciao";
    }

    public String getNome () {
        return nome;
    }

    public void setNome (String nuovo) {
        this.nome=nuovo;
    }
}
