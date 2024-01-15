package logica;
public class Test{
    public static void main(String[] args) {
        Personaggio p = new Giocante();
        try{
            System.out.println("tiro salvezza:\t\t" + p.tiroSalvezza("FoR"));
        }
        catch (Exception e){
            System.out.println("intento non raggiunto");
        }
    }
}