public interface Impiegato {
    public String getNome() throws Throwable;
    public String getID() throws Throwable;
    public int getStipendio() throws Throwable;
    public int aumentaStipendio(int diQuanto) throws Throwable;
}
