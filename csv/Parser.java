package csv;

public class Parser {
    public int parseInt(String stringa){
        int intero = 0;
        char[] array = stringa.toCharArray();
        for(int i=0;i<array.length;i++){
            if(array[i]<48 || array[i]>57){
                array = rimuoviCella(array, i);
            }
        }
        int[] arrayInteri = new int[array.length];
        for(int i=0;i<arrayInteri.length;i++){
            arrayInteri[i] = array[i];
        }
        arrayInteri = giraArray(arrayInteri);
        for (int i = 0; i < arrayInteri.length; i++) {
            arrayInteri[i] = arrayInteri[i] - 48;
        }
        for(int i=0;i<arrayInteri.length;i++){
            intero+=arrayInteri[i]*potenza(10, i);
        }
        return intero;
    }
    public int potenza(int base, int esponente){
        int risultato = 1;
        for(int i=0;i<esponente;i++){
            risultato*=base;
        }
        return risultato;
    }
    public static char[] rimuoviCella(char[] oldArray, int daEliminare){
        char[] newArray = new char[oldArray.length-1];
        int newIndex = 0;
        for(int oldIndex=0;oldIndex<oldArray.length;oldIndex++){
            if(oldIndex!=daEliminare){
                newArray[newIndex++] = oldArray[oldIndex];
            }
        }
        return newArray;
    }
    public int[] giraArray(int[] array){
        int[] newArray = new int[array.length];
        for(int i=0;i<array.length;i++){
            newArray[i] = array[array.length-1-i];
        }
        return newArray;
    }

    public boolean parseBool(String stringa){
        boolean booleano;
        String vero = "true";
        stringa = stringa.toLowerCase();
        return stringa.equals(vero);
    }
}