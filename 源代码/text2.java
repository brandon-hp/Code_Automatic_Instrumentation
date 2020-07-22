package com.tutorialspoint;

import java.lang.*;

public class text2 {

   public static void main(String[] args) throws Throwable {

      try {
         newException();
      } catch(Throwable e) {
         System.err.println(e);
         // returns null as the cause is nonexistent or unknown.
         System.err.println("Cause = " + e.getCause());
      }
   }
  
   public static void newException() throws Exception {
      System.out.println("This is newException() function");
      throw new Exception("Exception...");
   }
}
 
