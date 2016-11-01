package school.lemon.changerequest.java.hw6;


import java.util.Comparator;
import java.util.List;

public interface ExtendedList<E> extends List<E> {
    /**
     * Returns an iterator over the elements in this list in sequence, based on given filter object
     *
     * @param filter object to use in iterator
     * @return an iterator over the elements in this list sequence, based on given filter object
     */
    ConditionalIterator<E> conditionalIterator(Filter<E> filter);
}
