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

    private int size;

    private String defaultKeySymbol = "Д";

    private transient Object[] elementData;
    private static final Object[] EMPTY_ELEMENTDATA = {};
    private static final int DEFAULT_CAPACITY = 10;
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE;

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

    //TODO
    @Override
    public <T> T[] toArray(IntFunction<T[]> generator) {
        return null;
    }

    //TODO Check if size++ changes size attribute as well
    @Override
    public boolean add(E element) {
        ensureCapacityInternal(size + 1);  // Increments modCount!!
        elementData[size++] = element;
        map.put(defaultKeySymbol+size,element);
        return true;
    }

    //Adds an entire array of E at the end of the current array
    public E[] addArray(E[] objects) {
        int amountOfIndexs = size + objects.length;
        ensureCapacityInternal(amountOfIndexs);  // Increments modCount!!
        for (int i = 0; i < amountOfIndexs;i++) {
            elementData[size++] = objects;
            map.put(defaultKeySymbol+size,objects[i]);
        }
        return (E[]) elementData;
    }

    public E[] addArray(E[] objects, String[] keys) {
        int amountOfIndexs = size + objects.length;
        ensureCapacityInternal(amountOfIndexs);  // Increments modCount!!
        for (int i = 0; i < amountOfIndexs;i++) {
            elementData[size++] = objects;
            map.put(keys[i],objects[i]);
        }
        return (E[]) elementData;
    }


    private void ensureCapacityInternal(int minCapacity) {
        if (elementData == EMPTY_ELEMENTDATA) {
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }

        ensureExplicitCapacity(minCapacity);
    }

    private void ensureExplicitCapacity(int minCapacity) {
        modCount++;

        // overflow-conscious code
        if (minCapacity - elementData.length > 0) {
            grow(minCapacity);
        }
    }

    private void grow(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0) {
            newCapacity = minCapacity;
        }
        if (newCapacity - MAX_ARRAY_SIZE > 0) {
            newCapacity = hugeCapacity(minCapacity);
        }
        // minCapacity is usually close to size, so this is a win:
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    private static int hugeCapacity(int minCapacity) {
        // overflow
        if (minCapacity < 0) {
            System.err.println("\nOut of memory...\n");
            throw new OutOfMemoryError();
        }
        return (minCapacity > MAX_ARRAY_SIZE) ?
                Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
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
        elementData = (E[]) new Object[0];
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
        Object[] newList = new Object[size-1];
        Object elementOfDelete = new Object();

        for (int i = 0; i < size-1;i++) {
            if(i<index) {
                newList[i] = elementData[i];
            }
            else if (i>index) {
                newList[i] = elementData[i+1];
            }
            else {
                elementOfDelete = elementData[i];
            }
        }
        elementData = newList;

        return (E) elementOfDelete;
    }

    public E remove(int index,String key) {
        Object[] newList =  new Object[size-1];
        Object elementOfDelete =  new Object();

        for (int i = 0; i < size-1;i++) {
            if(i<index) {
                newList[i] = elementData[i];
            }
            else if (i>index) {
                newList[i] = elementData[i+1];
            }
            else {
                elementOfDelete = elementData[i];
            }
        }
        elementData = newList;
        map.remove(key);

        return (E)elementOfDelete;
    }

    public E[] removeIndexs(int[] indexs,String[] keys) {
        Object[] newList = new Object[size-indexs.length];
        Object[] elementsToDelete = new Object[indexs.length];

        int amountsOfDeletes = 0;

        for (int i = 0; i < elementsToDelete.length-1;i++) {
            if(i<indexs[i]) {
                newList[i] = elementData[i];
            }
            else if (i!=indexs[i]) {
                newList[i] = elementData[i+amountsOfDeletes];
            }
            else {
                elementsToDelete[i] = elementData[i];
                amountsOfDeletes++;
            }
        }
        elementData = newList;

        for (int i = 0; i < keys.length;i++) {
            map.remove(keys[i]);
        }

        return (E[]) elementsToDelete;
    }

    //Runs through the array, and if the object is identical the to an index, it returns an int of that object's index
    @Override
    public int indexOf(Object object) {
        for (int i = 0; i < size(); i++) {
            if (object.equals(elementData[i])) {
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
