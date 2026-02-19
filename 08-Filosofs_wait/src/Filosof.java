import java.util.Random;

public class Filosof extends Thread {
    private int num;
    private Forquilla forquillaEsquerra;
    private Forquilla forquillaDreta;
    private int hunger = 0;
    public Random rnd = new Random();

    public Filosof() {
    }

    public Filosof(int num, Forquilla forquillaEsquerra, Forquilla forquillaDreta) {
        this.num = num;
        this.forquillaEsquerra = forquillaEsquerra;
        this.forquillaDreta = forquillaDreta;
    }

    public void menja() throws InterruptedException{
        // ESTÁ MENJANT
        System.out.printf("Filòsof: %s menja \n",
                this.getName());
        esperaMes();

        // HA TERMINAT DE MENJAR
        System.out.printf("Filòsof: %s ha acabat de menjar \n",
        this.getName());
        this.hunger = 0;
        deixarForquilles();
    }

    public void pensa() {
        System.out.printf("Filòsof: %s pensant \n",
        this.getName());
        esperaMes();
    }

    public void espera() {
        try {
            Thread.sleep(rnd.nextInt(500) + 500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void esperaMes() {
        try {
            Thread.sleep(rnd.nextInt(1000) + 1000);
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

    public boolean agafarForquilles() throws InterruptedException{
        if(!agafarForquillaEsquerra()){
            return false;
        }
        return agafarForquillaDreta();
    }

    public boolean agafarForquillaEsquerra() throws InterruptedException{
        if (this.forquillaEsquerra.getLliure() != -1) {
            this.hunger++;
            System.out.printf("Filòsof: %s gana=%d \n", 
            this.getName(),
            this.hunger);
            deixarForquilles();
            espera();
            return false; 
        }
        this.forquillaEsquerra.agafar(num);
        System.out.printf("Filòsof: %s agafa la forquilla esquerra %d \n",
        this.getName(),
        this.forquillaEsquerra.getNumber());
        return true;
    }
    
     public boolean agafarForquillaDreta() throws InterruptedException{
        if (this.forquillaDreta.getLliure() != -1) {
            System.out.printf("Filòsof: %s deixa l'esquerra (%d) i espera (dreta ocupada) \n",
                    this.getName(),
                    this.forquillaEsquerra.getNumber());
            this.hunger++;
            System.out.printf("Filòsof: %s gana=%d \n", 
            this.getName(),
            this.hunger);
            deixarForquilles();
            espera();
            return false; 
        }
        this.forquillaDreta.agafar(num);
        System.out.printf("Filòsof: %s agafa la forquilla dreta %d \n",
        this.getName(),
        this.forquillaDreta.getNumber());
        return true;
     }

    public void deixarForquilles(){
        if(this.num == this.forquillaEsquerra.getLliure()){
            this.forquillaEsquerra.deixar();
        }
        if(this.num == this.forquillaDreta.getLliure()){
            this.forquillaDreta.deixar();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
            if (agafarForquilles()) {
                menja();
                pensa();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        }
    }
}
