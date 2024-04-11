package logica;
public class Test{
    public static void main(String[] args) {
        csv.Lettore_csv readerFileName = new csv.Lettore_csv();
        try{
            readerFileName.getFile("csv\\elencoFileDati");
        }catch (java.io.FileNotFoundException e){
            e.printStackTrace();
        }
        csv.Lettore_csv reader = new csv.Lettore_csv();
        System.out.println("stai eseguendo un test?");
        Giocante.test = Personaggio.getBoolean();
        System.out.println("inserisci il numero di personaggi che vuoi creare");
        Esecuzione exec = new Esecuzione(new Personaggio[Personaggio.getInt(1)]);
        exec.readFile(reader, readerFileName);
        reader.outElencoPersonaggi();
        System.out.println("vuoi importare tutti i personaggi presenti nel file?");
        if(Personaggio.getBoolean()) exec.importAll(reader);
        exec.setPg(reader);
        exec.elencoNomiPg();
        System.out.println("vuoi i dettagli dei personaggi?");
        if(Personaggio.getBoolean()) exec.elencoPg();

        exec.preparazione();

        System.out.println("vuoi salvare i dati dei personaggi?");
        if (Personaggio.getBoolean()) {
            System.out.println("inserisci il nome del file da creare per salvare l'attuale esecuzione\t\tATTENZIONE: NEL CASO IL FILE ESISTA GIÀ VERRÀ SOVRASCRITTO");
            String nomeFile = "csv\\file_dati\\" + new java.util.Scanner(System.in).nextLine() + ".csv";
            csv.Scrittore_csv saveNameFile = new csv.Scrittore_csv(readerFileName, nomeFile);
            saveNameFile.close();
            exec.saveInFile(nomeFile);
        }
        System.out.print("fine programma");
    }
}