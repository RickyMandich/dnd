package creazionecsv;

import java.io.FileWriter;
import java.io.IOException;

public class Scrittore_csv {
    FileWriter writer;

    public Scrittore_csv(String file) {
        try {
            writer = new FileWriter(file);

            String descrizione = "nome, ";
            descrizione += "iniziativa, ";
            descrizione += "punti ferita, ";
            descrizione += "punti ferita totali, ";
            descrizione += "classe armatura, ";
            descrizione += "competenza, ";

            descrizione += "punteggio forza, ";
            descrizione += "punteggio destrezza, ";
            descrizione += "punteggio costituzione, ";

            descrizione += "punteggio intelligenza, ";
            descrizione += "punteggio saggezza, ";
            descrizione += "punteggio carisma, ";

            descrizione += "punti esperienza, ";
            descrizione += "livello, ";
            descrizione += "ispirazione, ";
            descrizione += "danno iniziale, ";
            descrizione += "amico, ";

            descrizione += "primo successo contro morte, ";
            descrizione += "secondo successo contro morte, ";
            descrizione += "terzo successo contro morte, ";

            descrizione += "primo fallimento contro morte, ";
            descrizione += "secondo fallimento contro morte, ";
            descrizione += "terzo fallimento contro morte, ";

            descrizione += "morto";

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