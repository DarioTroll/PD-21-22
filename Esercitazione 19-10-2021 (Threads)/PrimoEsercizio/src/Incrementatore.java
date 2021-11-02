public class Incrementatore {
    public static void main(String[] args) throws Exception {
        Counter contatore = new Counter();

        for (int i=0;i<40000;++i) {
            contatore.incrementa();
        }

        System.out.println("Valore: "+contatore.getValore());
    }
}
