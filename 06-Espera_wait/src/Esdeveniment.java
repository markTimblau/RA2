import java.util.ArrayList;
import java.util.List;

public class Esdeveniment extends Thread{
    private static Esdeveniment esdeveniment;
    private List<Assistent> assistents;
    private int placesDisponibles;
    private boolean ocupat = false;

    private Esdeveniment(){}

    public Esdeveniment(int placesDisponibles){
        this.placesDisponibles = placesDisponibles;
        assistents = new ArrayList<>();
    }

    public static synchronized Esdeveniment getInstancia() {
        if (esdeveniment == null) {
            esdeveniment = new Esdeveniment();
        }
        return esdeveniment;
    }

    public synchronized void ferReserva(Assistent assistent){
        while (ocupat) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ocupat = true;
        this.placesDisponibles--;
        assistents.add(assistent);
        System.out.printf("%s ha fet una reserva. Places disponibles: %d%n", assistent.getName(), this.placesDisponibles);
        ocupat = false;
        notifyAll();

    }
    public synchronized void cancelaReserva(Assistent assistent){
        while (ocupat) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ocupat = true;
        boolean found = false;
        //BUSCAMOS EN LA LISTA
        for (int i = 0; i < assistents.size(); i++) {
            if (assistents.get(i) == assistent){
                //SE ENCONTRÓ
                found = true;
                this.placesDisponibles++;
                assistents.remove(assistent);
                System.out.printf("%s ha cancel·lat una reserva. Places disponibles: %d%n", assistent.getName(), this.placesDisponibles);
                break;
            }
        }
        //NO SE ENCONTRÓ
        if (!found){
            System.out.printf("%s no ha pogut cancel·lat una reserva inexistent. Places disponibles: %d%n", assistent.getName(), this.placesDisponibles);
        }
        ocupat = false;
        notifyAll();  
    }
}