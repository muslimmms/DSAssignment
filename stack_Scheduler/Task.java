/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ds_project;

/**
 *
 * @author User
 */
public class Task {
    String method;
    String variable;
    String value;
    
    Task(String m, String v, String value){
        this.method=m;
        this.value=value;
        this.variable=v;  
    }
    String getMethod(){
        return this.method;
    }
    String getVariable(){
        return this.variable;
    }
    String getValue(){
        return this.value;
    }
    @Override
    public  String toString(){
        return method+" "+variable+" "+value;
    }
}
