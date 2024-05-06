package logica;
public class Personaggio {
    protected String nome;
    protected int iniziativa;
    protected Vita puntiFerita;
    protected int classeArmatura;
    protected int competenza;
    protected int puntiEsperienza;
    protected int livello;
    protected int tiro;
    protected int dannoIniziale;
    protected boolean amico;
    protected boolean morto;
    protected Statistica forza;
    protected Statistica destrezza;
    protected Statistica costituzione;
    protected Statistica intelligenza;
    protected Statistica saggezza;
    protected Statistica carisma;

    /**
     * costruttore di Personaggio per input dell'utente
     */
    public Personaggio() {
        System.out.println("inserisci il nome del personaggio");
        this.nome = getString();
        System.out.println("Inserisci i punti ferita di " + this.nome + " poi inserisci i punti ferita totali di " + this.nome);
        this.puntiFerita = new Vita(
                getInt(),
                getInt());
        System.out.println("Inserisci la classe armatura di " + this.nome);
        this.classeArmatura = getInt();
        System.out.println("Inserisci la competenza di " + this.nome);
        this.competenza = getInt();
        System.out.println("Inserisci i punti esperienza di " + this.nome);
        this.puntiEsperienza = getInt();
        System.out.println("Inserisci il livello di " + this.nome);
        this.livello = getInt();
        System.out.println("Inserisci il danno iniziale di " + this.nome);
        this.dannoIniziale = getInt();
        this.morto = !(puntiFerita.attuale>0);
        if(this instanceof Giocante){
            System.out.println("Inserisci il punteggio di forza di " + this.nome + " e poi inserisci se " + nome + " ha bonus salvezza in forza");
            this.forza = new Statistica(getInt(), getBoolean());
            System.out.println("Inserisci il punteggio di destrezza di " + this.nome + " e poi inserisci se " + nome + " ha bonus salvezza in destrezza");
            this.destrezza = new Statistica(getInt(), getBoolean());
            this.iniziativa = this.destrezza.bonus;
            System.out.println("Inserisci il punteggio di costituzione di " + this.nome + " e poi inserisci se " + nome + " ha bonus salvezza in costituzione");
            this.costituzione = new Statistica(getInt(), getBoolean());
            System.out.println("Inserisci il punteggio di intelligenza di " + this.nome + " e poi inserisci se " + nome + " ha bonus salvezza in intelligenza");
            this.intelligenza = new Statistica(getInt(), getBoolean());
            System.out.println("Inserisci il punteggio di saggezza di " + this.nome + " e poi inserisci se " + nome + " ha bonus salvezza in saggezza");
            this.saggezza = new Statistica(getInt(), getBoolean());
            System.out.println("Inserisci il punteggio di carisma di " + this.nome + " e poi inserisci se " + nome + " ha bonus salvezza in carisma");
            this.carisma = new Statistica(getInt(), getBoolean());
        }else{
            System.out.println("Inserisci il punteggio di forza di " + this.nome);
            this.forza = new Statistica(getInt(), false);
            System.out.println("Inserisci il punteggio di destrezza di " + this.nome);
            this.destrezza = new Statistica(getInt(), false);
            this.iniziativa = this.destrezza.bonus;
            System.out.println("Inserisci il punteggio di costituzione di " + this.nome);
            this.costituzione = new Statistica(getInt(), false);
            System.out.println("Inserisci il punteggio di intelligenza di " + this.nome);
            this.intelligenza = new Statistica(getInt(), false);
            System.out.println("Inserisci il punteggio di saggezza di " + this.nome);
            this.saggezza = new Statistica(getInt(), false);
            System.out.println("Inserisci il punteggio di carisma di " + this.nome);
            this.carisma = new Statistica(getInt(), false);
        }
        System.out.println(this.nome + " è un amico?");
        this.amico = getBoolean();
    }

    /**
     * costruttore di Personaggio da file csv, prende come parametro un array di stringhe già diviso sulle virgole che immagazzina l'intera riga
     * @throws csv.exception.UnexpectedTypeOnCsvException se nella riga trovo dei tipi che non corrispondono a ciò che mi aspetto di trovare in quel punto
     * @param row un array di stringhe che rappresenta la riga del file
     */
    public Personaggio(String[] row){
        try {
            nome = row[0];
            iniziativa = Integer.parseInt(row[1]);
            String[] subStringVita = new String[2];
            for (int sub = 0, originale = 2; sub < subStringVita.length; sub++, originale++)
                subStringVita[sub] = row[originale];
            puntiFerita = new Vita(subStringVita);
            classeArmatura = Integer.parseInt(row[4]);
            competenza = Integer.parseInt(row[5]);
            puntiEsperienza = Integer.parseInt(row[6]);
            livello = Integer.parseInt(row[7]);
            dannoIniziale = Integer.parseInt(row[8]);
            this.amico = Boolean.parseBoolean(row[9]);
            this.morto = Boolean.parseBoolean(row[10]);

            String[] subStringForza = new String[2];
            for (int sub = 0, originale = 11; sub < subStringForza.length; sub++, originale++)
                subStringForza[sub] = row[originale];
            forza = new Statistica(subStringForza);

            String[] subStringDestrezza = new String[2];
            for (int sub = 0, originale = 13; sub < subStringDestrezza.length; sub++, originale++)
                subStringDestrezza[sub] = row[originale];
            destrezza = new Statistica(subStringDestrezza);

            String[] subStringCostituzione = new String[2];
            for (int sub = 0, originale = 15; sub < subStringCostituzione.length; sub++, originale++)
                subStringCostituzione[sub] = row[originale];
            costituzione = new Statistica(subStringCostituzione);

            String[] subStringIntelligenza = new String[2];
            for (int sub = 0, originale = 17; sub < subStringIntelligenza.length; sub++, originale++)
                subStringIntelligenza[sub] = row[originale];
            intelligenza = new Statistica(subStringIntelligenza);

            String[] subStringSaggezza = new String[2];
            for (int sub = 0, originale = 19; sub < subStringSaggezza.length; sub++, originale++)
                subStringSaggezza[sub] = row[originale];
            saggezza = new Statistica(subStringSaggezza);

            String[] subStringCarisma = new String[2];
            for (int sub = 0, originale = 21; sub < subStringCarisma.length; sub++, originale++)
                subStringCarisma[sub] = row[originale];
            carisma = new Statistica(subStringCarisma);
        }catch (java.lang.NumberFormatException e){
            throw new csv.exception.UnexpectedTypeOnCsvException();
        }
    }

    public String getNome() {
        return nome;
    }

    /**
     * metodo che prende in input una stringa
     * @return la string inserita dall'utente
     */
    public static String getString(){
        return new java.util.Scanner(System.in).nextLine();
    }

    /**
     * metodo che prende in input un intero. <p>nel caso in cui l'utente non vada ad inserire un intero verrà segnalato l'errore e atteso un nuovo input</p>
     * @return l'intero inserito dall'utente
     */
    public static int getInt(){
        try{
            return new java.util.Scanner(System.in).nextInt();
        }catch (java.util.InputMismatchException e){
            System.out.println("ERRORE:\tdevi inserire un numero intero");
            return getInt();
        }
    }

    /**
     * metodo che va a prendere un input intero che sia maggiore del parametro inserito
     * @param min numero minimo da prenmdere in input
     * @return l'intero inserito dall'utente
     */
    protected static int getInt(int min){
        int ret = getInt();
        if (ret >= min) return ret;
        System.out.println("devi inserire un numero maggiore o uguale a " + min);
        return getInt(min);
    }

    /**
     * metodo che va a prendere un input intero che sia compreso tra i parametri inseriti
     * @param min numero minimo da prendere in input
     * @param max numero massimo da prendere in input
     * @return l'intero inserito dall'utente
     */
    protected static int getInt(int min, int max){
        if(min>max) throw new RuntimeException("il numero minimo non può essere maggiore del massimo");
        int ret = getInt(min);
        if (ret <= max) return ret;
        System.out.println("devi inserire un numero minore o uguale a " + max);
        return getInt(min, max);
    }

    /**
     * metodo che prende in input un booleano. <p>nel caso in cui l'utente non vada ad inserire un booleano verrà segnalato l'errore e atteso un nuovo input</p>
     * @return il booleano inserito dall'utente
     */
    public static boolean getBoolean(){
        try{
            System.out.println("(insert \"true\" or \"false\")");
            return new java.util.Scanner(System.in).nextBoolean();
        }catch (java.util.InputMismatchException e){
            return getBoolean();
        }
    }

    /**
     * metodo che serve a modificare un Personaggio
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
                """;
        System.out.println(richiestaAttributoDaModificare);
        switch (getInt()){
            default -> {
                return;
            }
            case 1 -> {
                System.out.println("inserisci il nome del personaggio");
                this.nome = getString();
            }
            case 2 -> {
                System.out.println("Inserisci i punti ferita di " + this.nome + " poi inserisci i punti ferita totali di " + this.nome);
                this.puntiFerita = new Vita(
                        getInt(),
                        getInt());
            }
            case 3 -> {
                System.out.println("Inserisci la classe armatura di " + this.nome);
                this.classeArmatura = getInt();
            }
            case 4 -> {
                System.out.println("Inserisci la competenza di " + this.nome);
                this.competenza = getInt();
            }
            case 5 -> {
                System.out.println("Inserisci i punti esperienza di " + this.nome);
                this.puntiEsperienza = getInt();
            }
            case 6 -> {
                System.out.println("Inserisci il livello di " + this.nome);
                this.livello = getInt();
            }
            case 7 -> {
                System.out.println("Inserisci il danno iniziale di " + this.nome);
                this.dannoIniziale = getInt();
            }
            case 8 -> modificaStatistica();
            case 9 -> {
                System.out.println(this.nome + " è un amico?");
                this.amico = getBoolean();
            }
        }
        modifica();
    }

    /**
     * metodo che serve a modificare le caratteristiche di un Personaggio
     */
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
                System.out.println("Inserisci il punteggio di forza di " + this.nome);
                this.forza = new Statistica(
                        getInt(),
                        false);
            }
            case 2 -> {
                System.out.println("Inserisci il punteggio di destrezza di " + this.nome);
                this.destrezza = new Statistica(
                        getInt(),
                        false);
                this.iniziativa = this.destrezza.punteggio;
            }
            case 3 -> {
                System.out.println("Inserisci il punteggio di costituzione di " + this.nome);
                this.costituzione = new Statistica(
                        getInt(),
                        false);
            }
            case 4 -> {
                System.out.println("Inserisci il punteggio di intelligenza di " + this.nome);
                this.intelligenza = new Statistica(
                        getInt(),
                        false);
            }
            case 5 -> {
                System.out.println("Inserisci il punteggio di saggezza di " + this.nome);
                this.saggezza = new Statistica(
                        getInt(),
                        false);
            }
            case 6 -> {
                System.out.println("Inserisci il punteggio di carisma di " + this.nome);
                this.carisma = new Statistica(
                        getInt(),
                        false);
            }
        }
    }

    /**
     * metodo che serve a tirare un dado, il dado verò tirato in automatico e il risultato sarà compreso tra i due parametri che sono stati passati a questo metodo
     * @param origin risultato minimo del tiro del dado
     * @param bound risultato massimo del tiro del dado
     * @return il risultato del tiro del dado
     */
    public int tiro(int origin, int bound){
        int num = new java.util.Random().nextInt(origin, bound);
        System.out.println("il risultato del tiro di " + nome + " è:\t" + num);
        return num;
    }

    /**
     * metodo che serve a tirare un dado, il dado verò tirato in automatico e il risultato sarà compreso tra i primi due parametri che sono stati passati a questo metodo e varrà aggiunto il bonus passato come terzo parametro
     * @param origin risultato minimo del tiro del dado
     * @param bound risultato massimo del tiro del dado
     * @param bonus il bonus che verrà aggiunto al risultato del dado
     * @return il risultato del tiro del dado dopo aver aggiunto il bonus
     */
    public boolean tiro(int origin, int bound, int bonus){
        int naturale = tiro(origin, bound);
        tiro = naturale + bonus;
        return naturale == 20;
    }

    public int tiroDado(int numeroDadi, int tipoDado){
        int risultato = 0;
        while (numeroDadi-->0){
            risultato += tiro(1, tipoDado);
        }
        return risultato;
    }

    public boolean tiro(String caratteristica) throws NoSuchStatistic {
        switch (caratteristica.toLowerCase()){
            case "for" -> {
                return tiroForza();
            }
            case "des" -> {
                return tiroDestrezza();
            }
            case "cos" -> {
                return tiroCostituzione();
            }
            case "int" -> {
                return tiroIntelligenza();
            }
            case "sag" -> {
                return tiroSaggezza();
            }
            case "car" -> {
                return tiroCarisma();
            }
        }
        throw new NoSuchStatistic("caratteristica non riconosciuta");
    }

    /**
     * metodo che serve a controllare se il tiro ha superato la sua classe difficoltà
     * @param tiro il risultato del tiro del dado da verificare
     * @param classeDifficolta il risultato minimo per superare questo tiro
     * @return un booleano che indica la riuscita di questo tiro
     */
    public boolean verificaTiro(int tiro, int classeDifficolta){
        return tiro>=classeDifficolta;
    }

    /**
     * esegue un tiro con la caratteristica forza
     * @return il risultato del tiro
     */
    public boolean tiroForza(){
        return tiro(1, 21, this.forza.bonus);
    }

    /**
     * esegue un tiro con la caratteristica destrezza
     * @return il risultato del tiro
     */
    public boolean tiroDestrezza(){
        return tiro(1,21, this.destrezza.bonus);
    }

    /**
     * esegue un tiro con la caratteristica costituzione
     * @return il risultato del tiro
     */
    public boolean tiroCostituzione(){
        return tiro(1,21, this.costituzione.bonus);
    }

    /**
     * esegue un tiro con la caratteristica intelligenza
     * @return il risultato del tiro
     */
    public boolean tiroIntelligenza(){
        return tiro(1,21, this.intelligenza.bonus);
    }

    /**
     * esegue un tiro con la caratteristica saggezza
     * @return il risultato del tiro
     */
    public boolean tiroSaggezza(){
        return tiro(1,21, this.saggezza.bonus);
    }

    /**
     * esegue un tiro con la caratteristica carisma
     * @return il risultato del tiro
     */
    public boolean tiroCarisma(){
        return tiro(1,21, this.carisma.bonus);
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
    public int tiroSalvezza(String statistica) throws NoSuchStatistic {
        statistica = statistica.toLowerCase();
        switch (statistica) {
            case "for" -> {
                tiroForza();
                return tiro;
            }
            case "des" -> {
                tiroDestrezza();
                return tiro;
            }
            case "cos" -> {
                tiroCostituzione();
                return tiro;
            }
            case "int" -> {
                tiroIntelligenza();
                return tiro;
            }
            case "sag" -> {
                tiroSaggezza();
                return tiro;
            }
            case "car" -> {
                tiroCarisma();
                return tiro;
            }
        }
        throw new NoSuchStatistic("la statistica inserita non è stata trovata");
    }

    public boolean controllaMorto() {
        if(puntiFerita.attuale<=0) {
            morto = true;
            System.out.println(nome + " è morto");
        }
        return morto;
    }

    protected class Vita{
        public int attuale;
        public int totale;

        public Vita(int attuale, int totale){
            this.attuale = attuale;
            this.totale = totale;
        }

        public Vita(String[] row){
            this.attuale = Integer.parseInt(row[0]);
            this.totale = Integer.parseInt(row[1]);
        }

        public String toString(){
            String info = "";
            info += "\tattuali:\t\t\t" + attuale + "\n";
            info += "\ttotali:\t\t\t\t" + totale + "\n";
            return info;
        }
        public String toCsv(){
            String info = "";
            info += "," + attuale;
            info += "," + totale;
            return info;
        }
    }

    public class Statistica{
        public int punteggio;
        public int bonus;
        public boolean salvezza;

        public Statistica(int punteggio, boolean salvezza){
            this.punteggio = punteggio;
            bonus = punteggio-10;
            if(bonus<0)
                bonus--;
            bonus /= 2;
            this.salvezza = salvezza;
        }
        public Statistica(String[] row){
            this.punteggio = Integer.parseInt(row[0]);
            bonus = punteggio-10;
            if(bonus<0)
                bonus--;
            bonus /= 2;
            this.salvezza = Boolean.parseBoolean(row[1]);
        }

        @Override
        public String toString() {
            String info = "";
            info += "\tpunteggio:\t\t\t" + punteggio + "\n";
            info += "\tbonus:\t\t\t\t" + bonus + "\n";
            info += "\tsalvezza:\t\t\t" + salvezza + "\n";
            return info;
        }

        public String toCsv(){
            String info = "";
            info += "," + punteggio;
            info += "," + salvezza;
            return info;
        }
    }

    /**
     * metodo che genera una stringa che rappresenti il personaggio in un formato leggibile una volta mandato a schermo
     * @return la stringa generata
     */
    @Override
    public String toString(){
        String info = "";
        info += "nome:\t\t\t\t\t" + nome + "\n";
        info += "iniziativa:\t\t\t\t" + iniziativa + "\n";
        info += "punti ferita:\n" + puntiFerita;
        info += "classe armatura:\t\t" + classeArmatura + "\n";
        info += "competenza:\t\t\t\t" + competenza + "\n";
        info += "punti esperienza:\t\t" + puntiEsperienza + "\n";
        info += "livello:\t\t\t\t" + livello + "\n";
        if(tiro != 0) info += "tiro:\t\t\t\t\t" + tiro + "\n";
        info += "danno iniziale:\t\t\t" + dannoIniziale + "\n";
        info += "amico:\t\t\t\t\t" + amico + "\n";
        info += "morto:\t\t\t\t\t" + morto + "\n";
        info += "forza:\n" + forza;
        info += "destrezza:\n" + destrezza;
        info += "costituzione:\n" + costituzione;
        info += "intelligenza:\n" + intelligenza;
        info += "saggezza:\n" + saggezza;
        info += "carisma:\n" + carisma;
        return info;
    }

    /**
     * genera una stringa che rappresenti il personaggio nel formato corretto per essere immagazzinato in un file csv
     * @return la stringa generata
     */
    public String toCsv(){
        String info = "";
        info += nome;
        info += "," + iniziativa;
        info += puntiFerita.toCsv();
        info += "," + classeArmatura;
        info += "," + competenza;
        info += "," + puntiEsperienza;
        info += "," + livello;
        info += "," + dannoIniziale;
        info += "," + amico;
        info += "," + morto;
        info += forza.toCsv();
        info += destrezza.toCsv();
        info += costituzione.toCsv();
        info += intelligenza.toCsv();
        info += saggezza.toCsv();
        info += carisma.toCsv();
        return info;
    }

    /**
     * metodo che simula il singolo attacco a uno o più personaggi avversari
     * @param attaccati array di classe personaggio contenente tutti i personaggi che vengono attaccati
     */
    public void attacca(Personaggio[] attaccati){
        System.out.println("stai facendo un attacco fisico?\naltrimenti suppongo che colpisci in automatico e l'avversario deve tirare su salvezza");
        if(getBoolean()) {
            System.out.println("hai competenza in questo attacco?");
            boolean competenza = getBoolean();
            System.out.println("quale bonus caratteristica usi per questo attacco?\tinserisci le prime tre lettere");
            boolean critico = getNomeStatistica();
            if(competenza) tiro += this.competenza;
            System.out.println("in questo attacco c'è un ulteriore tiro bonus per colpire?");
            if(getBoolean()) {
                System.out.println("quanti dadi tiro?");
                int numeroDadiBonus = getInt(1);
                System.out.println("che dado tiro?");
                tiro += tiroDado(numeroDadiBonus, getInt());
            }
            System.out.println("in questo attacco c'è un ulteriore bonus per colpire?");
            if(getBoolean()){
                System.out.println("inserisci l'ultriore bonus");
                tiro = tiro + getInt();
            }
            for(Personaggio pg : attaccati){
                System.out.println("controllo " + pg.classeArmatura + "<" + tiro);
                if(pg.classeArmatura<tiro){
                    danneggia(pg);
                    if(critico) {
                        System.out.println(pg.nome + " ha subito un attacco critico per cui ora subirà di nuovo i danni");
                        danneggia(pg);
                    }
                }else{
                    System.out.println("questo attacco non è andato a segno");
                }
            }
        }else{
            System.out.println("devo ancora implementare l'attacco inevitabile");
        }
        System.out.println("attacco terminato");
    }

    /**
     * metodo che assegna i danni che il peersonaggio su cui viene chiamato fa al personaggio passato come parametro
     * @param pg personaggio che subise i danni
     */
    protected void danneggia(Personaggio pg){
        System.out.println("quanti dadi tiro per i danni?");
        int numeroDadi = getInt(1);
        System.out.println("che dadi tiro?");
        int danni = tiroDado(numeroDadi, getInt());
        System.out.println("in questo attacco c'è un ulteriore tiro bonus di danni?");
        if(getBoolean()) {
            System.out.println("quanti dadi tiro?");
            int numeroDadiBonus = getInt(1);
            System.out.println("che dado tiro?");
            tiro += tiroDado(numeroDadiBonus, getInt());
        }
        System.out.println("in questo attacco c'è un ulteriore bonus di danni?");
        if(getBoolean()){
            System.out.println("inserisci l'ultriore bonus");
            tiro = tiro + getInt();
        }
        System.out.println(pg.nome + " ha resistenza a questo attacco?");
        if(getBoolean()){
            danni /= 2;
        }
        pg.puntiFerita.attuale -= danni;
    }

    /**
     * metodo che fa un tiro su di una statistica di cui vengono passate le prime tre lettere del nome come parametro e mette il risultato nell'attributo tiro di personaggio
     * @return se il risultato è un successo critico
     */
    protected boolean getNomeStatistica() {
        try{
            return tiro(getString());
        }catch (NoSuchStatistic e){
            System.out.println("caratteristica non riconosciuta, inserire nuovamente");
            return getNomeStatistica();
        }
    }

    /**
     * metodo che controlla se un personaggio è svenuto o morto
     * @return true se il personaggio non può combattere
     */
    public boolean indisposto(){
        return controllaMorto();
    }
}