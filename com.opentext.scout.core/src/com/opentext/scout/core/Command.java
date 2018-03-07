package com.opentext.scout.core;

import java.util.*;


public class Command implements Cloneable {
    private String                    command      = null;
    private Hashtable<String, String> options      = null;
    
    @SuppressWarnings("unchecked")
    public Object clone() {
      Command newClone = new Command( command, (Hashtable<String, String>) options.clone() );
      return newClone;
    }

    public Command() {
    }
    
    public Command( String command ) {
      this.command = command;
    }

    public Command( String command, Hashtable<String, String> options ) {
      this( command );
      this.options = options;
    }

    public String getCommand() {
      return command;
    }
    
    //public void setCommand( String command ) {
    //  this.command = command;
    //}

    public Hashtable<String, String> getOptions() {
      return options;
    }
 
    public void setOptions( Hashtable<String, String> options ) {
      this.options = options;
    }
   

    public Command doTranspose( Map<String, ?> transpose ) {
      command = Transposition.transpose( command, transpose );
      options.keySet().forEach( key -> options.put( key, Transposition.transpose( options.get( key ), transpose )));
      return this;
    }
}
