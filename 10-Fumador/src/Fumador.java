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
            Thread.sleep(rnd.nextInt(500)+500);
            fumades++;
            return true;
        }
        return false;
    }
    public void compraTabac(){
        if (tabac == null) tabac = estanc.venTabac();
    }

    public void compraLlumi(){
        if (llumi == null) llumi = estanc.venLlumi();
    }        
    public void compraPaper(){
        if (paper == null) paper = estanc.venPaper();
    }
    public void main(String[] args) throws InterruptedException{
        while (fumades < 3){
            compraTabac();
            compraLlumi();
            compraPaper();
            fuma();
        }
    }
}
