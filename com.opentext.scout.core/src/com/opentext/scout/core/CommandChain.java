package com.opentext.scout.core;

import java.io.*;
import java.nio.file.Files;
import java.util.*;
import java.util.regex.Pattern;

public class CommandChain implements Cloneable {
  public static int LOCAL_SCOPE  = 0;
  public static int GLOBAL_SCOPE = 1;
  
  private String          idCommandChain = UUID.randomUUID().toString();
  private String          namespace      = null;
  private String          sendingAgent   = null;
  private boolean         isCritical     = false;
  private boolean         terminated     = false;
  private long            timeOut        = 1000 * 30;
  private boolean         debugMode      = false;
  
  private Vector<String>  data           = new Vector<String>();
  private Vector<Command> commands       = new Vector<Command>();
  
  public static CommandChain add( CommandChain theChain, String command, Hashtable<String, String> options, Map<String, String> transpose ) {
    Command newCommand = new Command( command, options );
    
    if( theChain.commands == null ) {
      theChain.commands = new Vector< Command >();
    }
    
    if( transpose != null ) {
      newCommand.doTranspose( transpose );
    }
    
    theChain.commands.add( newCommand );
    
    return theChain;
  }
  
  public static CommandChain add( CommandChain theChain, String command, Hashtable<String, String> options ) {
    return add( theChain, command, options, null );
  }
  
  
  protected class CommandChainIterator implements Iterator<Command> {
    CommandChain lclChain = null;
    Object[]     cmds     = null;
    int          index    = 0;
    
    CommandChainIterator( CommandChain chain ) {
      lclChain = chain;
      cmds     = lclChain.commands.toArray();
    }
   
    public boolean hasNext() {
      boolean retval = false;
      if( cmds != null && !CommandChain.this.isTerminated() ) {
        if( cmds.length > index ) {
          // skip any null commands
          while( cmds[ index ] == null && cmds.length > index ) {
            ++index;
          }
          
          if( cmds.length > index ) {
            retval = true;
          }
        }
      }
      
      return retval;
    }

    public Command next() {
      Command command = null; //emptyCommand;
      if( hasNext() ) {
        command = (Command) cmds[ index++ ];
      }

      return command;
    }
    
    /*
     *  causes the iterator to skip to just past the next instance of the specified class.
     *  returns true if it found an instance of the specified class, false otherwise
     */
    @SuppressWarnings("unchecked")
    public boolean skipThrough( java.lang.Class stopClass ) {
      boolean retval    = false;
      int     tempIndex = index;
      
      while( cmds.length > index  && cmds[ index ].getClass() != stopClass ) {
        lclChain.commands.remove( cmds[ index ] );
      }
      
      if( cmds != null ) {
        if( cmds.length > tempIndex ) {
          // skip any null commands
          while( cmds.length > tempIndex ) {
            Object object = cmds[ tempIndex ];
            cmds[ tempIndex ] = null;
            ++tempIndex;
            if( object.getClass() == stopClass ) {
              retval = true;
              break;
            }
          }
        }
      }
      
      index = tempIndex;
      
      return retval;
    }
    
    public void remove() {
      if( cmds != null && cmds.length > index ) {
        lclChain.commands.remove( cmds[ index ] );
        cmds[ index ] = null;
      }
    }
  }

  
  
  public CommandChain() {
    super();
  }
  
  public CommandChain( Hashtable<String,?> hashtable ) {
   fromHashtable( hashtable );
  }

  public String getIdCommandChain() {
    return idCommandChain;
  }
  
  public void setData( Vector<String> data ) {
    this.data = data;
  }

  /* (non-Javadoc)
   * @see com.hyperbridge.core.CommandChainInterface#getData()
   */
  public Vector<String> getData() {
    if( data == null ) {
      data = new Vector<String>();
    }
    return data;
  }
  
  /**
   * @return Returns the terminated.
   */
  public boolean isTerminated() {
    return terminated;
  }

  
  /**
   * @param terminated The terminated value to set.
   */
  public void setTerminated( boolean terminated ) {
    this.terminated = terminated;
  }


  public void terminate() {
    terminated = true;
  }
  
  
  public Hashtable<String, String> getRequiredFields( Map<String, String> options ) {
    Hashtable<String, String> retval = new Hashtable<String, String>();
    String[]                  values = null;
    Set<String>               keys   = options.keySet();
    Pattern                   p      = Pattern.compile( "%" );
    
    for( String key : keys ) {
      values = p.split( options.get( key ));
      
      for( int i = 0; i < values.length - 1; i = i + 2 ) {
        retval.put( key + "|" + values[ i ].trim(), values[ i + 1 ].trim() );
      }
    }
    
    return retval;
  }
  
  
  
  /* (non-Javadoc)
   * @see com.hyperbridge.core.CommandChainInterface#add(java.lang.String, java.util.Hashtable)
   */
  public void add( String command, Hashtable<String, String> options, Map<String, String> transpose ) {
    add( this, command, options, transpose );
  }

  public void doTranspose( Map<String, ?> transpose ) {
    for( Command command : commands ) {
      command.doTranspose( transpose );
    }
  }

  /* (non-Javadoc)
   * @see com.hyperbridge.core.CommandChainInterface#remove(com.hyperbridge.core.CommandInterface)
   */
  public boolean remove( Command command ) {
    return commands.remove( command );
  }
  
  // meeting Iterable interface requirements......
  /* (non-Javadoc)
   * @see com.hyperbridge.core.CommandChainInterface#iterator()
   */
  public Iterator<Command> iterator() {
    return new CommandChainIterator( this );
  }
  
  
  public Hashtable<String,?> toHashtable() {
    Hashtable<String,Object> table = new Hashtable<String,Object>();
    table.put( "data", data );
    
    int i = 0;
    for( Command command : commands ) {
      Hashtable<String,Object> commandTable = new Hashtable<String,Object>();
      commandTable.put( "command", command.getCommand() );
      commandTable.put( "options", command.getOptions() );
      String key = ( "commandChain." + ++i ).toLowerCase();
      table.put( key, commandTable );
    }
    
    return table;
  }
  
  @SuppressWarnings("unchecked")
  public CommandChain fromHashtable( Hashtable<String,?> table ) {
    data = (Vector<String>) table.get( "data" );
    
    boolean keepLooping = true;
    int     i           = 1;
    
    while( keepLooping ) {
      String key = ( "commandChain." + i ).toLowerCase();
      if( table.containsKey( key )) {
        Hashtable<String,Object> commandTable = (Hashtable<String,Object>) table.get( key );
        String command = (String) commandTable.get( "command" );
        Hashtable<String,String> options = (Hashtable<String,String>) commandTable.get( "options" );
        add( command, options, null );
        ++i;
      }
      else {
        keepLooping = false;
      }
    }
    
    return this;
  }
  

  public boolean skipThrough( String breakCommand ) {
    boolean retval = false;
    
    
    while( commands.size() > 0  && !commands.get( 0 ).getCommand().equalsIgnoreCase( breakCommand )) {
      commands.remove( 0 );
    }
    
    // we hit the stop class, we need to remove it too
    if( commands.size() > 0 && commands.get( 0 ).getCommand().equalsIgnoreCase( breakCommand )) {
      commands.remove(  0 );
      retval = true;
    }
    
    // we hit the end of the chain.  return false.....
    if( commands.size() == 0 ) {
      terminate();
    }
    
    return retval;
  }

  
  public Object deepClone( CommandChain newChain, String breakCommand ) {
    newChain.idCommandChain = this.idCommandChain;
    
    newChain.setData( this.data );
    newChain.setSendingAgent( sendingAgent );
    newChain.setTerminated( terminated );
    
    for( Command command : commands ) {
      if( breakCommand != null && command.getCommand().equalsIgnoreCase( breakCommand )) {
        break;
      }
      
      Hashtable<String,String> newOptions = new Hashtable<String,String>();
      Enumeration<String> iter = command.getOptions().keys();
      while( iter.hasMoreElements() ) {
        String key = iter.nextElement();
        newOptions.put( key, command.getOptions().get( key ));
      }

      newChain.add( command.getCommand(), newOptions, null );
    }
    
    return newChain;
  }

  public Object deepClone( CommandChain newChain ) {
    return deepClone( newChain, null );
  }
  
  public Object deepClone( String breakCommand ) {
    CommandChain newChain = new CommandChain();
    return deepClone( newChain, breakCommand );
  }

  public Object deepClone() {
    CommandChain newChain = new CommandChain();
    return deepClone( newChain, null );
  }
  
  /**
   * @return Returns the sendingAgent.
   */
  public synchronized String getSendingAgent() {
    return sendingAgent;
  }

  
  /**
   * @param sendingAgent The sendingAgent to set.
   */
  public synchronized void setSendingAgent( String sendingAgent ) {
    this.sendingAgent = sendingAgent;
  }

  
  public String toString() {
    return "CommandChain: " + this.getSendingAgent();
  }
  
  /**
   * @return the commandChain
   */
  public Vector<Command> getCommands() {
    return commands;
  }
  
  protected void setCommandChain( Vector<Command> commands ) {
    this.commands = commands;
  }

}
