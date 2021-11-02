import java.util.logging.Logger;

public class Incrementatore {

    static Logger logger = Logger.getLogger("global");
    public static void main(String[] args) throws Exception {
        logger.info("Inizio");

        Contatore contatore = new Contatore();

        /*
        new Thread(new ThreadEsercizio(1, contatore)).start();
        new Thread(new ThreadEsercizio(2, contatore)).start();
        new Thread(new ThreadEsercizio(3, contatore)).start();
        new Thread(new ThreadEsercizio(4, contatore)).start();
        */

        long begin = System.currentTimeMillis();

        Thread t1 = new Thread(new ThreadEsercizio(1, contatore));
        Thread t2 = new Thread(new ThreadEsercizio(2, contatore));
        Thread t3 = new Thread(new ThreadEsercizio(3, contatore));
        Thread t4 = new Thread(new ThreadEsercizio(4, contatore));
        
        t1.start();
        t2.start();
        t3.start();
        t4.start();


        t1.join();
        t2.join();
        t3.join();
        t4.join();

        logger.info("Terminato!");
        long end = System.currentTimeMillis();
        logger.info("Valore finale: "+contatore.getValore());
        System.out.println("Tempo finale: "+(end-begin)+" ms");
    }
}
