import java.util.Random;

public class Client extends Thread{
    private final String name;

    public Client(int num){
        this.name = "Client " + num;
    }
    
    public void tallaseElCabell(){
        //LE CORTAN EL PELO
        Random rnd = new Random();
        System.out.printf("Tallant cabell a %s \n", getNom());
        try {
            Thread.sleep(900 + rnd.nextInt(100));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public String getNom() {
        return name;
    }

    @Override
    public void run() {
        tallaseElCabell();
    }
}
