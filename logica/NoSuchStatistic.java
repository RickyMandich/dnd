package logica;
public class NoSuchStatistic extends RuntimeException{
    public NoSuchStatistic(String message) {
        super(message);
    }
}