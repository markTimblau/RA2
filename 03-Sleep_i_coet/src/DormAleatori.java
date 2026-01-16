
public class DormAleatori extends Thread{
    
    public DormAleatori(String name){
        super(name);

    }
    @Override
    public void run(){
        long inicio = System.nanoTime();
        int sleep = (int)(Math.random() * 1000);
        for (int i = 0; i < 10; i++){
            long fin = System.nanoTime();
            try {
                    Thread.sleep(sleep);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            System.out.printf("%s (%d) a dormir %d ms - Temps total: %4d ms\n", Thread.currentThread().getName(), i, sleep, (fin - inicio)/1_000_000);

            
        }
    }
    public static void main(String[] args ) throws InterruptedException {
 
        DormAleatori fil1 = new DormAleatori("Juan");
        DormAleatori fil2 = new DormAleatori("Pepe");
        System.out.println("Fi del main");
        fil1.start();
        fil2.start();

    }
}
