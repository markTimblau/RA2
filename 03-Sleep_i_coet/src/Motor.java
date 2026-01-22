public class Motor extends Thread{
    private int potencia;
    private int potObjectiu;
    //DEFINIMOS LOS MOTORES
    public Motor(String name, int p){
        super(name);
        this.potencia = 0;
        this.potObjectiu = p;
    }
    //CAMBIAMOS LA POTENCIA
    public void setPotencia(int p){
        this.potObjectiu = p;
    } 
    public int getPotencia(){
        return this.potencia;
    }
    
    @Override
    public void run(){
        while(true){
            String estat;
            //MOVEMOS LA POTENCIA Y MOSTRAMOS EL ESTADO
            if(this.potencia < this.potObjectiu){
                this.potencia++;
                estat = "Incre.";
            } else if(this.potencia > this.potObjectiu){
                this.potencia--;
                estat = "Decre.";
            } else {
                estat = "FerRes";
            }
            
            System.out.printf("%s: %s Objetiu: %d Actual: %d \n", this.getName(), estat, this.potObjectiu, this.potencia);
            //SI HEMOS APAGADO LOS MOTORES SE PARA
            if(this.potencia == 0){
                break;
            }

            //GENERAMOS EL DELAY HASTA 2 SEGUNDOS
            int delay = (int)(Math.random() * 2000);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            
        }
    }
}
