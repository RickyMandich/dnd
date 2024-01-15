package logica;
public class Test{
    public static void main(String[] args) {
        Lettore_csv l = new Lettore_csv();
        String[][] table = l.getCsv("csv\\Personaggi_output.csv");
        outElencoCsv(table);
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

    public static void outElencoCsv(String[][] tabel){
        for(int i=0;i<tabel.length;i++){
            System.out.print(i + ")\t");
            for(int j=0;j<tabel[i].length;j++){
                System.out.printf("%-35s", tabel[i][j]);
            }
            System.out.println();
        }
    }
}