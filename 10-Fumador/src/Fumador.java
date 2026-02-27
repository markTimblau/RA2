import java.util.Random;

public class Fumador extends Thread{
    private int id;
    private Estanc estanc;
    private Tabac tabac;
    private Llumi llumi;
    private Paper paper;
    private int fumades = 0;

    public Random rnd = new Random();

    public Fumador(){}

        //CONSTRUCTOR
    public Fumador(Estanc estanc, int id){
        this.estanc = estanc;
        this.id = id;
    }

    public boolean fuma() throws InterruptedException{
        if(tabac != null && llumi != null && paper != null){
            tabac = null;
            llumi = null;
            paper = null;
            //FUMA
            System.out.printf("Fumador %d fumant \n", id);
            Thread.sleep(rnd.nextInt(500)+500);
            fumades++;
            System.out.printf("Fumador %d ha fumat %d vegades \n", id, fumades);
            return true;
        }
        return false;
    }
    public void compraTabac() throws InterruptedException{
        if (tabac == null) tabac = estanc.venTabac();
        System.out.printf("Fumador %d compra Tabac \n", id);
    }

    public void compraLlumi() throws InterruptedException{
        if (llumi == null) llumi = estanc.venLlumi();
        System.out.printf("Fumador %d compra Llumi \n", id);
    }        
    public void compraPaper() throws InterruptedException{
        if (paper == null) paper = estanc.venPaper();
        System.out.printf("Fumador %d compra Paper \n", id);
    }
    @Override
    public void run() {
        try {
            while (fumades < 3) {
                compraTabac();
                compraLlumi();
                compraPaper();
                fuma();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
