package com.opentext.scout.core;

import java.util.Map;
import java.util.Set;

public class Transposition {
  public static String transpose( String value, Map<String, ?> transpose ) {
    Set<String> tKeys = transpose.keySet();
    
    String lastValue = null;
    do {
      lastValue = new String( value );
      
      for( String tKey : tKeys ) {
        String keyVal      = "(?ui)[{][{]\\s*" + tKey + "\\s*[}][}]";
        String matchString = "(?ui)(.*)[{][{]\\s*" + tKey + "\\s*[}][}](.*)";
      
        if( value.matches( matchString )) {
          value = value.replaceFirst( keyVal, transpose.get( tKey ).toString() );
        }
      }
    }
    while( !lastValue.equals( value ));
    
    return value;
  }
}
