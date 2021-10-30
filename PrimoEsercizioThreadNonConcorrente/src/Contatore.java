public class Contatore {
    private int valore;

    public Contatore () {
        valore = 0;
    }


    //In questa versione, ogni thread quando prova ad incrementare una sola volta prende il lock sull'oggetto
    //Per questo motivo, nessun thread incrementa DI FILA 10000 volte
    public synchronized void incrementa () {
        ++valore;
    }

    public synchronized int getValore () {
        return valore;
    }
    
}
