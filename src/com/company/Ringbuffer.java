package com.company;

import java.io.Serializable;
import java.util.*;

public class Ringbuffer<T> implements Deque<T>, RandomAccess, Serializable, Cloneable {
    private ArrayList<T> elements;
    private int head = 0;
    private int tail = 0;
    private int size = 0;
    private int capacity;
    private boolean fixedCapacity = false;
    private boolean discarding = false;

    Ringbuffer(int capacity, boolean fixedCapacity, boolean discarding){
        this.capacity = capacity;
    }

    public void toggleDiscarding(){
        if(discarding){
            discarding = false;
            System.out.println("Information: Discarding of elements has been DISABLED");
        }
        else {
            discarding = true;
            System.out.println("Information: Discarding of elements has been ENABLED");
        }
    }

    public void toggleFixedCapacity(){
        if(fixedCapacity){
            fixedCapacity = false;
            System.out.println("Information: Ringbuffer capacity is now VARIABLE");
        }
        else {
            fixedCapacity = true;
            System.out.println("Information: Ringbuffer capacity is now FIXED");
        }
    }

    private void incrementHead(){
        //increase capacity of buffer if the buffer is full and the buffer capacity is variable
        if(size == capacity && !fixedCapacity){
            capacity = capacity*2;
        }
        //reset head position if head reached rightmost bound
        if(head == capacity-1){
            head = 0;
        }else
            head++;
    }

    private void incrementTail(){
        
    }

    @Override
    public void addFirst(T t) {
        if(size < capacity){
            elements.set(0, t);
        }
    }

    @Override
    public void addLast(T t) {
        if(size < capacity){
            elements.set(capacity-1, t);
        }
    }

    @Override
    public boolean offerFirst(T t) {
        return false;
    }

    @Override
    public boolean offerLast(T t) {
        return false;
    }

    @Override
    public T removeFirst() {
        if(size > 0) {
            tail++;
            return elements.get(0);
        }
        return null;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T pollFirst() {
        return null;
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
        return null;
    }

    @Override
    public T peekLast() {
        return null;
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean add(T t) {
        return false;
    }

    @Override
    public boolean offer(T t) {
        return false;
    }

    @Override
    public T remove() {
        return null;
    }

    @Override
    public T poll() {
        return null;
    }

    @Override
    public T element() {
        return null;
    }

    @Override
    public T peek() {
        return null;
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

    }

    @Override
    public T pop() {
        return null;
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
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] t1s) {
        return null;
    }

    @Override
    public Iterator<T> descendingIterator() {
        return null;
    }
}
