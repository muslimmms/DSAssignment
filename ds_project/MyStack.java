/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ds_project;

/**
 *
 * @author User
 */
public class MyStack <T>{
    int size;
    T[] arr;
    int top;
    MyStack(){
        this.size=0;
        this.top=-1;
        arr = (T[]) new Object[size];
    }
    MyStack(int size){
        this.size=size;
        this.top=-1;
        arr=(T[]) new Object[size];
    }
    boolean isFull(){
        return top ==size-1;
    }
    boolean isEmpty(){
        return top ==-1;
    }
    T peek(){
        return this.arr[top];
    }
    public void push(T data){
        if(!isFull()){
            arr[++top]=data;
        }
    }
//    public void pushMany(String data){
//        String[] split = data.split(" ");
//    }
    T pop(){
        if(!isEmpty()){
            T temp = arr[top--];
            return temp;
        }
        return null;
    }
    void popAll(){
        while(!isEmpty()){
            pop();
        }
        System.out.println("All data have been removed");
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i=0;i<= top;i++){
            sb.append(arr[i]);
            if(i<top){
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
