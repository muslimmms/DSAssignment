/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package scheduler;

/**
 *
 * @author muslim
 */
public class Node<T extends Object> {
    private T data;
    private Node<T> next;
    private Node<T> prev;


    // Constructor
    public Node(T data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    // Getter and Setter methods for data
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    // Getter and Setter methods for next
    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
    public Node<T> getPrev() {
        return prev;
    }
    public void setPrev(Node<T> prev) {
        this.prev = prev;
    }

}

