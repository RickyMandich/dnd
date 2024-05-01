package logica;
public class TestVari {
    public static void main(String[] args) {
        Personaggio[] pg = new Personaggio[3];
        String matString = "Mat al'Aybara,12,52,52,12,3,6500,5,0,true,false,12,false,12,false,14,false,12,false,12,true,16,true,true,3,false,false,false,false,false,false";
        pg[0] = new Giocante(matString.split(","));
        String paesanoString = "Paesano,10,5,5,10,0,0,1,0,true,false,10,false,10,false,10,false,10,false,10,false,10,false";
        pg[1] = new Personaggio(paesanoString.split(","));
        String banditoString = "Malvivente dei marchi rossi,14,16,16,14,2,100,1,0,true,false,11,false,14,false,12,false,9,false,9,false,11,false";
        pg[2] = new Personaggio(banditoString.split(","));
        PgListWithArray exec = new PgListWithArray(pg);
        System.out.println(pg[0].nome + pg[0].indisposto());
        pg[0].puntiFerita.attuale = -3;
        System.out.println(pg[0].nome + pg[0].indisposto());
        System.out.println(pg[1].nome + pg[1].indisposto());
        pg[1].puntiFerita.attuale = -2;
        System.out.println(pg[1].nome + pg[1].indisposto());
    }
}