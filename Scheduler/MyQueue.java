/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author muslim
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package scheduler;

/**
 *
 * @author muslim
 */
class MyQueue<T extends Object>implements Iterable<T>{
    private int head,tail,capacity;
    private T []arr;
    public MyQueue(){
        this.head = 0;
        this.tail = 0;
        this.capacity = 10;
        arr = (T[]) new Object[capacity];
    }
    public MyQueue(int capacity){
        this.head = 0;
        this.tail = 0;
        this.capacity = capacity;
        arr = (T[]) new Object[capacity];
    }
    int getSize(){
        return capacity;
    }
    boolean isFull(){
        return tail >= capacity;
    }
    boolean isEmpty(){
        return tail == 0;
    }
    void enqueue(T element){
        if(!isFull()){
            arr[tail] = element;
            tail++;
        } else {
            //System.out.println("Queue overflow. "+element+" cannot be added.");
        }
    }
    T dequeue(){
        if(!isEmpty()){
            T temp = arr[head];
            for(int i = 0;i<tail-1;i++){
                arr[i] = arr[i+1];
            }
            tail--;
            return temp;
        } else {
            System.out.println("Queue is empty.");
            return null;
        }
    }
    T peek(){
        return arr[head];
    }
    @Override
    public String toString(){
        StringBuilder ab = new StringBuilder();
        ab.append("[");
        for(int i = 0; i<tail;i++){
            ab.append(arr[i]);
            
            if(i < tail-1){
                ab.append(", ");
            }
        }
        ab.append("]");
        return ab.toString();
    }
    public boolean contains(T element){
        for (int i = 0; i < capacity; i++) {
            if (arr[i] != null && arr[i].equals(element)) {
                return true;
            }
        }
        return false;
    }
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < tail && arr[currentIndex] != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return arr[currentIndex++];
            }
        };
    }
}

