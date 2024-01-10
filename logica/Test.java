package logica;
public class Test{
    public static void main(String[] args) {
        Personaggio p = new Personaggio();
        try{
            System.out.println("tiro salvezza:\t\t" + p.tiroSalvezza("STR"));
        }
        catch (Exception e){
            System.out.println("intento non raggiunto");
        }
    }
}