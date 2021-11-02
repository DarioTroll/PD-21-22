public class ThreadEsercizio implements Runnable {

    private Contatore contatore;
    private int id;

    public void run () {


        //In questa versione dell'esercizio, ogni thread prende il lock sul contatore e lo incrementa 10000 volte DI FILA
        synchronized(contatore) {
            for (int i=0;i<10000;++i)
            contatore.incrementa();
            System.out.println("Thread "+id+" ha incrementato fino a : "+contatore.getValore());
        }
        
    }

    public ThreadEsercizio (int i, Contatore c) {
        contatore=c;
        id = i;
    }
}
