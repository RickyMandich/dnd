package csv;
public class TestVari {
    public static void main(String[] args){
        /*
        Lettore_csv readerFileName = new Lettore_csv();
        try{
            readerFileName.getFile("csv\\elencoFileDati");
        }catch (java.io.FileNotFoundException e){
            e.printStackTrace();
        }
        Lettore_csv reader = new Lettore_csv();
        readerFileName.outElencoNomiFile();
        inputPG.readFile(reader, readerFileName);
        logica.Personaggio[] pg = inputPG.importAll(new logica.Personaggio[1], reader);
        System.out.println("inserisci il nome del file da creare per salvare l'attuale esecuzione\t\tATTENZIONE: NEL CASO IL FILE ESISTA GIÀ VERRÀ SOVRASCRITTO");
        String nomeFile = "csv\\file_dati\\" + new java.util.Scanner(System.in).nextLine() + "_Personaggi.csv";
        Scrittore_csv saveNewFileName = new Scrittore_csv(readerFileName, "csv\\elencoFileDati");
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
        }finally {
            writer.close();
        }
        /**/

        Lettore_csv reader = new Lettore_csv();
        try {
            reader.getFile("csv\\elencoFileDati");
            reader.orderAndCompact();
            reader.outElencoNomiFile();
        }catch (java.io.FileNotFoundException e){
            e.printStackTrace();
        }
    }
}