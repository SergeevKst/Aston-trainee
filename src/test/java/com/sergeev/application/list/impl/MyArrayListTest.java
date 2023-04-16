package com.sergeev.application.list.impl;

import com.sergeev.application.exception.InvalidArgumentException;
import com.sergeev.application.list.MyList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Comparator;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals(index, myList.indexOf(value));
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
        assertEquals(2, myList.indexOf(value));
    }

    @Test
    public void addValueByIndexExceptionTest() {
        //given
        int index = myList.size() + 100;
        Integer value = 2;
        //when
        var exception = assertThrows(InvalidArgumentException.class, () -> myList.add(index, value));
        //then
        assertEquals("Invalid bond", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("getArguments")
    public void constructorTest(Integer testValue) {
        //given
        MyList<Integer> testList = new MyArrayList<>();
        Integer value = 2;
        Integer value2 = 4;
        //when
        testList.add(value);
        testList.add(value2);
        myList = new MyArrayList<>(testList);
        //then
        assertTrue(myList.contains(testValue));
    }

    @Test
    public void indexOfTest() {
        //given
        int index = 0;
        Integer value = 2;
        //when
        myList.add(value);
        //then
        assertEquals(index, myList.indexOf(value));
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
        assertEquals(value, myList.set(index, value2));
    }

    @Test
    public void setExceptionTest() {
        //given
        Integer value = 2;
        Integer value2 = 4;
        int index = myList.size() + 100;
        //when
        myList.add(value);
        var exception = assertThrows(InvalidArgumentException.class, () -> myList.set(index, value2));
        //then
        assertEquals("Invalid bond", exception.getMessage());
    }

    @Test
    public void getExceptionTest() {
        //given
        Integer value = 2;
        int index = myList.size() + 100;
        //when
        myList.add(value);
        var exception = assertThrows(InvalidArgumentException.class, () -> myList.get(index));
        //then
        assertEquals("Invalid bond", exception.getMessage());
    }

    @Test
    public void getTest() {
        //given
        Integer value = 2;
        int index = 0;
        //when
        myList.add(value);
        //then
        assertEquals(value, myList.get(index));
    }

    @ParameterizedTest
    @MethodSource("getArguments")
    public void addAllTest(Integer testValue) {
        //given
        MyList<Integer> testList = new MyArrayList<>();
        Integer value = 2;
        Integer value2 = 4;
        //when
        testList.add(value);
        testList.add(value2);
        myList.addAll(testList);
        //then
        assertTrue(myList.contains(testValue));
    }

    @ParameterizedTest
    @MethodSource("getArguments")
    public void addAllInNotEmptyListTest(Integer testValue) {
        //given
        MyList<Integer> testList = new MyArrayList<>();
        Integer value = 3;
        Integer value2 = 4;
        Integer value3 = 2;
        Integer value4 = 4;
        //when
        myList.add(value);
        myList.add(value2);
        testList.add(value3);
        testList.add(value4);
        myList.addAll(testList);
        //then
        assertTrue(myList.contains(testValue));

    }

    @Test
    public void containsTest() {
        //given
        Integer value = 2;
        //when
        myList.add(value);
        //then
        assertTrue(myList.contains(value));
    }

    @Test
    public void sizeTest() {
        //given
        int size = myList.size();
        //when
        int sizeDefault = myList.size();
        //then
        assertEquals(size, sizeDefault);
    }

    @Test
    public void removeByIndexTest() {
        //given
        Integer value = 2;
        int index = 0;
        //when
        myList.add(value);
        //then
        assertEquals(value, myList.remove(index));
    }

    @Test
    public void removeByIndexExceptionTest() {
        //given
        Integer value = 2;
        int index = myList.size() + 100;
        //when
        myList.add(value);
        var exception = assertThrows(InvalidArgumentException.class, () -> myList.remove(index));
        //then
        assertEquals("Invalid bond", exception.getMessage());
    }

    @Test
    public void removeByValueTest() {
        //given
        Integer value = 2;
        //when
        myList.add(value);
        //then
        assertTrue(myList.remove(value));
    }

    @Test
    public void clearTest() {
        //given
        Integer value = 2;
        //when
        myList.add(value);
        myList.clear();
        //then
        assertFalse(myList.contains(value));
    }

    @Test
    public void addValueTest() {
        //given
        Integer test = 2;
        //when
        myList.add(test);
        //then
        assertEquals(1, myList.size());
    }

    @Test
    public void isEmptyTest() {
        //given
        boolean empty = myList.isEmpty();
        //then
        assertTrue(empty);
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
        assertEquals(1, value);
        assertEquals(2, value2);
        assertEquals(3, value3);
    }

    @Test
    public void quickSortComparatorTest() {
        //given
        String[] testArray = {"A", "B", "C"};
        Comparator<String> comparator = String::compareTo;
        //when
        myList.quickSort(testArray, 0, testArray.length - 1, comparator);
        String value = testArray[0];
        String value2 = testArray[1];
        String value3 = testArray[2];
        //then
        assertEquals("A", value);
        assertEquals("B", value2);
        assertEquals("C", value3);
    }

    @AfterEach
    public void destroy() {
        myList = null;
    }

    public static Stream<Arguments> getArguments() {
        return Stream.of(
                Arguments.of(2),
                Arguments.of(4)
        );
    }
}
