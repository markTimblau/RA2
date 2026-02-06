import java.util.Random;

public class Filosof extends Thread {
    private Forquilla forquillaEsquerra;
    private Forquilla forquillaDreta;
    private int hunger = 0;
    public Random rnd = new Random();

    public Filosof() {
    }

    public Filosof(String name, Forquilla forquillaEsquerra, Forquilla forquillaDreta) {
        super(name);
        this.forquillaEsquerra = forquillaEsquerra;
        this.forquillaDreta = forquillaDreta;
    }

    public void menja() {
        // FORQUILLA ESQUERRA
        if (this.forquillaEsquerra.isInUs()) {
            espera();
            this.hunger++;
        } else {
            this.forquillaEsquerra.setEnUs(true);
            System.out.printf("Filòsof: %s agafa la forquilla esquerra %d \n",
                    this.getName(),
                    this.forquillaEsquerra.getNumber());
        }
        // FORQUILLA DRETA
        if (this.forquillaDreta.isInUs()) {
            this.forquillaEsquerra.setEnUs(false);
            System.out.printf("Filòsof: %s deixa l'esquerra (%d) i espera (dreta ocupada) \n",
                    this.getName(),
                    this.forquillaEsquerra.getNumber());
            this.hunger++;
            System.out.printf("Filòsof: %s gana= %d \n", 
            this.getName(),
            this.hunger);
            espera();
            ///////////////
        } else {
            this.forquillaDreta.setEnUs(true);
            System.out.printf("Filòsof: %s agafa la forquilla dreta %d \n",
                    this.getName(),
                    this.forquillaDreta.getNumber());
        }
        // ESTÁ MENJANT
        System.out.printf("Filòsof: %s menja \n",
                this.getName());
        try {
            Thread.sleep(rnd.nextInt(1000) + 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // HA TERMINAT DE MENJAR

        System.out.printf("Filòsof: %s ha acabat de menjar \n",
        this.getName());
        this.hunger = 0;
        this.forquillaEsquerra.setEnUs(false);
        this.forquillaEsquerra.setEnUs(false);
    }

    public void pensa() {
        System.out.printf("Filòsof: %s pensant \n",
        this.getName());
        try {
            Thread.sleep(rnd.nextInt(1000) + 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void espera() {
        try {
            Thread.sleep(rnd.nextInt(500) + 500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Forquilla getForquillaEsquerra() {
        return forquillaEsquerra;
    }

    public Forquilla getForquillaDreta() {
        return forquillaDreta;
    }

    @Override
    public void run() {
        while (true) {
            menja();
            pensa();
        }
    }
}
