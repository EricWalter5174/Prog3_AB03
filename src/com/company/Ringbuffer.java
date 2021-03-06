package com.company;
/*
 * Autor: Eric Walter
 * Prog3 AB03
 * Hochschule Osnabrueck
 * */
import java.io.Serializable;
import java.util.*;

public class Ringbuffer<T> implements Deque<T>, RandomAccess, Serializable, Cloneable {
    private ArrayList<T> elements = new ArrayList<>();
    private ArrayList<Boolean> occupied = new ArrayList<>();
    private int head = 0;
    private int tail = 0;
    private int size = 0;
    private int unread = 0;
    private int capacity;
    private boolean fixedCapacity = false;
    private boolean discarding = false;
    private boolean fifo = false;
    private Stack<Integer> fifoTail = new Stack<Integer>();

    Ringbuffer(int capacity) {
        this.capacity = capacity;
        occupied.add(false);

    }

    public void setLifo() {
        fifo = false;
    }

    public void setFifo() {
        fifo = true;
    }

    public void toggleDiscarding() {
        if (discarding) {
            discarding = false;
            System.out.println("Information: Discarding of elements has been DISABLED");
        } else {
            discarding = true;
            System.out.println("Information: Discarding of elements has been ENABLED");
        }
    }

    public void toggleFixedCapacity() {
        if (fixedCapacity) {
            fixedCapacity = false;
            System.out.println("Information: Ringbuffer capacity is now VARIABLE");
        } else {
            fixedCapacity = true;
            System.out.println("Information: Ringbuffer capacity is now FIXED");
        }
    }

    private void incrementHead() {
        if (fifo) {
            fifoTail.add(head);
        }

        if (elements.size() == capacity && !fixedCapacity) {
            capacity = capacity * 2;
        }

        if (head == capacity - 1) {
            head = 0;
        } else {
            head++;
        }
    }

    private void incrementTail() {
        if (tail == capacity - 1) {
            tail = 0;
        } else tail++;
    }

    @Override
    public T pop() {
        unread = Math.max(unread--, 0);
        return fifo
                ? popFifo()
                : popLifo();
    }

    public T popFifo() {
        return elements.get(fifoTail.pop());
    }

    public T popLifo() {
        var oldTail = tail;
        if (occupied.get(tail)) {
            incrementTail();
            size--;
        }

        return elements.get(oldTail);
    }

    @Override
    public boolean add(T t) {
        if (unread == capacity && !discarding) throw new IllegalStateException();
        if (fifo && fifoTail.size() == capacity) throw new IllegalStateException();
        if (elements.size() <= capacity) {
            if (elements.size() - 1 >= head) {
                elements.set(head, t);
                occupied.set(head, true);
            } else {
                elements.add(head, t);
                occupied.add(head, true);
            }
            unread++;
            size++;
            incrementHead();
            return true;
        }
        return false;
    }

    @Override
    public void addFirst(T t) {
        if (size < capacity) {
            elements.set(0, t);
        }
    }

    @Override
    public void addLast(T t) {
        if (size < capacity) {
            elements.set(capacity - 1, t);
        }
    }

    @Override
    public boolean offerFirst(T t) {
        if (size < capacity) {
            addFirst(t);
            return true;
        } else
            return false;
    }

    @Override
    public boolean offerLast(T t) {
        if (size < capacity) {
            addLast(t);
            return true;
        } else
            return false;
    }

    @Override
    public T removeFirst() {
        if (size > 0) {
            tail++;
            return elements.get(0);
        }
        return null;
    }

    @Override
    public T removeLast() {
            return occupied.get(capacity - 1)
                    ?   elements.get(capacity - 1)
                    :   null;
    }

    @Override
    public T pollFirst() {
        return elements.size() > 0
                ? elements.get(0)
                : null;
    }

    @Override
    public T pollLast() {
        return null;
    }

    @Override
    public T getFirst() {
        return null;
    }

    @Override
    public T getLast() {
        return null;
    }

    @Override
    public T peekFirst() {
        if (occupied.get(0)) {
            return elements.get(0);
        }
        return null;
    }

    @Override
    public T peekLast() {
        if (occupied.get(capacity - 1)) {
            return elements.get(capacity - 1);
        }
        return null;
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        for (int i = 0; i < size; i++) {
            if (elements.get(i).equals(o)) {
                occupied.set(i, false);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        for (int i = size; i > 0; i--) {
            if (elements.get(i).equals(o)) {
                occupied.set(i, false);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean offer(T t) {
        if (size < capacity) {
            elements.add(head, t);
            occupied.add(head, true);
            incrementHead();
            return true;
        }
        return false;
    }

    @Override
    public T remove() {
        if (occupied.get(tail)) {
            incrementTail();
            size--;
            return tail == 0
                    ? elements.get(capacity - 1)
                    : elements.get(tail - 1);
        }
        return null;
    }

    @Override
    public T poll() {
        if (elements.isEmpty()) {
            return null;
        }
        return remove();
    }

    @Override
    public T element() {
        return null;
    }

    @Override
    public T peek() {
        return elements.get(tail);
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public void push(T t) {
        add(t);
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    @Override
    public Iterator<T> iterator() {
        return elements.iterator();
    }

    @Override
    public Object[] toArray() {
        return elements.toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] t1s) {
        return elements.toArray(t1s);
    }

    @Override
    public Iterator<T> descendingIterator() {
        return elements.iterator();
    }
}
