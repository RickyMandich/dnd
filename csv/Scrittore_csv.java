package csv;
public class Scrittore_csv {
    java.io.FileWriter writer;

    /**
     * costruttore che prende come parametri il file contenente l'elenco dei file sorgente disponibili e il nuovo file in cui esportare i personaggi e aggiorna l'elenco dei file con quello nuovo, mette il tutto in ordine alfabetico ed elimina i doppioni
     * @param reader oggetto di tipo Lettore_csv che contiene una copia del file contenente la lista dei file sorgente
     * @param file stringa contenente l'indirizzo del file in cui esportare i personaggi attuali
     */
    public Scrittore_csv(Lettore_csv reader, String file) {
        try {
            writer = new java.io.FileWriter("csv/elencoFileDati");
            reader.aggiungiRiga(file);
            reader.orderAndCompact();

            String info = "";
            for (int i = 0; i < reader.tabel.length; i++) {
                info = info.concat(reader.tabel[i] + "\n");
            }
            writer.write(info);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * costruttore che prende come parametro una stringa contenente l'indirizzo del file in cui esportare i personaggi
     * <p>
     * ATTENZIONE: se questo file esiste già verrà sovrascritto
     * </p>
     * @param file stringa contenente l'indirizzo del file di output
     */
    public Scrittore_csv(String file){
        try {
            writer = new java.io.FileWriter(file);
            String info = getDescrizioneCampi();
            writer.write(info + "\n");
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * metodo che genera una stringa contenente la descrizione di tutti gli attributi di un personaggio giocante che funga da prima riga dei file sorgente per rendere comprensibile a cosa corrispondano i valori presenti
     * @return la stringa generata
     */
    public static String getDescrizioneCampi() {
        String info = "nome";
        info += puntiFeritaToCsv();
        info += "," + "classeArmatura";
        info += "," + "competenza";
        info += "," + "puntiEsperienza";
        info += "," + "livello";
        info += "," + "dannoIniziale";
        info += "," + "amico";
        info += "," + "morto";
        info += caratteristicaToCsv("forza");
        info += caratteristicaToCsv("destrezza");
        info += caratteristicaToCsv("costituzione");
        info += caratteristicaToCsv("intelligenza");
        info += caratteristicaToCsv("saggezza");
        info += caratteristicaToCsv("carisma");

        info += "," + "ispirazione";
        info += "," + "bonusSalvezza";
        info += tiriControMorteToCsv();
        return info;
    }

    /**
     * metodo che serve a generare una stringa che fornisce la descrizione della nested class vita
     * <p>
     *     ATTENZIONE: va usata esclusivamente nel metodo getDescrizioneCampi()
     * </p>
     * @return la stringa generata
     */
    private static String puntiFeritaToCsv(){
        String info = "";
        info += ",punti ferita attuali";
        info += ",punti ferita totali";
        return info;
    }

    /**
     * metodo che serve a generare una stringa che fornisce la descrizione della nested class statistica
     * <p>
     *     ATTENZIONE: va usata esclusivamente nel metodo getDescrizioneCampi()
     * </p>
     * @return la stringa generata
     */
    public static String caratteristicaToCsv(String caratteristica){
        String info = "";
        info += ",punteggio " + caratteristica;
        info += ",bonus salvezza " + caratteristica;
        return info;
    }

    /**
     * metodo che serve a generare una stringa che fornisce la descrizione dell'array di booleani tiri contro morte
     * <p>
     *     ATTENZIONE: va usata esclusivamente nel metodo getDescrizioneCampi()
     * </p>
     * @return la stringa generata
     */
    public static String tiriControMorteToCsv(){
        String info = "";
        for(int i=0;i<2;i++){
            for(int j=0;j<3;j++){
                info = info.concat(tiroControMorteToCsv(i, j));
            }
        }
        return info;
    }

    /**
     * metodo che serve a generare una stringa che fornisce la descrizione di un singolo tiro contro morte
     * <p>
     *     ATTENZIONE: va usata esclusivamente nel metodo tiriControMOrteToCsv()
     * </p>
     * @return la stringa generata
     */
    public static String tiroControMorteToCsv(int i, int j){
        return "," + (j+1) + ((i==0) ? " successo" : " fallimento") + " tiri contro morte";
    }

    /**
     * metodo che aggiunge la stringa passata come parametro al file a cui si riferisce l'oggetto su cui viene chiamato
     * @param stringa la stringa da aggiungere al file
     */
    public void addCsv(String stringa) {
        try {
            writer.append(stringa);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * metodo che chiude lo scrittore e libera il file
     * <p>
     *     ATTENZIONE: se lo scrittore non viene chiuso il file risulterà completamente vuoto
     * </p>
     */
    public void close(){
        try {
            writer.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}