
import java.util.ArrayList;

public class Barri {
    private Estanc estanc;
    private static ArrayList<Fumador> fumadors;
    private int finished = 0;
    
    public static void main(String[] args) throws InterruptedException{
        estanc = new Estanc();
        fumadors = new ArrayList<>();
        for (int i = 0; i < 3; i++){
            fumadors.add(new Fumador(estanc, i));
        }
        //INICIAMOS LAS THREADS
        estanc.start();

        for (Fumador f : fumadors) {
            f.start();
        }      
        //ESPERAMOS A QUE TODOS TERMINEN
        for (Fumador f : fumadors) {
            f.join();
        }

        //CERRAR EL ESTANCO
        estanc.tencarEstanc();
    }
    public void fin(){
        finished++;
    }
}
