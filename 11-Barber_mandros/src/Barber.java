
public class Barber extends Thread{
    private Barberia barberia;

    public Barber(String name){
        super(name);
    }

    public void setBarberia(Barberia barberia) {
        this.barberia = barberia;
    }

    @Override
    public void run() {
        while(true){
            try {
                // CONSIGUE UN CLIENTE DE LA BARBERIA
                Client client = barberia.seguentClient();
                if (client != null) {
                    client.tallaseElCabell();
                    
                } else {
                    // NO HAY CLIENTES, DUERME
                    System.out.printf("Barber %s dormint\n", getName());
                    synchronized (barberia.getCondBarber()) {
                        barberia.getCondBarber().wait();
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}