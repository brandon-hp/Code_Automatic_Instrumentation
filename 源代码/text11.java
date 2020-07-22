package com.tutorialspoint;

import java.lang.*;

public class text11 {

   public static void main (String [] args) {

      newThread t1 = new newThread("R");
      newThread t2 = new newThread("S");

      // this will call run() method
      t1.start();
      t2.start();
   }
}

class newThread extends Thread {
   private static ThreadLocal t = new ThreadLocal() {
      protected Object initialValue() {
         return new Integer(n--);
      }
   };

   private static int n = 10;
   newThread(String name) {
      super(name);
   }

   public void run() {
      for(int i = 0; i < 2; i++)
      System.out.println (getName() + " " + t.get());
   }
} 
