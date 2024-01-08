public class Statistica {
    public String nome;
    public int punteggio;
    public int bonus;
    
    public static final int forza = 0;
    public static final int destrezza = 1;
    public static final int costituzione = 2;
    
    public static final int intelligenza = 3;
    public static final int saggezza = 4;
    public static final int carisma = 5;

    public Statistica(int i){
        switch (i) {
            case Statistica.forza -> nome = "Forza";
            case Statistica.destrezza -> nome = "Destrezza";
            case Statistica.costituzione -> nome = "Costituzione";
            case Statistica.intelligenza -> nome = "Intelligenza";
            case Statistica.saggezza -> nome = "Saggezza";
            case Statistica.carisma -> nome = "Carisma";
        }
        punteggio = 0;
        getBonus();
    }

    protected void scambiaNome(Statistica c){
        String nome = this.nome;
        this.nome = c.nome;
        c.nome = nome;
    }

    protected void scambiaPunteggio(Statistica c){
        int punteggio = this.punteggio;
        this.punteggio = c.punteggio;
        c.punteggio = punteggio;
    }

    protected void scambiaBonus(Statistica c){
        int bonus = this.bonus;
        this.bonus = c.bonus;
        c.bonus = bonus;
    }
    public void scambiaCaratteristica(Statistica c){
        this.scambiaNome(c);
        this.scambiaPunteggio(c);
        this.scambiaBonus(c);
    }

    public void getBonus(){
        bonus = punteggio-10;
        if(bonus<0)
            bonus--;
        bonus /= 2;
    }

    public boolean equals(Statistica c) {
        if(this.nome!=c.nome) return false;
        if(this.punteggio!=c.punteggio) return false;
        if(this.bonus!=c.bonus) return false;
        return true;
    }
}