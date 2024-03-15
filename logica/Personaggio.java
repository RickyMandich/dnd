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
            this.iniziativa = this.destrezza.punteggio;
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
            this.iniziativa = this.destrezza.punteggio;
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

    public Personaggio(String[] row){
        nome = row[0];
        iniziativa = Integer.parseInt(row[1]);
        String[] subStringVita = new String[2];
        for(int sub=0, originale=2;sub<subStringVita.length;sub++, originale++) subStringVita[sub] = row[originale];
        puntiFerita = new Vita(subStringVita);
        classeArmatura = Integer.parseInt(row[4]);
        competenza = Integer.parseInt(row[5]);
        puntiEsperienza = Integer.parseInt(row[6]);
        livello = Integer.parseInt(row[7]);
        dannoIniziale = Integer.parseInt(row[8]);
        this.amico = Boolean.parseBoolean(row[9]);
        this.morto = Boolean.parseBoolean(row[10]);

        String[] subStringForza = new String[2];
        for(int sub=0, originale=11;sub<subStringForza.length;sub++, originale++) subStringForza[sub] = row[originale];
        forza = new Statistica(subStringForza);

        String[] subStringDestrezza = new String[2];
        for(int sub=0, originale=13;sub<subStringDestrezza.length;sub++, originale++) subStringDestrezza[sub] = row[originale];
        destrezza = new Statistica(subStringDestrezza);

        String[] subStringCostituzione = new String[2];
        for(int sub=0, originale=15;sub<subStringCostituzione.length;sub++, originale++) subStringCostituzione[sub] = row[originale];
        costituzione = new Statistica(subStringCostituzione);

        String[] subStringIntelligenza = new String[2];
        for(int sub=0, originale=17;sub<subStringIntelligenza.length;sub++, originale++) subStringIntelligenza[sub] = row[originale];
        intelligenza = new Statistica(subStringIntelligenza);

        String[] subStringSaggezza = new String[2];
        for(int sub=0, originale=19;sub<subStringSaggezza.length;sub++, originale++) subStringSaggezza[sub] = row[originale];
        saggezza = new Statistica(subStringSaggezza);

        String[] subStringCarisma = new String[2];
        for(int sub=0, originale=21;sub<subStringCarisma.length;sub++, originale++) subStringCarisma[sub] = row[originale];
        carisma = new Statistica(subStringCarisma);
    }

    public static String getString(){
        return new java.util.Scanner(System.in).nextLine();
    }

    public static int getInt(){
        try{
            return new java.util.Scanner(System.in).nextInt();
        }catch (java.util.InputMismatchException e){
            System.out.println("ERRORE:\tdevi inserire un numero intero");
            return getInt();
        }
    }
    protected static int getInt(int min){
        int ret = getInt();
        if (ret >= min) return ret;
        System.out.println("devi inserire un numero maggiore o uguale a " + min);
        return getInt(min);
    }
    protected static int getInt(int min, int max){
        if(min>max) throw new RuntimeException("il numero minimo non può essere maggiore del massimo");
        int ret = getInt(min);
        if (ret <= max) return ret;
        System.out.println("devi inserire un numero minore o uguale a " + max);
        return getInt(min, max);
    }

    public static boolean getBoolean(){
        try{
            System.out.println("(insert \"true\" or \"false\")");
            return new java.util.Scanner(System.in).nextBoolean();
        }catch (java.util.InputMismatchException e){
            return getBoolean();
        }
    }

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
        if(this instanceof Giocante) {
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
        }else{
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
    }

    public String getNome() {
        return nome;
    }

    public int tiro(int origin, int bound){
        int num = new java.util.Random().nextInt(origin, bound);
        System.out.println("il risultato del tiro è :" + num);
        return num;
    }

    public int tiro(int origin, int bound, int bonus){
        return tiro(origin, bound) + bonus;
    }

    public boolean verificaTiro(int tiro, int classeDifficolta){
        return tiro>=classeDifficolta;
    }

    public int tiroForza(){
        return tiro(0, 20, this.forza.bonus);
    }

    public int tiroDestrezza(){
        return tiro(0,20, this.destrezza.bonus);
    }

    public int tiroCostituzione(){
        return tiro(0,20, this.costituzione.bonus);
    }

    public int tiroIntelligenza(){
        return tiro(0,20, this.intelligenza.bonus);
    }

    public int tiroSaggezza(){
        return tiro(0,20, this.saggezza.bonus);
    }

    public int tiroCarisma(){
        return tiro(0,20, this.carisma.bonus);
    }

    public int tiroSalvezza(String statistica) throws NoSuchStatistic {
        statistica = statistica.toLowerCase();
        switch (statistica) {
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
        throw new NoSuchStatistic("la statistica inserita non è stata trovata");
    }

    public void controllaMorto(){
        if(puntiFerita.attuale<=0) morto = true;
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
}