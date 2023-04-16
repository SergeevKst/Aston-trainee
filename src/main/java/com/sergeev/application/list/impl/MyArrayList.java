package com.sergeev.application.list.impl;

import com.sergeev.application.exception.InvalidArgumentException;
import com.sergeev.application.list.MyList;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

/**
 * it's realization which implement and describe some logic in a couple of methods
 *
 * @param <T> it's generic type which should be replaced on your own type
 */
public class MyArrayList<T> implements MyList<T> {
    private int size;
    private Object[] defaultArray;
    private static final int DEFAULT_CAPACITY = 10;

    public MyArrayList() {
        this.defaultArray = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int capacity) {
        if (capacity >= 0) this.defaultArray = new Object[capacity];
        else throw new InvalidArgumentException("Invalid capacity");
    }

    public MyArrayList(MyList<? extends T> myListImpl) {
        Object[] array = myListImpl.toArray();
        this.size = myListImpl.size();
        this.defaultArray = array;
    }

    /**
     * This method gives response: the data structure is empty or not
     *
     * @return true if the data structure is empty
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * This method can obtain you the quantity of elements
     *
     * @return the quantity of elements in the data structure
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * This method can add your element by index
     *
     * @param index it's place where you want to add element
     * @param obj   element which will be added
     */
    @Override
    public void add(int index, T obj) {
        checkLength(index);
        if (index == size) {
            enhanceLength();
        }
        if (defaultArray[index] == null) {
            defaultArray[index] = obj;
        } else if (defaultArray[index] != null) {
            Object[] list = new Object[defaultArray.length + 1];
            System.arraycopy(defaultArray, 0, list, 0, index);
            list[index] = obj;
            System.arraycopy(defaultArray, index, list, index + 1, defaultArray.length - index);
            defaultArray = list;
        }
        size++;
    }

    /**
     * The method for transformation into array
     *
     * @return array with some elements or empty
     */
    @Override
    public Object[] toArray() {
        Object[] list = new Object[size + 1];
        System.arraycopy(defaultArray, 0, list, 0, size);
        return list;
    }

    /**
     * The method can add element in the data structure
     *
     * @param obj your element which you want to add in the data structure
     * @return true: The element was added
     * false: Something was wrong
     */
    @Override
    public boolean add(T obj) {
        for (int i = 0; i < defaultArray.length; i++) {
            if (defaultArray[i] == null) {
                defaultArray[i] = obj;
                size++;
                return true;
            }
            if (i == defaultArray.length - 1) {
                enhanceLength();
                defaultArray[defaultArray.length - 1] = obj;
                size++;
                return true;
            }
        }
        return false;
    }

    /**
     * This method can clear the data structure
     */
    @Override
    public void clear() {
        for (int i = 0; i < defaultArray.length; i++) {
            if (defaultArray[i] != null) {
                defaultArray[i] = null;
            }
        }
    }

    /**
     * This method can remove an element by index
     *
     * @param index it's place for your element
     * @return Element: which you want to remove
     * null: Something was wrong
     */
    @Override
    public T remove(int index) {
        checkLength(index);
        T value = (T) defaultArray[index];
        Object[] list = new Object[defaultArray.length - 1];
        System.arraycopy(defaultArray, 0, list, 0, index);
        System.arraycopy(defaultArray, index + 1, list, index, defaultArray.length - (index + 1));
        defaultArray = list;
        size--;
        return value;
    }

    /**
     * his method can remove an element by element
     *
     * @param obj it's an element which you want ti remove
     * @return true: The element was removed
     * false: Something was wrong
     */
    @Override
    public boolean remove(T obj) {
        int index = indexOf(obj);
        if (index >= 0) {
            remove(index);
            size--;
            return true;
        }
        return false;
    }

    /**
     * This method can give information about the element
     *
     * @param obj it's an element which will be searched
     * @return true: element does exist in the data structure
     * false: element doesn't exist in the data structure
     */
    @Override
    public boolean contains(T obj) {
        for (Object o : defaultArray) {
            if (Objects.equals(o, obj)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method can add another data structure which was implemented by MyList<O>
     *
     * @param myList The data structure which will be added
     * @return true: The element was added
     * false: Something was wrong
     */
    @Override
    public boolean addAll(MyList<? extends T> myList) {
        if (myList.size() == 0) {
            return false;
        }
        Object[] list = myList.toArray();
        Object[] objects = new Object[defaultArray.length + list.length];
        System.arraycopy(defaultArray, 0, objects, 0, size);
        System.arraycopy(list, 0, objects, size, myList.size());
        defaultArray = objects;
        size = size + myList.size();
        return true;
    }

    /**
     * This method can return index for element
     *
     * @param obj it's an element which will be added
     * @return number which will be deffer from -1 it's an index
     * -1: element wasn't searched
     */
    @Override
    public int indexOf(T obj) {
        for (int i = 0; i < defaultArray.length; i++) {
            if (defaultArray[i] == obj) {
                return i;
            }
        }
        return -1;
    }

    /**
     * The method for transformation into array
     *
     * @param objects The data structure
     * @return The array with your elements or empty
     */
    @Override
    public <T> T[] toArray(T[] objects) {
        Object[] list = new Object[objects.length];
        System.arraycopy(defaultArray, 0, list, 0, objects.length);
        return (T[]) list;
    }

    /**
     * This method can replace element from the data structure on your element by index
     *
     * @param index it's place with element which you want to replace
     * @param obj   it's element which must be in the dats structure
     * @return element which was replaced
     * null: index was invalid or place was empty
     */
    @Override
    public T set(int index, T obj) {
        checkLength(index);
        T value = (T) defaultArray[index];
        defaultArray[index] = obj;
        return (T) value;
    }

    /**
     * This method can obtain you element by an index
     *
     * @param index it's place with an element which will be searched
     * @return The element which will be found
     * null: element doesn't exist
     */
    @Override
    public T get(int index) {
        checkLength(index);
        return (T) defaultArray[index];
    }

    /**
     * This method can establish a full-order relation for the classes which implement Comparable<O>
     *
     * @param array The data structure which contains some elements whose class implements Comparable
     * @param low   You should choose lower index in the data structure
     * @param high  You should choose higher index in the data structure
     * @param <T>   It's generic type which has been chosen
     */
    @Override
    public <T extends Comparable<T>> void quickSort(T[] array, int low, int high) {
        if (array.length == 0) return;
        if (low >= high) return;

        int middle = low + (high - low) / 2;
        T point = array[middle];

        int i = low, j = high;
        while (i <= j) {

            while (array[i].compareTo(point) < 0) i++;
            while (array[j].compareTo(point) > 0) j--;

            if (i <= j) {
                T temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

        if (low < j) quickSort(array, low, j);
        if (high > i) quickSort(array, i, high);
    }

    /**
     * This method can establish a full-order relation for the data structure by means of specific object Comparator
     *
     * @param array      The data structure which contains some elements which you want to sort
     * @param low        You should choose lower index in the data structure
     * @param high       You should choose higher index in the data structure
     * @param comparator Specific object which can establish a full-order relation
     * @param <T>        It's generic type which has been chosen
     */
    @Override
    public <T> void quickSort(T[] array, int low, int high, Comparator<T> comparator) {
        if (array.length == 0) return;
        if (low >= high) return;

        int middle = low + (high - low) / 2;
        T point = array[middle];

        int i = low, j = high;
        while (i <= j) {
            while (comparator.compare(array[i], point) < 0) i++;
            while (comparator.compare(array[j], point) > 0) j--;

            if (i <= j) {
                T temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }
        if (low < j) quickSort(array, low, j, comparator);
        if (high > i) quickSort(array, i, high, comparator);
    }

    private void checkLength(int index) {
        if (index > defaultArray.length || index < 0) {
            throw new InvalidArgumentException("Invalid bond");
        }
    }

    private void enhanceLength() {
        Object[] list = new Object[defaultArray.length + 1];
        System.arraycopy(defaultArray, 0, list, 0, defaultArray.length - 1);
        defaultArray = list;
    }

    /**
     * Basic realization "equals"
     *
     * @param o it's object which will be checked
     * @return true: objects are equals
     * false: objects are different
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyArrayList<?> that = (MyArrayList<?>) o;
        return size == that.size && Arrays.equals(defaultArray, that.defaultArray);
    }

    /**
     * Basic realization "hashCode"
     *
     * @return hashCode for object
     */
    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(defaultArray);
        return result;
    }
}
