/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package scheduler;

/**
 *
 * @author muslim
 */
public class MyLinkedList<T extends Object> implements Iterable<T>{
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public MyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    public int getSize(){
        return size;
    }
    public void addFirst(T data){
        Node<T> newNode = new Node<>(data);
        if(head == null){
            head = newNode;
            tail = head;
        } else {
            newNode.setNext(head);
            head = newNode;
        }
        size++;
    }
    public void addLast(T data){
        Node<T> newNode = new Node<>(data);
        if(tail == null){
            head = newNode;
            tail = head;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }
    
    public T removeFirst() {
        if(head == null){
            return null;
        }
        T removedData = head.getData();
        head = head.getNext();
        size--;
        
        if(head == null) {
            tail = null;
        }
        
        return removedData;
    }
    
    @Override
    public String toString(){
        StringBuilder result = new StringBuilder("[size=" + size + "] ");
        Node<T> current = head;
        while (current != null) {
            result.append(" >> ").append(current.getData());
            current = current.getNext();
        }
        return result.toString(); 
    }
    
    public boolean contains(T element){
        Node<T> current = head;
        while (current != null){
            if(current.getData().equals(element))
                return true;
            current = current.getNext();
        }
        return false;
    }
    
    public void clear() {
        head = null;
        tail = null;
        size = 0;
        System.out.println("The list is cleared.");
    }
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T data = current.getData();
                current = current.getNext();
                return data;
            }
        };
    }
    
    
}
