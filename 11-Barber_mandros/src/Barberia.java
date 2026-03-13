import java.util.LinkedList;

public class Barberia extends Thread{
    public static int chairAmout = 3;
    private static LinkedList<Client> listaEspera;
    

    public void barberia(){
    }

    public void barberia(int chairAmout){
        this.chairAmout = chairAmout;
    }

    public void salaEspera(){}   

    public void entrenClients(){
        for (int i = 0; i < 10; i++) {
            Thread.sleep(500);
            //METER CLIENTES EN LA SALA DE ESPERA
            listaEspera.add(new Client(i));
        }
    }

    public static void main(String[] args) {
        //CREAMOS EL BARBERO
        Barber barber = new Barber("Pepe");
        //LA BARBERIA
        Barberia barberia = new Barberia();
        //CREAMOS LA LISTA DE ESPERA
        listaEspera = new LinkedList<>();
        barber.start();
        barberia.start();
    }

    @Override
    public void run(){
        entrenCLients();
        try {
            Thread.sleep(100000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        entrenCLients();
    }
}