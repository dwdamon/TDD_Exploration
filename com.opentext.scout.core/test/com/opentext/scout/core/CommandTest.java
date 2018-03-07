package com.opentext.scout.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance( TestInstance.Lifecycle.PER_CLASS )
public class CommandTest {
  
  @Test
  public void canCreateComponent() {
    assertNotNull( new Command( "NewCommand" ));
  }
  
  @Test
  public void canCreateNamedComponent() {
    assertEquals( "NewCommand", ( new Command( "NewCommand" )).getCommand() );
  }
  
  @Test
  public void canCreateComponentWithOptions() {
    Hashtable<String,String> options = new Hashtable<String,String>();
    options.put( "Option1", "Option1" );
    options.put( "Option2", "Option2" );
    
    Command command = new Command( "NewCommand", options );
    
    assertEquals( "NewCommand", command.getCommand() );
    command.getOptions().forEach(( key, value ) ->  assertEquals( key, value ));
  }
  
  @Test
  public void canCreateComponentWithOptionsAndTranspositions() {
    Hashtable<String,String> options        = new Hashtable<String,String>();
    Map<String,String>       transpositions = new HashMap<String,String>();
    
    transpositions.put( "value1", "Option1" );
    transpositions.put( "value2", "Option2" );
    transpositions.put( "name",   "NewCommand" );
    
    options.put( "Option1 Option2", "{{ value1 }} Option2" );
    options.put( "Option2", "{{ value2 }}" );
    
    Command command = new Command( "{{ name }}", options ).doTranspose( transpositions );
    
    assertEquals( "NewCommand", command.getCommand() );
    command.getOptions().forEach(( key, value ) ->  assertEquals( key, value ));
  }
  
}
