package com.covisint;

public class FizzBuzz1 {
  public static String evaluate( int value ) {
    StringBuilder retval = new StringBuilder();
    
    if( value % 3 == 0 ) {
      retval.append( "Fizz" );
    }
    
    if( value % 5 == 0 ) {
      retval.append( "Buzz" );
    }
    
    if( retval.length() == 0 ) {
      retval.append( Integer.toString( value ));
    }
    
    return retval.toString();
  }
}
