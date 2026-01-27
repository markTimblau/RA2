public class Compte {
    private static Compte compte;
    private int saldo = 0;

    private Compte() {}
    //TOD0 NECESITA SYNCHRONIZED PARA QUE NO HAYAN ERRORES AL MODIFICAR EL SALDO
    public static synchronized Compte getInstancia() {
        if (compte == null) {
            compte = new Compte();
        }
        return compte;
    }
    public synchronized void setSaldo(int diners){
        saldo += diners;
    }
    public synchronized int getSaldo(){
        return saldo;
    }
}