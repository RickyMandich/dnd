package csv;

import logica.Giocante;
import logica.Personaggio;

public class inputPG {
    public static void main(String[] args) {
        Lettore_csv reader = new Lettore_csv();
        String[][] table = reader.getCsv("csv\\Personaggi.csv");
        reader.outElencoCsv(table);
        System.out.println("stai eseguendo un test?");
        logica.Giocante.test = new java.util.Scanner(System.in).nextBoolean();
        System.out.println("inserisci il numero di personaggi che vuoi creare");
        logica.Personaggio[] pg = new logica.Personaggio[new java.util.Scanner(System.in).nextInt()];
        for(logica.Personaggio personaggio:pg){
            personaggio = creaPgSicuro(table);
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
    public static logica.Personaggio creaPgSicuro(String[][] table){
        try{
            return creaPg(table);
        }catch(IncompatibleCsvException e){
            System.out.println("questa serie di dati non è compatibile, potrebbe essere una vecchia versione di un personaggio o una serie sbagliata");
            return creaPgSicuro(table);
        }
    }
    public static logica.Personaggio creaPg(String[][] tabel){
        System.out.println("vuoi prendere un personaggio che esiste già nel file?");
        if(new java.util.Scanner(System.in).nextBoolean()){
            String[] row = tabel[new java.util.Scanner(System.in).nextInt()];
            if(row.length == 30) return new Personaggio(row);
            else if(row.length == 37) return new Giocante(row);
            else throw new IncompatibleCsvException();
        }else {
            System.out.println("vuoi creare un personaggio giocante?");
            if (new java.util.Scanner(System.in).nextBoolean()) {
                return new logica.Giocante();
            } else {
                return new logica.Personaggio();
            }
        }
    }
}