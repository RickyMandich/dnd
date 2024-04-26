package logica;
public class PgListWithArray {
    protected Personaggio[] pg;

    public PgListWithArray(Personaggio[] pg) {
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

    public void setPg(csv.Lettore_csv reader){
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

    public logica.Personaggio creaPgSicuro(csv.Lettore_csv reader, logica.Personaggio[] pg){
        System.out.println("personaggi attualmente creati:");
        elencoNomiPg();
        try{
            return creaPg(reader);
        }catch(csv.exception.IncompatibleCsvException e){
            System.out.println("questa serie di dati non è compatibile, potrebbe essere una vecchia versione di un personaggio o una serie sbagliata");
            return creaPgSicuro(reader, pg);
        }
    }
    public logica.Personaggio creaPg(csv.Lettore_csv reader){
        System.out.println("vuoi prendere un personaggio che esiste già nel file?");
        if(Personaggio.getBoolean() && reader.tabel.length != 0){
            reader.outElencoPersonaggi();
            System.out.println("inserisci il numero relativo al personaggio da prelevare");
            String[] row = reader.tabel[Personaggio.getInt(1, reader.tabel.length-1)].split(",");
            try{
                if(row.length == 23) return new logica.Personaggio(row);
                else if(row.length == 31) return new logica.Giocante(row);
                else throw new csv.exception.IncompatibileRowLengthInCsvException();
            }catch (csv.exception.UnexpectedTypeOnCsvException e){
                System.out.println("in questa riga non è presente un personaggio compatibile");
                return creaPg(reader);
            }
        }else {
            if(reader.tabel.length == 0) System.out.println("dato che nel file non sono presenti personaggi lo creo da 0");
            System.out.println("vuoi creare un personaggio giocante?");
            if (Personaggio.getBoolean()) {
                return new logica.Giocante();
            } else {
                return new logica.Personaggio();
            }
        }
    }

    protected void readFile(csv.Lettore_csv reader, csv.Lettore_csv readerFileName) {
        try{
            readerFileName.outElencoNomiFile();
            System.out.println("inserisci il numero relativo al file sorgente scelto");
            reader.getFile("csv\\file_dati\\" + readerFileName.tabel[Personaggio.getInt()]);
        }catch (java.io.FileNotFoundException e){
            System.out.println("questo file non esiste");
            readFile(reader, readerFileName);
        }
    }

    protected void importAll(csv.Lettore_csv reader){
        for (int i = 0, j = 1; i < pg.length; i++, j++) {
            if(pg[i] == null) pg[i] = importaPgSicuro(reader, j);
            if(importaPgSicuro(reader, j+1) != null && i+1==pg.length) aggiungiPg();
        }
    }
    public logica.Personaggio importaPgSicuro(csv.Lettore_csv reader, int i){
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
        String[] simulazioneFile = new String[pg.length+1];
        simulazioneFile[0] = csv.Scrittore_csv.getDescrizioneCampi();
        int i=1;
        for(Personaggio personaggio:pg){
            simulazioneFile[i] = personaggio.toCsv();
            i++;
        }
        for(i=0;i<simulazioneFile.length;i++){
            System.out.print(i + ")\t");
            String[] row = simulazioneFile[i].split(",");
            for(int j=0;j<row.length;j++){
                System.out.printf("%-35s", row[j]);
            }
            System.out.println();
        }
    }
    public void saveInFile(String nomeFile) {
        csv.Scrittore_csv writer = new csv.Scrittore_csv(nomeFile);
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

    public void order(){
        boolean scambia;
        do {
            scambia = false;
            for(int i=0;i<pg.length-1;i++){
                if(pg[i].iniziativa<pg[i+1].iniziativa){
                    Personaggio temp = pg[i];
                    pg[i] = pg[i+1];
                    pg[i+1] = temp;
                    scambia = true;
                }
            }
        }while (scambia);
    }

    public void preparazione(){
        for(Personaggio Pg:pg){
            Pg.iniziativa = Pg.tiro(1, 20, Pg.destrezza.bonus);
        }
        order();
    }
}