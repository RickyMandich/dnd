public class programma_dnd_java {
    public static void main(String[] args) {
        int tot = Interazione.input("quanti altri personaggi stanno combattendo?");
        Personaggio[] pg = new Personaggio[tot];
        creaPg(pg, tot);
        for(int i=0;i<tot;i++) {
            Interazione.output(pg[i].toString());
        }
        pg[0].preparazioneOrdine(pg);
        pg[0].combattimento(pg);
        Interazione.close();
    }

    public static void scambia(Personaggio pg1, Personaggio pg2){
        Personaggio pgTemp = pg1;
        pg1 = pg2;
        pg2 = pgTemp;
    }

    public static void creaPg(Personaggio[] pg, int tot){
        for(int i=0;i<tot;i++) {
            pg[i] = new Personaggio(Interazione.strput("qual'è il nome di questo personaggio?"));
        }
    }
}