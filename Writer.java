import java.io.*;
public class Writer{
    FileWriter writer;

    public Writer(String file) {
        try {
            writer = new FileWriter(file);
            writer.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addCsv(String stringa) {
        try {
            writer.append(stringa);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void close(){
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}