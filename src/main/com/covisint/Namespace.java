package com.covisint;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Namespace {
  private String name;
  private ConcurrentHashMap<String, Object> map;
  
  public Namespace( String name ) {
    this.name = name;
    this.map  = new ConcurrentHashMap( 16, 0.9f, 1 );
  }
  
  public Object put( final String key, final Object value ) {
    return map.put( key, value );
  }
  
}
