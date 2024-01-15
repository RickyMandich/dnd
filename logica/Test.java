package logica;
public class Test{
    public static void main(String[] args) {
        Lettore_csv reader = new Lettore_csv();
        String[][] table = reader.getCsv("csv\\Personaggi_output.csv");
        reader.outElencoCsv(table);
        System.out.println("inserisci il numero del personaggio che vuoi importare");
        Personaggio p = new Giocante(table[new java.util.Scanner(System.in).nextInt()], true);
        try{
            System.out.println("tiro salvezza:\t\t" + p.tiroSalvezza("FoR"));
        }
        catch (NoSuchStatistic e){
            System.out.println("tiro salvezza non eseguito");
        }
        finally {
            System.out.println("programma terminato");
        }
    }
}