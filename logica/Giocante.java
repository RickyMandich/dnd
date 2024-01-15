package logica;
public class Giocante extends Personaggio{
    public Giocante(boolean test){
        super();
        this.test = test;
    }
    public Giocante(String[] row, boolean test){
        super(row);
        this.test = test;
    }

    public int tiro(int origin, int bound){
        if(test) return super.tiro(origin, bound);
        else return getInt("inserisci il risultado dei dadi");
    }
    public int tiro(int origin, int bound, int bonus) {
        return tiro(origin, bound) + bonus;
    }
}