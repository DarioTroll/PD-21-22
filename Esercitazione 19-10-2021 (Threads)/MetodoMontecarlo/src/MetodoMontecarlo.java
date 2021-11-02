import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MetodoMontecarlo extends Thread {
    static Logger logger = Logger.getLogger("global");

    //Qui si può scegliere il numero di thread su cui eseguire la stima del PiGreco
    public static final int NUM_THREAD = 4;
    //Qui il numero di tentativi (N nella formula dalle slides)
    public static final long NUM_ATTEMPTS = 1000000L;
    public static void main(String[] args) throws Exception {

        //Abilitare il logger (cioè commentare riga successiva) per maggiori informazioni sui singoli thread
        //logger.setLevel(Level.OFF);

        long begin,end;
        double pi_sum = 0;
        double final_pi;
        int j;
        begin = System.currentTimeMillis();
        MetodoMontecarlo[] threads = new MetodoMontecarlo[NUM_THREAD];
        for (j=0;j<NUM_THREAD;++j) {
            threads[j] = new MetodoMontecarlo(NUM_ATTEMPTS/NUM_THREAD);
            threads[j].start();
        }
        for (j=0;j<NUM_THREAD;++j) {
            try {
                threads[j].join();
                pi_sum += threads[j].getPigreco();
                logger.info("Il thread "+j+" ha avuto "+threads[j].getSuccessi()+" successi su "+threads[j].getTentativi()+" tentativi");
                logger.info("Il thread "+j+" stima il pigreco a "+threads[j].getPigreco());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        end = System.currentTimeMillis();
        final_pi = (pi_sum)/NUM_THREAD;
        System.out.println("Il Metodo Montecarlo su "+NUM_ATTEMPTS+" tentativi stima Pi Greco a : "+final_pi);
        System.out.println("Differenza di "+(Math.PI-final_pi)+" dal Pi Greco");
        System.out.println("Tempo impiegato : "+(end-begin)+" ms");
        System.out.println("Numero Thread utilizzati: "+NUM_THREAD);
    }

    public MetodoMontecarlo (long attempts) {
        this.random = new Random();
        this.tentativi = attempts;
        this.successi = 0;
    }

    public void run () {
        for (int i=0;i<tentativi;++i) {
            x = random.nextDouble();
            y = random.nextDouble();
            if (Math.sqrt(x*x + y*y) < 1) {
                ++successi;
            }
        }
    }

    public double getPigreco() {
        return (4.0*successi)/tentativi;
    }

    public long getTentativi () {
        return tentativi;
    }

    public long getSuccessi () {
        return successi;
    }

    private double x;
    private double y;
    private Random random;

    //Questo è N
    private long tentativi;
    //Questo è C
    private long successi;
}
