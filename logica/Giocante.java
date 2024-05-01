package logica;
public class Giocante extends Personaggio{
    protected boolean ispirazione;
    protected int bonusSalvezza;
    public static boolean test;
    protected boolean[][] tiriControMorte;

    /**
     * costruttore di Personaggio giocante per input dell'utente
     */
    public Giocante(){
        super();
        // chiedo in input all'utente tutte le sue statistiche
        System.out.println(this.nome + " ha ispirazione?");
        this.ispirazione = getBoolean();
        this.bonusSalvezza = competenza;
        this.tiriControMorte = new boolean[2][3];
        System.out.println("quanti successi nei tiri contro morte ha " + nome);
        int successi = getInt();
        System.out.println("quanti fallimenti nei tiri contro morte ha " + nome);
        int fallimenti = getInt();
        for(int i=0;i<successi;i++){
            tiriControMorte[0][i] = true;
        }
        for(int i=0;i<fallimenti;i++){
            tiriControMorte[1][i] = true;
        }
            // dato che se un personaggio ha 3 fallimenti nel tiro salvezza contro morte posso assumere che sia morto nel momento in cui fallisce il 3° tiro
        this.morto = this.tiriControMorte[1][2];
    }

    /**
     * costruttore di Personaggio Giocante da file csv, prende come parametro un array di stringhe già diviso sulle virgole che immagazzina l'intera riga
     * @throws csv.exception.UnexpectedTypeOnCsvException se nella riga trovo dei tipi che non corrispondono a ciò che mi aspetto di trovare in quel punto
     * @param row un array di stringhe che rappresenta la riga del file
     */
    public Giocante(String[] row){
        super(row);
        try {
            //vado a inserire ogni elemento di questo personaggio presente nel file nel relativo attributo
            this.ispirazione = Boolean.parseBoolean(row[23]);
            bonusSalvezza = Integer.parseInt(row[24]);
            tiriControMorte = new boolean[2][3];
            this.tiriControMorte[0][0] = Boolean.parseBoolean(row[25]);
            this.tiriControMorte[0][1] = Boolean.parseBoolean(row[26]);
            this.tiriControMorte[0][2] = Boolean.parseBoolean(row[27]);
            this.tiriControMorte[1][0] = Boolean.parseBoolean(row[28]);
            this.tiriControMorte[1][1] = Boolean.parseBoolean(row[29]);
            this.tiriControMorte[1][2] = Boolean.parseBoolean(row[30]);
            morto = tiriControMorte[1][2];
        }catch (java.lang.NumberFormatException e){
            // nel caso le operazione di parsing trovino un tipo che non si aspettano nelle stringhe vado a lanciare
            // un errore che indica a chi lo lancia che questa riga non è valida per creare un personaggio
            throw new csv.exception.UnexpectedTypeOnCsvException();
        }
    }

    /**
     * metodo che serva a modificare un Personaggio Giocante
     */
    public void modifica(){
        String richiestaAttributoDaModificare = """
                cosa vuoi fare?
                    0)\tterminare la modifica
                    1)\tcambiare il nome
                    2)\tcambiare la vita
                    3)\tcambiare la classe armatura
                    4)\tcambiare il bonus competenza
                    5)\tcambiare i punti esperienza
                    6)\tcambiare il livello
                    7)\tcambiare il danno iniziale
                    8)\tcambiare una statistica
                    9)\tcambiare la fazione
                    10)\tcambiare l'ispirazione
                    11)\tcambiare i tiri contro morte
                """;
        // chiedo all'utente che attributo vuole modificare di questo personaggio
        System.out.println(richiestaAttributoDaModificare);
        switch (getInt()){
            default -> {
                // nel caso lui inserisca un valore non considerato termino e esco dalla modifica
                return;
            }
            case 1 -> {
                // nel caso l'utente inserisca 1 chiedo il nuovo valore per l'attributo nome
                System.out.println("inserisci il nome del personaggio");
                this.nome = getString();
            }
            case 2 -> {
                // nel caso l'utente inserisca 2 chiedo il nuovo valore per l'attributo punti ferita
                System.out.println("Inserisci i punti ferita di " + this.nome + " poi inserisci i punti ferita totali di " + this.nome);
                this.puntiFerita = new Vita(
                        getInt(),
                        getInt());
            }
            case 3 -> {
                // nel caso l'utente inserisca 3 chiedo il nuovo valore per l'attributo classe armatura
                System.out.println("Inserisci la classe armatura di " + this.nome);
                this.classeArmatura = getInt();
            }
            case 4 -> {
                // nel caso l'utente inserisca 4 chiedo il nuovo valore per l'attributo competenza
                System.out.println("Inserisci la competenza di " + this.nome);
                this.competenza = getInt();
                this.bonusSalvezza = competenza;
            }
            case 5 -> {
                // nel caso l'utente inserisca 5 chiedo il nuovo valore per l'attributo punti esperienza
                System.out.println("Inserisci i punti esperienza di " + this.nome);
                this.puntiEsperienza = getInt();
            }
            case 6 -> {
                // nel caso l'utente inserisca 6 chiedo il nuovo valore per l'attributo livello
                System.out.println("Inserisci il livello di " + this.nome);
                this.livello = getInt();
            }
            case 7 -> {
                // nel caso l'utente inserisca 7 chiedo il nuovo valore per l'attributo danno iniziale
                System.out.println("Inserisci il danno iniziale di " + this.nome);
                this.dannoIniziale = getInt();
            }
                // nel caso l'utente inserisca 8 chiedo il nuovo valore per gli attributi statistica
            case 8 -> modificaStatistica();
            case 9 -> {
                // nel caso l'utente inserisca 9 chiedo il nuovo valore per l'attributo amico
                System.out.println(this.nome + " è un amico?");
                this.amico = getBoolean();
            }
            case 10 -> {
                // nel caso l'utente inserisca 10 chiedo il nuovo valore per l'attributo ispirazione
                System.out.println(this.nome + " ha ispirazione?");
                this.ispirazione = getBoolean();
            }
            case 11 -> {
                // nel caso l'utente inserisca 11 chiedo il nuovo valore per l'attributo tiri contro morte
                this.tiriControMorte = new boolean[2][3];
                System.out.println("quanti successi nei tiri contro morte ha " + nome);
                int successi = getInt();
                System.out.println("quanti fallimenti nei tiri contro morte ha " + nome);
                int fallimenti = getInt();
                for (int i = 0; i < successi; i++) {
                    tiriControMorte[0][i] = true;
                }
                for (int i = 0; i < fallimenti; i++) {
                    tiriControMorte[1][i] = true;
                }
                this.morto = this.tiriControMorte[1][2];
            }
        }
        // richiamo la stessa funzione in modo tale da continuare con le modifiche
        modifica();
    }

    /**
     * metodo che serve a modificare le caratteristiche di un Personaggio Giocante
     */
    @Override
    protected void modificaStatistica() {
        String richiestaStatisticaDaModificare = """
                quale statistica vuoi modificare?
                    1)\tforza
                    2)\tdestrezza
                    3)\tcostituzione
                    4)\tintelligenza
                    5)\tsaggezza
                    6)\tcarisma
                    """;
        System.out.println(richiestaStatisticaDaModificare);
        switch (getInt()) {
            case 1 -> {
                System.out.println("Inserisci il punteggio di forza di " + this.nome + " e poi inserisci se " + nome + " ha bonus salvezza in forza?");
                this.forza = new Statistica(
                        getInt(),
                        getBoolean());
            }
            case 2 -> {
                System.out.println("Inserisci il punteggio di destrezza di " + this.nome + " e poi inserisci se " + nome + " ha bonus salvezza in destrezza?");
                this.destrezza = new Statistica(
                        getInt(),
                        getBoolean());
                this.iniziativa = this.destrezza.punteggio;
            }
            case 3 -> {
                System.out.println("Inserisci il punteggio di costituzione di " + this.nome + " e poi inserisci se " + nome + " ha bonus salvezza in costituzione?");
                this.costituzione = new Statistica(
                        getInt(),
                        getBoolean());
            }
            case 4 -> {
                System.out.println("Inserisci il punteggio di intelligenza di " + this.nome + " e poi inserisci se " + nome + " ha bonus salvezza in intelligenza?");
                this.intelligenza = new Statistica(
                        getInt(),
                        getBoolean());
            }
            case 5 -> {
                System.out.println("Inserisci il punteggio di saggezza di " + this.nome + " e poi inserisci se " + nome + " ha bonus salvezza in saggezza?");
                this.saggezza = new Statistica(
                        getInt(),
                        getBoolean());
            }
            case 6 -> {
                System.out.println("Inserisci il punteggio di carisma di " + this.nome + " e poi inserisci se " + nome + " ha bonus salvezza in carisma?");
                this.carisma = new Statistica(
                        getInt(),
                        getBoolean());
            }
        }
    }

    /**
     * metodo che tira un dado per questo personaggio se sto eseguendo un test verrà generato randomicamente un valore compreso tra minimo e massimo, altrimenti verrà chiesto in input all'utente
     * @param origin il risultato minimo di questo tiro di dado
     * @param bound il risultato massimo di questo tiro di dado
     * @return il risultato di questo tiro del dado
     */
    @Override
    public int tiro(int origin, int bound){
        if(test) return super.tiro(origin, bound);
        else {
            System.out.println("inserisci il risultado del tiro dei dadi di " + nome);
            return getInt(origin, bound);
        }
    }

    /**
     * metodo che esegue un tiro salvezza su una determinata caratteristica inserita come parametro.
     * <p>
     *     il parametro va inserito in questo modo:
     *     <p>"for" per la forza</p>
     *     <p>"des" per la destrezza</p>
     *     <p>"cos" per la costituzione</p>
     *     <p>"int" per l'intelligenza</p>
     *     <p>"sag" per la saggezza</p>
     *     <p>"car" per il carisma</p>
     * </p>
     * @return il risultato del tiro
     * @throws NoSuchStatistic se il parametro non corrisponde a nessuna caratteristica
     */
    @Override
    public int tiroSalvezza(String statistica) throws NoSuchStatistic {
        // rendo il parametro in caratteri minuscoli così che io lo possa riconoscere anche se le maiuscole sono presenti
        statistica = statistica.toLowerCase();
        switch (statistica) {
            // se trovo una caratteristica ritorno il relativo tiro di caratteristica
            // e, se ne ha diritto, aggiungo il bonus salvezza
            case "for" -> {
                return tiroForza() + (forza.salvezza ? bonusSalvezza : 0);
            }
            case "des" -> {
                return tiroDestrezza() + (destrezza.salvezza ? bonusSalvezza : 0);
            }
            case "cos" -> {
                return tiroCostituzione() + (costituzione.salvezza ? bonusSalvezza : 0);
            }
            case "int" -> {
                return tiroIntelligenza() + (intelligenza.salvezza ? bonusSalvezza : 0);
            }
            case "sag" -> {
                return tiroSaggezza() + (saggezza.salvezza ? bonusSalvezza : 0);
            }
            case "car" -> {
                return tiroCarisma() + (carisma.salvezza ? bonusSalvezza : 0);
            }
        }
        // nel caso in cui la stringa inserita non identifichi una caratteristica lancio un errore
        throw new NoSuchStatistic("la statistica inserita non è stata trovata");
    }

    /**
     * metodo che ritorna il tiro salvezza su di una carattaristica inserita dall'utente
     * @return risultato di un tiro salvezza
     */
    public int inputTiroSalvezza(){
        // chiedo all'utente di inserire la caratteristica su cui lanciare il tiro salvezza
        System.out.println("inserisci le prime tre lettere della statistica che vuoi usare");
        String car = new java.util.Scanner(System.in).nextLine();
        try {
            // ritorno il risultato del tiro salvezza
            return tiroSalvezza(car);
        } catch (NoSuchStatistic e) {
            // nel caso in cui l'utente ha inserito una stringa che non identifica una caratteristica lo avviso
            // e ritorno questo stesso metodo
            System.out.println("caratteristica non riconosciuta");
            return inputTiroSalvezza();
        }
    }

    /**
     * eseguo un singolo tiro contro morte e poi vado a verificare se il personaggio si è ripreso o se è morto definitivamente
     */
    public void tiroControMorte() {
        // eseguo un tiro puro
        int tiro = tiro(1, 20);
        // controllo se il tiro viene superato oppure no
        if (verificaTiro(tiro, 10)) {
            // se supero il tiro aumento i successi
            incrementaSuccessiControMorte(tiro);
        } else {
            // se fallisco il tiro aumento i fallimenti
            incrementaFallimentiControMorte();
        }
        // controllo se il personaggio è morto definitivamente o se si è ripreso
        verificaTiriControMorte();
    }

    /**
     * metodo che incrementa i successi nei tiri contro morte, nel caso in cui si faccia un successo critico (20) i successi verranno aumentati di due mentre se faccio un successo critico al primo tiro contro morte mi riprendo immediatamente
     */
    public void incrementaSuccessiControMorte(int tiro){
        int i=0;
        while (tiriControMorte[0][i]) i++;
        tiriControMorte[0][i] = true;
        if(tiro == 20) {
            if(i<2) tiriControMorte[0][i+1] = true;
            else if(!tiriControMorte[1][0]){
                for(boolean singoloTiro:tiriControMorte[0]) singoloTiro = true;
            }
        }
    }

    /**
     * metodo che incrementa i fallimenti nei tiri contro morte
     */
    public void incrementaFallimentiControMorte(){
        int i=0;
        while (tiriControMorte[1][i]) i++;
        tiriControMorte[1][i] = true;
    }

    /**
     * metodo che, nel caso siano terminati i tiri contro morte, fa riprendere il personaggio o lo uccide definitivamente
     */
    public void verificaTiriControMorte(){
        if(tiriControMorte[0][2]) puntiFerita.attuale = 1;
        else if(tiriControMorte[1][2]) morto = true;
    }

    /**
     * metodo che controlla se il personaggio è morto definitivamente in alternativa lancia il tiro contro morte quando necessario
     */
    @Override
    public boolean controllaMorto() {
        if(puntiFerita.attuale<=-(puntiFerita.totale/2)) morto = true;
        else if(puntiFerita.attuale<1) tiroControMorte();
        return morto;
    }

    /**
     * il toString() dei tiri contro morte
     * @return toString
     */
    public String tiriControMorteToString() {
        String info = "";
        info += "\tsuccessi:\t\t\t";
        for (int i = 0; i < 3; i++) info = info.concat(tiriControMorte[0][i] + "\t");
        info = info.concat("\n\tfallimenti:\t\t\t");
        for (int i = 0; i < 3; i++) info = info.concat(tiriControMorte[1][i] + "\t");
        return info + "\n";
    }

    /**
     * metodo che crea una stringa in un formato adatto a essere inserito in un file dati di tipo CommaSeparatedValue
     * <p>
     *     da chiamare solo all'interno del metodo toCsv()
     * </p>
     * @return una stringa che può essere concatenata agli altri dati per inserire l'oggetto in un file csv
     */
    private String tiriControMorteToCsv() {
        String info = "";
        info = info.concat("," + tiriControMorte[0][0]);
        info = info.concat("," + tiriControMorte[0][1]);
        info = info.concat("," + tiriControMorte[0][2]);
        info = info.concat("," + tiriControMorte[1][0]);
        info = info.concat("," + tiriControMorte[1][1]);
        info = info.concat("," + tiriControMorte[1][2]);
        return info;
    }

    @Override
    public String toString() {
        String info = super.toString();
        info += "ispirazione:\t\t\t" + ispirazione + "\n";
        info += "bonus salvezza:\t\t\t" + bonusSalvezza + "\n";
        info += tiriControMorteToString();
        return info;
    }

    /**
     * metodo che converte il personaggio in una stringa che può essere inserita in un file csv per salvare lo stato attuale di questo personaggio
     * @return una stringa che rappresenta il personaggio
     */
    @Override
    public String toCsv() {
        String info = super.toCsv();
        info += "," + ispirazione;
        info += "," + bonusSalvezza;
        info += tiriControMorteToCsv();
        return info;
    }

    /**
     * metodo che controlla se un personaggio è svenuto o morto
     * @return true se il personaggio non può combattere
     */
    @Override
    public boolean indisposto() {
        return super.indisposto() || (tiriControMorte[0][0] || tiriControMorte[1][0]);
    }
}