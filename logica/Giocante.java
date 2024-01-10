package logica;
public class Giocante extends Personaggio{
    @Override
    public int tiro(int origin, int bound) {
        return getInt("inserisci il risultado dei dadi", new java.util.Scanner(System.in));
    }
}