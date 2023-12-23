public class ArrayCaratteristica {
    public Caratteristica[] carat = new Caratteristica[6];

    public ArrayCaratteristica(){
        for(int i = 0; i< carat.length; i++){
            carat[i] = new Caratteristica(i);
        }
    }

    public void scambiaArrayCaratteristica(ArrayCaratteristica a){
        for(int i=0;i< carat.length;i++) {
            this.carat[i].scambiaCaratteristica(a.carat[i]);
        }
    }

    public boolean equals(ArrayCaratteristica c){
        int i=0;
        for(Caratteristica car:carat){
            if(!c.carat[i].equals(car)) return false;
            i++;
        }
        return true;
    }
}