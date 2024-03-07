package logica;

import csv.Lettore_csv;
import csv.Scrittore_csv;

public class Test{
    public static void main(String[] args) {
        Lettore_csv readerFileName = new Lettore_csv();
        try{
            readerFileName.getFile("csv\\elencoFileDati");
        }catch (java.io.FileNotFoundException e){
            e.printStackTrace();
        }
        Lettore_csv reader = new Lettore_csv();
        readFile(reader, readerFileName);
        System.out.println("stai eseguendo un test?");
        logica.Giocante.test = getBoolean();
        System.out.println("inserisci il numero di personaggi che vuoi creare");
        logica.Personaggio[] pg = new logica.Personaggio[getInt(1)];
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
            Scrittore_csv saveNameFile = new Scrittore_csv(readerFileName, nomeFile);
            saveNameFile.close();
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
}