package com.covisint;

public class FizzBuzz6 {
  public static String evaluate( int value ) {
    if( value % 15 == 0 ) {
      return "FizzBuzz";
    }
  
    if( value % 5 == 0 ) {
      return "Buzz";
    }
    
    if( value % 3 == 0 ) {
      return "Fizz";
    }
    
    return Integer.toString( value );
  }
}
