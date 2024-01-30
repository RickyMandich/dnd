package vecchio;

public class ArrayStatistica {
    public Statistica[] carat = new Statistica[6];

    public ArrayStatistica(String nome){
        for(int i = 0; i< carat.length; i++){
            carat[i] = new Statistica(i);
            carat[i].punteggio = Interazione.input("qual'è il punteggio di " + carat[i].nome +" di " + nome);
            carat[i].getBonus();
        }
    }
    public ArrayStatistica(){
        for(int i = 0; i< carat.length; i++){
            carat[i] = new Statistica(i);
        }
    }
    public ArrayStatistica(String[] row){
        for(int i=0;i<carat.length;i++) carat[i] = new Statistica(i);
        csv.Parser p = new csv.Parser();
        for(int i=0;i<carat.length;i++){
            this.carat[i].punteggio = p.parseInt(row[i]);
            this.carat[i].getBonus();
        }
    }

    public void scambiaArrayCaratteristica(ArrayStatistica a){
        for(int i=0;i< carat.length;i++) {
            this.carat[i].scambiaCaratteristica(a.carat[i]);
        }
    }

    public boolean equals(ArrayStatistica c){
        int i=0;
        for(Statistica car:carat){
            if(!c.carat[i].equals(car)) return false;
            i++;
        }
        return true;
    }
}