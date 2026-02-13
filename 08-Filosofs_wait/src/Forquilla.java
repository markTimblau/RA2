
public class Forquilla{
    private int number;
    private int lliure = -1;

    public Forquilla(){}

    public Forquilla(int number){
        this.number = number;
    }
    public int getNumber() {return number;}

    public int getLliure() {return lliure;}

    public synchronized void agafar(int num) throws InterruptedException{
        while(lliure > -1){
            wait();
        }
        lliure = num;
    }
    public synchronized void deixar(){
        lliure = -1;
        notifyAll();
    }
    
}