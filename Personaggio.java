public class Personaggio{
    private String nome;
    private int iniziativa;
    private int hp;
    private int ca;
    private int comp;
    private Caratteristica[] punteggi = new Caratteristica[6];
    private Caratteristica[] bonus = new Caratteristica[6];
    private int xp;
    private int lvl;
    private int ordine;
    private int tiro;
    private int dannoIniziale;
    private boolean amico;
    private boolean[][] morte;

    //inserire come parametro la stringa "valori" nel caso si vogliano assegnare dei valori di default mentre
    //inserire la stringa "input" nel caso si vogliano inserire da tastiera tutti i dati non ricavabili
    public Personaggio(String metodo_valori) {
        switch (metodo_valori) {
            case "valori":
                nome = "nome";
                iniziativa = 0;
                hp = 10;
                ca = 14;
                comp = 2;
                for (int i = 0; i < 6; i++) {
                    punteggi[i] = new Caratteristica();
                    bonus[i] = new Caratteristica();
                    punteggi[i].valore = 10;
                    bonus[i].valore = Caratteristica.getBonus(punteggi[i].valore);
                    switch (i) {
                        case 0:
                            punteggi[i].nome = "Forza\t\t";
                            bonus[i].nome = "Forza\t\t";
                            break;
                        case 1:
                            punteggi[i].nome = "Destrezza\t";
                            bonus[i].nome = "Destrezza\t";
                            break;
                        case 2:
                            punteggi[i].nome = "Costituzione";
                            bonus[i].nome = "Costituzione";
                            break;
                        case 3:
                            punteggi[i].nome = "Intelligenza";
                            bonus[i].nome = "Intelligenza";
                            break;
                        case 4:
                            punteggi[i].nome = "Saggezza\t";
                            bonus[i].nome = "Saggezza\t";
                            break;
                        case 5:
                            punteggi[i].nome = "Carisma\t";
                            bonus[i].nome = "Carisma\t";
                            break;
                    }
                }
                xp = 200;
                lvl = 4;
                ordine = 0;
                tiro = 0;
                dannoIniziale = 0;
                amico = true;
                morte = new boolean[2][3];
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 3; j++) {
                        morte[i][j] = false;
                    }
                }
                break;
            case "input":
                nome = "nome";
                iniziativa = 0;
                hp = 10;
                ca = 14;
                comp = 2;
                for (int i = 0; i < 6; i++) {
                    punteggi[i] = new Caratteristica();
                    bonus[i] = new Caratteristica();
                    switch (i) {
                        case 0:
                            punteggi[i].nome = "Forza\t\t";
                            bonus[i].nome = "Forza\t\t";
                            break;
                        case 1:
                            punteggi[i].nome = "Destrezza\t";
                            bonus[i].nome = "Destrezza\t";
                            break;
                        case 2:
                            punteggi[i].nome = "Costituzione";
                            bonus[i].nome = "Costituzione";
                            break;
                        case 3:
                            punteggi[i].nome = "Intelligenza";
                            bonus[i].nome = "Intelligenza";
                            break;
                        case 4:
                            punteggi[i].nome = "Saggezza\t";
                            bonus[i].nome = "Saggezza\t";
                            break;
                        case 5:
                            punteggi[i].nome = "Carisma\t";
                            bonus[i].nome = "Carisma\t";
                            break;
                    }
                    punteggi[i].valore = Interazione.input("qual'è il punteggio di " + punteggi[i].nome + "di " + nome);
                    bonus[i].valore = Caratteristica.getBonus(punteggi[i].valore);
                }
                xp = 200;
                lvl = 4;
                ordine = 0;
                tiro = 0;
                dannoIniziale = 0;
                amico = true;
                morte = new boolean[2][3];
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 3; j++) {
                        morte[i][j] = false;
                    }
                }
                break;
        }
    }

    public String getNome() {
        return nome;
    }

    public int getIniziativa() {
        return iniziativa;
    }

    public int getHp() {
        return hp;
    }

    public int getCa() {
        return ca;
    }

    public int getComp() {
        return comp;
    }

    public int getValorePunteggi(int indice) {
        return punteggi[indice].valore;
    }

    public String getNomePunteggi(int indice) {
        return punteggi[indice].nome;
    }

    public int getValoreBonus(int indice) {
        return bonus[indice].valore;
    }

    public String getNomeBonus(int indice) {
        return bonus[indice].nome;
    }

    public int getXp() {
        return xp;
    }

    public int getLvl() {
        return lvl;
    }

    public int getOrdine() {
        return ordine;
    }

    public int getTiro() {
        return tiro;
    }

    public int getDannoIniziale() {
        return dannoIniziale;
    }

    public boolean getAmico() {
        return amico;
    }

    public boolean getMorte(int riga, int colonna) {
        return morte[riga][colonna];
    }

    public String getMorte() {
        String info = "";
        for(int i=0;i<2;i++){
            info += "\t";
            switch (i){
                case 0:
                    info += "superati\t\t\t\t\t";
                    break;
                case 1:
                    info += "falliti\t\t\t\t\t\t";
                    break;
            }
            for(int j=0;j<3;j++){
                info += morte[i][j]+"\t";
            }
            info += "\n";
        }
        return info;
    }

    public String getPunteggi() {
        String info = "";
        for (int i = 0; i < 6; i++) {
            info += "\tpunteggio " + getNomePunteggi(i) + "\t\t" + getValorePunteggi(i) + "\n";
        }
        return info;
    }

    public String getBonus() {
        String info = "";
        for (int i = 0; i < 6; i++) {
            info += "\tbonus " + getNomeBonus(i) + "\t\t\t" + getValoreBonus(i) + "\n";
        }
        return info;
    }

    public String getPersonaggio(){
        String info = "nome:\t\t\t\t\t\t\t"+getNome()+"\n";
        info+="iniziativa:\t\t\t\t\t\t"+getIniziativa()+"\n";
        info+="punti ferita:\t\t\t\t\t"+getHp()+"\n";
        info+="classe armatura:\t\t\t\t"+getCa()+"\n";
        info+="bonus competenza:\t\t\t\t"+getComp()+"\n";
        info+="punteggi statistiche:\n"+getPunteggi()+"\n";
        info+="bonus statistiche:\n"+getBonus()+"\n";
        info+="punti esperienza:\t\t\t\t"+getXp()+"\n";
        info+="livello:\t\t\t\t\t\t"+getLvl()+"\n";
        info+="ordine:\t\t\t\t\t\t\t"+getOrdine()+"\n";
        info+="tiro:\t\t\t\t\t\t\t"+getTiro()+"\n";
        info+="danno iniziale:\t\t\t\t\t"+getDannoIniziale()+"\n";
        info+="amico:\t\t\t\t\t\t\t"+getAmico()+"\n";
        info+="tiri salvezza contro morte:\n"+getMorte()+"\n";
        return info;
    }
}