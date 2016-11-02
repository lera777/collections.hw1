package school.lemon.changerequest.java.collections.hw1;


import java.util.List;

public interface ExtendedList<E> extends List<E> {
    /**
     * Returns an iterator over the elements in this list in sequence, based on given filter object
     *
     * @param filter object to use in iterator
     * @return an iterator over the elements in this list sequence, based on given filter object
     * @throws IllegalArgumentException if filter is null
     */
    ConditionalIterator<E> conditionalIterator(Filter<E> filter) throws IllegalArgumentException;
}
