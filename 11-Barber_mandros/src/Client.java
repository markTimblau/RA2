public class Client extends Thread{
    private String name;

    public Client(int num){
        this.name = "Client " + num;
    }
    public void tallaseElCabell(){}

    
    public String getNom() {
        return name;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        super.run();
    }
}
