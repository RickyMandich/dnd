package logica;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class PgList<T extends Personaggio> implements List<T> {
    protected Object[] pg;
    protected int size;
/*
    protected T[] addT(T[] oldArray){
        Object[] newArray = new Object[oldArray.length+1];
        System.arraycopy(oldArray, 0, newArray, 0, newArray.length);
        return newArray;
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
            for(T1 o:a){
                o = null;
            }
            for(int i=0;i<pg.length;i++) a[i] = pg[i];
        }else a = toArray();
        return a;
    }
/*
    @Override
    public boolean add(T t) {
        return false;
    }
/*
    @Override
    public boolean remove(Object o) {
        return false;
    }
/*
    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }
/*
    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }
/*
    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }
/*
    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }
/*
    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
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
