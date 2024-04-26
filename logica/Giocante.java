package logica;
public class Giocante extends Personaggio{
    protected boolean ispirazione;
    protected int bonusSalvezza;
    public static boolean test;
    protected boolean[][] tiriControMorte;

    public Giocante(){
        super();
        System.out.println(this.nome + " ha ispirazione?");
        this.ispirazione = getBoolean();
        this.bonusSalvezza = competenza;
        this.tiriControMorte = new boolean[2][3];
        System.out.println("quanti successi nei tiri contro morte ha " + nome);
        int successi = getInt();
        System.out.println("quanti fallimenti nei tiri contro morte ha " + nome);
        int fallimenti = getInt();
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
        try {
            this.ispirazione = Boolean.parseBoolean(row[23]);
            bonusSalvezza = Integer.parseInt(row[24]);
            tiriControMorte = new boolean[2][3];
            this.tiriControMorte[0][0] = Boolean.parseBoolean(row[25]);
            this.tiriControMorte[0][1] = Boolean.parseBoolean(row[26]);
            this.tiriControMorte[0][2] = Boolean.parseBoolean(row[27]);
            this.tiriControMorte[1][0] = Boolean.parseBoolean(row[28]);
            this.tiriControMorte[1][1] = Boolean.parseBoolean(row[29]);
            this.tiriControMorte[1][2] = Boolean.parseBoolean(row[30]);
            morto = tiriControMorte[1][2];
        }catch (java.lang.NumberFormatException e){
            throw new csv.exception.UnexpectedTypeOnCsvException();
        }
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
        System.out.println(richiestaAttributoDaModificare);
        switch (getInt()){
            default -> {
                return;
            }
            case 1 -> {
                System.out.println("inserisci il nome del personaggio");
                this.nome = getString();
            }
            case 2 -> {
                System.out.println("Inserisci i punti ferita di " + this.nome + " poi inserisci i punti ferita totali di " + this.nome);
                this.puntiFerita = new Vita(
                        getInt(),
                        getInt());
            }
            case 3 -> {
                System.out.println("Inserisci la classe armatura di " + this.nome);
                this.classeArmatura = getInt();
            }
            case 4 -> {
                System.out.println("Inserisci la competenza di " + this.nome);
                this.competenza = getInt();
                this.bonusSalvezza = competenza;
            }
            case 5 -> {
                System.out.println("Inserisci i punti esperienza di " + this.nome);
                this.puntiEsperienza = getInt();
            }
            case 6 -> {
                System.out.println("Inserisci il livello di " + this.nome);
                this.livello = getInt();
            }
            case 7 -> {
                System.out.println("Inserisci il danno iniziale di " + this.nome);
                this.dannoIniziale = getInt();
            }
            case 8 -> modificaStatistica();
            case 9 -> {
                System.out.println(this.nome + " è un amico?");
                this.amico = getBoolean();
            }
            case 10 -> {
                System.out.println(this.nome + " ha ispirazione?");
                this.ispirazione = getBoolean();
            }
            case 11 -> {
                this.tiriControMorte = new boolean[2][3];
                System.out.println("quanti successi nei tiri contro morte ha " + nome);
                int successi = getInt();
                System.out.println("quanti fallimenti nei tiri contro morte ha " + nome);
                int fallimenti = getInt();
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
        else {
            System.out.println("inserisci il risultado del tiro dei dadi di " + nome);
            return getInt(origin, bound);
        }
    }

    @Override
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
    public int inputTiroSalvezza(){
        String car = "";
        System.out.println("inserisci le prime tre lettere della statistica che vuoi usare");
        car = new java.util.Scanner(System.in).nextLine();
        try {
            return tiroSalvezza(car);
        } catch (NoSuchStatistic e) {
            System.out.println("caratteristica non riconosciuta");
            return inputTiroSalvezza();
        } catch (ClassCastException e){
            System.out.println("questo personaggio non può eseguire il tiro salvezza");
            return -1;
        }
    }

    public void tiroControMorte() {
        int tiro = tiro(20, 0);
        if (verificaTiro(tiro, 10)) {
            incrementaSuccessiControMorte(tiro);
        } else {
            decrementaSuccessiControMorte(tiro);
        }
        verificaTiriControMorte();
    }

    public void incrementaSuccessiControMorte(int tiro){
        int i=0;
        while (tiriControMorte[0][i]) i++;
        tiriControMorte[0][i] = true;
        if(i<2 && tiro == 20) tiriControMorte[0][i+1] = true;
    }

    public void decrementaSuccessiControMorte(int tiro){
        int i=0;
        while (tiriControMorte[1][i]) i++;
        tiriControMorte[1][i] = true;
        if(i<2 && tiro == 20) tiriControMorte[1][i+1] = true;
    }

    public void verificaTiriControMorte(){
        if(tiriControMorte[0][2]) puntiFerita.attuale = 1;
        else if(tiriControMorte[1][2]) morto = true;
    }

    @Override
    public void controllaMorto() {
        if(puntiFerita.attuale<=-(puntiFerita.totale/2)){
            morto = true;
        }else tiroControMorte();
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
        info = info.concat("," + tiriControMorte[0][0]);
        info = info.concat("," + tiriControMorte[0][1]);
        info = info.concat("," + tiriControMorte[0][2]);
        info = info.concat("," + tiriControMorte[1][0]);
        info = info.concat("," + tiriControMorte[1][1]);
        info = info.concat("," + tiriControMorte[1][2]);
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
        info += "," + ispirazione;
        info += "," + bonusSalvezza;
        info += tiriControMorteToCsv();
        return info;
    }
}