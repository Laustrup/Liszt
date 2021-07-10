import java.util.*;

public class Liszt implements List {

    Object[] list = new Object[0];
    Map<String,Object> map;

    public Liszt(boolean isHashMap) {
        if (isHashMap) {
            map = new HashMap<>();
        }
        else {
            map = new TreeMap<>();
        }
    }

    public Liszt(boolean isHashMap, Object[] values) {
        if (isHashMap) {
            map = new HashMap<>();
        }
        else {
            map = new TreeMap<>();
        }
        add(values);
    }

    @Override
    public int size() {

        int amounts = 0;

        for (int i = 0; i < list.length; i++) {
            amounts++;
        }

        return amounts;
    }

    @Override
    public boolean isEmpty() {

        for (int i = 0; i < list.length; i++) {
            if (list[i] != null) {
                return false;
            }
        }
            return true;

    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < list.length; i++) {
            if (list[i] == o) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator iterator() {
        //Todo
        return null;
    }

    @Override
    public Object[] toArray() {

        int index = 0;
        Object[] arr = new Object[list.length+1];

        while (list[index] != null) {
            arr[index] = list[index];
            index++;
        }

        return arr;
    }

    @Override
    public boolean add(Object o) {
        Object[] arr;

        arr = list;
        arr[list.length+2] = o;

        return true;
    }

    public boolean add(Object[] o) {
        Object[] arr;

        arr = list;
        for (int i = 0; i < o.length;i++) {
        arr[list.length+2+i] = o;
        }

        return true;
    }

    @Override
    public boolean remove(Object o) {

        boolean isFound = false;
        int index = 0;
        Object[] newArr = new Object[list.length];

        while (!isFound) {
            if (list[index] == o) {
                for (int i = 0; i < index; i++) {
                    newArr[i] = list[i];
                }
                if (index+1 < list.length) {
                    for (int i = index + 1; i < list.length; i++) {
                        newArr[i] = list[i];
                    }
                }

                list = newArr;
                isFound = true;
            }

            if (index == list.length) {
                break;
            }

            index++;
        }

        return isFound;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public Object get(int index) {
        return null;
    }

    @Override
    public Object set(int index, Object element) {
        return null;
    }

    @Override
    public void add(int index, Object element) {

    }

    @Override
    public Object remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return true;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }
}
