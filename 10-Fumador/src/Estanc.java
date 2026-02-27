import java.util.ArrayList;
import java.util.Random;

public class Estanc extends Thread{
    private ArrayList<Llumi> llumi =  new ArrayList<>();
    private ArrayList<Paper> paper = new ArrayList<>();
    private ArrayList<Tabac> tabac = new ArrayList<>();
    public boolean open = true;
    public Random rnd = new Random();

    @Override
    public void run() {
        try{
            while (open) {
                nouSubministrament();
                Thread.sleep(rnd.nextInt(500)+500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
        tabac.add(new Tabac());
    }
    public synchronized void addLlumi(){
        llumi.add(new Llumi());
    }
    public synchronized void addPaper(){
        paper.add(new Paper());
    }

    //VENDER EXISTENCIAS
    public synchronized Llumi venLlumi(){
        if (!llumi.isEmpty()){
            return llumi.remove(0);
        }    
        return null;
    }
    public synchronized Paper venPaper(){
        if (!paper.isEmpty()){
            return paper.remove(0);
        }
        return null;
    }   
    public synchronized Tabac venTabac(){
        if (!tabac.isEmpty()){
            return tabac.remove(0);
        }
        return null;
    }
    public void tencarEstanc(){
        open = false;
    }
}