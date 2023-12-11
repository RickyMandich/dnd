public class programma_dnd_java {
    public static void main(String[] args) {
        int tot = Interazione.input("quanti altri personaggi stanno combattendo?");
        Personaggio[] pg = new Personaggio[tot];
        creaPg(pg);
        for(int i=0;i<tot;i++) {
            Interazione.output(pg[i].toString());
        }
        pg[0].preparazioneOrdine(pg);
        pg[0].combattimento(pg);
        Interazione.close();
    }

    public static void creaPg(Personaggio[] pg){
        for(int i=0;i<pg.length;i++) {
            pg[i] = new Personaggio(Interazione.strput("qual'è il nome del personaggio " + (i+1) + "?"));
        }
    }
}