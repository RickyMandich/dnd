import java.util.Scanner;
public class Interazione {
    //funzione che da in output una stringa
    public static void output(String a){
        System.out.println(a);
    }
    //funzione che prende in input un intero
    public static int input(String a){
        output(a);
        Scanner scan = new Scanner(System.in);
        int b;
        b = scan.nextInt();
        scan.close();
        return b;
    }
    //funzione che prende in input un double
    public static double doubput(String a){
        output(a);
        Scanner scan = new Scanner(System.in);
        double b;
        b = scan.nextDouble();
        scan.close();
        return b;
    }
    //funzione che prende in input un double
    public static boolean boolput(String a){
        return input(a+"\n(insert 1 for true and 0 for false)")==1;
    }
    //funzione che prende in input una stringa
    public static String strput(String a){
        output(a);
        Scanner scan = new Scanner(System.in);
        String b;
        b  = scan.nextLine();
        scan.close();
        return b;
    }
}