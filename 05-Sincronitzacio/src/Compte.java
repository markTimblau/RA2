public class Compte {
    private static Compte compte;
    private static int saldo = 0;

    private Compte() {
        if (getInstancia() != null) {
            throw new IllegalStateException("Ja existeix una instancia de Compte");
        }
    }
    public static Compte getInstancia() {
        if (compte == null) {
            compte = new Compte();
        }
        return compte;
    }
    public static void setSaldo(int diners){
        saldo += diners;
    }
    public static int getSaldo(){return saldo;}
}