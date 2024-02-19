package csv;
public class TestParseBoolean{
    public static void main(String[] args) {
        System.out.println("vuoi usare quello standard o quello creato a mano?\tinserisci true per fatto a mano e false per standard");
        if(new java.util.Scanner(System.in).nextBoolean()) {
            try{
                System.out.println("true parsato è ->>");
                System.out.println(logica.Personaggio.parseBoolean("true"));
            }catch (Exception e){
                e.printStackTrace();
            }
            try{
                System.out.println("trUe parsato è ->>");
                System.out.println(logica.Personaggio.parseBoolean("trUe"));
            }catch (Exception e){
                e.printStackTrace();
            }
            try{
                System.out.println("faLse parsato è ->>");
                System.out.println(logica.Personaggio.parseBoolean("faLse"));
            }catch (Exception e){
                e.printStackTrace();
            }
            try{
                System.out.println("false parsato è ->>");
                System.out.println(logica.Personaggio.parseBoolean("false"));
            }catch (Exception e){
                e.printStackTrace();
            }
            try{
                System.out.println("ciao parsato è ->>");
                System.out.println(logica.Personaggio.parseBoolean("ciao"));
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            System.out.println("true parsato è ->>" + Boolean.parseBoolean("true"));
            System.out.println("trUe parsato è ->>" + Boolean.parseBoolean("trUe"));
            System.out.println("faLse parsato è ->>" + Boolean.parseBoolean("faLse"));
            System.out.println("false parsato è ->>" + Boolean.parseBoolean("false"));
            System.out.println("ciao parsato è ->>" + Boolean.parseBoolean("ciao"));
        }
    }
}