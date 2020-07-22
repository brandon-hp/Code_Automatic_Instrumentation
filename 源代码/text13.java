package com.tutorialspoint;

import java.lang.*;

public class text13 {

   public static void main(String[] args) {

      ThreadLocal<Integer> tlocal = new ThreadLocal<Integer>();  

      /* sets the current thread's copy of this thread-local variable
      to the specified value. */

      tlocal.set(100);
      // returns the current thread's value of this thread-local
      System.out.println("value = " + tlocal.get());
 
      tlocal.set(0);
      // returns the current thread's value of this thread-local
      System.out.println("value = " + tlocal.get());
   }
} 
