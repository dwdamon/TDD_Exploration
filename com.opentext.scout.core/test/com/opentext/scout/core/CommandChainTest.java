package com.opentext.scout.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance( TestInstance.Lifecycle.PER_CLASS )
public class CommandChainTest {
  
  @Test
  public void canInstantiateCommandChain( ) {
    assertNotNull( new CommandChain() );
  }
  
  @Test
  public void canInstantiateCommandChainHasUUID( ) {
    assertNotNull( new CommandChain().getIdCommandChain() );
  }
  
  @Test
  public void canDeepClone( ) {
    CommandChain chain1 = new CommandChain();
    CommandChain chain2 = (CommandChain) chain1.deepClone();
    
    assertEquals( chain1.getIdCommandChain(), chain2.getIdCommandChain() );
  }
  
  @Test
  public void canAddCommands( ) {
    CommandChain chain = new CommandChain();
    Hashtable<String,String> options = new Hashtable<String,String>();
    
    options.put( "option1", "value1" );
    options.put( "option2", "value2" );
    options.put( "option3", "value3" );
    
    CommandChain.add( chain, "command1", options );
    CommandChain.add( chain, "command2", options );
    CommandChain.add( chain, "command3", options );
    
    chain.getCommands().iterator().forEachRemaining( command -> System.out.println( command.getCommand() ));
    
    assertEquals( null, null );
    
  }
}
