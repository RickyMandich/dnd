package logica;

import java.util.Collection;
import java.util.List;

public class PgList<T extends Personaggio> implements List<T> {
    protected Object[] pg;
    protected int size;

    protected void aggiungiCella(){
        Object[] newArray = new Object[pg.length+1];
        System.arraycopy(pg, 0, newArray, 0, newArray.length);
        pg = newArray;
    }
    protected void aggiungiCelle(int quante){
        for (int i=0;i<quante;i++) {
            aggiungiCella();
        }
    }
/**/
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

    public boolean notContains(Object o){
        return !contains(o);
    }
/*
    @Override
    public Iterator<T> iterator() {
        return null;
    }
/**/
    @Override
    public Object[] toArray() {
        Object[] newPg = new Object[pg.length];
        for (int j = 0; j < newPg.length; j++) {
            newPg[j] = pg[j];
        }
        return newPg;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if(a.length>=pg.length){
            for(T1 t:a){
                t = null;
            }
            for(int i=0;i<pg.length;i++) a[i] = pg[i];
        }else a = toArray();
        return a;
    }
/**/
    @Override
    public boolean add(T element) {
        if(element==null) return false;
        else{
            int i=0;
            while(pg[i]!=null){
                if(i+1==pg.length) aggiungiCella();
                i++;
            }
            pg[i] = element;
            return true;
        }
    }

    @Override
    public void add(int index, T element) {
        if(pg[pg.length-1] != null){
            aggiungiCella();
        }
        for(int i=pg.length-1;i>index;i--) pg[i] = pg[i-1];
        pg[index] = element;
    }

    @Override
    public boolean remove(Object o) {
        int i=0;
        while(pg[i]!=null && pg[i] != o){
            if(i+1 == pg.length) return false;
            i++;
        }
        pg[i] = o;
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for(Object o:c){
            if(notContains(o)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean modificato = false;
        for(T t:c){
            if(add(t)) modificato = true;
        }
        return modificato;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        for(T t:c) add(index++, t);
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modificato = false;
        for(Object o:c){
            if(remove(o)) modificato = true;
        }
        return modificato;
    }

    @Override
    public boolean retainAll(Collection<?> c) {

    }
/*
    @Override
    public void clear() {

    }
/*
    @Override
    public T get(int index) {
        return null;
    }
/*
    @Override
    public T set(int index, T element) {
        return null;
    }
/*
    @Override
    public void add(int index, T element) {

    }
/*
    @Override
    public T remove(int index) {
        return null;
    }
/*
    @Override
    public int indexOf(Object o) {
        return 0;
    }
/*
    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }
/*
    @Override
    public ListIterator<T> listIterator() {
        return null;
    }
/*
    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }
/*
    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    /**/
}
