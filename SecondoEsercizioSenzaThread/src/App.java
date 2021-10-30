import java.util.logging.Logger;

public class App {
    private static final int SIZE = 1200000;
    static Logger logger = Logger.getLogger("global");
    public static void main(String[] args) throws Exception {
        logger.info("Avviato!");
        long begin = System.currentTimeMillis();
        int[] array = new int[SIZE];

        for (int i=0;i<SIZE;++i) {
            array[i]=42;
        }

        long end = System.currentTimeMillis();
        logger.info("Terminato in "+(end-begin)+ " ms");
        
    }
}
