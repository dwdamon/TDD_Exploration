package com.covisint.com.covisint.parallel;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.config.NetworkConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

public class TestConcurrentMap {
  //private static ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap( 16, 0.9f, 2 );
  //private static ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap( 32, 0.5f, 20 );
  //private static Map<String, Integer> map = new HashMap();
  
  private static HazelcastInstance hazelcastInstance = null;
  private static Map<String, Object> map = null;
  
  private static Map<String, Object> instantiateMap( boolean clustered ) {
    Map<String,Object> retval = null;
    
    if( clustered ) {
      Config config = new Config();
    
      NetworkConfig network = config.getNetworkConfig();
      JoinConfig join = network.getJoin();
    
      join.getMulticastConfig().setEnabled( false );
      join.getTcpIpConfig().setEnabled( true );
    
      //if( hosts != null && !hosts.isEmpty() ) {
      //  for( String host : hosts ) {
      //    join.getTcpIpConfig().addMember( host );
      //  }
      //}
    
      hazelcastInstance = Hazelcast.newHazelcastInstance( config );
      retval = hazelcastInstance.getMap( "test" );
    }
    else {
      retval = new <String,Object> ConcurrentHashMap();
    }
  
    return retval;
  }
  
  public static void main( String[] args ) {
    boolean clustered = true;
    
    map = instantiateMap( clustered );
    
    for( int i = 0; i < 100000; ++i ) {
      String value = Integer.toString( i );
      map.put( "com.covisint." + value, value );
    }
    /*
    map.put( "A", 1 );
    map.put( "B", 2 );
    map.put( "C", 3 );
    map.put( "D", 4 );
    map.put( "E", 5 );
    map.put( "F", 6 );
    map.put( "G", 7 );
    */
  
  
    //map.forEach( 2, (k, v) -> System.out.println( "key->" + k + "is related with value-> " + v + ", by thread-> " + Thread.currentThread().getName() ));
    
    //System.out.println( "Size: " + map.size() );
    //map.remove( "com.covisint." + ( new Integer( 50000 )).toString() );
    //System.out.println( "Size: " + map.size() );
  
    
    map.put( "X", "x" );
    System.out.println( "1st ==> " + map );
    System.out.println( "2nd ==> " + map.merge( "X", "x", (v1, v2) -> null ));
    System.out.println( "3rd ==> " + map );
    map.put( "Y", "y" );
    map.put( "X", "x1" );
    System.out.println( "4th ==> " + map.merge( "X", "x1", (v1, v2) -> "z" ));
    System.out.println( "5th ==> " + map );
    System.out.println( "6th ==> " + map.merge( "X", "x1", (v1, v2) -> (v1.toString() + v2.toString()).concat( "z" )));
    System.out.println( "7th ==> " + map );
    System.out.println( "8th ==> " + map.merge( "X", "x1", (v1, v2) -> v1 + "." + v2 ));
    System.out.println( "9th ==> " + map );
    
    map.forEach( (k, v) -> { if( !k.startsWith( "com.covisint" )) System.out.println( "key -> " + k + " is related with value-> " + v );});
    
    if( clustered ) {
      hazelcastInstance.shutdown();
    }
  }
}
