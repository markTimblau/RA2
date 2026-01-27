import java.util.Random;

public class Soci extends Thread{
    public int aportacio = 10;
    public int esperaMax = 100;
    public Random rnd = new Random();
    public int maxAnys = 10;

    //CONSTRUCTOR
    public Soci(String nom) {
        super(nom);
    }
    public Compte getCompte(){ return Compte.getInstancia();}

    @Override
    public void run(){
        //RECOGEMOS EL OBJETO SIMPLETON COMPTE
        Compte compte = Compte.getInstancia();
        //CICLO DE APORTACIONES Y EXPORTACIONES
        for (int i = 0; i < maxAnys; i++) {
            //CICLO DE 12 MESES
            for (int j = 0; j < 12; j++){
                if (j%2 == 1){
                    compte.setSaldo(aportacio);
                } else {
                    compte.setSaldo(-(aportacio));
                }
            }
            try {
                Thread.sleep(rnd.nextInt(esperaMax));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
