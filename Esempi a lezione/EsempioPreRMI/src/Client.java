public class Client {
    public static void main (String[] args) {
        ImpiegatoServer imp = new ImpiegatoServer("Mario Rossi", "01721", 30000);
        System.out.println("Nome: "+imp.getNome());
        System.out.println("ID: "+imp.getID());
        System.out.println("Stipendio: "+imp.getID());

        System.out.println("Aumentiamo lo stipendio!");
        System.out.println("Lo stipendio ora vale: "+imp.aumentaStipendio(1000));
    }
}
