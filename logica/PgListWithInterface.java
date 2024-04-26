package logica;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class PgListWithInterface<Personaggio> implements java.util.List<Personaggio>{
    protected Personaggio[] pg;
    protected int size;
    public PgListWithInterface(Personaggio[] pg) {
        this.pg = pg;
        size = 0;
        int i=0;
        while (i<pg.length && pg[i] != null) {
            size++;
            i++;
        }
    }

    protected void aggiungiCella(){
        Personaggio[] newArray = (Personaggio[]) new Object[pg.length + 1];
        System.arraycopy(pg, 0, newArray, 0, newArray.length);
        pg = newArray;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return pg[0] == null;
    }

    @Override
    public boolean contains(Object o) {
        int i=0;
        while(i<pg.length){
            if(pg[i] == o) return true;
            i++;
        }
        return false;
    }

    @Override
    public Iterator<Personaggio> iterator() {
        return null;
    }

    @Override
    public Personaggio[] toArray() {
        Personaggio[] newPg = (Personaggio[]) new Object[pg.length];
        for (int j = 0; j < newPg.length; j++) {
            newPg[j] = pg[j];
        }
        return newPg;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if(a == null) throw new NullPointerException();
        Class<?> personaggioType = pg.getClass().getComponentType();
        Class<?> supposedSuperType = a.getClass().getComponentType();
        if(!supposedSuperType.isAssignableFrom(personaggioType)) throw new ArrayStoreException();
        else {

        }


        return null;
    }

    @Override
    public boolean add(Personaggio element) {
        if(element==null) throw new NullPointerException();
        else{
            int i=0;
            while(pg[i]!=null){
                if(i+1==pg.length) aggiungiCella();
                i++;
            }
            pg[i] = element;
            size++;
            return true;
        }
    }

    @Override
    public void add(int index, Personaggio element) {
        if(pg[pg.length-1] != null){
            aggiungiCella();
        }
        for(int i=pg.length-1;i>index;i--) pg[i] = pg[i-1];
        pg[index] = element;
        size++;
    }

    @Override
    public boolean remove(Object o) {
        int i=0;
        while(pg[i]!=null && pg[i] != o){
            if(i+1 == pg.length) return false;
            i++;
        }
        if(pg[i] == o){
            while (i<pg.length-1) {
                pg[i] = pg[i+1];
                i++;
            }
            pg[i]= null;
        }
        return true;
    }

    @Override
    public boolean containsAll(java.util.Collection<?> c) {
        for(Object o:c){
            if(!contains(o)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(java.util.Collection<? extends Personaggio> c) {
        boolean modificato = false;
        for(Personaggio t:c){
            if(add(t)) modificato = true;
        }
        return modificato;
    }

    @Override
    public boolean addAll(int index, java.util.Collection<? extends Personaggio> c) {
        for(Personaggio t:c) add(index++, t);
        return true;
    }

    @Override
    public boolean removeAll(java.util.Collection<?> c) {
        boolean modificato = false;
        for(Object o:c){
            if(remove(o)) modificato = true;
        }
        return modificato;
    }

    @Override
    public boolean retainAll(java.util.Collection<?> c) {
        boolean modificato = false;
        for(Personaggio o:pg){
            if(!c.contains(o)){
                modificato = remove(o);
            }
        }
        return modificato;
    }

    @Override
    public void clear() {
        for(Personaggio o:pg) o = null;
    }

    @Override
    public Personaggio get(int index){
        return pg[index];
    }

    @Override
    public Personaggio set(int index, Personaggio element) {
        Personaggio temp = pg[index];
        pg[index] = element;
        return temp;
    }

    @Override
    public Personaggio remove(int index){
        Personaggio temp = pg[index];
        for(int i=index;i<pg.length-1;i++){
            pg[i] = pg[i+1];
        }
        return temp;
    }

    @Override
    public int indexOf(Object o) {
        if(!contains(o)) return -1;
        int i=0;
        while(!pg[i].equals(o)){
            i++;
        }
        return i;
    }

    @Override
    public int lastIndexOf(Object o) {
        if(!contains(o)) return -1;
        int i=pg.length;
        while(!pg[i].equals(o)){
            i--;
        }
        return i;
    }

    @Override
    public ListIterator<Personaggio> listIterator() {
        return null;
    }

    @Override
    public ListIterator<Personaggio> listIterator(int index) {
        return null;
    }

    @Override
    public List<Personaggio> subList(int fromIndex, int toIndex) {
        return null;
    }

}
