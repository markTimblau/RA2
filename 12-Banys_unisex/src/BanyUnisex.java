import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class BanyUnisex {

    //Constantes
    public static final int BANY_BUIT = 0;
    public static final int BANY_AMB_HOMES = 1;
    public static final int BANY_AMB_DONES = 2;

    //Variables
    private int estatActual = BANY_BUIT;
    private int ocupants = 0;
    private static final int CAPACITAT_MAX = 3;

    //Sincronización
    private Semaphore capacitat = new Semaphore(CAPACITAT_MAX, true);
    private ReentrantLock lockEstat = new ReentrantLock(true);

    //Entra hombre
    public void entraHome() {
        boolean dins = false;

        while (!dins) {
            lockEstat.lock();
            try {
                if (estatActual == BANY_BUIT || estatActual == BANY_AMB_HOMES) {

                    if (capacitat.tryAcquire()) {
                        ocupants++;
                        estatActual = BANY_AMB_HOMES;
                        System.out.println("Home entra al bany. Ocupants: " + ocupants);
                        dins = true;
                    }
                }
            } finally {
                lockEstat.unlock();
            }
        }
    }

    //Entra mujer
    public void entraDona() {
        boolean dins = false;

        while (!dins) {
            lockEstat.lock();
            try {
                if (estatActual == BANY_BUIT || estatActual == BANY_AMB_DONES) {

                    if (capacitat.tryAcquire()) {
                        ocupants++;
                        estatActual = BANY_AMB_DONES;
                        dins = true;
                    }
                }
            } finally {
                lockEstat.unlock();
            }
        }
    }

    //Salir del baño
    public void surt() {
        lockEstat.lock();
        try {
            ocupants--;
            if (estatActual == 1)
                System.out.println("Home surt del bany. Ocupants: " + ocupants);
            else{
                System.out.println("Dona surt del bany. Ocupants: " + ocupants);
            }
            capacitat.release();

            if (ocupants == 0) {
                estatActual = BANY_BUIT;
                System.out.println("El bany està buit");
            }
        } finally {
            lockEstat.unlock();
        }
    }

    public static void main(String[] args) {
        BanyUnisex banyUnisex = new BanyUnisex();
        for (int i = 0; i < 5; i++) {
            
            Home home = new Home("Home-" + i, banyUnisex);
            home.start();
            Dona dona = new Dona("Dona-" + i, banyUnisex);
            dona.start();

        }

    }
}