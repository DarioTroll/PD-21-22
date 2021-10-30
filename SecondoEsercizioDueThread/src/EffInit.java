public class EffInit extends Thread {

    private int start;
    private int dim;
    public static int[] data;
    public static final int SIZE = 1200000;

    //PER POTER CAMBIARE IL NUMERO DI THREAD BASTA SETTARLO DA QUI
    //quindi questo esercizio per 2 thread ingloba anche quello per 4
    public static final int NUM_THREAD = 2;

    public static void main (String[] args) {
        data = new int[SIZE];
        long begin,end;
        int start,j;

        EffInit[] threads;

        begin=System.currentTimeMillis();
            start = 0;
            threads = new EffInit[NUM_THREAD];
            for (j=0;j<NUM_THREAD;++j) {
                threads[j] = new EffInit(start,SIZE/NUM_THREAD);
                threads[j].start();
                start += SIZE/NUM_THREAD;
            }
            for (j=0;j<NUM_THREAD;++j) {
                try {
                    threads[j].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            end = System.currentTimeMillis();
            System.out.println(NUM_THREAD + "Thread(s): "+(end-begin)+" ms");
    }

    public EffInit (int start, int size) {
        this.start=start;
        this.dim=size;
    }

    public void run () {
        for (int i = start; i<this.dim;++i) {
            data[i]=42;
        }
    }
    
}