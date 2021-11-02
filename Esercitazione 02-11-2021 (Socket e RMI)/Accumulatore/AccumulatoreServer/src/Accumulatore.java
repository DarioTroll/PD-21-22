public class Accumulatore {
    private int attuale;

    public Accumulatore () {
        this.attuale=0;
    }

    public void accumula (int quanto) {
        this.attuale+=quanto;
    }
    
    public int getAttuale () {
        return attuale;
    }
}
