package com.sergeev.application.list.impl;

import com.sergeev.application.list.MyList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

public class MyArrayListTest {
    private MyList<Integer> myList;

    @BeforeEach
    public void init() {
        myList = new MyArrayList<>();
    }

    @Test
    public void addValueByIndexTest() {
        //given
        int index = 1;
        Integer value = 2;
        //when
        myList.add(index, value);
        //then
        Assertions.assertEquals(index, myList.indexOf(value));
    }

    @Test
    public void addValueByIndexIfPlaceNotNullTest() {
        //given
        int index = 1;
        Integer value = 2;
        Integer value2 = 3;
        //when
        myList.add(index, value);
        myList.add(index, value2);
        //then
        Assertions.assertEquals(index, myList.indexOf(value2));
        Assertions.assertEquals(2, myList.indexOf(value));
    }

    @Test
    public void addValueByIndexExceptionTest() {
        //given
        int index = myList.size() + 100;
        Integer value = 2;
        //when
        var exception = Assertions.assertThrows(IndexOutOfBoundsException.class, () -> myList.add(index, value));
        //then
        Assertions.assertEquals("Invalid bond", exception.getMessage());
    }

    @Test
    public void constructorTest() {
        //given
        MyList<Integer> testList = new MyArrayList<>();
        Integer value = 2;
        Integer value2 = 4;
        //when
        testList.add(value);
        testList.add(value2);
        myList = new MyArrayList<>(testList);
        //then
        Assertions.assertTrue(myList.contains(value));
        Assertions.assertTrue(myList.contains(value2));
    }

    @Test
    public void indexOfTest() {
        //given
        int index = 0;
        Integer value = 2;
        //when
        myList.add(value);
        //then
        Assertions.assertEquals(index, myList.indexOf(value));
    }

    @Test
    public void setTest() {
        //given
        Integer value = 2;
        Integer value2 = 4;
        int index = 0;
        //when
        myList.add(value);
        //then
        Assertions.assertEquals(value, myList.set(index, value2));
        Assertions.assertTrue(myList.contains(value2));
    }

    @Test
    public void setExceptionTest() {
        //given
        Integer value = 2;
        Integer value2 = 4;
        int index = myList.size() + 100;
        //when
        myList.add(value);
        var exception = Assertions.assertThrows(IndexOutOfBoundsException.class, () -> myList.set(index, value2));
        //then
        Assertions.assertEquals("Invalid bond", exception.getMessage());
    }

    @Test
    public void getExceptionTest() {
        //given
        Integer value = 2;
        int index = myList.size() + 100;
        //when
        myList.add(value);
        var exception = Assertions.assertThrows(IndexOutOfBoundsException.class, () -> myList.get(index));
        //then
        Assertions.assertEquals("Invalid bond", exception.getMessage());
    }

    @Test
    public void getTest() {
        //given
        Integer value = 2;
        int index = 0;
        //when
        myList.add(value);
        //then
        Assertions.assertEquals(value, myList.get(index));
    }

    @Test
    public void addAllTest() {
        //given
        MyList<Integer> testList = new MyArrayList<>();
        Integer value = 2;
        Integer value2 = 4;
        //when
        testList.add(value);
        testList.add(value2);
        myList.addAll(testList);
        //then
        Assertions.assertTrue(myList.contains(value));
        Assertions.assertTrue(myList.contains(value2));
    }

    @Test
    public void addAllInNotEmptyListTest() {
        //given
        MyList<Integer> testList = new MyArrayList<>();
        Integer value = 2;
        Integer value2 = 4;
        Integer value3 = 3;
        Integer value4 = 4;
        //when
        myList.add(value);
        myList.add(value2);
        testList.add(value3);
        testList.add(value4);
        myList.addAll(testList);
        //then
        Assertions.assertTrue(myList.contains(value3));
        Assertions.assertTrue(myList.contains(value4));
    }

    @Test
    public void containsTest() {
        //given
        Integer value = 2;
        //when
        myList.add(value);
        //then
        Assertions.assertTrue(myList.contains(value));
    }

    @Test
    public void sizeTest() {
        //given
        int size = myList.size();
        //when
        int sizeDefault = myList.size();
        //then
        Assertions.assertEquals(size, sizeDefault);
    }

    @Test
    public void removeByIndexTest() {
        //given
        Integer value = 2;
        int index = 0;
        //when
        myList.add(value);
        //then
        Assertions.assertEquals(value, myList.remove(index));
    }

    @Test
    public void removeByIndexExceptionTest() {
        //given
        Integer value = 2;
        int index = myList.size() + 100;
        //when
        myList.add(value);
        var exception = Assertions.assertThrows(IndexOutOfBoundsException.class, () -> myList.remove(index));
        //then
        Assertions.assertEquals("Invalid bond", exception.getMessage());
    }

    @Test
    public void removeByValueTest() {
        //given
        Integer value = 2;
        //when
        myList.add(value);
        //then
        Assertions.assertTrue(myList.remove(value));
    }

    @Test
    public void clearTest() {
        //given
        Integer value = 2;
        //when
        myList.add(value);
        myList.clear();
        //then
        Assertions.assertFalse(myList.contains(value));
    }

    @Test
    public void addValueTest() {
        //given
        Integer test = 2;
        //when
        myList.add(test);
        //then
        Assertions.assertEquals(1, myList.size());
    }

    @Test
    public void isEmptyTest() {
        //given
        boolean empty = myList.isEmpty();
        //then
        Assertions.assertTrue(empty);
    }

    @Test
    public void quickSortComparableTest() {
        //given
        Integer[] testArray = {3, 2, 1};
        //when
        myList.quickSort(testArray, 0, testArray.length - 1);
        int value = testArray[0];
        int value2 = testArray[1];
        int value3 = testArray[2];
        //then
        Assertions.assertEquals(1, value);
        Assertions.assertEquals(2, value2);
        Assertions.assertEquals(3, value3);
    }

    @Test
    public void quickSortComparatorTest() {
        //given
        String[] testArray = {"B", "C", "A"};
        Comparator<String> comparator = String::compareTo;
        //when
        myList.quickSort(testArray, 0, testArray.length - 1, comparator);
        String value = testArray[0];
        String value2 = testArray[1];
        String value3 = testArray[2];
        //then
        Assertions.assertEquals("A", value);
        Assertions.assertEquals("B", value2);
        Assertions.assertEquals("C", value3);
    }

    @AfterEach
    public void destroy() {
        myList = null;
    }
}
