package com.sergeev.application.list;

import java.util.Comparator;

/**
 * It's interface which describes some methods which must be overridden
 *
 * @param <T> it's generic type which should be replaced on your own type
 */
public interface MyList<T> {
    boolean isEmpty();

    int size();

    void add(int index, T obj);

    Object[] toArray();

    boolean add(T obj);

    void clear();

    T remove(int index);

    boolean remove(T obj);

    boolean contains(T obj);

    boolean addAll(MyList<? extends T> myList);

    int indexOf(T obj);

    Object[] toArray(Object[] objects);

    T set(int index, T obj);

    T get(int index);

    <T extends Comparable<T>> void quickSort(T[] array, int low, int high);

    <T> void quickSort(T[] array, int low, int high, Comparator<T> comparator);
}
