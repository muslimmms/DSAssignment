/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.ds_project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class DS_PROJECT {
    private  MyStack<Task> SystemC;
    private StarterPack sp;
    DS_PROJECT(){
        SystemC = new MyStack<>(30);
        sp = new StarterPack();
    }
     void readText(String txt){
        try {
            Scanner scanner = new Scanner(new File(txt));
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                
                String[] parts = line.split(" ");
                String method = parts[0];
                String variable = parts[1];
                String value = parts[2];
                Task task = new Task(method, variable, value);
                
                SystemC.push(task);
            }
            scanner.close();
            System.out.println(SystemC.toString());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DS_PROJECT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     void executeSystem(){
         while(!SystemC.isEmpty()){
             Task current = SystemC.pop();
             
             method(current);
         }
     }
     void method(Task task){
         if(task.getMethod().equals("isPrime")){
             int integer = Integer.parseInt(task.getValue());
             boolean result = sp.isPrime(integer);
             System.out.println(result);
         }
         else if(task.getMethod().equals("fib")){
             int integer = Integer.parseInt(task.getValue());
             int result = sp.fib(integer);
             System.out.println(result);
         }
         else if(task.getMethod().equals("longestPalSubstr")){
             String str = task.getValue();
             String result = sp.longestPalSubstr(str);
             System.out.println(result);
         }
         else if(task.getMethod().equals("sumOfDigitsFrom1ToN")){
             int integer = Integer.parseInt(task.getValue());
             int result = sp.sumOfDigitsFrom1ToN(integer);
             System.out.println(result);
         }
         else if(task.getMethod().equals("sumOfDigits")){
             int integer = Integer.parseInt(task.getValue());
             int result = sp.sumOfDigits(integer);  
             System.out.println(result);
         }
         else if(task.getMethod().equals("isUgly")){
             int integer = Integer.parseInt(task.getValue());
             int result = sp.isUgly(integer);         
             System.out.println(result);
         }
         else if(task.getMethod().equals("getNthUglyNo")){
             int integer = Integer.parseInt(task.getValue());
             int result = sp.getNthUglyNo(integer);      
             System.out.println(result);
         }         
     }
     
    public static void main(String[] args) {
        DS_PROJECT dp= new DS_PROJECT();
        dp.readText("C://Users//User//Downloads//scheduler project//tasks.txt");
        dp.executeSystem();
    }
}
