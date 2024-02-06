package csv;

import java.io.FileWriter;
import java.io.IOException;

public class Scrittore_csv {
    FileWriter writer;

    public Scrittore_csv(String file) {
        try {
            writer = new FileWriter(file);

            String descrizione = "";
            descrizione += "nome";
            descrizione += ", " + "iniziativa";
            descrizione += ", " + "punti ferita attuali";
            descrizione += ", " + "punti ferita totali";
            descrizione += ", " + "classe armatura";
            descrizione += ", " + "competenza";
            descrizione += ", " + "punti esperienza";
            descrizione += ", " + "livello";
            descrizione += ", " + "danno iniziale";
            descrizione += ", " + "amico";
            descrizione += ", " + "morto";


            descrizione += ", " + "punteggio forza";
            descrizione += ", " + "bonus forza";
            descrizione += ", " + "bonus salvezza forza";

            descrizione += ", " + "punteggio destrezza";
            descrizione += ", " + "bonus destrezza";
            descrizione += ", " + "bonus salvezza destrezza";

            descrizione += ", " + "punteggio costituzione";
            descrizione += ", " + "bonus costituzione";
            descrizione += ", " + "bonus salvezza costituzione";

            descrizione += ", " + "punteggio intelligenza";
            descrizione += ", " + "bonus intelligenza";
            descrizione += ", " + "bonus salvezza intelligenza";

            descrizione += ", " + "punteggio saggezza";
            descrizione += ", " + "bonus saggezza";
            descrizione += ", " + "bonus salvezza saggezza";

            descrizione += ", " + "punteggio carisma";
            descrizione += ", " + "bonus carisma";
            descrizione += ", " + "bonus salvezza carisma";


            descrizione += ", " + "ispirazione";
            descrizione += ", " + "bonus salvezza";


            descrizione += ", " + "primo successo tiri contro morte";
            descrizione += ", " + "secondo successo tiri contro morte";
            descrizione += ", " + "terzo successo tiri contro morte";

            descrizione += ", " + "primo fallimento tiri contro morte";
            descrizione += ", " + "secondo fallimento tiri contro morte";
            descrizione += ", " + "terzo fallimento tiri contro morte";

            writer.write(descrizione + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addCsv(String stringa) {
        try {
            writer.append(stringa);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void close(){
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}