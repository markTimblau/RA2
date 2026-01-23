
import java.util.Random;

public class Treballador extends Thread {
    public int sou_anual_brut;    
    public int edat_inici_treball;
    public int  edat_fi_treball;
    public double cobrat = 0.0f;
    public int edat_actual = 0;
    public Random rnd;

    public Treballador(String name, int sou_anual_brut, int edat_inici_treball, int edat_fi_treball){
        super(name);
        this.sou_anual_brut = sou_anual_brut;
        this.edat_inici_treball = edat_inici_treball;
        this.edat_fi_treball = edat_fi_treball;
        rnd = new Random();
    }
    //COBRA LA MESADA
    public void cobra(){
        this.cobrat += (sou_anual_brut/12.0f);
    }
    //SE LE RESTAN LOS IMPUESTOS 24%
    public void pagaImpostos(){
        this.cobrat -= (sou_anual_brut/12.0f*0.24f);
    }
    //GETTERS
    public double getCobrat(){return this.cobrat;}

    public int getEdat(){return this.edat_actual;}

    @Override
    public void run() {
        int r = rnd.nextInt(100);

        for (this.edat_actual = this.edat_inici_treball; this.edat_actual < this.edat_fi_treball; this.edat_actual++){
            for (int j = 0; j < 12; j++){
                cobra();
                pagaImpostos();
            }
            try {
                Thread.sleep(r);
                } catch (InterruptedException e){
                e.printStackTrace();
            }
            this.edat_inici_treball++;
        }
        //System.out.printf("%s -> edat: %d / total: %6.2f \n", this.getName(), this.edat_actual, this.cobrat);
    }
}
