public class programma_dnd_java {
    public static void main(String[] args) {
        int tot = Interazione.input("quanti altri personaggi stanno combattendo?");
        Personaggio[] pg = new Personaggio[tot];
        creaPg(pg, tot);
        for(int i=0;i<tot;i++) {
            Interazione.output(pg[i].toString());
        }

        Interazione.close();
    }

    public static void creaPg(Personaggio[] pg, int tot){
        for(int i=0;i<tot;i++) {
            pg[i] = new Personaggio(Interazione.strput("qual'è il nome di questo personaggio?"));
        }
    }
}