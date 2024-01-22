package vecchio;

public class Personaggio{
    protected String nome;
    protected int iniziativa;
    protected int hp;
    protected int hpTot;
    protected int classeArmatura;
    protected int comp;
    protected ArrayStatistica caratteristiche = new ArrayStatistica();
    protected int xp;
    protected int lvl;
    protected boolean ispirazione;
    protected int tiro;
    protected int dannoIniziale;
    protected boolean amico;
    protected boolean[][] tiriControMorte = new boolean[2][3];
    protected boolean morto;

    public Personaggio() {
        this.nome = Interazione.strput("inserisci il nome del personaggio");
        this.hp = Interazione.input("inserisci i punti ferita attuali di " + this.nome);
        this.hpTot = Interazione.input("inserisci i punti ferita massimi di " + this.nome);
        this.classeArmatura = Interazione.input("inserisci la classe armatura di " + this.nome);
        this.comp = Interazione.input("inserisci il bonus competenza di " + this.nome);
        this.caratteristiche = new ArrayStatistica(this.nome);
        this.xp = Interazione.input("quanti punti esperienza ha " + this.nome);
        this.lvl = Interazione.input("qual'è il livello di " + this.nome);
        this.ispirazione = Interazione.boolput(this.nome + " ha un punto ispirazione?");
        this.dannoIniziale = Interazione.input("qual'è il danno iniziale di " + this.nome);
        this.amico = Interazione.boolput(this.nome + "è un amico?");
        int successi;
        do {
            successi = Interazione.input("quanti successi nei tiri contro morte ha " + this.nome + "?");
        } while (successi > 2 || successi < 0);
        int fallimenti;
        do {
            fallimenti = Interazione.input("quanti fallimenti nei tiri contro morte ha " + this.nome + "?");
        }while(fallimenti>2||fallimenti<0);
        for(int i=0;i<2;i++){
            for(int j=0;j<3;j++) tiriControMorte[i][j] = false;
        }
        for(int i=0;i<successi;i++) tiriControMorte[0][i] = true;
        for(int i=0;i<fallimenti;i++) tiriControMorte[1][i] = true;
        this.morto = tiriControMorte[1][2];
    }

    public Personaggio(String[] row){
        Parser p = new Parser();
        this.nome = row[0];
        this.iniziativa = p.parseInt(row[1]);
        this.hp = p.parseInt(row[2]);
        this.hpTot = p.parseInt(row[3]);
        this.classeArmatura = p.parseInt(row[4]);
        this.comp = p.parseInt(row[5]);
        this.caratteristiche = new ArrayStatistica(subRow(row, 6, 11));
        this.xp = p.parseInt(row[12]);
        this.lvl = p.parseInt(row[13]);
        this.ispirazione = p.parseBool(row[14]);
        this.dannoIniziale = p.parseInt(row[15]);
        this.amico = p.parseBool(row[16]);
        this.tiriControMorte[0][0] = p.parseBool(row[17]);
        this.tiriControMorte[0][1] = p.parseBool(row[18]);
        this.tiriControMorte[0][2] = p.parseBool(row[19]);
        this.tiriControMorte[1][0] = p.parseBool(row[20]);
        this.tiriControMorte[1][1] = p.parseBool(row[21]);
        this.tiriControMorte[1][2] = p.parseBool(row[22]);
        this.morto = p.parseBool(row[23]);
    }
    public String[] subRow(String[] row, int inizio, int fine){
        String[] subRow = new String[fine-inizio+1];
        for(int i = 0; i<subRow.length; i++){
            subRow[i] = row[i+inizio];
        }
        return subRow;
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

    public int getHpTot() {
        return hpTot;
    }

    public int getClasseArmatura() {
        return classeArmatura;
    }

    public int getComp() {
        return comp;
    }

    public int getValorePunteggi(int indice) {
        return caratteristiche.carat[indice].punteggio;
    }

    public String getNomeCaratteristica(int indice) {
        return caratteristiche.carat[indice].nome;
    }

    public int getValoreBonus(int indice) {
        return caratteristiche.carat[indice].bonus;
    }

    public int getXp() {
        return xp;
    }

    public int getLvl() {
        return lvl;
    }

    public boolean getIspirazione() {
        return ispirazione;
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

    //funzione che prende come parametro il tiro contro morte, segna il successo o il fallimento e ritorna un booleano
    // corrispondente allo stato del personaggio (0 = morto, 1 = svenuto, 2 = ripreso)
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
            info = info.concat("\tpunteggio " + getNomeCaratteristica(i) + "\t\t" + getValorePunteggi(i) + "\n");
        }
        return info;
    }

    public String getBonus() {
        String info = "";
        for (int i = 0; i < 6; i++) {
            info = info.concat("\tbonus " + getNomeCaratteristica(i) + "\t\t\t" + getValoreBonus(i) + "\n");
        }
        return info;
    }

    public String toString(){
        String info = "";
        info += "nome:\t\t\t\t\t\t\t"+getNome()+"\n";
        info +="iniziativa:\t\t\t\t\t\t"+getIniziativa()+"\n";
        info +="punti ferita:\t\t\t\t\t"+getHp()+"\n";
        info +="punti ferita totali:\t\t\t"+getHpTot()+"\n";
        info +="classe armatura:\t\t\t\t"+ getClasseArmatura()+"\n";
        info +="bonus competenza:\t\t\t\t"+getComp()+"\n";
        info +="punteggi statistiche:\n"+getPunteggi()+"\n";
        info +="bonus statistiche:\n"+getBonus()+"\n";
        info +="punti esperienza:\t\t\t\t"+getXp()+"\n";
        info +="livello:\t\t\t\t\t\t"+getLvl()+"\n";
        info +="ordine:\t\t\t\t\t\t\t"+ getIspirazione()+"\n";
        info +="tiro:\t\t\t\t\t\t\t"+getTiro()+"\n";
        info +="danno iniziale:\t\t\t\t\t"+getDannoIniziale()+"\n";
        info +="amico:\t\t\t\t\t\t\t"+getAmico()+"\n";
        info +="tiri salvezza contro morte:\n"+ getTiriControMorte()+"\n";
        return info;
    }

    public String toCsv(){
        String info = "";
        info = nome;
        info += ", " + iniziativa;
        info += ", " + hp;
        info += ", " + hpTot;
        info += ", " + classeArmatura;
        info += ", " + comp;
        for(Statistica c: caratteristiche.carat) info = info.concat(", " + c.punteggio);
        info += ", " + xp;
        info += ", " + lvl;
        info += ", " + ispirazione;
        info += ", " + dannoIniziale;
        info += ", " + amico;
        for(int i=0;i<2;i++){
            for(int j=0;j<3;j++) info = info.concat(", " + tiriControMorte[i][j]);
        }
        info += ", " + morto;
        info += "\n";
        return info;
    }

    public boolean equals(Personaggio pg){
        if(this.nome != pg.nome) return false;
        if(this.hp != pg.hp) return false;
        if(this.classeArmatura != pg.classeArmatura) return false;
        if(this.comp != pg.comp) return false;
        if(!caratteristiche.equals(pg.caratteristiche)) return false;
        if(this.xp != pg.xp) return false;
        if(this.lvl != pg.lvl) return false;
        if(this.dannoIniziale != pg.dannoIniziale) return false;
        if (this.amico != pg.amico) return false;
        return true;
    }
    protected void scambiaNome(Personaggio pg){
        String nome = this.nome;
        this.nome = pg.nome;
        pg.nome = nome;
    }
    protected void scambiaIniziativa(Personaggio pg){
        int iniziativa = this.iniziativa;
        this.iniziativa = pg.iniziativa;
        pg.iniziativa = iniziativa;
    }
    protected void scambiaHp(Personaggio pg){
        int hp = this.hp;
        this.hp = pg.hp;
        pg.hp = hp;
    }
    protected void scambiaHpTot(Personaggio pg){
        int hpTot = this.hpTot;
        this.hpTot = pg.hpTot;
        pg.hpTot = hpTot;
    }
    protected void scambiaCa(Personaggio pg){
        int ca = this.classeArmatura;
        this.classeArmatura = pg.classeArmatura;
        pg.classeArmatura = ca;
    }
    protected void scambiaComp(Personaggio pg){
        int comp = this.comp;
        this.comp = pg.comp;
        pg.comp = comp;
    }
    protected void scambiaCaratteristiche(Personaggio pg){
        this.caratteristiche.scambiaArrayCaratteristica(pg.caratteristiche);
    }
    protected void scambiaXp(Personaggio pg){
        int xp = this.xp;
        this.xp = pg.xp;
        pg.xp = xp;
    }
    protected void scambiaLvl(Personaggio pg){
        int lvl = this.lvl;
        this.lvl = pg.lvl;
        pg.lvl = lvl;
    }
    protected void scambiaIspirazione(Personaggio pg){
        boolean ispirazione = this.ispirazione;
        this.ispirazione = pg.ispirazione;
        pg.ispirazione = ispirazione;
    }
    protected void scambiaTiro(Personaggio pg){
        int tiro = this.tiro;
        this.tiro = pg.tiro;
        pg.tiro = tiro;
    }
    protected void scambiaDannoIniziale(Personaggio pg){
        int dannoIniziale = this.dannoIniziale;
        this.dannoIniziale = pg.dannoIniziale;
        pg.dannoIniziale = dannoIniziale;
    }
    protected void scambiaAmico(Personaggio pg){
        boolean amico = this.amico;
        this.amico = pg.amico;
        pg.amico = amico;
    }
    protected void scambiaTiriControMorte(Personaggio pg){
        for(int i=0;i<2;i++){
            for(int j=0;j<3;j++) this.scambiaTiriControMorte(pg, i, j);
        }
    }
    protected void scambiaTiriControMorte(Personaggio pg, int i, int j){
        boolean tiriControMorte = this.tiriControMorte[i][j];
        this.tiriControMorte[i][j] = pg.tiriControMorte[i][j];
        pg.tiriControMorte[i][j] = tiriControMorte;
    }
    protected void scambiaMorto(Personaggio pg){
        boolean morto = this.morto;
        this.morto = pg.morto;
        pg.morto = morto;
    }

    public void scambiaPersonaggio(Personaggio pg){
        this.scambiaNome(pg);
        this.scambiaIniziativa(pg);
        this.scambiaHp(pg);
        this.scambiaHpTot(pg);
        this.scambiaCa(pg);
        this.scambiaComp(pg);
        this.scambiaCaratteristiche(pg);
        this.scambiaXp(pg);
        this.scambiaLvl(pg);
        this.scambiaIspirazione(pg);
        this.scambiaTiro(pg);
        this.scambiaDannoIniziale(pg);
        this.scambiaAmico(pg);
        this.scambiaTiriControMorte(pg);
        this.scambiaMorto(pg);
    }
    public void modificaPg(){
        boolean run = true;
        String output = getOutput();
        while (run){
            switch (Interazione.input(output)){
                case 0:
                    this.nome = Interazione.strput("inserisci il nome del personaggio che stai modificando");
                    break;
                case 1:
                    this.hp = Interazione.input("inserisci i punti ferita attuali di " + this.nome);
                    break;
                case 2:
                    this.hpTot = Interazione.input("inserisci i punti ferita massimi di " + this.nome);
                    break;
                case 3:
                    this.classeArmatura = Interazione.input("inserisci la classe armatura di " + this.nome);
                    break;
                case 4:
                    this.comp = Interazione.input("inserisci il bonus competenza di " + this.nome);
                    break;
                case 5:
                    int caratteristicaUsata=Interazione.input(
                            """
                                    quale caratteristica vuoi modificare?
                                    1) forza
                                    2) destrezza
                                    3) costituzione
                                    4) intelligenza
                                    5) saggezza
                                    6) carisma"""
                    );
                    caratteristiche.carat[caratteristicaUsata].punteggio =
                            Interazione.input(
                                    "qual'è il punteggio di "
                                        + caratteristiche.carat[caratteristicaUsata].nome
                                        + " di " + this.nome
                            );
                    break;
                case 6:
                    this.xp = Interazione.input("quanti punti esperienza ha " + this.nome);
                    break;
                case 7:
                    this.lvl = Interazione.input("qual'è il livello di " + this.nome);
                    break;
                case 8:
                    this.dannoIniziale = Interazione.input("qual'è il danno iniziale di " + this.nome);
                    break;
                case 9:
                    this.amico = Interazione.boolput(this.nome + "è un amico?");
                    break;
                case 10:
                    int successi;
                    do {
                        successi = Interazione.input("quanti successi nei tiri contro morte ha " + this.nome + "?");
                    } while (successi > 2 || successi < 0);
                    int fallimenti;
                    do {
                        fallimenti = Interazione.input(
                                "quanti fallimenti nei tiri contro morte ha "
                                        + this.nome
                                        + "?");
                    }while(fallimenti > 2 || fallimenti < 0);
                    for(int i=0;i<2;i++){
                        for(int j=0;j<3;j++) tiriControMorte[i][j] = false;
                    }
                    for(int i=0;i<successi;i++) tiriControMorte[0][i] = true;
                    for(int i=0;i<fallimenti;i++) tiriControMorte[1][i] = true;
                    this.morto = tiriControMorte[1][2];
                    break;
                case 11:
                    run = false;
                    break;
            }
        }
    }

    private static String getOutput() {
        String output = "cosa vuoi fare?\n";
        output += "inserisci 01 per modificare i punti ferita attuali\n";
        output += "inserisci 02 per modificare i punti ferita massimi\n";
        output += "inserisci 03 per modificare la classe armatura\n";
        output += "inserisci 04 per modificare il bonus competenza\n";
        output += "inserisci 05 per modificare le caratteristiche\n";
        output += "inserisci 06 per modificare i punti esperienza\n";
        output += "inserisci 07 per modificare il livello\n";
        output += "inserisci 08 per modificare il danno iniziale\n";
        output += "inserisci 09 per modificare se è amico o meno\n";
        output += "inserisci 10 per modificare i tiri salvezza contro morte\n";
        output += "inserisci 11 per uscire\n";
        return output;
    }

    public void combattimento(Personaggio[] pg){
        while(pg[0].controlloScontro(pg)) {
            for(int i=0;i<pg.length;i++){
                Interazione.output("ora tocca a " + pg[i].info());
                if(pg[i].hp>0) {
                    Interazione.output(pg[i].elencoNemici(pg));
                    int totNemici = 0;
                    for(int j=0;j<pg.length;j++){
                        if(pg[j].amico != pg[i].amico){
                            if(pg[j].hp>0){
                                totNemici++;
                            }
                        }
                    }
                    int tot;
                    do {
                        tot = Interazione.input("quanti personaggi subiscono l'attacco?");
                    }while(tot>totNemici);
                    Personaggio[] pgAtt = new Personaggio[tot];
                    for(int j=0;j<tot;j++){
                        pgAtt[j] = pg[Interazione.input("quale personaggio attacchi?") -1];
                    }
                    pg[i].attacco(pgAtt);
                    Interazione.output("tiro per colpire:\t" + pg[i].tiro + "\n");
                }else if(pg[i].hp> -(pg[i].hpTot/2) && !pg[i].morto){
                    pg[i].controMorte();
                }else{
                    Interazione.output(pg[i].nome + " è morto, per cui passo al personaggio successivo\n");
                    pg[i].morto = true;
                }
            }
        }
        Interazione.output("lo scontro è finito");
    }

    protected void preparazioneOrdine(Personaggio[] pg){
        for(Personaggio p: pg){
            p.iniziativa = Dadi.tiro(1, 20) + p.caratteristiche.carat[Statistica.destrezza].bonus;
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
                for(Personaggio p: pg){
                    p.iniziativa = Dadi.tiro(1, 20) + p.caratteristiche.carat[Statistica.destrezza].bonus;
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
                    pg[i].scambiaPersonaggio(pg[i - 1]);
                    scambio = true;
                }
            }
        }while(scambio);
    }
    protected boolean controlloScontro(Personaggio[] pg){
        boolean scontro = true;
        int j = 0;
        while(((pg[j].tiriControMorte[0][0] || pg[j].tiriControMorte[1][0]) || pg[j].morto) && scontro){
            j++;
            if(j==pg.length){
                scontro = false;
            }
        }
        int i = j;
        while(scontro && (pg[i].morto || pg[i].amico==pg[j].amico)){
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
            if(this.amico != pg[i].amico && pg[i].hp > 0){
                elenco = elenco.concat((i + 1) + "\t" + pg[i].nome + "(" + pg[i].hp + ")" + "\n");
            }
        }
        return elenco;
    }
    protected boolean controlloAttaccato(Personaggio[] pg, int attaccato){
        if(attaccato<0 || attaccato >= pg.length) return false;
        if(this.amico == pg[attaccato].amico) return false;
        return pg[attaccato].hp > 0;
    }

    protected void attacco(Personaggio[] pgAtt){
        this.hp -= dannoIniziale;
        int caratteristicaUsata;
        if(Interazione.boolput("uso forza per attaccare?\t(altrimenti considero destrezza)")){
            caratteristicaUsata = Statistica.forza;
        }else{
            caratteristicaUsata = Statistica.destrezza;
        }
        int competenza = 0;
        if(Interazione.boolput(this.nome + " ha competenza in questo attacco?")){
            competenza = 1;
        }
        int dado = 2;
        do{
            if(dado<=1) Interazione.output("il dado non può essere più piccolo o uguale a 1");
            dado = Interazione.input("che dado tiro per i danni?");
        }while(dado<=1);
        int nDadi = 1;
        do{
            if(nDadi<1) Interazione.output("devi tirare almeno un dado");
            nDadi = Interazione.input("quanti d" + dado + " tiro per i danni?");
        }while(nDadi<1);
        this.tiro = Dadi.tiro(1, 20)+caratteristiche.carat[caratteristicaUsata].bonus + (competenza * this.comp);
        for(Personaggio pg:pgAtt){
            if (this.tiro > pg.classeArmatura) {
                pg.hp -= (this.caratteristiche.carat[caratteristicaUsata].bonus + this.comp) * competenza;
                for (int j = 0; j < nDadi; j++) {
                    pg.hp -= Dadi.tiro(1, dado);
                }
                if (this.tiro - (this.caratteristiche.carat[caratteristicaUsata].bonus + (competenza * this.comp)) == 20) {
                    pg.hp -= (this.caratteristiche.carat[caratteristicaUsata].bonus + this.comp) * competenza;
                    for (int j = 0; j < nDadi; j++) {
                        pg.hp -= Dadi.tiro(1, dado);
                    }
                }
            }
        }
    }
    protected String info(){
        return nome + "\thp:  " + hp + "\n";
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