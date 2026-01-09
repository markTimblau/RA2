

public class Futbolista extends Thread{
    private int ngols = 0;
    private final int ntirades = 20;

    private final int NUM_JUGADORS = 11;
    private final double PROBABILITAT = 0.5;

    public Futbolista(String name){
        super(name);
    }
    @Override
    public void run(){
        for (int i = 0; i <= ntirades; i++){
            if (Math.random() > PROBABILITAT){
                ngols++;
            }

        }
    }
    public static void main(String[] args ) throws InterruptedException {
        //CREEM LOS FUTBOLISTES
        String[] futbolistas = {"Pique", "Messi","Suárez","Neymar","Busquets","Xavi","Iniesta","Villa","Alves","Valdes","Mascherano"};
        Futbolista[] jugadores = new Futbolista[futbolistas.length];
    
        System.out.println("Inici dels chuts----------");
        //DEFINIMOS Y ARRANCAMOS LOS HILOS
        for (int i = 0; i < futbolistas.length; i++){
            jugadores[i] = new Futbolista(futbolistas[i]);
            jugadores[i].start();
        }
        //BLOQUEAMOS EL PROGRAMA HASTA QUE TODOS LOS HILOS TERMINEN
        for (Futbolista jugador : jugadores){
            jugador.join();
        }
        System.out.println("Fi dels chuts----------");
        System.out.println("---Estadístiques:------");
        //MOSTRAMOS LOS RESULTADOS
        for (Futbolista jugador : jugadores){
            System.out.printf("%-10s -> %d gols%n", jugador.getName(), jugador.ngols);
        }
    }

}
