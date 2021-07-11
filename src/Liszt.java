import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

/*

    Implements same objects from ArrayList except AbstractList<E>

    Has attributes of an array of E and as well an Map, which needs to be determined whether it's a hashmap or treemap.
    That way an object of E can quickly be returned and the whole array can be looped thru easily

 */

// Author Laust Eberhardt Bonnesen
public class Liszt<E> implements List<E>, RandomAccess, Cloneable, java.io.Serializable {

    private E[] list;
    private Map<String,E> map;

    public Liszt(boolean isHashMap) {
        if (isHashMap) {
            map = new HashMap<>();
        }
        else {
            map = new TreeMap<>();
        }
        list = (E[]) new Object[0];
    }

    public Liszt(boolean isHashMap, E[] firstValues) {
        if (isHashMap) {
            map = new HashMap<>();
        }
        else {
            map = new TreeMap<>();
        }
        list = (E[]) new Object[0];
        list = addArray(firstValues);
    }

    @Override
    public int size() {
        return list.length;
    }

    @Override
    public boolean isEmpty() {
        return size()==0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    public boolean containsKey(String key) {
        return map.containsKey(key);
    }

    // TODO
    @Override
    public Iterator<E> iterator() {
        return null;
    }

    // TODO
    @Override
    public void forEach(Consumer<? super E> action) {

    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public <T> T[] toArray(IntFunction<T[]> generator) {
        return null;
    }

    @Override
    public boolean add(E e) {
        return false;
    }

    //Adds an entire array of E at the end of the current array
    public E[] addArray(E[] objects) {
        Object[] allIndexs = new Object[list.length+objects.length];
        for (int i = 0; i < allIndexs.length;i++) {
            if (i < list.length) {
                allIndexs[i] = list[i];
            }
            else {
                for (int j = 0; j < objects.length;j++) {
                    allIndexs[i+j] = objects[j];
                }
                break;
            }
        }
        list = (E[]) allIndexs;
        return list;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void replaceAll(UnaryOperator<E> operator) {

    }

    @Override
    public void sort(Comparator<? super E> c) {

    }

    @Override
    public void clear() {

    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public void add(int index, E element) {

    }

    @Override
    public E remove(int index) {
        return null;
    }

    // Runs through the array, and if the object is identical the an index, it returns an int of that object's index
    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size(); i++) {
            if (o.equals(list[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public Spliterator<E> spliterator() {
        return null;
    }

    @Override
    public Stream<E> stream() {
        return null;
    }

    @Override
    public Stream<E> parallelStream() {
        return null;
    }
}
