package logica;
public class Giocante extends Personaggio{
    protected boolean ispirazione;
    protected int bonusSalvezza;
    public static boolean test;
    protected boolean[][] tiriControMorte;
    public Giocante(){
        super();
        this.ispirazione = getBoolean(this.nome + " ha ispirazione?");
        this.bonusSalvezza = getInt("qual'è il bonus salvezza di " + nome);
        this.tiriControMorte = new boolean[2][3];
        int successi = getInt("quanti successi nei tiri contro morte ha " + nome);
        int fallimenti = getInt("quanti fallimenti nei tiri contro morte ha " + nome);
        for(int i=0;i<successi;i++){
            tiriControMorte[0][i] = true;
        }
        for(int i=0;i<fallimenti;i++){
            tiriControMorte[1][i] = true;
        }
        this.morto = this.tiriControMorte[1][2];
    }
    public Giocante(String[] row){
        super(row);
        csv.Parser p = new csv.Parser();
        //da finire....
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

    public String getTiriControMorteString() {
        String info = "";
        info += "\tsuccessi:\t";
        for (int i = 0; i < 3; i++) info = info.concat(tiriControMorte[0][i] + "\t");
        info = info.concat("\n\tfallimenti:\t");
        for (int i = 0; i < 3; i++) info = info.concat(tiriControMorte[1][i] + "\t");
        return info + "\n";
    }
    public String getTiriControMorteCsv() {
        String info = "";
        info = info.concat(", " + tiriControMorte[0][0]);
        info = info.concat(", " + tiriControMorte[0][1]);
        info = info.concat(", " + tiriControMorte[0][2]);
        info = info.concat(", " + tiriControMorte[1][0]);
        info = info.concat(", " + tiriControMorte[1][1]);
        info = info.concat(", " + tiriControMorte[1][2]);
        return info;
    }

    @Override
    public String toString() {
        String info = super.toString();
        info += "ispirazione:\t\t\t\t" + ispirazione + "\n";
        info += "bonus salvezza:\t\t" + bonusSalvezza + "\n";
        info += getTiriControMorteString();
        return info;
    }

    @Override
    public String toCsv() {
        String info = super.toCsv();
        info += ", " + ispirazione;
        info += ", " + bonusSalvezza;
        info += getTiriControMorteCsv();
        return info;
    }
}