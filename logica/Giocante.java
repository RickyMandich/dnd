package logica;
public class Giocante extends Personaggio{
    public int tiro(int origin, int bound, boolean test){
        if(test) return tiro(origin, bound);
        else return getInt("inserisci il risultado dei dadi", new java.util.Scanner(System.in));
    }
    public int tiro(int origin, int bound, int bonus, boolean test) {
        return tiro(origin, bound, test) + bonus;
    }
}