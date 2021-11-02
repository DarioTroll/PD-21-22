public class Accumulatore {
    private int attuale;

    public Accumulatore () {
        this.attuale=0;
    }

    public synchronized void accumula (int quanto) {
        this.attuale+=quanto;
    }
    
    public synchronized int getAttuale () {
        return attuale;
    }
}
