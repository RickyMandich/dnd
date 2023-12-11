public class programma_dnd_java {
    public static void main(String[] args) {
        int tot = Interazione.input("quanti altri personaggi stanno combattendo?");
        Personaggio[] pg = new Personaggio[tot];
        creaPg(pg);
        for(int i=0;i<tot;i++) {
            Interazione.output(pg[i].toString());
        }
        preparazioneOrdine(pg);
        combattimento(pg);
        Interazione.close();
    }
    public static void combattimento(Personaggio[] pg){
        while(pg[0].controlloScontro(pg)) {
            for(int i=0;i<pg.length;i++){
                Interazione.output("ora tocca a " + pg[i].info());
                if(pg[i].hp>0) {
                    Interazione.output(pg[i].elencoNemici(pg));
                    int attaccato;
                    do {
                        attaccato = Interazione.input("inserisci il numero relativo al personaggio, tra quelli di questo elenco, che vuoi attaccare") - 1;
                    }while(!pg[i].controlloAttaccato(pg, attaccato));
                    pg[i].attacco(pg[attaccato]);
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

    protected static void preparazioneOrdine(Personaggio[] pg){
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

    protected static void bubbleSort(Personaggio[] pg){
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

    public static void creaPg(Personaggio[] pg){
        for(int i=0;i<pg.length;i++) {
            pg[i] = new Personaggio(Interazione.strput("qual'è il nome del personaggio " + (i+1) + "?"));
        }
    }
}