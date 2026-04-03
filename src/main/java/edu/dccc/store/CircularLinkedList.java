package edu.dccc.store;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

class CircularIterator<T> implements ListIterator<T> {
    private LinkedList<T> list;
    private ListIterator<T> listIterator;

    public CircularIterator(LinkedList<T> list) {
        this.list = list;
        listIterator = list.listIterator();
    }

    @Override
    public boolean hasNext() {
        return !list.isEmpty();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more elements in the circular list.");
        }

        if (!listIterator.hasNext()) {
            listIterator = list.listIterator(0);
        }

        return listIterator.next();
    }

    @Override
    public boolean hasPrevious() {
        return !list.isEmpty();
    }

    @Override
    public T previous() {
        if (!hasPrevious()) {
            throw new NoSuchElementException("No more elements in the circular list.");
        }

        if (!listIterator.hasPrevious()) {
            listIterator = list.listIterator(list.size());
        }

        return listIterator.previous();
    }

    // Implementing other methods of ListIterator interface
    @Override
    public int nextIndex() {
        return listIterator.nextIndex();
    }

    @Override
    public int previousIndex() {
        return listIterator.previousIndex();
    }

    @Override
    public void remove() {
        listIterator.remove();
    }

    @Override
    public void set(T e) {
        listIterator.set(e);
    }

    @Override
    public void add(T e) {
        listIterator.add(e);
    }
}


public class CircularLinkedList<T> {
    private LinkedList<T> list;

    public CircularLinkedList() {
        list = new LinkedList<>();
    }

    public void add(T element) {
        list.add(element);
    }

    public ListIterator<T> iterator() {
        return new CircularIterator<>(list);
    }


    public void remove(int index) {
        list.remove(index);
    }

    public T get(int index) {
        int adjustedIndex = index % list.size();
        if (adjustedIndex < 0) {
            adjustedIndex += list.size();
        }
        return list.get(adjustedIndex);
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void clear() {
        list.clear();
    }

    public boolean contains(Object o) {
        return list.contains(o);
    }

    public int indexOf(Object o) {
        return list.indexOf(o);
    }

    public int lastIndexOf(Object o) {
        return list.lastIndexOf(o);
    }

    public Object[] toArray() {
        return list.toArray();
    }


    //  Example uses
    public static void main(String[] args) {
        CircularLinkedList<String> circularList = new CircularLinkedList<>();
        circularList.add("A");
        circularList.add("B");
        circularList.add("C");

        ListIterator<String> iterator = circularList.iterator();
        System.out.println("Forward iteration:");
        for (int i = 0; i < 9; i++) {
            if (iterator.hasNext()) {
                System.out.print(iterator.next() + " ");
            }
        }

        System.out.println("\nBackward iteration:");
        for (int i = 0; i < 9; i++) {
            if (iterator.hasPrevious()) {
                System.out.print(((CircularIterator<String>) iterator).previous() + " ");
            }
        }

        CircularLinkedList<Integer> circularListInt = new CircularLinkedList<>();
        circularListInt.add(1);
        circularListInt.add(2);
        circularListInt.add(3);

        ListIterator<Integer> iterator2 = circularListInt.iterator();


        System.out.println();
        System.out.println("Forward iteration:");
        for (int i = 0; i < 9; i++) {
            if (iterator2.hasNext()) {
                System.out.print(iterator2.next() + " ");
            }
        }

        System.out.println("\nBackward iteration:");
        for (int i = 0; i < 9; i++) {
            if (iterator2.hasPrevious()) {
                System.out.print( iterator2.previous() + " ");
            }
        }
        System.out.println();

        CircularLinkedList<String> fruits = new CircularLinkedList<>();

        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");
        fruits.add("Durian");

        ListIterator<String> fruitIterator = fruits.iterator();

        int loops = 1;
        System.out.println("\nForward iteration:");
        while (fruitIterator.hasNext()) {
            String element = fruitIterator.next();
            System.out.println(element);
            if (loops++ >=8)
                break;
        }
        System.out.println();
        System.out.println("Element at index 5: " + fruits.get(5));
        System.out.println("Element at index -2: " + fruits.get(-2));
        System.out.println("Element at index 1: " + fruits.get(1));
        System.out.println("Element at index 0: " + fruits.get(0));
        System.out.println("Element at index 4: " + fruits.get(4));
        System.out.println("Element at index -16: " + fruits.get(-16));


        System.out.println("\nBackward iteration:");
        fruitIterator = fruits.iterator();
        for (int i = 0; i < 4; i++) {
            if (fruitIterator.hasPrevious()) {
                System.out.println(((CircularIterator<String>)fruitIterator).previous() + " ");
            }
        }
        loops = 0;
        System.out.println("\nForward iteration:");
        while (fruitIterator.hasNext()) {
            String element = fruitIterator.next();
            System.out.println(element);
            if (loops++ > 2)
                break;
        }

    }
}