/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Scheduler;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author muslim
 */
public class MyStack <T extends Object> implements Iterable<T>{
    int size;
    T[] arr;
    int top;
    public MyStack(){
        this.size=10;
        this.arr = (T[])new Object[size];
        this.top = -1;
    }
    public MyStack(int size){
        this.size=size;
        this.arr = (T[])new Object[size];
        this.top = -1;
    }
    boolean isFull(){
        return top == size-1;
    }
    boolean isEmpty(){
        return top == -1;
    }
    T peek(){
        return this.arr[top];
    }
    void push(T data){
        if(!isFull()){
            arr[++top] = data;
        }
    }
    void pushMany(String data){
        String[] splitArray = data.split(",");
        T [] temp = (T[])new Object[splitArray.length];
        for(int i = 0; i<splitArray.length;i++){
            //temp[i] = Integer.parseInt(splitArray[i]);
            push(temp[i]);
        }

    }
    T pop(){
        if(!isEmpty()){
            T item = arr[top--];
            return item;
        }
        return null;
    }
    void popAll(){
        while(!isEmpty()){
            pop();
        }
        System.out.println("All item have been removed");
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i = 0; i <= top; i++) {
            sb.append(arr[i]);

            if (i < top) {
                sb.append(", ");
            }
        }

        sb.append("]");
        return sb.toString();
    }
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = top;

            @Override
            public boolean hasNext() {
                return currentIndex >= 0;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return arr[currentIndex--];
            }
        };
    }

}
