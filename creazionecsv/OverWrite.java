package creazionecsv;
public class OverWrite{
    public static void main(String[] args) {
        Lettore_csv reader = new Lettore_csv();
        String[][] table = reader.getCsv("csv\\Personaggi.csv");
        reader.outElencoCsv(table);
        System.out.println("inserisci il numero di personaggi che vuoi creare");
        logica.Personaggio[] pg = new logica.Personaggio[new java.util.Scanner(System.in).nextInt()];
        for(logica.Personaggio personaggio:pg){
            personaggio = creaPg();
        }

    }
    public static int inputTiroSalvezza(logica.Personaggio p){
        String car = "";
        System.out.println("inserisci le prime tre lettere della statistica che vuoi usare");
        car = new java.util.Scanner(System.in).nextLine();
        try {
            return ((logica.Giocante) p).tiroSalvezza(car);
        } catch (logica.NoSuchStatistic e) {
            return inputTiroSalvezza(p);
        }
    }
    public static logica.Personaggio creaPg(){
        System.out.println("vuoi creare un personaggio giocante?");
        if(new java.util.Scanner(System.in).nextBoolean()){
            System.out.println("stai eseguendo un test?");
            return new logica.Giocante(new java.util.Scanner(System.in).nextBoolean());
        }else{
            return new logica.Personaggio();
        }
    }
}