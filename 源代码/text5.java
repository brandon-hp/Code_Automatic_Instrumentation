package com.tutorialspoint;

import java.lang.*;

public class text5 {

   public static void main(String[] args) throws Throwable {
     try {
         Exception1();
      } catch(Exception e) {
         System.out.println(e);
      }
   }
  
   public static void Exception1()throws amitException{
      try {
         Exception2();
      } catch(otherException e) {
         amitException a1 = new amitException();
     
         // initializes the cause of this throwable to the specified value. 
         a1.initCause(e);
         throw a1;
      }
   }
  
   public static void Exception2() throws otherException {
      throw new otherException();
   }
}

class amitException extends Throwable {
   amitException() {
      super("This is my Exception....");
   }
}

class otherException extends Throwable {
   otherException() {
      super("This is any other Exception....");
   }
} 
