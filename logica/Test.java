package logica;
public class Test{
    public static void main(String[] args) {

    }
    public static int inputTiroSalvezza(Personaggio p){
        String car = "";
        System.out.println("inserisci le prime tre lettere della statistica che vuoi usare");
        car = new java.util.Scanner(System.in).nextLine();
        try {
            return ((Giocante) p).tiroSalvezza(car);
        } catch (NoSuchStatistic e) {
            return inputTiroSalvezza(p);
        }
    }
    public static Personaggio creaPg(){
        System.out.println("vuoi creare un personaggio giocante?");
        if(new java.util.Scanner(System.in).nextBoolean()){
            return new Giocante();
        }else{
            return new Personaggio();
        }
    }
}