public class Caratteristica {
    public String nome;
    public int valore;
    
    public static final int forza = 0;
    public static final int destrezza = 1;
    public static final int costituzione = 2;
    
    public static final int intelligenza = 3;
    public static final int saggezza = 4;
    public static final int carisma = 5;

    public Caratteristica(int i){
        switch (i) {
            case Caratteristica.forza -> nome = "Forza";
            case Caratteristica.destrezza -> nome = "Destrezza";
            case Caratteristica.costituzione -> nome = "Costituzione";
            case Caratteristica.intelligenza -> nome = "Intelligenza";
            case Caratteristica.saggezza -> nome = "Saggezza";
            case Caratteristica.carisma -> nome = "Carisma";
        }
        valore = 0;
    }

    protected void scambiaNome(Caratteristica c){
        String nome = this.nome;
        this.nome = c.nome;
        c.nome = nome;
    }

    protected void scambiavalore(Caratteristica c){
        int valore = this.valore;
        this.valore = c.valore;
        c.valore = valore;
    }
    public void scambiaCaratteristica(Caratteristica c){
        this.scambiaNome(c);
        this.scambiavalore(c);
    }

    public static int getBonus(int punteggio){
        int bonus = punteggio-10;
        if(bonus<0)
            bonus--;
        return bonus/2;
    }
}