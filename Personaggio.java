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
    private boolean[][] morte=new boolean[2][3];

    public Personaggio(String nome, int hp, int ca, Caratteristica[] punteggi, int lvl, boolean amico) {
        
    }
}