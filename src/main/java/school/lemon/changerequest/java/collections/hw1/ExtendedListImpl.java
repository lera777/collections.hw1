package school.lemon.changerequest.java.collections.hw1;

import java.util.*;

/**
 * Created by lera on 12.01.17.
 */
public class ExtendedListImpl<E> implements ExtendedList {
    private E[] array;
    private int size;

    public ExtendedListImpl() {
        this.array = (E[]) new Object[10];
    }

    private void checkSize() {
        if (size == array.length) {

            E[] newArray = (E[]) new Object[array.length * 2];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
    }

    private void checkIndexAdd(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    @Override
    public ConditionalIterator conditionalIterator(Filter filter) throws IllegalArgumentException {
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (o.equals(null)) throw new NullPointerException();
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(o)) return true;
        }
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public E[] toArray() {
        return Arrays.copyOf(array, size);
    }

    @Override
    public Object[] toArray(Object[] a) {
        if (a.length < size) {
            return (E[]) Arrays.copyOf(array, size, a.getClass());
        }
        System.arraycopy(array, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    @Override
    public boolean add(Object o) {
        if (o.equals(null)) throw new NullPointerException();
        array[size] = (E) o;
        ++size;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o.equals(null)) throw new NullPointerException();
        for (int i = 0; i < size; i++) {
            if (o.equals(array[i])) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        if (c.contains(null)) throw new NullPointerException();
        Object[] a = c.toArray();
        int sizeA = a.length;
        System.arraycopy(a, 0, array, size, sizeA);
        size += sizeA;
        return sizeA != 0;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        checkIndexAdd(index);
        Object[] a = c.toArray();
        int sizeA = a.length;
        int numMoved = size - index;
        if (numMoved > 0)
            System.arraycopy(array, index, array, index + sizeA,
                    numMoved);

        System.arraycopy(a, 0, array, index, sizeA);
        size += sizeA;
        return sizeA != 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return array[index];
    }

    @Override
    public E set(int index, Object element) {
        checkIndex(index);
        E oldValue = array[index];
        array[index] = (E) element;
        return oldValue;
    }

    @Override
    public void add(int index, Object element) {
        checkIndexAdd(index);
        checkSize();
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = (E) element;
        ++size;
    }

    @Override
    public Object remove(int index) {
        checkIndex(index);
        Object oldValue = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        --size;
        return oldValue;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(array[i])) return i;

        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (o.equals(array[i])) return i;
        }
        return -1;
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }

    private class Itr<E> implements Iterator {
        int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        public E next() {
            return (E) array[cursor++];
        }
    }

    public Iterator createIterator() {
        return new Itr<>();
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        if (fromIndex < 0)
            throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);
        if (toIndex > size)
            throw new IndexOutOfBoundsException("toIndex = " + toIndex);
        if (fromIndex > toIndex)
            throw new IllegalArgumentException("fromIndex(" + fromIndex +
                    ") > toIndex(" + toIndex + ")");
        List subList = new ArrayList(toIndex - fromIndex + 1);
        for (int i = fromIndex; i < toIndex; i++) {
            subList.add(array[i]);
        }
        return subList;
    }

    @Override
    public boolean retainAll(Collection c) {
        if (c == null) throw new NullPointerException();
        Object[] a = c.toArray();
        int sizeA = a.length;
        int currentsize = size();
        Object[] newArray = new Object[size];
        int index = 0;
        for (int i = 0; i < sizeA; i++) {
            for (int j = 0; j < size; j++) {
                if ((a[i].equals(array[j]))) {
                    newArray[index++] = a[i];
                }
            }
        }
        System.arraycopy(newArray, 0, array, 0, index);
        size = index;
        return currentsize != size;
    }


    @Override
    public boolean removeAll(Collection c) {
        Object[] a = c.toArray();
        int sizeA = a.length;
        int currentsize = size();
        for (int i = 0; i < sizeA; i++) {
            for (int j = 0; j < size; j++) {
                if (a[i].equals(array[j])) {
                    remove(j);
                }
            }
        }
        return currentsize != size;
    }

    @Override
    public boolean containsAll(Collection c) {
        Object[] a = c.toArray();
        int sizeA = a.length;
        int count = 0;
        for (int i = 0; i < sizeA; i++) {
            for (int j = 0; j < size; j++) {
                if (a[i].equals(array[j])) {
                    count++;
                    break;
                }
            }
        }
        return count == sizeA;
    }

    @Override
    public String toString() {
        StringBuilder arrayInString = new StringBuilder();
        for (int i = 0; i < size; i++) {
            arrayInString.append(array[i] + " ");
        }
        return arrayInString + " size = " + size + ";";

    }

//    public static void main(String[] args) {
//
//        Integer[] integers = new Integer[2];
//        integers[0] = 95;
//        integers[0] = 96;
//        integers[0] = 97;
//
//        List<Integer> integ = new ArrayList();
//        integ.add(1);
//        integ.add(2);
//        integ.add(3);
//
//        List<Integer> integers1 = new ArrayList();
//        integers1.add(98);
//        integers1.add(97);
//
//        ExtendedListImpl <Integer>extendedList = new ExtendedListImpl();
//        extendedList.add(1);
//        extendedList.add(2);
//        extendedList.add(3);
//        extendedList.add(4);
//        extendedList.add(5);
//        extendedList.add(6);
//        extendedList.add(7);
//        System.out.println(integ);
//        System.out.println(extendedList.toString());
//        System.out.println(extendedList.remove(3));
//        System.out.println(extendedList.toString());
//        System.out.println(extendedList.retainAll(integ));
//        System.out.println( extendedList.removeAll(integ));
//        System.out.println(extendedList.toString());
//        System.out.println(extendedList.addAll(2, integ));
//        System.out.println(extendedList.toString());
//        System.out.println(Arrays.asList(extendedList.toArray()));
//        System.out.println(Arrays.asList(extendedList.toArray(integers)));
//        System.out.println(extendedList.removeAll(integ));
//        System.out.println(extendedList.toString());
//        System.out.println(extendedList.add(27));
//        System.out.println(extendedList.toString());
//        System.out.println(extendedList.remove(1));
//        System.out.println(extendedList.toString());
//        System.out.println(extendedList.indexOf(27));
//        System.out.println(extendedList.add(98));
//        System.out.println(extendedList.add(97));
//        System.out.println(extendedList.toString());
//        extendedList.add(2, 100);
//        System.out.println(extendedList.toString());
//        System.out.println(extendedList.subList(1,3));
//        System.out.println(extendedList.containsAll(integers1));
//    }
}
