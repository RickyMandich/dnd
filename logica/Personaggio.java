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

    protected java.util.Scanner scan;

    public Personaggio() {
        this.scan = new java.util.Scanner(System.in);
        this.nome = getString("inserisci il nome del personaggio");
        this.puntiFerita = new Vita(
                getInt("Inserisci i punti ferita di " + this.nome),
                getInt("Inserisci i punti ferita totali di " + this.nome));
        this.classeArmatura = getInt("Inserisci la classe armatura di " + this.nome);
        this.competenza = getInt("Inserisci la competenza di " + this.nome);
        this.puntiEsperienza = getInt("Inserisci i punti esperienza di " + this.nome);
        this.livello = getInt("Inserisci il livello di " + this.nome);
        this.dannoIniziale = getInt("Inserisci il danno iniziale di " + this.nome);
        this.morto = !(puntiFerita.attuale>0);
        this.forza = new Statistica(getInt("Inserisci il punteggio di forza di " + this.nome), getBoolean(nome + " ha bonus salvezza in forza?"));
        this.destrezza = new Statistica(getInt("Inserisci il punteggio di destrezza di " + this.nome), getBoolean(nome + " ha bonus salvezza in destrezza?"));
        this.iniziativa = this.destrezza.punteggio;
        this.costituzione = new Statistica(getInt("Inserisci il punteggio di costituzione di " + this.nome), getBoolean(nome + " ha bonus salvezza in costituzione?"));
        this.intelligenza = new Statistica(getInt("Inserisci il punteggio di intelligenza di " + this.nome), getBoolean(nome + " ha bonus salvezza in intelligenza?"));
        this.saggezza = new Statistica(getInt("Inserisci il punteggio di saggezza di " + this.nome), getBoolean(nome + " ha bonus salvezza in saggezza?"));
        this.carisma = new Statistica(getInt("Inserisci il punteggio di carisma di " + this.nome), getBoolean(nome + " ha bonus salvezza in carisma?"));
        this.amico = getBoolean(this.nome + " è un amico?");
    }

    public Personaggio(String[] row){
        this.scan = new java.util.Scanner(System.in);
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
        this.amico = parseBoolean(row[9]);
        this.morto = parseBoolean(row[10]);

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

    public String getString(String stringa){
        System.out.println(stringa);
        stringa = scan.nextLine();
        return stringa;
    }

    public int getInt(String stringa){
        System.out.println(stringa);
        try{
            return scan.nextInt();
        }catch (java.util.InputMismatchException e){
            System.out.println("ERRORE:\tdevi inserire un numero intero");
            return getInt(stringa);
        }
    }

    public boolean getBoolean(String stringa){
        return getInt(stringa + "\n(0 per falso e tutto il resto per vero)") != 0;
    }
    public static boolean parseBoolean(String s){
        s = s.toLowerCase();
        boolean ritorno;
        String True = "true";
        if(s == "true"){
            ritorno = true;
        }else if(s == "false"){
            ritorno = false;
        }else{
            throw new csv.exception.UnexpectedTypeOnCsvException();
        }
        return ritorno;
    }

    public int tiro(int origin, int bound){
        int num = new java.util.Random().nextInt(origin, bound);
        System.out.println("il risultato del tiro è :" + num);
        return num;
    }

    public int tiro(int origin, int bound, int bonus){
        return tiro(origin, bound) + bonus;
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
            info += ", " + attuale;
            info += ", " + totale;
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
            this.salvezza = parseBoolean(row[1]);
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
            info += ", " + punteggio;
            info += ", " + salvezza;
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
        info += ", " + iniziativa;
        info += puntiFerita.toCsv();
        info += ", " + classeArmatura;
        info += ", " + competenza;
        info += ", " + puntiEsperienza;
        info += ", " + livello;
        info += ", " + dannoIniziale;
        info += ", " + amico;
        info += ", " + morto;
        info += forza.toCsv();
        info += destrezza.toCsv();
        info += costituzione.toCsv();
        info += intelligenza.toCsv();
        info += saggezza.toCsv();
        info += carisma.toCsv();
        return info;
    }
}