import java.util.Random;

public class Assistent extends Thread{
    public Random rnd = new Random();
    private Esdeveniment esdeveniment;

    public Assistent(String nom, Esdeveniment esdeveniment){
        super(nom);
        this.esdeveniment = esdeveniment;
    }    
    @Override
    public void run(){
        while (true) { 
            if (rnd.nextInt(10) < 5){
                esdeveniment.ferReserva(this);
            } else {
                esdeveniment.cancelaReserva(this);
            }
            try {
                Thread.sleep(rnd.nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }            
        }
    }
}
