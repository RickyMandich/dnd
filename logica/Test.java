package logica;
public class Test{
    public static void main(String[] args) {
        csv.Lettore_csv reader = new csv.Lettore_csv();
        String[][] table = reader.getCsv("csv\\Personaggi.csv");
        reader.outElencoCsv(table);
        System.out.println("stai eseguendo un test?");
        Giocante.test = new java.util.Scanner(System.in).nextBoolean();
        System.out.println("inserisci il numero di personaggi che vuoi creare");
        Personaggio[] pg = new Personaggio[new java.util.Scanner(System.in).nextInt()];
        for(Personaggio personaggio:pg){
            personaggio = creaPg();
        }

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