package csv;

import java.io.FileNotFoundException;

public class Lettore_csv {
    public String[] tabel;
    public void getCsv(String file) throws FileNotFoundException {
        java.io.BufferedReader reader = null;
        String line;
        tabel = new String[0];
        boolean exist = false;
        try{
            reader = new java.io.BufferedReader(new java.io.FileReader(file));
            while((line = reader.readLine()) != null){
                aggiungiRiga(line);
            }
        }
        catch (java.io.FileNotFoundException e){
            throw new FileNotFoundException();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                reader.close();
            } catch (java.io.IOException | NullPointerException e) {
                //e.printStackTrace();
            }
        }
    }
    public void aggiungiRiga(String row){
        String[] newTabel;
        newTabel = new String[tabel.length+1];
        for(int i=0;i<tabel.length;i++){
            newTabel[i] = tabel[i];
        }
        newTabel[tabel.length] = row;
        tabel = newTabel;
    }

    public void outElencoCsv(){
        for(int i=0;i<tabel.length;i++){
            System.out.print(i + ")\t");
            String[] row = tabel[i].split(",");
            for(int j=0;j<row.length;j++){
                System.out.printf("%-35s", row[j]);
            }
            System.out.println();
        }
    }
}