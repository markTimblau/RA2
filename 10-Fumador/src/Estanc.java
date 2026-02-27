import java.util.ArrayList;
import java.util.Random;

public class Estanc extends Thread{
    private final ArrayList<Llumi> llumi =  new ArrayList<>();
    private final ArrayList<Paper> paper = new ArrayList<>();
    private final ArrayList<Tabac> tabac = new ArrayList<>();
    public boolean open = true;
    public Random rnd = new Random();

    @Override
    public void run() {
        System.out.println("Estanc obert");
        try{
            while (open) {
                nouSubministrament();
                Thread.sleep(rnd.nextInt(1000)+500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Estanc tancat");
    }
    public void nouSubministrament(){
        int nouSubministre = rnd.nextInt(3);
        switch (nouSubministre) {
            case 0 -> addTabac();
            case 1 -> addLlumi();
            case 2 -> addPaper();
        }
    }
    //AÑADIR EXISTENCIAS
    public synchronized void addTabac(){
        System.out.println("Afegint Tabac");
        tabac.add(new Tabac());
        notifyAll();
    }
    public synchronized void addLlumi(){
        System.out.println("Afegint Llumí");
        llumi.add(new Llumi());
        notifyAll();
    }
    public synchronized void addPaper(){
        System.out.println("Afegint Paper");
        paper.add(new Paper());
        notifyAll();
    }

    //VENDER EXISTENCIAS
    public synchronized Llumi venLlumi() throws InterruptedException{
        while (llumi.isEmpty()){
            wait();
        }
        Llumi l = llumi.remove(0);
        notifyAll();
        return l;
    }
    public synchronized Paper venPaper() throws InterruptedException{
        while (paper.isEmpty()){
            wait();
        }
        Paper p = paper.remove(0);
        notifyAll();
        return p;
    }   
    public synchronized Tabac venTabac() throws InterruptedException{
        while (tabac.isEmpty()){
            wait();
        }
        Tabac t = tabac.remove(0);
        notifyAll();
        return t;
    }
    public void tencarEstanc(){
        open = false;
    }
}