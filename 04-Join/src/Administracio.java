public class Administracio {
    public static int num_poblacio_activa = 50;

    public static void iniciarTreballadors() throws InterruptedException{
        Treballador[] treballador = new Treballador[num_poblacio_activa];
        for (int i = 0; i <num_poblacio_activa; i++){
            String nom = "Ciutadá-"+ i;
            treballador[i] = new Treballador(nom, 25000, 20, 65);
            //INICIAMOS
            treballador[i].start();
            //LOS JUNTAMOS
            
        }
        for (Treballador aquest : treballador) {
            aquest.join();
        }
        for (Treballador aquest : treballador) {
            System.out.printf("%s -> edat: %d / total: %6.2f \n", aquest.getName(), aquest.getEdat(), aquest.getCobrat());
        }
    }
    public static void main(String[] args) throws InterruptedException{
        iniciarTreballadors();
    }
}
