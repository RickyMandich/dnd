package csv;
public class TestParseBoolean{
    public static void main(String[] args) {
        System.out.println("true:\t\tparsato è ->>" + logica.Personaggio.parseBoolean("true"));
        System.out.println("false:\t\tparsato è ->>" + logica.Personaggio.parseBoolean("false"));
        System.out.println("ciao:\t\tparsato è ->>" + logica.Personaggio.parseBoolean("ciao"));
    }
}