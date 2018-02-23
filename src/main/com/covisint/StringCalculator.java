package com.covisint;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator
{
  public StringCalculator()
  {
  }
  
  private String[] getParseInfo( String value )
  {
    String[] retval = new String[] { null, null };
    String   work   = value;
    
    retval[ 0 ] = "[,]";
    
    char first  = value.charAt( 0 );
  
    //if( value.length() > 1 && first != '-' && ( first < '0' || first > '9' ))
    if( work.length() > 1 && work.startsWith( "//" ))
    {
      work = work.substring( 2 );
      String[] temp = work.split( "\n", 2 );
      
      if( temp[ 1 ] != null )
      {
        retval[ 1 ] = temp[ 1 ];
        
        if( temp[ 0 ].length() > 0 )
        {
          Pattern pattern = Pattern.compile( "([^\\[\\]]+)" );
          Matcher matcher = pattern.matcher( temp[ 0 ]);
          
          while( matcher.find() ) {
            retval[ 1 ] = retval[ 1 ].replaceAll( Pattern.quote( matcher.group( 1 ) ), "," );
          }
        }
      }
    }
    
    if( retval[ 1 ] == null )
    {
      retval[ 1 ] = work.replaceAll( "\n", "," );
    }
    
    return retval;
  }
  
  public int calculate( String value )
  {
    int retval = 0;
    
    if( value != null && value.length() > 0 )
    {
      String[] parseInfo = getParseInfo( value );
      String[] values    = parseInfo[ 1 ].split( parseInfo[ 0 ]);
      
      for( int i = 0; i < values.length; ++i ) {
        int temp = Integer.parseInt( values[ i ] );
        if( temp < 0 ) {
          throw new IllegalArgumentException( "Negative numbers are not allowed." );
        }
        retval += temp < 1001 ? temp : 0;
      }
    }
    
    return retval;
  }
  
}
