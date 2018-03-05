package com.covisint;

public class FizzBuzz4_1 {
  public static String evaluate( int value ) {
    String retval = null;
    
    if( value % 15 == 0 ) {
      retval = "FizzBuzz";
    }
    else if( value % 5 == 0 ) {
      retval = "Buzz";
    }
    else if( value % 3 == 0 ) {
      retval = "Fizz";
    }
    else {
      retval = Integer.toString( value );
    }
    
    return retval;
  }
}
