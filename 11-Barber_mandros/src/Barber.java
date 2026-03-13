import java.util.Random;

public class Barber extends Thread{
    public Random rnd = new Random();

    public Barber(String name){
        super(name);
    }

    @Override
    public void run() {

        //REVISA SI HAY CLIENTES
        while(true){

            //CONSIGUE UN CLIENTE
            System.out.printf("Li toca al client s% ", client.getName());
            System.out.printf("Tallant cabell a client s% ", client.getName());
            //CORTAMOS EL PELO
            Thread.sleep(rnd.nextInt(900) + 100);
        }
        
    }
}