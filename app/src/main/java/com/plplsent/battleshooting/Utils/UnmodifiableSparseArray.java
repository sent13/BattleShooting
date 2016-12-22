package com.plplsent.battleshooting.Utils;


import android.util.SparseArray;

public class UnmodifiableSparseArray<E> {

    private final SparseArray<E> mArray;
    public UnmodifiableSparseArray(SparseArray<E> array) {
        mArray = array;
    }
    public int size() {
        return mArray.size();
    }
    public E get(int key) {
        return mArray.get(key);
    }
    public E get(int key, E valueIfKeyNotFound) {
        return mArray.get(key, valueIfKeyNotFound);
    }
    public int keyAt(int index) {
        return mArray.keyAt(index);
    }
    public E valueAt(int index) {
        return mArray.valueAt(index);
    }
    public int indexOfValue(E value) {
        return mArray.indexOfValue(value);
    }
    @Override
    public String toString() {
        return mArray.toString();
    }
    public boolean remove(int index){
        return mArray.remove(index);
    }
}
