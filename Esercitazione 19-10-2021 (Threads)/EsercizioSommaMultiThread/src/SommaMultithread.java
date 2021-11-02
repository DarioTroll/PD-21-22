import java.util.Random;
public class SommaMultithread extends Thread {

    private int start;
    private int dim;
    private long somma_parziale;
    public static int[] data;
    public static final int SIZE = 1200000;
    public static final int MAX_THREAD = 4;

    public static void main (String[] args) {
        data = new int[SIZE];


        Random random = new Random();


        for (int i=0;i<SIZE;++i) {
            data[i]=random.nextInt(5);
        }

        long begin,end;
        int start,j;
        long somma_totale = 0;

        SommaMultithread[] threads;

        for (int numThread = 1;numThread<=MAX_THREAD;numThread*=2) {
            begin=System.currentTimeMillis();
            start = 0;
            threads = new SommaMultithread[numThread];
            for (j=0;j<numThread;++j) {
                threads[j] = new SommaMultithread(start,SIZE/numThread);
                System.out.println("Thread da "+start);
                threads[j].start();
                start += SIZE/numThread;
            }
            for (j=0;j<numThread;++j) {
                try {
                    threads[j].join();
                    System.out.println("Thread["+j+"] ha restituito "+threads[j].getSommaParziale()+" come somma parziale");
                    somma_totale+=threads[j].getSommaParziale();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            end = System.currentTimeMillis();
            System.out.println("La somma totale vale: "+somma_totale);
            somma_totale=0;
            System.out.println(numThread + "Thread(s): "+(end-begin)+" ms");

            System.out.println("________");

        }
    }

    public SommaMultithread (int start, int size) {
        this.start=start;
        this.dim=size;
        this.somma_parziale=0;
    }

    public void run () {
        for (int i = 0; i<this.dim;++i) {
            somma_parziale+=data[i+start];
        }
    }

    public long getSommaParziale () {
        return this.somma_parziale;
    }
    
}
