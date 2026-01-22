import java.util.Scanner;

public class Coet {
    //ARRANCAMOS LOS MOTORES
    public static void arranca(String[] motoresN, Motor[] motores, int p){
        for (int i = 0; i < motoresN.length; i++){
            motores[i] = new Motor(motoresN[i], p);
            motores[i].start();
        }
    }
    //CAMBIAMOS LA POTENCIA DE TODOS LOS MOTORES
    public static void passaAPotencia(Motor[] motores, int p){
        if(p < 0 || p > 10){
            System.out.println("Valor inacceptable");
            return;
        }
        System.out.println("Passant a potència " + p);
        for (Motor motor : motores){            
            motor.setPotencia(p);
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int p;
        p = scanner.nextInt();

        String[] motoresN = {"motor0","motor1","motor2","motor3"};
        Motor[] motores = new Motor[motoresN.length];
        
        //DEFINIMOS Y ARRANCAMOS LOS HILOS
        arranca(motoresN, motores, p);
        while (true){
            //ESCUCHAMOS A POR SI EL USUARIO CAMBIA LA VELOCIDAD DEL MOTOR
            p = scanner.nextInt();
            passaAPotencia(motores, p);
            //si la potencia de todos los motores es 0 termina el programa
            boolean apagado = true;
            for (Motor motor : motores){
                if (motor.getPotencia() != 0){
                    apagado = false;
                }
            }
            //TODOS LOS MOTORES ESTÁN APAGADOS
            if (apagado == true){
                System.out.println("TODO APAGADO");
                break;
            }
        }
        scanner.close();
    }
    
}