public class arrayCaratteristica {
    public Caratteristica[] caratt = new Caratteristica[6];

    public arrayCaratteristica(){
        for(int i=0;i< caratt.length;i++){
            caratt[i] = new Caratteristica(i);
        }
    }
}