package logica;

import csv.Lettore_csv;

public class Test{
    public static void main(String[] args) {
        //creazione di oggetti necessari sia durante la creazione che durante la chiusura del programma
        PgListWithArray exec = null;
        csv.Lettore_csv readerFileName = null;
        // creazione di un'elenco di personaggi
        try {
            readerFileName = new csv.Lettore_csv();
            // lettura dell'elenco di file sorgenti disponibili
            try {
                readerFileName.getFile("csv\\elencoFileDati");
            } catch (java.io.FileNotFoundException e) {
                e.printStackTrace();
            }
            csv.Lettore_csv reader = new csv.Lettore_csv();
            // richiesta all'utente se sta testando il programma oppure no (utile per risparmiare tempo negli input di valori che vengono invece generati automaticamente)
            System.out.println("stai eseguendo un test?");
            Giocante.test = Personaggio.getBoolean();
            //creazione di della struttura dell'elenco che poi verrà riempito di personaggi
                // poi si potrà modificarne la dimensione in caso sia troppo corto ma è più efficente dare già il numero corretto di personaggi che verranno creati
            System.out.println("inserisci il numero di personaggi che vuoi creare");
            exec = new PgListWithArray(new Personaggio[Personaggio.getInt(1)]);
            //lettura del file sorgente e print del suo contenuto
            exec.readFile(reader, readerFileName);
            reader.outElencoPersonaggi();
            // richiesta per inserire tutti i personaggi presenti nel file in maniera rapida
            System.out.println("vuoi importare tutti i personaggi presenti nel file?");
            if (Personaggio.getBoolean()) exec.importAll(reader);
            // proseguo nel riempire l'elenco
            exec.setPg(reader);
            exec.elencoNomiPg();
            System.out.println("vuoi i dettagli dei personaggi?");
            if (Personaggio.getBoolean()) exec.elencoPg();
            System.out.println("vuoi salvare lo stato dei personaggi prima di iniziare il combattimento?");
            if (Personaggio.getBoolean()) {
                // generazione di un file di output con le statistiche di tutti i personaggi
                printFile(readerFileName, exec);
            }
            // simulazione dello scontro
            if(exec.preparazione()) {
                System.out.println("ora inizia il combattimento");
                exec.combattimento();
            } else System.out.println("non posso iniziare il combattimente visto che le condizioni non sono soddisfatte (almeno una delle due fazioni non ha personaggi in vita)");
        }finally {
            // chiusura dei vari oggetti e salvataggio finale
            System.out.println("vuoi salvare i dati dei personaggi?");
            if (Personaggio.getBoolean()) {
                printFile(readerFileName, exec);
            }
            System.out.print("fine programma");
        }
    }

    static void printFile(Lettore_csv readerFileName, PgListWithArray exec) {
        // generazione di un file di output con le statistiche di tutti i personaggi
        System.out.println("inserisci il nome del file da creare per salvare l'attuale esecuzione\nATTENZIONE: NEL CASO IL FILE ESISTA GIÀ VERRÀ SOVRASCRITTO");
        String nomeFile = new java.util.Scanner(System.in).nextLine() + ".csv";
        try {
            exec.saveInFile("csv\\file_dati\\" + nomeFile);
            csv.Scrittore_csv saveNameFile = new csv.Scrittore_csv(readerFileName, nomeFile);
            saveNameFile.close();
        } catch (Exception e) {
            System.out.println("file non valido, il salvataggio non verrà eseguito");
        }
    }
}
