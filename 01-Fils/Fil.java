public class Fil extends Thread {
    private final String nom;
    private int time = 0;

    public Fil(String nom){
        this.nom = nom;
    }

    public Fil(String nom, int time){
        this.nom = nom;
        this.time = time;
    }
    @Override
    public void run(){
        for (int i = 1; i <= 9; i++) {
            System.out.println(nom + " " + i);
            //CONTADOR PARA PASAR EL TIEMPO
            for (int j = 1; j <= 1000; j++) {}
            //SI HAY SLEEP TIME SE DUERME
            if (time != 0){
                try {
                    Thread.sleep(time);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }  
        System.out.println("Acaba el fil " + nom);
    }
}
