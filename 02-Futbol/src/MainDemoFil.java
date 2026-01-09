public class MainDemoFil {
    public static void main(String[] args) {
        System.out.print("Prioritat ->" + Thread.currentThread().getPriority() + ", ");
        System.out.println("nom -> " + Thread.currentThread().getName());
        System.out.println("toString(): " + Thread.currentThread().toString());
    }
}
