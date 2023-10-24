public class Caratteristica {
    public String nome;
    public int valore;

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