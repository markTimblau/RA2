public class Associacio {
    public static int numSocis = 10;
    public static int esperaPeriodeSocis = 100;
    
    //MAIN
    public static void main(String[] args ) throws InterruptedException{
        Soci[] soci = new Soci[numSocis];
        iniciaCompteTempsSocis(soci);
        esperaPeriodeSocis(soci);
        mostraBalancCompte();
    }
    //CREAMOS LAS CUENTAS
    public static void iniciaCompteTempsSocis(Soci[] soci){
        for (int i = 0; i <numSocis; i++){
            String nom = "soci-"+ i;
            soci[i] = new Soci(nom);
        }        
    }
    //INICIAMOS Y LAS JUNTAMOS
    public static void esperaPeriodeSocis(Soci[] soci) throws InterruptedException{
        for (int i = 0; i <numSocis; i++){
            soci[i].start();
        }
        for (int i = 0; i <numSocis; i++){
            soci[i].join();
        }
    }
    //MOSTRAMOS EL DINERO DE LA CUENTA
    public static void mostraBalancCompte(){
        System.out.println("La compta té " + Compte.getInstancia().getSaldo() + " euros");        
    }
}
