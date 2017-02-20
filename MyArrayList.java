package ca.uwo.eng.se2205b.lab01;

import java.util.*;

/**
 * Array List implementation
**/

public class MyArrayList<T> extends AbstractList<T> implements List<T> {

    private T [] array;
    private int capacity;
    private int size;

    /**
     *
     * @param base another list that we are copying elements from into an instance of MyLinkedList
     */

    @SuppressWarnings("unchecked")
    public MyArrayList(List<? extends T> base){
        if (base == null)
            throw new NullPointerException();

        capacity = base.size();
        size = base.size();
        this.array = (T[])(new Object[capacity]);

        //Loop through every element in the base list and copy it over to the MyArrayList
        for (int i = 0; i < size; i++){
            array[i] = base.get(i);
        }
    }

    /**
     *
     * @param initialCapacity accepts an initial capacity that a user wants for MyArrayList
     */

    @SuppressWarnings("unchecked")
    public MyArrayList(int initialCapacity) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException();

        capacity = initialCapacity;
        this.array = (T[])(new Object[initialCapacity]);
        size = 0;
    }

    /**
     * Adds a new element into the list at a specified index
     * @param index the index where we want the item to be stored
     * @param element the value of the item
     * @throws IndexOutOfBoundsException if the index is greater than size or less than 0
     */

    @Override
    public void add(int index, T element)
    {
        if  (index < 0 || index > size())
            throw new IndexOutOfBoundsException();

        //If  the capacity of the MyArrayList is initially 0, assign an arbitrary capacity to it and
        //create a new underlying array with that capacity so elements can be inserted
        if (capacity == 0){
            capacity = 5;
            T [] tempArray = (T[])(new Object[capacity]);
            array = tempArray;
        }

        //If the capacity of the array is the same as the current number of elements, we need to expand the array
        //so double the capacity and make a new temporary array with this capacity
        else if (size == capacity)
        {
            capacity = capacity * 2;
            T [] tempArray = (T[])(new Object[capacity]);

            //Copy all elements into the new array and assign it to the underlying array reference
            for (int i = 0; i < size; i++)
                tempArray[i] = array[i];
            array = tempArray;
        }

        if (index == size)
        {
            array[size] = element;
            size++;
        }

        else if (index < size)
        {
            //Copy all the elements one index to the right starting at the specified index and insert the new element to that specified index
            for (int i = (size - 1); i >= index; i--)
                array[i + 1] = array[i];
            array[index] = element;
            size++;
        }
    }

    /**
     * Retrieves an item at a specified index
     * @param index the index where we want the item to be stored
     * @return the the value of the item retrieved
     * @throws IndexOutOfBoundsException if the index is greater than or equal to size or less than 0
     */

    @Override
    public T get(int index) {
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException();
        return array[index];
    }

    /**
     * Retrieves the number of items in the list
     * @return the number of items in the list
     */

    @Override
    public int size() {
        return size;
    }

    /**
     * Removes an item from the list at a specified index
     * @param index the index where we want the item to be stored
     * @return the value of the item removed
     * @throws IndexOutOfBoundsException if the index is greater than or equal to size or less than 0
     */

    @Override
    public T remove(int index){
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException();

        T temp = array[index];

        if (index == capacity) {
            array[index] = null;
            size--;
            return temp;
        }


        else {
            //Copy all of the elements in the list one over to the left starting at the index and moving right
            for (int i = index; i < (size - 1); i++)
                array[i] = array[i + 1];
            array[size - 1] = null;
            size--;
            return temp;
        }
    }

    /**
     * Changes the value of an item at a specified index to another value
     * @param index the index where we want the item to be stored
     * @param element the value of the item to be inserted
     * @return the value of the item that was replaced
     * @throws IndexOutOfBoundsException if the index is greater than or equal to size or less than 0
     */

    @Override
    public T set(int index, T element)
    {
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException();

        T temp = array[index];
        array[index] = element;
        return temp;
    }

    /**
     * Retrieves a string containing the values of the items in the array
     * @return a string containing the values of the items in the array in the form of [a, b, c]
     */

    @Override
    public String toString()
    {
        //Create a new StringBuilder instance and append a '[' character as the first element
        StringBuilder string = new StringBuilder(size);
        string.append("[");

        //Loop through the entire array and append every element into the string we are building
        for (int i = 0; i < size; i++)
        {
            if (i == (size - 1))
                string.append(get(i));
            else
                string.append(get(i) + ", ");
        }

        string.append("]");
        return string.toString();
    }

    /**
     * Checks another list for equality
     * @param o the object to be checked for equality
     * @return whether the elements of the passed in object were equal to the elements in the list or not
     */

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof List))
            return false;
        if (o == this)
            return true;
        if (!(((List) o).size() == this.size()))
            return false;

        //Loop through and compare the equality of every element in the passed in list to its corresponding obj in myArrayList
        for (int i = 0; i < size; i++) {
            T element1 = get(i);
            T element2 = (T) ((List) o).get(i);

            boolean check = false;
            check = element1 == null ? element2 == null : element1.equals(element2);

            if (check == false)
            {
                return false;
            }
        }
        return true;
    }
}