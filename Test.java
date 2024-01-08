public class Test {
    public static void main(String[] args) {
        eseguiProgramma();
        Interazione.close();
    }
    public static void writePgCsv(Personaggio[] pg){
        Writer w = new Writer("csv\\" + Interazione.strput("come si chiama il file output?") + ".csv");
        for(Personaggio p:pg) {
            w.addCsv(p.toCsv());
            Interazione.output("ho aggiunto " + p.nome + " al file csv");
        }
        w.close();
    }
    public static void eseguiProgramma() {
        int tot = Interazione.input("quanti personaggi stanno combattendo?");
        Personaggio[] pg = new Personaggio[tot];
        pg = creaPg(pg);
        for(int i=0;i<tot;i++) {
            Interazione.output(pg[i].toString());
        }
        pg[0].preparazioneOrdine(pg);
        pg[0].combattimento(pg);

        if(Interazione.boolput("vuoi salvare i personaggi allo stato attuale?")) writePgCsv(pg);
    }

    public static Personaggio[] creaPg(Personaggio[] pg){
        int i=0;
        while(i<pg.length){
            if(pg[i] != null){
                if(Interazione.boolput("vuoi modificare il personaggio?")) pg[i].modificaPg();
                if(i == pg.length-1){
                    if(Interazione.boolput("vuoi aggiungere altri personaggi?")) pg = addPg(pg);
                }
                i++;
            }else if(Interazione.boolput("vuoi prendere il " + (i+1) +"° personaggio dal file dati?")){
                Reader r = new Reader();
                String[][] tabel = r.getCsv("Personaggi.csv");
                outElencoCsv(tabel);
                pg[i] = new Personaggio(tabel[Interazione.input("\ninserisci il valore corrispondente al personaggio che vuoi inserire\t(poi sarà possibile modificare il personaggio")]);
            }else{
                pg[i] = new Personaggio();
            }
        }
        return pg;
    }
    public static Personaggio[] addPg(Personaggio[] pg){
        Personaggio[] newPg = new Personaggio[pg.length + Interazione.input("quanti altri personaggi vuoi aggiungere?")];
        System.arraycopy(pg, 0, newPg, 0, pg.length);
        return newPg;
    }
    public static void outElencoCsv(String[][] tabel){
        for(int i=0;i<tabel.length;i++){
            System.out.print(i + ")\t");
            for(int j=0;j<tabel[i].length;j++){
                System.out.printf("%-35s", tabel[i][j]);
            }
            Interazione.output("");
        }
    }
}