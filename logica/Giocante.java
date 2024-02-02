package logica;
public class Giocante extends Personaggio{
    protected int bonusSalvezza;
    public static boolean test;
    protected boolean[][] tiriControMorte = new boolean[2][3];
    public Giocante(){
        super();
        System.out.println("qual'è il bonus salvezza di " + nome);
    }
    public Giocante(String[] row, boolean test){
        super(row);
    }

    @Override
    public int tiro(int origin, int bound){
        if(test) return super.tiro(origin, bound);
        else return getInt("inserisci il risultado dei dadi");
    }

    public int tiroSalvezza(String statistica) throws NoSuchStatistic {
        statistica = statistica.toLowerCase();
        switch (statistica) {
            case "for" -> {
                if (forza.salvezza) return tiroForza() + bonusSalvezza;
                else return tiroForza();
            }
            case "des" -> {
                if (destrezza.salvezza) return tiroDestrezza() + bonusSalvezza;
                else return tiroDestrezza();
            }
            case "cos" -> {
                if (costituzione.salvezza) return tiroCostituzione() + bonusSalvezza;
                else return tiroCostituzione();
            }
            case "int" -> {
                if (intelligenza.salvezza) return tiroIntelligenza() + bonusSalvezza;
                else return tiroIntelligenza();
            }
            case "sag" -> {
                if (saggezza.salvezza) return tiroSaggezza() + bonusSalvezza;
                else return tiroSaggezza();
            }
            case "car" -> {
                if (carisma.salvezza) return tiroCarisma() + bonusSalvezza;
                else return tiroCarisma();
            }
        }
        throw new NoSuchStatistic("la statistica inserita non è stata trovata");
    }
}