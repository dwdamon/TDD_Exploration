package com.covisint;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance( TestInstance.Lifecycle.PER_CLASS )
public class FizzBuzzTest6 {
  
  private void expectContainsFizz( int value ) {
    assertTrue( FizzBuzz6.evaluate( value ).contains( "Fizz" ), "Failure on value of: " + value );
  }
  
  private void expectContainsBuzz( int value ) {
    assertTrue( FizzBuzz6.evaluate( value ).contains( "Buzz" ), "Failure on value of: " + value );
  }
  
  private void expectFizz( int value ) {
    assertEquals( "Fizz", FizzBuzz6.evaluate( value ), "Failure on value of: " + value );
  }
  
  private void expectBuzz( int value ) {
    assertEquals( "Buzz", FizzBuzz6.evaluate( value ), "Failure on value of: " + value );
  }
  
  private void expectFizzBuzz( int value ) {
    assertEquals( "FizzBuzz", FizzBuzz6.evaluate( value ), "Failure on value of: " + value );
  }
  
  private void expectValue( int value ) {
    assertEquals( Integer.toString( value ), FizzBuzz6.evaluate( value ), "Failure on value of: " + value );
  }
  
  @Test
  public void returnsFizzOnValueThree() {
    expectFizz( 3 );
  }
  
  @Test
  public void returnsBuzzOnValueFive() {
    assertEquals( "Buzz", FizzBuzz6.evaluate( 5 ));
  }
  
  @Test
  public void returnsFizzBuzzOnValueFifteen() {
    expectFizzBuzz( 15 );
  }
  
  @Test
  public void returnsValueAsStringAsDefault() {
    assertEquals( Integer.toString( 2 ), FizzBuzz6.evaluate( 2 ));
  }
  
  @Test
  public void returnsFizzOnNumbersDivisibleByThree() {
    int value = 0;
    
    for( int i = 0; i < 1000000; ++i ) {
      value += 3;
      expectContainsFizz( value );
    }
  }
  
  @Test
  public void returnsFizzBuzzOnNumbersDivisibleByFifteen() {
    int value = 0;
    
    for( int i = 0; i < 1000000; ++i ) {
      value += 15;
      expectFizzBuzz( value );
    }
  }
  
  @Test
  public void returnsBuzzOnNumbersDivisibleByFive() {
    int value = 0;
  
    for( int i = 0; i < 1000000; ++i ) {
      value += 5;
      expectContainsBuzz( value );
    }
  }
  
  @Test
  public void testRandomValues() {
    for( int i = 0; i < 10000000; ++i ) {
      int value = ThreadLocalRandom.current().nextInt( 0, Integer.MAX_VALUE );
      
      if( value % 15 == 0 ) {
        expectFizzBuzz( value );
      }
      else if( value % 5 == 0 ) {
        expectBuzz( value );
      }
      else if( value % 3 == 0 ) {
        expectFizz( value );
      }
      else {
        expectValue( value );
      }
    }
  }
  
}
