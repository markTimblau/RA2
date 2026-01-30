public class Organitzador {
    public static int placesDisponibles = 5;
    public static int assistents = 10;

    public static void main(String[] args) throws InterruptedException{
        Esdeveniment esdeveniment = new Esdeveniment(placesDisponibles);

        Assistent[] assistent = new Assistent[assistents];
        
        iniciaCompteAssistents(assistent,esdeveniment);
        esperaPeriodeAssistents(assistent);
    }
        //CREAMOS LAS CUENTAS
    public static void iniciaCompteAssistents(Assistent[] assistent, Esdeveniment esdeveniment){
        for (int i = 0; i <assistents; i++){
            String nom = "Assistent-"+ i;
            assistent[i] = new Assistent(nom, esdeveniment);
        }        
    }
        //INICIAMOS Y LAS JUNTAMOS
    public static void esperaPeriodeAssistents(Assistent[] assistent) throws InterruptedException{
        for (int i = 0; i <assistents; i++){
            assistent[i].start();
        }
        for (int i = 0; i <assistents; i++){
            assistent[i].join();
        }
    }
}
