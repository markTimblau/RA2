
public class Forquilla{
    private boolean inUs = false;
    private int number;

    public Forquilla(){}

    public Forquilla(int number){
        this.number = number;
    }
    public void setEnUs(boolean inUs) {this.inUs = inUs;}
    public boolean isInUs() {return inUs;}
    public int getNumber() {return number;}
}