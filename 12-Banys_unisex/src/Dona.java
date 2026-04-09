import java.util.Random;

public class Dona extends Thread {
    private BanyUnisex banyUnisex;

    public Dona(String name, BanyUnisex banyUnisex){
        super(name);
        this.banyUnisex = banyUnisex;
    }

    public void entraDona() {
        System.out.println("" + getName() + " vol entrar al bany");
        banyUnisex.entraDona();

    }

    public void utilizaLavabo() {
        Random rnd = new Random();
        try {
            Thread.sleep(1000 + rnd.nextInt(1000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void surtDona() {
        banyUnisex.surt();
        System.out.println("" + getName() + " ha acabat d'usar el bany");
    }

    @Override
    public void run() {
        entraDona();
        utilizaLavabo();
        surtDona();
    }
}