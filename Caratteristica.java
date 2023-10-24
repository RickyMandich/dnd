public class Caratteristica {
    public String nome;
    public int valore;
    
    public static final int forza = 0;
    public static final int destrezza = 1;
    public static final int costituzione = 2;
    
    public static final int intelligenza = 3;
    public static final int saggezza = 4;
    public static final int carisma = 5;

    public Caratteristica(){
        nome = "";
        valore = 0;
    }

    public static int getBonus(int punteggio){
        int bonus = punteggio-10;
        if(bonus<0)
            bonus--;
        return bonus/2;
    }
}