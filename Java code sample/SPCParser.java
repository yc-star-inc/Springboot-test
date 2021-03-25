package com.tsmc.ecp;

import java.lang.reflect.Method;


interface ICollector{
	void newMethod(); 
}

public class SPCParser implements IDataParser {

	@Override
	public void doConverter() {
		// TODO Auto-generated method stub

	}

}

class Example {

    static void exampleMethod(Method toInvoke) throws Exception {
        // null as callee for static methods
        toInvoke.invoke(null);
    }

    public static void sayHello() {
        System.out.println("Hello");
    }

    public static void main(String[] args) throws Exception {
        // prints "Hello"
        exampleMethod(Example.class.getMethod("sayHello"));
        
        
        DaoHandler<String> daoHandler = new DaoHandler<String>(); 
        daoHandler.doInvoke(); 
    }
} 

class DaoHandler<E> {
	
	@SuppressWarnings("unchecked")
	public E doInvoke() { String testString = "13"; return (E) testString;  }; 
	
}