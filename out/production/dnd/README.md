# D&D
programma che va a simulare il combattimento nella 5e di Dangeouns and Dragons così da semplificare il lavoro del dangeoun master
## contenuto dei package
- `csv`
    - alcune classi utili all'elaborazione dei file `.csv`
    - alcune eccezioni utili alla gestione degli errori nei file `.csv`
    - un file contenente l'elenco dei file `.csv` disponibili
    - una cartella contenente i file sorgente
- `logica`
  - la classe `Test.java` che contiene il metodo `main` ossia il metodo da cui parte tutto il programma
  - la classe `TestVari.java` ossia un'altra classe contenente un metodo `main` utile per fare dei test durante lo sviluppo di alcuni metodi
  - la classe `Personaggio.java` ossia la classe che definisce il personaggio non giocante
  - la classe `Giocante.java` ossia la classe che definisce il personaggio giocante e che eredita da `Personaggio.java`
  - la classe `PgListWithArray` ossia la classe contenente l'elenco di personaggi (gestito come un'array) e tutti i metodi reativi
  - la classe `PgListWithInterface` ossia la classe che contiene l'elenco dei personaggi creato tramite l'interfaccia `list<e>` e tutti i metodi relativi
  - l'eccezione `NoSuchStatistic` ossia l'eccezione lanciata quando l'utente inserisce una statistica non riconosciuta
- `vecchio` un package obsoleto contenente una vecchia versione del programma che funzionava male
  - la classe `Test.java` contenente il metodo `main` e dei metodi che lavorano su tutto l'array di personaggi
  - la classe `Personaggio.java` ossia la classe che definisce il personaggio
  - la classe `Statistica.java` ossia la classe che definisce la statistica
  - la classe `ArrayStatistica.java` ossia la classe che definisce l'array di 6 elemente di tipo `statistica`
  - la classe `Interazione` accessibile anche nella repository [Interazione](https://github.com/MandichRiccardo/java_00_Class_Interazione) che definisce dei metodi di input e di output con l'utente
  - la classe `Dadi.java` ossia la classe che definisce il tiro dei dadi


### ATTENZIONE: esiste la possibilità che il file sia stato aggiornato, di conseguenza è giusto sapere che il file con questo nome presente nel link esterno è la versione più recente e più corretta 