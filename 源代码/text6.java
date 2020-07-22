package com.tutorialspoint;

import java.lang.*;

public class text6 {

   public static void main(String[] args) {

      try {
         ExceptionFunc();
      } catch(Throwable e) {
         // prints stacktace for this Throwable Object
         e.printStackTrace();
      }
   }
  
   public static void ExceptionFunc() throws Throwable {

      Throwable t = new Throwable("This is new Exception...");
      StackTraceElement[] trace = new StackTraceElement[] {
         new StackTraceElement("ClassName","methodName","fileName",5)
      };

      // sets the stack trace elements
      t.setStackTrace(trace);
      throw t;
   }
} 
