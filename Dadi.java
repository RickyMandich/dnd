import java.util.Random;
public class Dadi{
    protected static Random ran = new Random();
    protected static boolean tiro = Interazione.boolput("vuoi i dadi come input\t(altrimenti li tiro io in automatico)");
    public static int tiro(int origin, int bound){
        if(tiro){
            return Interazione.input("inserisci il risultato naturale del dado tra " + origin + " e " + bound);
        }else{
            int num = ran.nextInt(origin, bound);
            System.out.println("il risultato del tiro è:\t" + num);
            return num;
        }
    }
}