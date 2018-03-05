package com.covisint;

public class FizzBuzz4 {
  public static String evaluate( int value ) {
    if( value % 15 == 0 ) {
      return "FizzBuzz";
    }
    else if( value % 5 == 0 ) {
      return "Buzz";
    }
    else if( value % 3 == 0 ) {
      return "Fizz";
    }
    
    return Integer.toString( value );
  }
}
