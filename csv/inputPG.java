package csv;
public class inputPG{
    public static void main(String[] args) {
        Lettore_csv reader = new Lettore_csv();
        System.out.println("inserisci il nome del file sorgente\t(solo data, ora e numero di test)");
        reader.getCsv("csv\\file_dati\\" + new java.util.Scanner(System.in).nextLine() + "_Personaggi.csv");
        System.out.println("stai eseguendo un test?\t\t\t(insert \"true\" or \"false\")");
        logica.Giocante.test = getBoolean();
        System.out.println("inserisci il numero di personaggi che vuoi creare");
        logica.Personaggio[] pg = new logica.Personaggio[getInt()];
        for (int i = 0; i < pg.length; i++) {
            pg[i] = creaPgSicuro(reader, pg);
            if(i+1==pg.length) pg = creaUltimoPg(pg);
        }
        elencoNomiPg(pg);
        System.out.println("vuoi i dettagli dei personaggi?\t\t\t(insert \"true\" or \"false\")");
        if(getBoolean()) elencoPg(pg);
        System.out.println("inserire \"true\" se si vuole terminare ora il programma senza salvare\t(in caso alternativo eseguiro la procedura di salvataggio e poi terminerò l'esecuzione)");
        if (getBoolean()) return;
        System.out.println("inserisci il nome del file da creare per salvare l'attuale esecuzione");
        Scrittore_csv writer = new Scrittore_csv("csv\\file_dati\\" + new java.util.Scanner(System.in).nextLine() + "_Personaggi.csv");
        try {
            for (logica.Personaggio personaggio : pg) {
                writer.addCsv(personaggio.toCsv() + "\n");
            }
        } catch (Error | Exception e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }

    private static boolean getBoolean() {
        return new java.util.Scanner(System.in).nextBoolean();
    }

    private static void elencoNomiPg(logica.Personaggio[] pg) {
        for(int i = 0; i< pg.length; i++){
            System.out.println((i+1) + ")\t" + pg[i].getNome());
        }
    }

    private static void elencoPg(logica.Personaggio[] pg) {
        for (logica.Personaggio personaggio : pg) {
            try {
                System.out.println(personaggio);
            } catch (NullPointerException e) {
                System.out.println("NullPointerException in sout di pg righe 13/19");
            }
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
    public static logica.Personaggio[] creaUltimoPg(logica.Personaggio[] pg){
        System.out.println("vuoi aggiungere un'altro pg\t\t\t(insert \"true\" or \"false\")");
        if(getBoolean()) return aggiungiPg(pg);
        else return pg;
    }

    private static logica.Personaggio[] aggiungiPg(logica.Personaggio[] oldPg) {
        logica.Personaggio[] newPg = new logica.Personaggio[oldPg.length+1];
        for(int i=0;i<oldPg.length;i++) newPg[i] = oldPg[i];
        return newPg;
    }

    public static logica.Personaggio creaPgSicuro(Lettore_csv reader, logica.Personaggio[] pg){
        System.out.println("personaggi attualmente creati:");
        elencoPg(pg);
        try{
            return creaPg(reader);
        }catch(csv.exception.IncompatibleCsvException e){
            System.out.println("questa serie di dati non è compatibile, potrebbe essere una vecchia versione di un personaggio o una serie sbagliata");
            return creaPgSicuro(reader, pg);
        }
    }
    public static logica.Personaggio creaPg(Lettore_csv reader){
        System.out.println("vuoi prendere un personaggio che esiste già nel file?\t\t\t(insert \"true\" or \"false\")");
        if(getBoolean()){
            reader.outElencoCsv();
            System.out.println("inserisci il numero relativo al personaggio da prelevare");
            String[] row = reader.tabel[getInt()].split(", ");
            if(row.length == 23) return new logica.Personaggio(row);
            else if(row.length == 31) return new logica.Giocante(row);
            else throw new csv.exception.IncompatibileRowLengthInCsvException();
        }else {
            System.out.println("vuoi creare un personaggio giocante?\t\t\t(insert \"true\" or \"false\")");
            if (getBoolean()) {
                return new logica.Giocante();
            } else {
                return new logica.Personaggio();
            }
        }
    }

    private static int getInt() {
        try{
            return new java.util.Scanner(System.in).nextInt();
        }catch (java.util.InputMismatchException e){
            return getInt();
        }
    }
}