package logica;
public class TestPgFromRow {
    public static void main(String[] args) {
        String matString = "Mat al'Aybara, 12, 52, 52, 12, 3, 6500, 5, 0, true, false, 12, false, 12, false, 14, false, 12, false, 12, true, 16, true, true, 3, false, false, false, false, false, false";
        String paesanoString = "Paesano, 10, 5, 5, 10, 0, 0, 1, 0, true, false, 10, false, 10, false, 10, false, 10, false, 10, false, 10, false";
        System.out.println("lunghezza di giocante:\t" + matString.split(", ").length);
        System.out.println("lunghezza di personaggio:\t" + paesanoString.split(", ").length);
        Giocante matPg = null;
        Personaggio paesanoPg = null;
        try{
            matPg = new Giocante(matString.split(", "));
        }catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }
        try{
            paesanoPg = new Personaggio(paesanoString.split(", "));
        }catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }
        //System.out.println(paesanoPg);
        //System.out.println(matPg.toString());
    }
}