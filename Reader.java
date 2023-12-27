import java.io.*;
public class Reader {
    public String[][] getCsv(String file) {
        BufferedReader reader = null;
        String line;
        String[][] tabel = new String[0][];
        boolean exist = false;
        try{
            reader = new BufferedReader(new FileReader(file));
            while((line = reader.readLine()) != null){
                String[] row = line.split(", ");
                tabel = aggiungiRiga(tabel, row);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return tabel;
    }
    public String[][] aggiungiRiga(String[][] tabel, String[] row){
        String[][] newTabel = new String[tabel.length+1][row.length];
        for(int i=0;i<tabel.length;i++){
            for(int j=0;j<row.length;j++){
                newTabel[i][j] = tabel[i][j];
            }
        }
        newTabel[tabel.length] = row;
        return newTabel;
    }
}