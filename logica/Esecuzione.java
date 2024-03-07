package logica;

import csv.Lettore_csv;
import csv.Scrittore_csv;

public class Esecuzione{
    protected Personaggio[] pg;

    public Esecuzione(Personaggio[] pg) {
        this.pg = pg;
    }

    public boolean controlloMorte(){
        boolean[] fazione = new boolean[2];
        fazione[0] = false;
        fazione[1] = false;
        for(Personaggio i:pg){
            if(!i.morto) fazione[i.amico ? 0 : 1] = true;
        }
        return fazione[0] && fazione[1];
    }

    public void setPg(Lettore_csv reader){
        for (int i = 0; i < pg.length; i++) {
            if(pg[i] == null) {
                pg[i] = creaPgSicuro(reader, pg);
                System.out.println("hai sbagliato a inserire i dati o devi modificare qualcosa del personaggio già salvato?");
                if (Personaggio.getBoolean()) {
                    pg[i].modifica();
                }
            }
            if(i+1==pg.length) creaUltimoPg(pg);
        }
    }
    public void creaUltimoPg(logica.Personaggio[] pg){
        System.out.println("vuoi aggiungere un'altro pg");
        if(Personaggio.getBoolean()) aggiungiPg();
    }

    public logica.Personaggio creaPgSicuro(Lettore_csv reader, logica.Personaggio[] pg){
        System.out.println("personaggi attualmente creati:");
        elencoNomiPg();
        try{
            return creaPg(reader);
        }catch(csv.exception.IncompatibleCsvException e){
            System.out.println("questa serie di dati non è compatibile, potrebbe essere una vecchia versione di un personaggio o una serie sbagliata");
            return creaPgSicuro(reader, pg);
        }
    }
    public logica.Personaggio creaPg(Lettore_csv reader){
        System.out.println("vuoi prendere un personaggio che esiste già nel file?");
        if(Personaggio.getBoolean()){
            reader.outElencoPersonaggi();
            System.out.println("inserisci il numero relativo al personaggio da prelevare");
            String[] row = reader.tabel[Personaggio.getInt()].split(",");
            if(row.length == 23) return new logica.Personaggio(row);
            else if(row.length == 31) return new logica.Giocante(row);
            else throw new csv.exception.IncompatibileRowLengthInCsvException();
        }else {
            System.out.println("vuoi creare un personaggio giocante?");
            if (Personaggio.getBoolean()) {
                return new logica.Giocante();
            } else {
                return new logica.Personaggio();
            }
        }
    }

    protected void readFile(Lettore_csv reader, Lettore_csv readerFileName) {
        try{
            readerFileName.outElencoNomiFile();
            System.out.println("inserisci il numero relativo al file sorgente scelto");
            reader.getFile(readerFileName.tabel[Personaggio.getInt()]);
        }catch (java.io.FileNotFoundException e){
            System.out.println("questo file non esiste");
            readFile(reader, readerFileName);
        }
    }

    protected void importAll(Lettore_csv reader){
        for (int i = 0, j = 1; i < pg.length; i++, j++) {
            if(pg[i] == null) pg[i] = importaPgSicuro(reader, j);
            if(importaPgSicuro(reader, j+1) != null && i+1==pg.length) aggiungiPg();
        }
        System.out.println("personaggi creati finora:");
        elencoNomiPg();
    }
    public logica.Personaggio importaPgSicuro(Lettore_csv reader, int i){
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
    protected void aggiungiPg() {
        logica.Personaggio[] newPg = new logica.Personaggio[pg.length+1];
        for(int i=0;i<pg.length;i++) newPg[i] = pg[i];
        this.pg =  newPg;
    }
    protected void elencoNomiPg() {
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
    protected void elencoPg() {
        for (logica.Personaggio personaggio : pg) {
            try {
                System.out.println(personaggio);
            } catch (NullPointerException e) {
                System.out.println("NullPointerException in sout di pg righe 13/19");
            }
        }
    }
    public void saveInFile(String nomeFile) {
        Scrittore_csv writer = new Scrittore_csv(nomeFile);
        try {
            for (Personaggio personaggio : pg) {
                writer.addCsv(personaggio.toCsv() + "\n");
            }
        } catch (Error | Exception e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }
}