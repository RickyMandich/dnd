package csv;
public class inputPG{
    public static void main(String[] args) {
        Lettore_csv reader = new Lettore_csv();
        reader.getCsv("csv\\file_dati\\2024_02_15_12-00_test7_Personaggi.csv");
        System.out.println("stai eseguendo un test?\t\t\t(insert \"true\" or \"false\")");
        logica.Giocante.test = new java.util.Scanner(System.in).nextBoolean();
        System.out.println("inserisci il numero di personaggi che vuoi creare");
        logica.Personaggio[] pg = new logica.Personaggio[new java.util.Scanner(System.in).nextInt()];
        for (int i = 0; i < pg.length; i++) {
            pg[i] = creaPgSicuro(reader);
        }
        for (logica.Personaggio personaggio : pg) {
            try {
                System.out.println(personaggio);
            } catch (NullPointerException e) {
                System.out.println("NullPointerException in sout di pg righe 13/19");
            }
        }
        System.out.println("inserire \"true\" se si vuole terminare ora il programma senza salvare\t(in caso alternativo eseguiro la procedura di salvataggio e poi terminerò l'esecuzione)");
        if (new java.util.Scanner(System.in).nextBoolean()) return;
        System.out.println("inserisci il nome del file da creare per salvare l'attuale esecuzione");
        Scrittore_csv writer = new Scrittore_csv("csv\\file_dati\\" + new java.util.Scanner(System.in).nextLine() + "_Personaggi.csv");
        try {
            for (logica.Personaggio personaggio : pg) {
                try {
                    writer.addCsv(personaggio.toCsv());
                } catch (NullPointerException ignored){}
            }
        } catch (Error | Exception e) {
            e.printStackTrace();
        } finally {
            writer.close();
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
    public static logica.Personaggio creaPgSicuro(Lettore_csv reader){
        try{
            return creaPg(reader);
        }catch(csv.exception.IncompatibleCsvException e){
            System.out.println("questa serie di dati non è compatibile, potrebbe essere una vecchia versione di un personaggio o una serie sbagliata");
            return creaPgSicuro(reader);
        }
    }
    public static logica.Personaggio creaPg(Lettore_csv reader){
        System.out.println("vuoi prendere un personaggio che esiste già nel file?\t\t\t(insert \"true\" or \"false\")");
        if(new java.util.Scanner(System.in).nextBoolean()){
            reader.outElencoCsv();
            System.out.println("inserisci il numero relativo al personaggio da prelevare");
            String[] row = reader.tabel[new java.util.Scanner(System.in).nextInt()].split(", ");
            if(row.length == 29) return new logica.Personaggio(row);
            else if(row.length == 37) return new logica.Giocante(row);
            else throw new csv.exception.IncompatibileRowLengthInCsvException();
        }else {
            System.out.println("vuoi creare un personaggio giocante?\t\t\t(insert \"true\" or \"false\")");
            if (new java.util.Scanner(System.in).nextBoolean()) {
                return new logica.Giocante();
            } else {
                return new logica.Personaggio();
            }
        }
    }
}