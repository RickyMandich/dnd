public class ArrayCaratteristica{
    public Caratteristica[] carat = new Caratteristica[6];

    public ArrayCaratteristica(String nome){
        for(int i = 0; i< carat.length; i++){
            carat[i] = new Caratteristica(i);
            carat[i].punteggio = Interazione.input("qual'è il punteggio di " + carat[i].nome +" di " + nome);
            carat[i].getBonus();
        }
    }
    public ArrayCaratteristica(){
        for(int i = 0; i< carat.length; i++){
            carat[i] = new Caratteristica(i);
        }
    }
    public ArrayCaratteristica(String[] row){
        for(int i=0;i<carat.length;i++) carat[i] = new Caratteristica(i);
        Parser p = new Parser();
        for(int i=0;i<carat.length;i++){
            this.carat[i].punteggio = p.parseInt(row[i]);
            this.carat[i].getBonus();
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