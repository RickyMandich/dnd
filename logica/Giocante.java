package logica;
import csv.IncompatibleCsvException;
public class Giocante extends Personaggio{
    protected boolean ispirazione;
    protected int bonusSalvezza;
    public static boolean test;
    protected boolean[][] tiriControMorte;
    public Giocante(){
        super();
        this.ispirazione = getBoolean(this.nome + " ha ispirazione?");
        this.bonusSalvezza = competenza;
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
        this.ispirazione = row[23] == "true";
        if(!ispirazione && row[23] != "false") throw new IncompatibleCsvException();
        bonusSalvezza = Integer.parseInt(row[24]);
        tiriControMorte = new boolean[2][3];
        this.tiriControMorte[0][0] = row[25] == "true";
        if(!tiriControMorte[0][0] && row[25] != "false") throw new IncompatibleCsvException();
        this.tiriControMorte[0][1] = row[26] == "true";
        if(!tiriControMorte[0][1] && row[26] != "false") throw new IncompatibleCsvException();
        this.tiriControMorte[0][2] = row[27] == "true";
        if(!tiriControMorte[0][2] && row[27] != "false") throw new IncompatibleCsvException();
        this.tiriControMorte[1][0] = row[28] == "true";
        if(!tiriControMorte[1][0] && row[28] != "false") throw new IncompatibleCsvException();
        this.tiriControMorte[1][1] = row[29] == "true";
        if(!tiriControMorte[1][1] && row[29] != "false") throw new IncompatibleCsvException();
        this.tiriControMorte[1][2] = row[30] == "true";
        if(!tiriControMorte[1][2] && row[30] != "false") throw new IncompatibleCsvException();
        morto = tiriControMorte[1][2];
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
        info += "\tsuccessi:\t\t\t";
        for (int i = 0; i < 3; i++) info = info.concat(tiriControMorte[0][i] + "\t");
        info = info.concat("\n\tfallimenti:\t\t\t");
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
        info += "ispirazione:\t\t\t" + ispirazione + "\n";
        info += "bonus salvezza:\t\t\t" + bonusSalvezza + "\n";
        info += getTiriControMorteString();
        return info;
    }

    @Override
    public String toCsv() {
        String info = super.toCsv();
        info += ", " + ispirazione;
        info += ", " + bonusSalvezza;
        info += getTiriControMorteCsv();
        return info + "\n";
    }
}