public class Fil extends Thread {
    private final String nom;

    public Fil(String nom){
        this.nom = nom;
    }
    
    @Override
    public void run(){
        for (int i = 1; i <= 9; i++) {
            System.out.println(nom + " " + i);
            //CONTADOR PARA PASAR EL TIEMPO
            for (int j = 1; j <= 1000; j++) {}
        }  
        System.out.println("Acaba el fil " + nom);
    }
}
