package ca.uwo.eng.se2205b.lab01;

import java.util.AbstractList;
import java.util.LinkedList;
import java.util.List;

/**
 * Doubly linked list implementation
 */
public class MyLinkedList<T> extends AbstractList<T> implements List<T> {

    class Node
    {
        T value;
        private Node next;
        private Node previous;
        public Node(T val, Node nx, Node prev){
            value = val;
            next = nx;
            previous = prev;
        }
    }

    int size;
    Node head;
    Node tail;


    public MyLinkedList(List<? extends T> base) {
        int i = 0;

        //Add every element of the list being copied over to the new list until you have copied them all
        while (i != base.size())
        {
            add(base.get(i));
            i++;
        }
    }

    /**
     * Default constructor for MyLinkedList
     */

    @SuppressWarnings("unchecked")
    public MyLinkedList() {
        size = 0;
        head = null;
        tail = null;
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
     * Adds a new element into the list at a specified index
     * @param index the index where we want the item to be stored
     * @param t the value of the item
     * @throws IndexOutOfBoundsException if the index is greater than size or less than 0
     */

    @Override
    public void add(int index, T t)
    {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException();

        //If there are no node in the LinkedList, create the first node
        if (size == 0)
        {
            Node newNode = new Node(t, null, null);
            head = newNode;
            tail = newNode;
        }

        else if (index < size){
            Node runner = head;


            for (int i = 0; i < index; i++)
                runner = runner.next;

            if (runner == head)
            {
                Node newNode = new Node(t, runner, null);
                runner.previous = newNode;
                head = newNode;
            }

            else {
                Node newNode = new Node(t, runner, runner.previous);
                runner.previous.next = newNode;
                runner.previous = newNode;
            }
        }

        else if ((size == 1) && (index == 1))
        {
            Node newNode = new Node(t, null, head);
            head.next = newNode;
            tail = newNode;
        }

        else if ((size > 1) && (index == size)){
            Node runner = head;

            for (int i = 0; i < (index - 1); i++)
                runner = runner.next;

            Node newNode = new Node(t, null, runner);
            runner.next = newNode;
            tail = newNode;
        }
        size++;
    }

    /**
     * Retrieves an item at a specified index
     * @param index the index where we want the item to be stored
     * @return the the value of the item retrieved
     * @throws IndexOutOfBoundsException if the index is greater than or equal to size or less than 0
     */

    @Override
    public T get(int index) {
        if (index >= size || index < 0)
            throw new IndexOutOfBoundsException();

        //Create a new node that traverses through the list and points to the indexed element
        Node runner = head;
        for (int i = 0; i < index; i++)
            runner = runner.next;

        return runner.value;
    }

    /**
     * Changes the value of an item at a specified index to another value
     * @param index the index where we want the item to be stored
     * @param element the value of the item to be inserted
     * @return the value of the item that was replaced
     * @throws IndexOutOfBoundsException if the index is greater than or equal to size or less than 0
     */

    @Override
    public T set (int index, T element){
        if (index >= size || index < 0)
            throw new IndexOutOfBoundsException();

        //Create a new node that traverses through the list and points to the indexed element
        Node runner = head;
        for (int i = 0; i < index; i++)
            runner = runner.next;

        T temp = runner.value;
        runner.value = element;
        return temp;
    }

    /**
     * Removes an item from the list at a specified index
     * @param index the index where we want the item to be stored
     * @return the value of the item removed
     * @throws IndexOutOfBoundsException if the index is greater than or equal to size or less than 0 or size is 0
     */

    @Override
    public T remove (int index){
        if (size == 0 || index >= size || index < 0)
            throw new IndexOutOfBoundsException();

        //Create a new node that traverses through the list and points to the indexed element
        Node runner = head;
        for (int i = 0; i < index; i++)
            runner = runner.next;

        T temp = null;

        if ((runner.previous == null) && (runner.next == null))
        {
            temp = runner.value;
            head = null;
            tail = null;
            size--;
            return temp;
        }

        else if (runner.previous == null)
        {
            temp = runner.value;
            head = runner.next;
            runner.next.previous = null;
            size--;
            return temp;
        }

        else if (runner.next == null)
        {
            temp = runner.value;
            tail = runner.previous;
            runner.previous.next = null;
            size--;
            return temp;
        }

        else
        {
            temp = runner.value;
            runner.previous.next = runner.next;
            runner.next.previous = runner.previous;
            size--;
            return temp;
        }
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

        //Loop through and compare the equality of every element in the passed in list to its corresponding obj in myLinkedList
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
