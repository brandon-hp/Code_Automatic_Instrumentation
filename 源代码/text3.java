package com.tutorialspoint;

import java.lang.*;

public class text3 {

   public static void main(String[] args) throws Throwable {

     try {
         newException();
      } catch(Throwable e) { 
         System.err.println(e);
         // returns the detail message string of this Throwable instance 
         System.out.println(e.getMessage());
      }
   }
  
   public static void newException() throws Exception {
      System.out.println("This is newException() function");
      throw new Exception("new Exception...");
   }
} 
