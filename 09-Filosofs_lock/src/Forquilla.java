import java.util.concurrent.locks.ReentrantLock;


public class Forquilla{
    private int number;
    private int lliure = -1;
    private final ReentrantLock bloqueig = new ReentrantLock();

    public Forquilla(){}

    public Forquilla(int number){
        this.number = number;
    }
    public int getNumber() {return number;}

    public int getLliure() {return lliure;}

    public void agafar(int num) throws InterruptedException{
            bloqueig.lock();
            lliure = num;
    }
    public void deixar(){
        lliure = -1;
        bloqueig.unlock();
    }
    
}
//    public ReentrantLock bloqueig(){}