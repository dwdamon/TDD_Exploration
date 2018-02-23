package com.covisint;

public class FizzBuzz {
  
  public String evaluate( int value )
  {
    StringBuilder builder = new StringBuilder();
    
    if( value % 3 == 0 )
    {
     builder.append( "Fizz" );
    }
    
    if( value % 5 == 0 )
    {
      builder.append( "Buzz" );
    }
    
    return( builder.length() == 0 ) ? Integer.toString( value ) : builder.toString();
  }
  
}
