package csv;
public class TestParseBoolean{
    public static void main(String[] args) {
        System.out.println("true parsato è ->>" + logica.Personaggio.parseBoolean("true"));
        System.out.println("false parsato è ->>" + logica.Personaggio.parseBoolean("false"));
        System.out.println("ciao parsato è ->>" + logica.Personaggio.parseBoolean("ciao"));
    }
}