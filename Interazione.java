import java.util.Scanner;
public class Interazione {
    private static final Scanner scan = new Scanner(System.in);
    //funzione che scambia due interi
    public static void scambiaInt(int a, int b){
        int temp = a;
        a = b;
        b = temp;
    }
    //funzione che scambia due double
    public static void scambiaDouble(double a, double b){
        double temp = a;
        a = b;
        b = temp;
    }
    //funzione che scambia due stringhe
    public static void scambiaBool(boolean a, boolean b){
        boolean temp = a;
        a = b;
        b = temp;
    }
    //funzione che scambia due stringhe
    public static void scambiaString(String a, String b){
        String temp = a;
        a = b;
        b = temp;
    }

    //funzione che da in output una stringa
    public static void output(String a){
        System.out.println(a);
    }
    //funzione che prende in input un intero
    public static int input(String a){
        output(a);
        int b = scan.nextInt();
        scan.nextLine();
        return b;
    }
    //funzione che prende in input un double
    public static double doubput(String a){
        output(a);
        double b;
        b = scan.nextDouble();
        return b;
    }
    //funzione che prende in input un double
    public static boolean boolput(String a){
        return input(a+"\n(insert 1 for true and 0 for false)")==1;
    }
    //funzione che prende in input una stringa
    public static String strput(String a){
        output(a);
        String b;
        b  = scan.nextLine();
        return b;
    }

    //funzione che chiude l'oggetto scanner
    public static void close(){
        scan.close();
    }
}