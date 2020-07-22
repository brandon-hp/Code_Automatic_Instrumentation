package com.tutorialspoint;

import java.lang.*;

public class text9 {

   public static void main(String[] args) {

      try {
         ExceptionFunc();
      } catch(Throwable e) {
         // access to the stack trace
         StackTraceElement[] trace = e.getStackTrace();
         System.err.println(trace[0].toString());
      }
   }
  
   public static void ExceptionFunc()throws Throwable {

      Throwable t = new Throwable("This is new Exception...");
      StackTraceElement[] trace = new StackTraceElement[] {
         new StackTraceElement("ClassName","methodName","fileName",10)
      };

      // sets the stack trace elements
      t.setStackTrace(trace);
      throw t;
   }
}
