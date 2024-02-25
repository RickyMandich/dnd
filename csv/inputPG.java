package csv;
public class inputPG{
    public static void main(String[] args) {
        Lettore_csv readerFileName = new Lettore_csv();
        Lettore_csv reader = new Lettore_csv();
        readFile(reader);
        System.out.println("stai eseguendo un test?");
        logica.Giocante.test = getBoolean();
        System.out.println("inserisci il numero di personaggi che vuoi creare");
        logica.Personaggio[] pg = new logica.Personaggio[getInt()];
        reader.outElencoPersonaggi();
        System.out.println("vuoi importare tutti i personaggi presenti nel file?");
        if(getBoolean()) pg = importAll(pg, reader);
        for (int i = 0; i < pg.length; i++) {
            if(pg[i] == null) {
                pg[i] = creaPgSicuro(reader, pg);
                System.out.println("hai sbagliato a inserire i dati o devi modificare qualcosa del personaggio già salvato?");
                if (getBoolean()) {
                    pg[i].modifica();
                }
            }
            if(i+1==pg.length) pg = creaUltimoPg(pg);
        }
        elencoNomiPg(pg);
        System.out.println("vuoi i dettagli dei personaggi?");
        if(getBoolean()) elencoPg(pg);
        System.out.println("vuoi salvare i dati dei personaggi?");
        if (getBoolean()) {
            System.out.println("inserisci il nome del file da creare per salvare l'attuale esecuzione\t\tATTENZIONE: NEL CASO IL FILE ESISTA GIÀ VERRÀ SOVRASCRITTO");
            String nomeFile = "csv\\file_dati\\" + new java.util.Scanner(System.in).nextLine() + ".csv";
//            Scrittore_csv saveNewFileName = new Scrittore_csv("csv\\elencoFileDati");
//            addFileName(saveNewFileName);
            Scrittore_csv writer = new Scrittore_csv(nomeFile);
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
        System.out.print("fine programma");
    }

    protected static void readFile(Lettore_csv reader) {
        try{
            System.out.println("inserisci il nome del file sorgente\t(la cartella in cui salvarlo è già inserite e la parte finale del nome \"_Personaggi.csv\" pure)");
            reader.getFile("csv\\file_dati\\" + new java.util.Scanner(System.in).nextLine() + "_Personaggi.csv");
        }catch (java.io.FileNotFoundException e){
            System.out.println("questo file non esiste");
            readFile(reader);
        }
    }
    protected static void readFile(Lettore_csv reader, Lettore_csv readerFileName) {
        try{
            System.out.println("inserisci il numero relativo al file sorgente scelto");
            reader.getFile(readerFileName.tabel[getInt()]);
        }catch (java.io.FileNotFoundException e){
            System.out.println("questo file non esiste");
            readFile(reader, readerFileName);
        }
    }

    protected static boolean getBoolean() {
        System.out.println("(insert \"true\" or \"false\")");
        try{
            return new java.util.Scanner(System.in).nextBoolean();
        }catch (java.util.InputMismatchException e){
            return getBoolean();
        }
    }

    protected static void elencoNomiPg(logica.Personaggio[] pg) {
        for(int i = 0; i< pg.length; i++){
            String pgI;
            try {
                pgI = pg[i].getNome();
            }catch (NullPointerException e){
                pgI = "null";
            }
            System.out.println((i+1) + ")\t" + pgI);
        }
    }

    protected static void elencoPg(logica.Personaggio[] pg) {
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
        System.out.println("vuoi aggiungere un'altro pg");
        if(getBoolean()) return aggiungiPg(pg);
        else return pg;
    }

    protected static logica.Personaggio[] aggiungiPg(logica.Personaggio[] oldPg) {
        logica.Personaggio[] newPg = new logica.Personaggio[oldPg.length+1];
        for(int i=0;i<oldPg.length;i++) newPg[i] = oldPg[i];
        return newPg;
    }

    public static logica.Personaggio creaPgSicuro(Lettore_csv reader, logica.Personaggio[] pg){
        System.out.println("personaggi attualmente creati:");
        elencoNomiPg(pg);
        try{
            return creaPg(reader);
        }catch(csv.exception.IncompatibleCsvException e){
            System.out.println("questa serie di dati non è compatibile, potrebbe essere una vecchia versione di un personaggio o una serie sbagliata");
            return creaPgSicuro(reader, pg);
        }
    }
    public static logica.Personaggio creaPg(Lettore_csv reader){
        System.out.println("vuoi prendere un personaggio che esiste già nel file?");
        if(getBoolean()){
            reader.outElencoPersonaggi();
            System.out.println("inserisci il numero relativo al personaggio da prelevare");
            String[] row = reader.tabel[getInt()].split(",");
            if(row.length == 23) return new logica.Personaggio(row);
            else if(row.length == 31) return new logica.Giocante(row);
            else throw new csv.exception.IncompatibileRowLengthInCsvException();
        }else {
            System.out.println("vuoi creare un personaggio giocante?");
            if (getBoolean()) {
                return new logica.Giocante();
            } else {
                return new logica.Personaggio();
            }
        }
    }

    protected static logica.Personaggio[] importAll(logica.Personaggio[] pg, Lettore_csv reader){
        for (int i = 0, j = 1; i < pg.length; i++, j++) {
            if(pg[i] == null) pg[i] = importaPgSicuro(reader, j);
            if(importaPgSicuro(reader, j+1) != null && i+1==pg.length) pg = aggiungiPg(pg);
        }
        System.out.println("personaggi creati finora:");
        elencoNomiPg(pg);
        return pg;
    }
    public static logica.Personaggio importaPgSicuro(Lettore_csv reader, int i){
        try{
            String[] row = reader.tabel[i].split(",");
            if(row.length == 23) return new logica.Personaggio(row);
            else if(row.length == 31) return new logica.Giocante(row);
            else throw new csv.exception.IncompatibileRowLengthInCsvException();
        }catch (csv.exception.IncompatibileRowLengthInCsvException e){
            System.out.println("questa serie di dati non è compatibile, potrebbe essere una vecchia versione di un personaggio o una serie sbagliata per cui la ignoro e inserisco la successiva");
            return importaPgSicuro(reader, i+1);
        }catch (java.lang.ArrayIndexOutOfBoundsException e){
            return null;
        }
    }

    protected static int getInt() {
        try{
            return new java.util.Scanner(System.in).nextInt();
        }catch (java.util.InputMismatchException e){
            System.out.println("devi inserire un numero intero");
            return getInt();
        }
    }
}