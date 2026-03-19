import java.util.LinkedList;

public class Barberia extends Thread{
    public int chairAmount = 3;
    private static LinkedList<Client> listaEspera;
    private final Object condBarber = new Object();

    public Barberia(){
    }

    public Barberia(int chairAmount){
        this.chairAmount = chairAmount;
    }

    public Object getCondBarber(){
        return condBarber;
    }

    public void entrenClients() throws InterruptedException{
        int clientCounter = 0;
        while (clientCounter < 20) {
            // AÑADIMOS CLIENTES DE 10 EN 10
            for (int i = 0; i < 10 && clientCounter < 20; i++) {
                Thread.sleep(500);
                Client client = new Client(clientCounter);
                // INTENTAMOS SENTAR AL CLIENTE EN LA BARBERIA
                if (listaEspera.size() < chairAmount) {
                    listaEspera.add(client);
                    System.out.printf("Client %s en espera\n", client.getNom());
                    clientCounter++;
                    synchronized (condBarber) {
                        condBarber.notify();
                    }
                } else {
                    // NO HAY SILLAS DISPONIBLES, EL CLIENTE SE VA
                    System.out.printf("No queden cadires, client %s se'n va\n", client.getNom());
                    clientCounter++;
                }
            }
            // ESPERAMOS 10 SEGUNDOS ANTES DE AÑADIR MÁS CLIENTES
            if (clientCounter < 20) {
                Thread.sleep(10000);
            }
        }
    }


    public synchronized Client seguentClient(){
        if (listaEspera.isEmpty()) {
            System.out.println("Ningú en espera");
            return null;  
        } else {
            Client client = listaEspera.remove();
            System.out.printf("Li toca al client %s \n", client.getNom());            return client;
        }
    }
    public static void main(String[] args) {
        //CREAMOS LA LISTA DE ESPERA
        listaEspera = new LinkedList<>();
        //LA BARBERIA CON 3 SILLAS
        Barberia barberia = new Barberia(3);
        //CREAMOS EL BARBERO
        Barber barber = new Barber("Pepe");
        barber.setBarberia(barberia);
        barber.start();
        barberia.start();
    }


    @Override
    public void run(){
        try {
            entrenClients();
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}