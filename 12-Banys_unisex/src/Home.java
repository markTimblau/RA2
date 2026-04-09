import java.util.Random;

public class Home extends Thread {
    private BanyUnisex banyUnisex;

    public Home(String name, BanyUnisex banyUnisex){
        super(name);
        this.banyUnisex = banyUnisex;
    }

    public void entraHome() {
        System.out.println("" + getName() + " vol entrar al bany");
        banyUnisex.entraHome();

    }

    public void utilizaLavabo() {
        Random rnd = new Random();
        try {
            Thread.sleep(1000 + rnd.nextInt(1000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void surtHome() {
        banyUnisex.surt();
        System.out.println("" + getName() + " ha acabat d'usar el bany");
    }

    @Override
    public void run() {
        entraHome();
        utilizaLavabo();
        surtHome();
    }
}