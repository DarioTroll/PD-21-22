import java.util.logging.Logger;

public class App {
    static Logger logger = Logger.getLogger("global");
    public static void main(String[] args) throws Exception {
        try {
            Impiegato imp = new Impiegato_Stub("localhost");
            System.out.println("Nome: "+imp.getNome());
            System.out.println("ID: "+imp.getID());
            System.out.println("Stipendio: "+imp.getStipendio());

            System.out.println("Aumentiamo lo stipendio!");

            System.out.println("Stipendio ora: "+imp.aumentaStipendio(1000));

            ((Impiegato_Stub) imp).close();
        } catch (Throwable t) {
            logger.severe("Lanciata Throwable: "+t.getMessage());
            t.printStackTrace();
        }
    }
}
