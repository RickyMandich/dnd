public class OverWrite{
    public static void main(String[] args) {
        Reader r = new Reader();
        String[][] tabel = r.getCsv("csv\\Personaggi_output.csv");
        Writer w = new Writer("Personaggi.csv");
        for(int i=1;i<tabel.length;i++){
            w.addCsv(toString(tabel[i]));
        }
        w.close();
    }

    public static String toString(String[] row){
        String varRow = "";
        for(int i=0;i<row.length;i++){
            varRow = varRow.concat(row[i]).concat(", ");
        }
        return varRow + "\n";
    }
}