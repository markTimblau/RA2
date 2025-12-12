public class PrincipalEstricte {
public static void main(String[] args) {
        //CREAMOS LOS OBJETOS Y LES DAMOS VALORES DE DORMIR
        Fil fil1 = new Fil("Juan", 1);
        Fil fil2 = new Fil("Pepe", 1);

        //LOS LLAMAMOS
        fil1.start();
        fil2.start();
        System.out.println("Acaba thread main");
    }
}
