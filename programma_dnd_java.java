public class programma_dnd_java {
    public static void main(String[] args) {
        Personaggio mat = new Personaggio("input");
        Interazione.output(mat.toString());
        int tot = Interazione.input("quanti personaggi stanno combattendo?");
        Personaggio[] pg = new Personaggio[tot];
        creaPg(pg, tot);
        for(int i=0;i<tot;i++){
            Interazione.output(pg[i].toString());
        }
    }

    public static void creaPg(Personaggio[] pg, int tot){
        for(int i=0;i<tot;i++){
            pg[i] = new Personaggio("valori");
        }
    }
}