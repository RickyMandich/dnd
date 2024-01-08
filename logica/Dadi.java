package logica;

import java.util.Random;

public class Dadi {
    protected static Random ran = new Random();
    protected static boolean tiro = getBoolean("vuoi i dadi come input\t(altrimenti li tiro io in automatico)\n(1 per sì)");
    
    public static int tiro(int origin, int bound){
        if(tiro){
            return getInt("inserisci il risultato naturale del dado tra " + origin + " e " + bound);
        }else{
            int num = ran.nextInt(origin, bound);
            System.out.println("il risultato del tiro è:\t" + num);
            return num;
        }
    }

    public static int tiro(int origin, int bound, int bonus){
        return tiro(origin, bound) + bonus;
    }
    public static String getString(String stringa){
        System.out.println(stringa);
        return System.console().readLine();
    }
    public static int getInt(String stringa){
        return parseInt(getString(stringa));
    }
    public static boolean getBoolean(String stringa){
        int in = getInt(stringa);
        return in != 0;
    }

    public static int parseInt(String stringa){
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
    public static int[] giraArray(int[] array){
        int[] newArray = new int[array.length];
        for(int i=0;i<array.length;i++){
            newArray[i] = array[array.length-1-i];
        }
        return newArray;
    }
    public static int potenza(int base, int esponente){
        int risultato = 1;
        for(int i=0;i<esponente;i++){
            risultato*=base;
        }
        return risultato;
    }
}