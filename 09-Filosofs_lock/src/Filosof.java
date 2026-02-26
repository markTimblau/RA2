import java.util.Random;

public class Filosof extends Thread {
    private int num;
    private Forquilla forquillaEsquerra;
    private Forquilla forquillaDreta;
    private long iniciGana = 0;
    private long fiGana = 0;
    private long gana = 0;

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
        System.out.printf("Fil%d menja amb gana %d ms\n", num, fiGana);
        esperaMes();

        // HA TERMINAT DE MENJAR
        System.out.printf("Fil%d ha acabat de menjar \n",
        this.num);
        resetGana();
        deixarForquilles();
    }

    public void pensa() {
        iniciGana = System.currentTimeMillis();
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

    public void agafarForquilles() throws InterruptedException{
        if (num == 0) {
            agafarForquillaDreta();
            agafarForquillaEsquerra();
        } else {
            agafarForquillaEsquerra();
            agafarForquillaDreta();
        }
    }

    public void agafarForquillaEsquerra() throws InterruptedException{
        forquillaEsquerra.agafar(num);
        System.out.printf("Fil%d agafa la forquilla esquerra %d \n",
        this.num,
        this.forquillaEsquerra.getNumber());
    }
    
     public void agafarForquillaDreta() throws InterruptedException{          
        this.forquillaDreta.agafar(num);
        System.out.printf("Fil%d agafa la forquilla dreta %d \n",
        this.num,
        this.forquillaDreta.getNumber());
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
        long diferenciaMs = System.currentTimeMillis() - iniciGana;
        fiGana = diferenciaMs / 1000;
    }
    public void resetGana(){
        iniciGana = 0;
        gana = 0;
    }

    @Override
    public void run() {
        while (true) {
            try {
                pensa(); 
                agafarForquilles();
                menja();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
