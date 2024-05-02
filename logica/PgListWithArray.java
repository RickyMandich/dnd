package logica;
public class PgListWithArray {
    protected Personaggio[] pg;

    public PgListWithArray(Personaggio[] pg) {
        this.pg = pg;
    }

    /**
     * metodo che controlla se c'è almeno un personaggio vivo in entrambe le fazioni per sapere se il combattimento deve continuare o se è finito
     * @return booleano che indica se il combattimento deve continuare
     */
    public boolean controlloMorte(){
        boolean[] fazione = new boolean[2];
        fazione[0] = false;
        fazione[1] = false;
        for(Personaggio i:pg){
            if(!i.morto) fazione[i.amico ? 0 : 1] = true;
        }
        return fazione[0] && fazione[1];
    }

    /**
     * metodo che serve a popolare l'array di personaggi
     * @param reader oggetto di tipo csv.Lettore_csv che contiene la copia del file letto per importare un personaggio dal file
     */
    public void setPg(csv.Lettore_csv reader){
        for (int i = 0; i < pg.length; i++) {
            if(pg[i] == null) {
                pg[i] = creaPgSicuro(reader);
                System.out.println("hai sbagliato a inserire i dati o devi modificare qualcosa del personaggio già salvato?");
                if (Personaggio.getBoolean()) {
                    pg[i].modifica();
                }
            }
            if(i+1==pg.length) creaUltimoPg();
        }
    }

    /**
     * metodo che chiede all'utente se vuole aumentare la dimensione dell'array di Personaggi e in base alla risposta dell'utente lo ingrandisce
     */
    public void creaUltimoPg(){
        System.out.println("vuoi aggiungere un'altro pg");
        if(Personaggio.getBoolean()) aggiungiPg();
    }

    /**
     * metodo che va a creare un singolo personaggio proponendo la scelta di crearlo da zero o importarlo dal file, in entrambi i casi fornisce l'opportunità di modificarlo. nel caso in cui venga selezionato un personaggio non compatibile dal file ricomincio
     * @param reader oggetto di tipo csv.Lettore_csv che contiene la copia del file letto per importare un personaggio dal file
     * @return il personaggio che è stato creato
     */
    public logica.Personaggio creaPgSicuro(csv.Lettore_csv reader){
        System.out.println("personaggi attualmente creati:");
        elencoNomiPg();
        try{
            return creaPg(reader);
        }catch(csv.exception.IncompatibleCsvException e){
            System.out.println("questa serie di dati non è compatibile, potrebbe essere una vecchia versione di un personaggio o una serie sbagliata");
            return creaPgSicuro(reader);
        }
    }

    /**
     * metodo che va a creare effettivamente il personaggio con i criteri di creaPgSicuro
     * <p>
     *     ATTENZIONE: usare questo metodo solo da creaPgSicuro()
     * </p>
     * @param reader oggetto di tipo csv.Lettore_csv che contiene la copia del file letto per importare un personaggio dal file
     * @return il personaggio che viene creato
     */
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

    /**
     * metodo che serve per scegliere un file sorgente da cui prendere dei peronaggi salvati in precedenza
     * @param reader  oggetto di tipo csv.Lettore_csv con cui leggere il file sorgente
     * @param readerFileName  oggetto di tipo csv.Lettore_csv che contiene la copia del file contenente tutti i file sorgente disponibili
     */
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

    /**
     * metodo che importa tutti i personaggi presenti nel file nell'array presente come attributo deel'oggetto su cui viene chiamato
     * @param reader  oggetto di tipo csv.Lettore_csv che contiene la copia del file letto per importare un personaggio dal file
     */
    protected void importAll(csv.Lettore_csv reader){
        for (int i = 0, j = 1; i < pg.length; i++, j++) {
            if(pg[i] == null) pg[i] = importaPgSicuro(reader, j);
            if(importaPgSicuro(reader, j+1) != null && i+1==pg.length) aggiungiPg();
        }
    }

    /**
     * metodo che importa il personaggio presente nel file alla riga i passata come parametro e ritorna il personaggio creato
     * @param reader  oggetto di tipo csv.Lettore_csv che contiene la copia del file letto per importare un personaggio dal file
     * @param i riga alla quale si trova il personaggio da importare dal file
     * @return il personaggio che viene creato
     */
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

    /**
     * metodo che aumenta la dimensione dell'array di Personaggi di uno
     */
    protected void aggiungiPg() {
        logica.Personaggio[] newPg = new logica.Personaggio[pg.length+1];
        for(int i=0;i<pg.length;i++) newPg[i] = pg[i];
        this.pg =  newPg;
    }

    /**
     * metodo che stampa un'elenco contenente il nome di tutti i personaggi e la loro fazione
     */
    protected void elencoNomiPg() {
        for(int i = 0; i< pg.length; i++){
            String pgI;
            try {
                pgI = (pg[i].amico ? "a" : "ne") + "mico\t" + pg[i].nome;
            }catch (NullPointerException e){
                pgI = "null";
            }
            System.out.println((i + 1) + "\t" + pgI);
        }
    }

    /**
     * metodo che stampa un'elenco contenente tutte le statistiche dei personaggi allineandole a colonne per renderle più leggibili
     */
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

    /**
     * metodo che prende in input il nome del file in cui salvare i personaggi e i esporta con tutte le loro statistiche attuali
     * @param nomeFile nome del file in cui esportare i personaggi
     */
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

    /**
     * metodo che mette in ordine tutti i personaggi sulla base del valore di iniziativa
     */
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

    /**
     * metodo che controlla di avere ancora personaggi vivi in ogni fazione e poi tira iniziativa e li mette in ordine per combattere
     * @return un booleano che indica se la preparazione è stata compiuta con successo o manca una delle fazioni
     */
    public boolean preparazione(){
        for(Personaggio Pg:pg){
            Pg.controllaMorto();
        }
        if(!controlloMorte()) return false;
        for(Personaggio Pg:pg){
            Pg.iniziativa = Pg.tiro(1, 20, Pg.destrezza.bonus);
        }
        order();
        return true;
    }

    /**
     * metodo che va a gestire il combattimento
     */
    public void combattimento(){
        int i=0;
        do{
            for(Personaggio pg:pg){
                if(pg.indisposto()){
                    System.out.println("questo personaggio non può combattere perchè");
                }
            }
            i++;
            if(i==3){
                for(Personaggio pg:pg){
                    pg.puntiFerita.attuale = -3;
                }
            }
        }while (controlloMorte());
    }
}