package logica;
public class TestVari {
    public static void main(String[] args) {

        Personaggio[] pg = new Personaggio[3];
        String matString = "Mat al'Aybara,52,52,12,3,6500,5,0,true,false,12,false,12,false,14,false,12,false,12,true,16,true,true,3,false,false,false,false,false,false";
        pg[0] = new Giocante(matString.split(","));
        String paesanoString = "Paesano,5,5,10,0,0,1,0,true,false,10,false,10,false,10,false,10,false,10,false,10,false";
        pg[1] = new Personaggio(paesanoString.split(","));
        String banditoString = "Malvivente dei marchi rossi,16,16,14,2,100,1,0,false,false,11,false,14,false,12,false,9,false,9,false,11,false";
        pg[2] = new Personaggio(banditoString.split(","));
        PgListWithArray exec = new PgListWithArray(pg);
        exec.elencoPg();
        System.out.println();
        Personaggio[] attaccati = new Personaggio[4];
        int i=0;
        for(Personaggio p : pg){
            attaccati[i++] = p;
        }
        attaccati[i] = pg[2];
        new PgListWithArray(attaccati).elencoPg();
        System.out.println("\ncompatto is running...\n");
        attaccati = pg[2].compatta(attaccati);
        System.out.println("compatto has runned");
        new PgListWithArray(attaccati).elencoPg();
    }
}