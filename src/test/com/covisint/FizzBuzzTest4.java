package com.covisint;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


@TestInstance( TestInstance.Lifecycle.PER_CLASS )
public class FizzBuzzTest4 {
  @Test
  public void ifThreeThenFizz() {
    assertEquals( "Fizz", FizzBuzz4.evaluate( 3 ));
  }
  
  @Test
  public void ifFiveThenBuzz() {
    assertEquals( "Buzz", FizzBuzz4.evaluate( 5 ));
  }
  
  @Test
  public void ifFifteenThenFizzBuzz() {
    assertEquals( "FizzBuzz", FizzBuzz4.evaluate( 15 ));
  }
  
  @Test
  public void ifDivisibleBy15ThenFizzBuzz() {
    for( int i = 0; i < 200; ++i ) {
      int value = ( i * 15 ) + 15;
      assertEquals( "FizzBuzz", FizzBuzz4.evaluate( value ), "Failed on value " + value );
    }
  }
  
  @Test
  public void ifDivisibleBy3ThenFizz() {
    for( int i = 0; i < 200; ++i ) {
      int value = ( i * 3 ) + 3;
      
      if( value % 5 == 0 ) {
        assertNotEquals( "Fizz", FizzBuzz4.evaluate( value ), "Failed on value " + value );
      }
      else {
        assertEquals( "Fizz", FizzBuzz4.evaluate( value ), "Failed on value " + value );
      }
    }
  }
  
  @Test
  public void ifDivisibleBy5ThenBuzz() {
    for( int i = 0; i < 200; ++i ) {
      int value = ( i * 5 ) + 5;
      
      if( value % 15 == 0 ) {
        assertNotEquals( "Buzz", FizzBuzz4.evaluate( value ), "Failed on value " + value );
      }
      else {
        assertEquals( "Buzz", FizzBuzz4.evaluate( value ), "Failed on value " + value );
      }
    }
  }
  
  @Test
  public void ifNotFizzBuzzableThenNumber() {
    assertEquals( "1", FizzBuzz4.evaluate( 1 ));
  }
  
  @Test
  public void randomTest() {
    for( int i = 0; i < 10000000; ++i ) {
      int value = ThreadLocalRandom.current().nextInt( 0, Integer.MAX_VALUE );
      
      if( value % 15 == 0 ) {
        assertEquals( "FizzBuzz", FizzBuzz4.evaluate( value ));
      }
      else if( value % 5 == 0 ) {
        assertEquals( "Buzz", FizzBuzz4.evaluate( value ));
      }
      else if( value % 3 == 0 ) {
        assertEquals( "Fizz", FizzBuzz4.evaluate( value ));
      }
      else {
        assertEquals( Integer.toString( value ), FizzBuzz4.evaluate( value ));
      }
    }
  }
}
