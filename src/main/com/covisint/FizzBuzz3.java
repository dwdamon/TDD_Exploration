package com.covisint;

public class FizzBuzz3 {
  public static String evaluate( int value ) {
    StringBuilder retval = new StringBuilder();
    
    if( value % 3 == 0 ) {
      retval.append( "Fizz" );
    }
    
    if( value % 5 == 0 ) {
      retval.append( "Buzz" );
    }
    
    return retval.length() > 0 ? retval.toString()
                               : Integer.toString( value );
  }
}
