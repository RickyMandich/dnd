package logica;

public class Personaggio {
    protected String nome;
    protected int iniziativa;
    protected int puntiFerita;
    protected int puntiFeritaTotali;
    protected int classeArmatura;
    protected int competenza;
    protected int puntiEsperienza;
    protected int livello;
    protected boolean ispirazione;
    protected int tiro;
    protected int dannoIniziale;
    protected int bonusSalvezza;
    protected boolean[][] tiriControMorte = new boolean[2][3];
    protected boolean morto;
    protected Statistica forza;
    protected Statistica destrezza;
    protected Statistica costituzione;
    protected Statistica intelligenza;
    protected Statistica saggezza;
    protected Statistica carisma;

    public Personaggio() {
        this.nome = getString("inserisci il nome del personaggio", new java.util.Scanner(System.in));
        this.iniziativa = getInt("Inserisci l'iniziativa di " + this.nome, new java.util.Scanner(System.in));
        this.puntiFerita = getInt("Inserisci i punti ferita di " + this.nome, new java.util.Scanner(System.in));
        this.puntiFeritaTotali = getInt("Inserisci i punti ferita totali di " + this.nome, new java.util.Scanner(System.in));
        this.classeArmatura = getInt("Inserisci la classe armatura di " + this.nome, new java.util.Scanner(System.in));
        this.competenza = getInt("Inserisci la competenza di " + this.nome, new java.util.Scanner(System.in));
        this.puntiEsperienza = getInt("Inserisci i punti esperienza di " + this.nome, new java.util.Scanner(System.in));
        this.livello = getInt("Inserisci l'iniziativa di " + this.nome, new java.util.Scanner(System.in));
        this.ispirazione = getBoolean("Inserisci l'ispirazione di " + this.nome, new java.util.Scanner(System.in));
        this.dannoIniziale = getInt("Inserisci il danno iniziale di " + this.nome, new java.util.Scanner(System.in));
        for(int i=0;i<2;i++) {
            for (boolean tiro : tiriControMorte[i]) tiro = false;
        }
        int successi = -1;
        int fallimenti = -1;
        while(successi<0 || successi > 2){
            successi = getInt("quanti successi nei tiri contro morte ha fatto " + this.nome, new java.util.Scanner(System.in));
        }
        for(int i=0;i<successi;i++) tiriControMorte[0][i] = true;
        while(fallimenti<0 || fallimenti > 2){
            fallimenti = getInt("quanti successi nei tiri contro morte ha fatto " + this.nome, new java.util.Scanner(System.in));
        }
        for(int i=0;i<fallimenti;i++) tiriControMorte[1][i] = true;
        this.morto = tiriControMorte[1][2];
        this.forza = new Statistica(getInt("Inserisci il punteggio di forza di " + this.nome, new java.util.Scanner(System.in)));
        this.destrezza = new Statistica(getInt("Inserisci il punteggio di destrezza di " + this.nome, new java.util.Scanner(System.in)));
        this.costituzione = new Statistica(getInt("Inserisci il punteggio di costituzione di " + this.nome, new java.util.Scanner(System.in)));
        this.intelligenza = new Statistica(getInt("Inserisci il punteggio di intelligenza di " + this.nome, new java.util.Scanner(System.in)));
        this.saggezza = new Statistica(getInt("Inserisci il punteggio di saggezza di " + this.nome, new java.util.Scanner(System.in)));
        this.carisma = new Statistica(getInt("Inserisci il punteggio di carisma di " + this.nome, new java.util.Scanner(System.in)));
    }

    public int tiro(int origin, int bound){
        int num = new java.util.Random().nextInt(origin, bound);
        System.out.println("il risultato del tiro è :" + num);
        return num;
    }

    public int tiro(int origin, int bound, int bonus){
        return tiro(origin, bound) + bonus;
    }

    public String getString(String stringa, java.util.Scanner scan){
        System.out.println(stringa);
        stringa = scan.nextLine();
        scan.close();
        return stringa;
    }

    public int getInt(String stringa, java.util.Scanner scan){
        System.out.println(stringa);
        int num = scan.nextInt();
        scan.close();
        return num;
    }

    public boolean getBoolean(String stringa, java.util.Scanner scan){
        return getInt(stringa, scan) != 0;
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

    public int tiroSalvezza(String statistica){
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
        throw new RuntimeException();
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