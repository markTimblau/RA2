import java.util.Random;

public class Filosof extends Thread {
    private int num;
    private Forquilla forquillaEsquerra;
    private Forquilla forquillaDreta;
    private int iniciGana = 0;
    private int fiGana;
    private int gana;

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
        calcularGana();
        System.out.printf("Fil%d menja amb gana %d\n",
                this.num, fiGana);
        esperaMes();

        // HA TERMINAT DE MENJAR
        System.out.printf("Fil%d ha acabat de menjar \n",
        this.num);
        resetGana();
        deixarForquilles();
    }

    public void pensa() {
        System.out.printf("Fil%d pensant \n",
        this.num);
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
            this.gana++;
            System.out.printf("Fil%d gana=%d \n", 
            this.num,
            this.gana);
            deixarForquilles();
            espera();
            return false; 
        }
        this.forquillaEsquerra.agafar(num);
        System.out.printf("Fil%d agafa la forquilla esquerra %d \n",
        this.num,
        this.forquillaEsquerra.getNumber());
        return true;
    }
    
     public boolean agafarForquillaDreta() throws InterruptedException{
        if (this.forquillaDreta.getLliure() != -1) {
            System.out.printf("Fil%d deixa l'esquerra (%d) i espera (dreta ocupada) \n",
                    this.num,
                    this.forquillaEsquerra.getNumber());
            this.gana++;
            System.out.printf("Fil%d gana=%d \n", 
            this.num,
            this.gana);
            deixarForquilles();
            espera();
            return false; 
        }
        this.forquillaDreta.agafar(num);
        System.out.printf("Fil%d agafa la forquilla dreta %d \n",
        this.num,
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
    public void calcularGana(){
        fiGana = gana - iniciGana;
    }
    public void resetGana(){
        iniciGana = 0;
        gana = 0;
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
