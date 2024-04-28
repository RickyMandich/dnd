package csv;
public class Lettore_csv {
    public String[] tabel;
    public void getFile(String file) throws java.io.FileNotFoundException {
        java.io.BufferedReader reader = null;
        String line;
        tabel = new String[0];
        try{
            reader = new java.io.BufferedReader(new java.io.FileReader(file));
            while((line = reader.readLine()) != null){
                // vado ad aggiungere ogni riga del file letto in un'elenco di stringe
                aggiungiRiga(line);
            }
        }
        catch (java.io.FileNotFoundException e){
            // catturo e rilancio un errore nel caso il file specificato non esista
            throw new java.io.FileNotFoundException();
        }
        catch (Exception e){
            // catturo e stampo a schermo un qualsiasi altro errore inatteso
            e.printStackTrace();
        }
        finally {
            try {
                // chiudo il lettore liberando il file
                reader.close();
            } catch (java.io.IOException | NullPointerException ignored) {}
        }
    }

    public void orderAndCompact(){
        // eseguo un ordinamento di tipo bubblesort in modo da mettere tutti i file sorgente disponibili in ordine alfabetico
        boolean scambio = true;
        while (scambio){
            scambio = false;
            for(int i=0;i<tabel.length-1;i++){
                if(tabel[i].compareTo(tabel[i+1])>0){
                    scambio = true;
                    String temp = tabel[i];
                    tabel[i] = tabel[i+1];
                    tabel[i+1] = temp;
                }
            }
        }
        // confronto ogni elemento con il suo vicino in modo da rimuovere i doppioni che vanno a crearsi nel momento in cui vado a sovrascrivere un file
        for(int i=1;i<tabel.length;i++){
            if(tabel[i].compareTo(tabel[i-1]) == 0){
                delete(i--);
            }
        }
    }

    public void delete(int h){
        // genero un nuovo elenco di righe dato che la dimensione di un array non è modificabile e io devo rimuovere una riga
        String[] newTabel = new String[tabel.length-1];
        // copio il vecchio array in quello nuovo di dimensione minore ad eccezione della riga da cancellare
        for(int i=0, j=0;j<newTabel.length;j++, i++){
            if(i==h) i++;
            newTabel[j] = tabel[i];
        }
        // assegno il nuovo array che ho creato al campo di questa classe in modo da sovrascrivere il vecchio elenco
        tabel = newTabel;
    }

    public void aggiungiRiga(String row){
        // genero un nuovo elenco di righe dato che la dimensione di un array non è modificabile e io devo aggiungere una riga
        String[] newTabel = new String[tabel.length+1];
        // copio il vecchio array in quello nuovo di dimensione maggiore
        for(int i=0;i<tabel.length;i++){
            newTabel[i] = tabel[i];
        }
        // inserisco nell'ultima cella (quella "nuova" che quindi so essere vuota la riga da aggiungere all'array
        newTabel[tabel.length] = row;
        // assegno il nuovo array che ho creato al campo di questa classe in modo da sovrascrivere il vecchio elenco
        tabel = newTabel;
    }

    public void outElencoPersonaggi(){
        // mando in output tutto il file separando e distanziondo le righe sulla base delle virgole
        // in modo da rendere il contenuto del file sorgente più leggibile
        System.out.println("personaggi presenti nel file:");
        for(int i=0;i<tabel.length;i++){
            System.out.print(i + ")\t");
            String[] row = tabel[i].split(",");
            for(int j=0;j<row.length;j++){
                System.out.printf("%-35s", row[j]);
            }
            System.out.println();
        }
    }
    public void outElencoNomiFile(){
        // mando in output tutto il file numerando le righe
        System.out.println("file csv:");
        for(int i=0;i<tabel.length;i++){
            System.out.println(i + ")\t" + tabel[i]);
        }
    }
}