public class PrincipalDiferents {
    public static void main(String[] args) {
        //CREAMOS LOS OBJETOS
        Fil fil1 = new Fil("Juan");
        Fil fil2 = new Fil("Pepe");
        //LES DAMOS PRIORIDADES
        fil1.setPriority(Thread.MIN_PRIORITY);
        fil2.setPriority(Thread.MAX_PRIORITY);
        //LOS LLAMAMOS
        fil1.start();
        fil2.start();
        System.out.println("Acaba thread main");
    }
}
