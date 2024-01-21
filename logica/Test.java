package logica;
public class Test{
    public static void main(String[] args) {
        Lettore_csv reader = new Lettore_csv();
        String[][] table = reader.getCsv("csv\\Personaggi_output.csv");
        //System.out.println("inserisci il numero di personaggi");
        //Personaggio[] pg = new Personaggio[new java.util.Scanner(System.in).nextInt()];
        reader.outElencoCsv(table);
        System.out.println("inserisci il numero del personaggio che vuoi importare");
        Personaggio p = new Giocante(table[new java.util.Scanner(System.in).nextInt()],false);
        inputTiroSalvezza(p);
        /*
        reader.outElencoCsv(table);
        System.out.println("inserisci il numero del personaggio che vuoi importare");
        Giocante p = new Giocante(table[new java.util.Scanner(System.in).nextInt()], true);
        try{
            System.out.println("tiro salvezza:\t\t" + p.tiroSalvezza("FoR"));
        }
        catch (NoSuchStatistic e){
            System.out.println("tiro salvezza non eseguito");
        }
        finally {
            System.out.println("programma terminato");
        }
        /**/
    }
    public static void inputTiroSalvezza(Personaggio p){
        String car = "";
        System.out.println("inserisci le prime tre lettere della statistica che vuoi usare");
        car = new java.util.Scanner(System.in).nextLine();
        try {
            System.out.println(((Giocante) p).tiroSalvezza(car));
        } catch (NoSuchStatistic e) {
            inputTiroSalvezza(p);
        }
    }


    public static boolean isStatistic(String statistica){
        statistica = statistica.toLowerCase();
        return switch (statistica){
            case "for" -> true;
            case "des" -> true;
            case "cos" -> true;
            case "int" -> true;
            case "sag" -> true;
            case "car" -> true;
            default -> false;
        };
    }
}