/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package scheduler;

/**
 *
 * @author muslim
 */
public class MyStack <T extends Object> {
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
    public static void main(String[] args) throws Exception {
        MyStack myStack = new MyStack(10);

        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        //myStack.pushMany("4,5,6");

        System.out.println("Stack contents: " + myStack);
        myStack.popAll();
        System.out.println("Stack contents: " + myStack);

    }   
}
