package logica;
import csv.Parser;
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
        this.morto = !(puntiFerita.attuale>0);
        this.forza = new Statistica(getInt("Inserisci il punteggio di forza di " + this.nome));
        this.destrezza = new Statistica(getInt("Inserisci il punteggio di destrezza di " + this.nome));
        this.costituzione = new Statistica(getInt("Inserisci il punteggio di costituzione di " + this.nome));
        this.intelligenza = new Statistica(getInt("Inserisci il punteggio di intelligenza di " + this.nome));
        this.saggezza = new Statistica(getInt("Inserisci il punteggio di saggezza di " + this.nome));
        this.carisma = new Statistica(getInt("Inserisci il punteggio di carisma di " + this.nome));
        this.amico = getBoolean(this.nome + " è un amico?");
    }

    public Personaggio(String[] row){
        this.scan = new java.util.Scanner(System.in);
        Parser p = new Parser();
        //da finire....
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
        return scan.nextInt();
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
    @Override
    public String toString(){
        String info = "";
        return info;
    }
    public String toCsv(){
        String info = "";

        return info;
    }
}
