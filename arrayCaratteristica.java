public class arrayCaratteristica {
    public Caratteristica[] carat = new Caratteristica[6];

    public arrayCaratteristica(){
        for(int i = 0; i< carat.length; i++){
            carat[i] = new Caratteristica(i);
        }
    }
}