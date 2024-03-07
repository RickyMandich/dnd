package logica;
public class Esecuzione{
    protected Personaggio[] pg;

    public Esecuzione(Personaggio[] pg) {
        this.pg = pg;
    }

    public boolean controlloMorte(){
        boolean[] fazione = new boolean[2];
        fazione[0] = false;
        fazione[1] = false;
        for(Personaggio i:pg){
            if(!i.morto) fazione[i.amico ? 0 : 1] = true;
        }
        return fazione[0] && fazione[1];
    }
    public static Personaggio creaPg(){
        System.out.println("vuoi creare un personaggio giocante?");
        if(new java.util.Scanner(System.in).nextBoolean()){
            return new Giocante();
        }else{
            return new Personaggio();
        }
    }
}