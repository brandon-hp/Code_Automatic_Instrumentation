package com.tutorialspoint;

import java.lang.*;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.*;

public class text7 {

   public static void main(String[] args) throws Throwable {

      OutputStream out;
      try {
         ExceptionFunc();
      } catch(Throwable e) {
         out = new FileOutputStream("file.text");
         // prints this throwable and its backtrace to the print stream
         PrintStream ps = new PrintStream(out);      
         e.printStackTrace(ps);
      }
   }
  
   public static void ExceptionFunc() throws Throwable {

      Throwable t = new Throwable("This is new Exception...");
      StackTraceElement[] trace = new StackTraceElement[] {
         new StackTraceElement("ClassName","methodName","fileName",10)
      };

      // sets the stack trace elements
      t.setStackTrace(trace);
      throw t;
   }
}
