package csv;
public class TestElencoFileDati {
    public static void main(String[] args) {
        Lettore_csv readerFileName = new Lettore_csv();
        try{
            readerFileName.getFile("csv\\elencoFileDati");
        }catch (java.io.FileNotFoundException e){
            e.printStackTrace();
        }
        readerFileName.outElencoNomiFile();
        Lettore_csv reader = new Lettore_csv();
        inputPG.readFile(reader);
        logica.Personaggio[] pg = inputPG.importAll(new logica.Personaggio[1], reader);
        System.out.println("inserisci il nome del file da creare per salvare l'attuale esecuzione\t\tATTENZIONE: NEL CASO IL FILE ESISTA GIÀ VERRÀ SOVRASCRITTO");
        String nomeFile = "csv\\file_dati\\" + new java.util.Scanner(System.in).nextLine() + ".csv";
        Scrittore_csv saveNewFileName = new Scrittore_csv(readerFileName.tabel, "csv\\elencoFileDati_test");
        try{
            saveNewFileName.addFileName(nomeFile);
        }finally {
            saveNewFileName.close();
        }
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
}