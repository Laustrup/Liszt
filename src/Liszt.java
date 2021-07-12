import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

/*

    Implements same objects as ArrayList at this moment

    Has attributes of an array of E and as well an Map, which needs to be determined whether it's a hashmap or treemap.
    That way an object of E can quickly be returned and the whole array can be looped thru easily

 */

// Author Laust Eberhardt Bonnesen
public class Liszt<E> extends AbstractList<E> implements List<E>, RandomAccess, Cloneable, java.io.Serializable {

    private Map<String,E> map;
    private transient Object[] elementData;
    private int size;

    public Liszt() {
        this(true,1);
    }

    public Liszt(boolean isHashMap, int size) {
        super();
        this.size = size;
        if (isHashMap) {
            map = new HashMap<>();
        }
        else {
            map = new TreeMap<>();
        }
        this.elementData = new Object[this.size];
    }

    public Liszt(boolean isHashMap, E[] firstValues) {
        super();
        if (isHashMap) {
            map = new HashMap<>();
        }
        else {
            map = new TreeMap<>();
        }
        this.elementData = addArray(firstValues);
    }

    //Both these two next methods returns the size of the array, but allows the command length that are of an array[]
    @Override
    public int size() {
        return size;
    }

    public int length() {
        return elementData.length;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public boolean contains(Object object) {
        return indexOf(object) >= 0;
    }

    public boolean contains(String key) {
        return map.containsKey(key);
    }

    //To insure that an object is registered in map
    public boolean isObjectInMap(String key, Object object) {
        if (contains(key) && contains(object)) {
            return true;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return (Iterator<E>) this;
    }

    // TODO
    @Override
    public void forEach(Consumer<? super E> action) {
        Objects.requireNonNull(action);
        final int expectedModCount = modCount;
        @SuppressWarnings("unchecked")
        final E[] elementData = (E[]) this.elementData;
        final int size = this.size;
        for (int i=0; modCount == expectedModCount && i < size; i++) {
            action.accept(elementData[i]);
        }
        if (modCount != expectedModCount) {
            throw new ConcurrentModificationException();
        }
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    @Override
    public <T> T[] toArray(T[] array) {
        // Make a new array of a's runtime type, but my contents:
        if (array.length < size) {
            return (T[]) Arrays.copyOf(elementData, size, array.getClass());
        }
        System.arraycopy(elementData, 0, array, 0, size);
        if (array.length > size) {
            array[size] = null;
        }
        return array;
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
        list = (E[]) new Object[0];
        map.clear();
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

    //Removes the index in the array of the parameter input, and returns the E of that is removed. It sorts the array as well
    @Override
    public E remove(int index) {
        E[] newList = (E[]) new Object[list.length-1];
        E elementOfDelete = (E) new Object();

        for (int i = 0; i < list.length-1;i++) {
            if(i<index) {
                newList[i] = list[i];
            }
            else if (i>index) {
                newList[i] = list[i+1];
            }
            else {
                elementOfDelete = list[i];
            }
        }
        list = newList;
        return elementOfDelete;
    }

    public E remove(int index,String key) {
        E[] newList = (E[]) new Object[list.length-1];
        E elementOfDelete = (E) new Object();

        for (int i = 0; i < list.length-1;i++) {
            if(i<index) {
                newList[i] = list[i];
            }
            else if (i>index) {
                newList[i] = list[i+1];
            }
            else {
                elementOfDelete = list[i];
            }
        }
        list = newList;

        map.remove(key);

        return elementOfDelete;
    }

    public E[] removeIndexs(int[] indexs,String[] keys) {
        E[] newList = (E[]) new Object[list.length-indexs.length];
        E[] elementsToDelete = (E[]) new Object[indexs.length];

        int amountsOfDeletes = 0;

        for (int i = 0; i < list.length-1;i++) {
            if(i<indexs[i]) {
                newList[i] = list[i];
            }
            else if (i!=indexs[i]) {
                newList[i] = list[i+amountsOfDeletes];
            }
            else {
                elementsToDelete[i] = list[i];
                amountsOfDeletes++;
            }
        }
        list = newList;

        for (int i = 0; i < keys.length;i++) {
            map.remove(keys[i]);
        }

        return elementsToDelete;
    }

    //Runs through the array, and if the object is identical the to an index, it returns an int of that object's index
    @Override
    public int indexOf(Object object) {
        for (int i = 0; i < size(); i++) {
            if (object.equals(list[i])) {
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
