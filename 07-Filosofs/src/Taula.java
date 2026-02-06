
public class Taula {
    public static int nForquilles = 5;
    public static int nComensals = 5;
    public static Forquilla[] forquilla;
    public static Filosof[] comensals;

    public static void main(String[] args) throws InterruptedException{
        forquilla = new Forquilla[nForquilles];
        comensals = new Filosof[nComensals];

        for(int i = 0; i < nForquilles; i++){
            forquilla[i] = new Forquilla(i);
        }
        
        for(int i = 0; i < nComensals; i++){
            //TODOS MENSO EL ULTIMO COMENSAL
            if (i < nComensals -1){
                comensals[i] = new Filosof("fil" + i, forquilla[i], forquilla[i+1]);
            } else {
                comensals[i] = new Filosof("fil" + i, forquilla[i], forquilla[0]);
            }
        }
        showTaula();
        cridarATaula();
    }
    public static void showTaula(){
        for(int i = 0; i < nComensals; i++){
            //ESTO CREO QUE YA NO FUNCIONA PORQUE FORQUILLA AHORA ES UN OBJETO
            System.out.printf("Comensal:%s esq:%d dreta:%d\n", 
            comensals[i].getName(),
            comensals[i].getForquillaEsquerra().getNumber(),
            comensals[i].getForquillaDreta().getNumber());
        }
        System.out.println("-----------------------------");
    }
    public static void cridarATaula(){
        for(int i = 0; i < nComensals; i++){
            comensals[i].start();
        }
    }
}
