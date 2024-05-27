package csv;

import java.io.FileNotFoundException;

public class TestVari {
    public static void main(String[] args){
        Lettore_csv readerFileName = new Lettore_csv();
        try{
            readerFileName.getFile("csv\\elencoFileDati");
        }catch (java.io.FileNotFoundException e){
            e.printStackTrace();
        }
        Lettore_csv reader = new Lettore_csv();
        readerFileName.outElencoNomiFile();
        for(String file: readerFileName.tabel){
            try {
                System.out.println("inizio a lavorare su " + file);
                reader.getFile(file);
                String info = "";
                System.out.println("concateno tutte le righe a eccezzione del nome dei campi");
                for(String line: reader.tabel){
                    if(!info.split(",")[0].equals("nome")) info = info.concat(line + "\n");
                }
                System.out.println("sostituisco\",NULL\" con \"\"");
                info = info.replace(",NULL", "");
                Scrittore_csv writer = new Scrittore_csv(file);
                System.out.println("scrivo sul file i dati elaborati");
                writer.addCsv(info);
            } catch (FileNotFoundException e) {
                System.out.println("Il file " + file + " ha lanciato un'eccezione");
                e.printStackTrace();
            }
        }
    }
}