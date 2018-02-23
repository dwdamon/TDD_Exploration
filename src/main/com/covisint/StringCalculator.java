package com.covisint;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator
{
  private static String[] EMPTY_TOKEN_SET = new String[ 0 ];
  
  public StringCalculator()
  {
  }
  
  private String[] tokenizeCommaAndNewLineDelimiters( String value ) {
    return value.split( "[\n,]" );
  }
  
  private String[] tokenizeCustomDelimiters( String value ) {
    String   work   = value.substring( 2 );
    String[] temp   = work.split( "\n", 2 );
    String[] retval = StringCalculator.EMPTY_TOKEN_SET;
  
    if( temp[ 0 ] != null && temp[ 1 ] != null && temp[ 0 ].length() > 0 && temp[ 1 ].length() > 0 ) {
      Pattern pattern = Pattern.compile( "([^\\[\\]]+)" );
      Matcher matcher = pattern.matcher( temp[ 0 ]);
  
      while( matcher.find() ) {
        temp[ 1 ] = temp[ 1 ].replaceAll( Pattern.quote( matcher.group( 1 )), "," );
      }
      
      retval = tokenizeCommaAndNewLineDelimiters( temp[ 1 ]);
    }
    
    return retval;
  }
  
  public int calculate( String value )
  {
    int retval = 0;
  
    if( value != null && value.length() > 0 )
    {
      String[] tokens = null;
      if( value.startsWith( "//" ))
      {
        tokens = this.tokenizeCustomDelimiters( value );
      }
      else {
        tokens = tokenizeCommaAndNewLineDelimiters( value );
      }
      
      for( int i = 0; i < tokens.length; ++i ) {
        int temp = Integer.parseInt( tokens[ i ] );
        if( temp < 0 ) {
          throw new IllegalArgumentException( "Negative numbers are not allowed." );
        }
        retval += temp < 1001 ? temp : 0;
      }
    }
  
    return retval;
    
  }
}
