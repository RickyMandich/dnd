package logica;
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
        this.ispirazione = parseBoolean(row[23]);
        bonusSalvezza = Integer.parseInt(row[24]);
        tiriControMorte = new boolean[2][3];
        this.tiriControMorte[0][0] = parseBoolean(row[25]);
        this.tiriControMorte[0][1] = parseBoolean(row[26]);
        this.tiriControMorte[0][2] = parseBoolean(row[27]);
        this.tiriControMorte[1][0] = parseBoolean(row[28]);
        this.tiriControMorte[1][1] = parseBoolean(row[29]);
        this.tiriControMorte[1][2] = parseBoolean(row[30]);
        morto = tiriControMorte[1][2];
    }
    public void modifica(){
        String richiestaAttributoDaModificare = """
                cosa vuoi fare?
                    0)\tterminare la modifica
                    1)\tcambiare il nome
                    2)\tcambiare la vita
                    3)\tcambiare la classe armatura
                    4)\tcambiare il bonus competenza
                    5)\tcambiare i punti esperienza
                    6)\tcambiare il livello
                    7)\tcambiare il danno iniziale
                    8)\tcambiare una statistica
                    9)\tcambiare la fazione
                    10)\tcambiare l'ispirazione
                    11)\tcambiare i tiri contro morte
                    """;
        switch (getInt(richiestaAttributoDaModificare)){
            default -> {
                return;
            }
            case 1 -> this.nome = getString("inserisci il nome del personaggio");
            case 2 -> this.puntiFerita = new Vita(
                    getInt("Inserisci i punti ferita di " + this.nome),
                    getInt("Inserisci i punti ferita totali di " + this.nome));
            case 3 -> this.classeArmatura = getInt("Inserisci la classe armatura di " + this.nome);
            case 4 -> {
                this.competenza = getInt("Inserisci la competenza di " + this.nome);
                this.bonusSalvezza = competenza;
            }
            case 5 -> this.puntiEsperienza = getInt("Inserisci i punti esperienza di " + this.nome);
            case 6 -> this.livello = getInt("Inserisci il livello di " + this.nome);
            case 7 -> this.dannoIniziale = getInt("Inserisci il danno iniziale di " + this.nome);
            case 8 -> modificaStatistica();
            case 9 -> this.amico = getBoolean(this.nome + " è un amico?");
            case 10 -> this.ispirazione = getBoolean(this.nome + " ha ispirazione?");
            case 11 -> {
                this.tiriControMorte = new boolean[2][3];
                int successi = getInt("quanti successi nei tiri contro morte ha " + nome);
                int fallimenti = getInt("quanti fallimenti nei tiri contro morte ha " + nome);
                for (int i = 0; i < successi; i++) {
                    tiriControMorte[0][i] = true;
                }
                for (int i = 0; i < fallimenti; i++) {
                    tiriControMorte[1][i] = true;
                }
                this.morto = this.tiriControMorte[1][2];
            }
        }
        modifica();
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

    public String tiriControMorteToString() {
        String info = "";
        info += "\tsuccessi:\t\t\t";
        for (int i = 0; i < 3; i++) info = info.concat(tiriControMorte[0][i] + "\t");
        info = info.concat("\n\tfallimenti:\t\t\t");
        for (int i = 0; i < 3; i++) info = info.concat(tiriControMorte[1][i] + "\t");
        return info + "\n";
    }
    public String tiriControMorteToCsv() {
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
        info += tiriControMorteToString();
        return info;
    }

    @Override
    public String toCsv() {
        String info = super.toCsv();
        info += ", " + ispirazione;
        info += ", " + bonusSalvezza;
        info += tiriControMorteToCsv();
        return info;
    }
}