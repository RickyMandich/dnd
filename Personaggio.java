public class Personaggio{
    protected String nome;
    protected int iniziativa;
    protected int hp;
    protected int hpTot;
    protected int ca;
    protected int comp;
    protected Caratteristica[] punteggi = new Caratteristica[6];
    protected Caratteristica[] bonus = new Caratteristica[6];
    protected int xp;
    protected int lvl;
    protected int ordine;
    protected int tiro;
    protected int dannoIniziale;
    protected boolean amico;
    protected boolean[][] tiriControMorte;
    protected boolean morto;

    //inserire come parametro la stringa "valori" nel caso si vogliano assegnare dei valori di default mentre
    //inserire la stringa "input" nel caso si vogliano inserire da tastiera tutti i dati non ricavabili
    public Personaggio(String nome) {
        int[] puntiEsperienza = {0, 300, 900, 2700, 6500, 14000, 23000, 48000, 64000, 85000, 100000, 120000, 140000, 165000, 195000, 195000, 225000, 265000, 305000, 355000};
        String metodoValori = Interazione.strput("vuoi che io prenda i valori di " + nome + " standard (inserisci \"valori\") o tramite input (inserisci \"input\")");
        for (int i = 0; i < 6; i++) {
            punteggi[i] = new Caratteristica(i);
            punteggi[i].nome += "\t\t\t";
            bonus[i] = new Caratteristica(i);
        }
        morto = false;
        switch (metodoValori) {
            case "valori" -> {
                hp = 10;
                hpTot = hp;
                ca = 14;
                for(int i=0;i<6;i++){
                    punteggi[i].valore = 10;
                    bonus[i].valore = Caratteristica.getBonus(punteggi[i].valore);
                }
                lvl = 1;
                dannoIniziale = 0;
            }
            case "input" -> {
                hp = Interazione.input("quanti punti ferita ha " + nome + "?");
                hpTot = hp;
                ca = Interazione.input("qual'è la classe armatura di " + nome + "?");
                for (int i = 0; i < 6; i++) {
                    punteggi[i].valore = Interazione.input("qual'è il punteggio di " + bonus[i].nome + " di " + nome);
                    bonus[i].valore = Caratteristica.getBonus(punteggi[i].valore);
                }
                lvl = Interazione.input("di che livello è " + nome + "?");
                dannoIniziale = Interazione.input("qual'è il danno iniziale di " + nome);
            }
            default -> {
            }
        }
        switch (lvl) {
            case 1, 2, 3, 4 -> comp = 2;
            case 5, 6, 7, 8 -> comp = 3;
            case 9, 10, 11, 12 -> comp = 4;
            case 13, 14, 15, 16 -> comp = 5;
            case 17, 18, 19, 20 -> comp = 6;
        }
        xp = puntiEsperienza[lvl - 1];
        this.nome = nome;
        ordine = 0;
        tiro = 0;
        tiriControMorte = new boolean[2][3];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                tiriControMorte[i][j] = false;
            }
        }
        amico = Interazione.boolput(nome+" è un amico?");
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

    //funzione che prende come parametro il tiro contro morte, segna il successo o il fallimento e riterno un booleano corrispondente allo stato del personaggio (0 = morto, 1 = svenuto, 2 = ripreso)
    public int setMorte(int risultato){
        int i = 0;
        int j;
        if(risultato>10){
            j = 0;
        }else {
            j = 1;
        }
        while(tiriControMorte[j][i]){
            i++;
        }
        tiriControMorte[j][i] = true;
        if(tiriControMorte[0][2]){
            for(i=0;i<3;i++){
                tiriControMorte[0][i] = false;
            }
            return 2;
        }else if(tiriControMorte[1][2]){
            return 0;
        }else {
            return 1;
        }
    }
    public boolean getMorte(int riga, int colonna) {
        return tiriControMorte[riga][colonna];
    }

    public String getTiriControMorte() {
        String info = "";
        for(int i=0;i<2;i++){
            info = info.concat("\t");
            switch (i) {
                case 0 -> info += "superati\t\t\t\t\t";
                case 1 -> info += "falliti\t\t\t\t\t\t";
            }
            for(int j=0;j<3;j++){
                info = info.concat(getMorte(i, j)+"\t");
            }
            info += "\n";
        }
        return info;
    }

    public String getPunteggi() {
        String info = "";
        for (int i = 0; i < 6; i++) {
            info = info.concat("\tpunteggio " + getNomePunteggi(i) + "\t\t" + getValorePunteggi(i) + "\n");
        }
        return info;
    }

    public String getBonus() {
        String info = "";
        for (int i = 0; i < 6; i++) {
            info = info.concat("\tbonus " + getNomePunteggi(i) + "\t\t\t" + getValoreBonus(i) + "\n");
        }
        return info;
    }

    public String toString(){
        String info = "";
        info += "nome:\t\t\t\t\t\t\t"+getNome()+"\n";
        info +="iniziativa:\t\t\t\t\t\t"+getIniziativa()+"\n";
        info +="punti ferita:\t\t\t\t\t"+getHp()+"\n";
        info +="classe armatura:\t\t\t\t"+getCa()+"\n";
        info +="bonus competenza:\t\t\t\t"+getComp()+"\n";
        info +="punteggi statistiche:\n"+getPunteggi()+"\n";
        info +="bonus statistiche:\n"+getBonus()+"\n";
        info +="punti esperienza:\t\t\t\t"+getXp()+"\n";
        info +="livello:\t\t\t\t\t\t"+getLvl()+"\n";
        info +="ordine:\t\t\t\t\t\t\t"+getOrdine()+"\n";
        info +="tiro:\t\t\t\t\t\t\t"+getTiro()+"\n";
        info +="danno iniziale:\t\t\t\t\t"+getDannoIniziale()+"\n";
        info +="amico:\t\t\t\t\t\t\t"+getAmico()+"\n";
        info +="tiri salvezza contro morte:\n"+ getTiriControMorte()+"\n";
        return info;
    }

    public boolean equals(Personaggio pg){
        if(this.nome != pg.nome){
            return false;
        }
        if(this.hp != pg.hp){
            return false;
        }
        if(this.ca != pg.ca){
            return false;
        }
        if(this.comp != pg.comp){
            return false;
        }
        for(int i=0;i<6;i++){
            if(!this.punteggi[i].equals(pg.punteggi[i])){
                return false;
            }
            if(!this.bonus[i].equals(pg.bonus[i])){
                return false;
            }
        }
        if(this.xp != pg.xp){
            return false;
        }
        if(this.lvl != pg.lvl){
            return false;
        }
        if(this.dannoIniziale != pg.dannoIniziale){
            return false;
        }
        if (this.amico != pg.amico) {
            return false;
        }
        return true;
    }

    protected void preparazioneOrdine(Personaggio[] pg){
        for(int i = 0; i < pg.length; i++){
            pg[i].iniziativa = Dadi.tiro(1, 20) + pg[i].bonus[Caratteristica.destrezza].valore;
        }
        boolean uguale = true;
        do {
            uguale = false;
            bubbleSort(pg);
            if (pg[0].iniziativa == pg[1].iniziativa) {
                int pareggi = 1;
                uguale = true;
                while(pg[pareggi].iniziativa == pg[pareggi-1].iniziativa){
                    pareggi++;
                }
                for(int i = 0; i < pareggi; i++){
                    pg[i].iniziativa = Dadi.tiro(1, 20) + pg[i].bonus[Caratteristica.destrezza].valore;
                }
            }
        }while(uguale);
    }

    protected void bubbleSort(Personaggio[] pg){
        boolean scambio;
        do{
            scambio = false;
            for (int i = 1; i < pg.length; i++) {
                if (pg[i].iniziativa > pg[i - 1].iniziativa) {
                    programma_dnd_java.scambia(pg[i - 1], pg[i]);
                    scambio = true;
                }
            }
        }while(scambio);
    }
    protected boolean controlloScontro(Personaggio[] pg){
        boolean scontro = true;
        int j = 0;
        while(pg[j].morto && scontro){
            j++;
            if(j==pg.length){
                scontro = false;
            }
        }
        int i = j;
        while((pg[i].morto || pg[i].amico==pg[j].amico) && scontro){
            i++;
            if(i==pg.length){
                scontro = false;
            }
        }
        return scontro;
    }
    protected String elencoNemici(Personaggio[] pg){
        String elenco = "";
        for(int i=0;i<pg.length;i++){
            if(this.amico!=pg[i].amico) {
                elenco = elenco.concat((i + 1) + "\t" + pg[i].nome + "(" + pg[i].hp + ")" + "\n");
            }
        }
        return elenco;
    }
    protected void attacco(Personaggio pg2){
        this.hp -= dannoIniziale;
        int caratteristicaUsata;
        if(Interazione.boolput("uso forza per attaccare?\t(altrimenti considero destrezza)")){
            caratteristicaUsata = Caratteristica.forza;
        }else{
            caratteristicaUsata = Caratteristica.destrezza;
        }
        int competenza = 0;
        if(Interazione.boolput(this.nome + " ha competenza in questo attacco?")){
            competenza = 1;
        }
        int dado = Interazione.input("che dado tiro per i danni?");
        this.tiro = Dadi.tiro(15, 20)+this.bonus[caratteristicaUsata].valore + (competenza * this.comp);
        if(this.tiro > pg2.ca){
            pg2.hp -= Dadi.tiro(1, dado)+(this.bonus[caratteristicaUsata].valore + this.comp) * competenza;
            if(this.tiro-(this.bonus[caratteristicaUsata].valore + (competenza * this.comp)) == 20){
                pg2.hp -= Dadi.tiro(1, dado)+(this.bonus[caratteristicaUsata].valore + this.comp) * competenza;
            }
        }
    }
    protected String info(){
        return nome + "\thp:  " + hp + "\n";
    }
    public void combattimento(Personaggio[] pg){
        while(controlloScontro(pg)) {
            for(int i=0;i<pg.length;i++){
                Interazione.output("ora tocca a " + pg[i].info());
                if(pg[i].hp>0){
                    Interazione.output(pg[i].elencoNemici(pg));
                    pg[i].attacco(pg[Interazione.input("inserisci il numero relativo al personaggio, tra quelli di questo elenco, che vuoi attaccare")-1]);
                    Interazione.output("tiro per colpire:\t" + pg[i].tiro + "\n");
                }else if(pg[i].hp> -(pg[i].hpTot/2) && !pg[i].morto){
                    controMorte();
                }else{
                    Interazione.output(pg[i].nome + " è morto, per cui passo al personaggio successivo\n");
                    morto = true;
                }
            }
        }
        Interazione.output("lo scontro è finito");
    }
    protected void incMorte(int tiro){
        int j;
        if(tiro>10) j=0;
        else j=1;
        int i=0;
        while(tiriControMorte[j][i]) i++;
        tiriControMorte[j][i] = true;
    }
    protected void controMorte(){
        incMorte(Dadi.tiro(1, 20));
        if(tiriControMorte[0][2]) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 2; j++) {
                    tiriControMorte[j][i] = false;
                }
            }
            hp = 1;
        }else if(tiriControMorte[1][2]){
            morto = true;
        }
    }
}