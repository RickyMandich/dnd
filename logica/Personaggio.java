package logica;
public class Personaggio {
    protected String nome;
    protected int iniziativa;
    protected Vita puntiFerita;
    protected int classeArmatura;
    protected int competenza;
    protected int puntiEsperienza;
    protected int livello;
    protected boolean ispirazione;
    protected int tiro;
    protected int dannoIniziale;
    protected int bonusSalvezza;
    protected boolean test = true;
    protected boolean amico;
    protected boolean[][] tiriControMorte = new boolean[2][3];
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
        this.iniziativa = getInt("Inserisci l'iniziativa di " + this.nome);
        this.puntiFerita = new Vita(
                getInt("Inserisci i punti ferita di " + this.nome),
                getInt("Inserisci i punti ferita totali di " + this.nome));
        this.classeArmatura = getInt("Inserisci la classe armatura di " + this.nome);
        this.competenza = getInt("Inserisci la competenza di " + this.nome);
        this.puntiEsperienza = getInt("Inserisci i punti esperienza di " + this.nome);
        this.livello = getInt("Inserisci l'iniziativa di " + this.nome);
        this.ispirazione = getBoolean(this.nome + " ha ispirazione?");
        this.dannoIniziale = getInt("Inserisci il danno iniziale di " + this.nome);
        for(int i=0;i<2;i++) {
            for (boolean tiro : tiriControMorte[i]) tiro = false;
        }
        int successi = -1;
        int fallimenti = -1;
        while(successi<0 || successi > 2){
            successi = getInt("quanti successi nei tiri contro morte ha fatto " + this.nome);
        }
        for(int i=0;i<successi;i++) tiriControMorte[0][i] = true;
        while(fallimenti<0 || fallimenti > 2){
            fallimenti = getInt("quanti fallimenti nei tiri contro morte ha fatto " + this.nome);
        }
        for(int i=0;i<fallimenti;i++) tiriControMorte[1][i] = true;
        this.morto = tiriControMorte[1][2];
        this.forza = new Statistica(getInt("Inserisci il punteggio di forza di " + this.nome));
        this.destrezza = new Statistica(getInt("Inserisci il punteggio di destrezza di " + this.nome));
        this.costituzione = new Statistica(getInt("Inserisci il punteggio di costituzione di " + this.nome));
        this.intelligenza = new Statistica(getInt("Inserisci il punteggio di intelligenza di " + this.nome));
        this.saggezza = new Statistica(getInt("Inserisci il punteggio di saggezza di " + this.nome));
        this.carisma = new Statistica(getInt("Inserisci il punteggio di carisma di " + this.nome));
        this.amico = getBoolean(this.nome + " è un amico?");
    }

    public Personaggio(String[] row){
        Parser p = new Parser();
        this.nome = row[0];
        this.iniziativa = p.parseInt(row[1]);
        this.puntiFerita = new Vita(
                p.parseInt(row[2]),
                p.parseInt(row[3]));
        this.classeArmatura = p.parseInt(row[4]);
        this.competenza = p.parseInt(row[5]);
        this.forza = new Statistica(p.parseInt(row[6]));
        this.destrezza = new Statistica(p.parseInt(row[7]));
        this.costituzione = new Statistica(p.parseInt(row[8]));
        this.intelligenza = new Statistica(p.parseInt(row[9]));
        this.saggezza = new Statistica(p.parseInt(row[10]));
        this.carisma = new Statistica(p.parseInt(row[11]));
        this.puntiEsperienza = p.parseInt(row[12]);
        this.livello = p.parseInt(row[13]);
        this.ispirazione = p.parseBool(row[14]);
        this.dannoIniziale = p.parseInt(row[15]);
        this.amico = p.parseBool(row[16]);
        int h=17;
        for(int i=0;i<tiriControMorte.length;i++){
            for(int j=0;j<tiriControMorte[i].length;j++){
                tiriControMorte[i][j] = p.parseBool(row[h++]);
            }
        }
        this.morto = p.parseBool(row[23]);
    }

    public int tiro(int origin, int bound){
        int num = new java.util.Random().nextInt(origin, bound);
        System.out.println("il risultato del tiro è :" + num);
        return num;
    }

    public int tiro(int origin, int bound, int bonus){
        return tiro(origin, bound) + bonus;
    }

    public String getString(String stringa){
        System.out.println(stringa);
        stringa = scan.nextLine();
        return stringa;
    }

    public int getInt(String stringa){
        System.out.println(stringa);
        int num = scan.nextInt();
        return num;
    }

    public boolean getBoolean(String stringa){
        return getInt(stringa + "\n(0 per falso e tutto il resto per vero)") != 0;
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

    public int tiroSalvezza(String statistica) throws noSuchStatistic {
        statistica = statistica.toLowerCase();
        switch (statistica) {
            case "for" -> {
                if (forza.salvezza) return tiroForza() + bonusSalvezza;
                else return tiroForza();
            }
            case "des" -> {
                if (destrezza.salvezza) return tiroDestrezza() + bonusSalvezza;
                else return tiroDestrezza();
            }
            case "cos" -> {
                if (costituzione.salvezza) return tiroCostituzione() + bonusSalvezza;
                else return tiroCostituzione();
            }
            case "int" -> {
                if (intelligenza.salvezza) return tiroIntelligenza() + bonusSalvezza;
                else return tiroIntelligenza();
            }
            case "sag" -> {
                if (saggezza.salvezza) return tiroSaggezza() + bonusSalvezza;
                else return tiroSaggezza();
            }
            case "car" -> {
                if (carisma.salvezza) return tiroCarisma() + bonusSalvezza;
                else return tiroCarisma();
            }
        }
        throw new noSuchStatistic("la statistica inserita non è stata trovata");
    }

    protected class Vita{
        public int attuale;
        public int totale;

        public Vita(int attuale, int totale){
            this.attuale = attuale;
            this.totale = totale;
        } 
    }

    public class Statistica{
        public int punteggio;
        public int bonus;
        public boolean salvezza;

        public Statistica(int punteggio){
            this.punteggio = punteggio;
            bonus = punteggio-10;
            if(bonus<0)
                bonus--;
            bonus /= 2;
        }
    }

}
