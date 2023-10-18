public class Personaggio{
    private String nome;
    private int iniziativa;
    private int hp;
    private int ca;
    private int comp;
    private Caratteristica[] punteggi;
    private Caratteristica[] bonus;
    private int xp;
    private int lvl;
    private int ordine;
    private int tiro;
    private int dannoIniziale;
    private boolean amico;
    private boolean[][] morte;

    public Personaggio(String nome, int hp, int ca, Caratteristica[] punteggi, int lvl, boolean amico) {
        nome = "nome";
        iniziativa = 0;
        hp = 10;
        ca = 14;
        comp = 2;
        punteggi = new Caratteristica[6];
        bonus = new Caratteristica[6];
        xp = 200;
        lvl = 4;
        ordine = 0;
        tiro = 0;
        dannoIniziale = 0;
        amico = true;
        morte = new boolean[2][3];
    }

    public String getNome() {
        return nome;
    }

    public int getIniziativa() {
        return iniziativa;
    }

    public int getHp() {
        return hp;
    }

    public int getCa() {
        return ca;
    }

    public int getComp() {
        return comp;
    }

    public int getValorePunteggi(int indice) {
        return punteggi[indice].valore;
    }

    public String getNomePunteggi(int indice) {
        return punteggi[indice].nome;
    }

    public int getValoreBonus(int indice) {
        return punteggi[indice].valore;
    }

    public String getNomeBonus(int indice) {
        return punteggi[indice].nome;
    }

    public int getXp() {
        return xp;
    }

    public int getLvl() {
        return lvl;
    }

    public int getOrdine() {
        return ordine;
    }

    public int getTiro() {
        return tiro;
    }

    public int getDannoIniziale() {
        return dannoIniziale;
    }

    public boolean getAmico() {
        return amico;
    }

    public boolean getMorte(int riga, int colonna) {
        return morte[riga][colonna];
    }

    public String getPersonaggio(){
        String info = "nome:\t"+getNome()+"\n";
        info+="iniziativa:\t"+getIniziativa()+"\n";
        info+="";
        return info;
    }
}