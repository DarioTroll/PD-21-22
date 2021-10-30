import java.util.Random;
public class MassimoMultithread extends Thread {

    private int start;
    private int dim;
    private int massimo_parziale;
    public static int[] data;
    public static final int SIZE = 1000;
    public static final int MAX_THREAD = 4;

    public static void main (String[] args) {
        data = new int[SIZE];
        int massimo;

        Random random = new Random();


        for (int i=0;i<SIZE;++i) {
            data[i]=random.nextInt(20000000);
        }

        long begin,end;
        int start,j;

        MassimoMultithread[] threads;

        for (int numThread = 1;numThread<=MAX_THREAD;numThread*=2) {
            begin=System.currentTimeMillis();
            start = 0;
            threads = new MassimoMultithread[numThread];

            massimo = Integer.MIN_VALUE;

            for (j=0;j<numThread;++j) {
                threads[j] = new MassimoMultithread(start,SIZE/numThread);
                System.out.println("Thread da "+start);
                threads[j].start();
                start += SIZE/numThread;
            }
            for (j=0;j<numThread;++j) {
                try {
                    threads[j].join();
                    System.out.println("Thread["+j+"] ha restituito "+threads[j].getMassimoParziale()+" come massimo parziale");

                    if (threads[j].getMassimoParziale()>massimo) massimo = threads[j].getMassimoParziale();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            end = System.currentTimeMillis();
            System.out.println("Il massimo vale: "+massimo);
            System.out.println(numThread + "Thread(s): "+(end-begin)+" ms");

            System.out.println("________");

        }
    }

    public MassimoMultithread (int start, int size) {
        this.start=start;
        this.dim=size;
        this.massimo_parziale=Integer.MIN_VALUE;
    }

    public void run () {
        for (int i = 0; i<this.dim;++i) {
            if (data[i+start]>massimo_parziale) massimo_parziale=data[i+start];
        }
    }

    public int getMassimoParziale () {
        return this.massimo_parziale;
    }
    
}
