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
        while(!scan.hasNextInt());
        int b = scan.nextInt();
        scan.close();
        return b;
    }
    //funzione che prende in input un double
    public static double doubput(String a){
        output(a);
        Scanner scan = new Scanner(System.in);
        while(!scan.hasNextDouble());
        double b = scan.nextDouble();
        scan.close();
        return b;
    }
    //funzione che prende in input una stringa
    public static String strput(String a){
        output(a);
        Scanner scan = new Scanner(System.in);
        while(!scan.hasNextLine());
        a = scan.nextLine();
        scan.close();
        return a;
    }

}