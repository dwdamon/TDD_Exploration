package com.covisint;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance( TestInstance.Lifecycle.PER_CLASS )
public class FizzBuzzTest5 {
  
  @Test
  public void returnsFizz() {
    assertEquals( "Fizz", FizzBuzz5.evaluate( 3 ));
  }
  
  @Test
  public void returnsBuzz() {
    assertEquals( "Buzz", FizzBuzz5.evaluate( 5 ));
  }
  
  @Test
  public void returnsFizzBuzz() {
    assertEquals( "FizzBuzz", FizzBuzz5.evaluate( 15 ));
  }
  
  @Test
  public void returnsFizzOnDivisibleByThree() {
    for( int i = 0; i < 100000; ++i ) {
      int value = ThreadLocalRandom.current().nextInt( 0, Integer.MAX_VALUE/3 ) * 3;
      
      if( value % 5 != 0 ) {
        assertEquals( "Fizz", FizzBuzz5.evaluate( value ), "Value " + value + " failed divisible by three == Fizz" );
      }
    }
  }
  
  @Test
  public void returnsBuzzOnDivisibleByFive() {
    for( int i = 0; i < 100000; ++i ) {
      int value = ThreadLocalRandom.current().nextInt( 0, Integer.MAX_VALUE/5 ) * 5;
      
      if( value % 15 != 0 ) {
        assertEquals( "Buzz", FizzBuzz5.evaluate( value ), "Value " + value + " failed divisible by five (only) == Buzz" );
      }
    }
  }
  
  @Test
  public void returnsFizzBuzzOnDivisibleByFifteen() {
    for( int i = 0; i < 100000; ++i ) {
      int value = ThreadLocalRandom.current().nextInt( 0, Integer.MAX_VALUE/15 ) * 15;
      
      assertEquals( "FizzBuzz", FizzBuzz5.evaluate( value ), "Value " + value + " failed divisible by fifteen == FizzBuzz" );
    }
  }
  
  @Test
  public void returnsValueOnNotDivisibleByThreeFiveOrFifteen() {
    for( int i = 0; i < 100000; ++i ) {
      int value = ThreadLocalRandom.current().nextInt( 0, Integer.MAX_VALUE );
      
      if( value % 3 != 0 && value % 5 != 0 ) {
        assertEquals( Integer.toString( value ), FizzBuzz5.evaluate( value ), "Value " + value + " failed on non-Fizzbuzzable" );
      }
    }
  }
  
  @Test
  public void returnsCorrectValueInAllCases() {
    for( int i = 0; i < 10000000; ++i ) {
      int value = ThreadLocalRandom.current().nextInt( 0, Integer.MAX_VALUE );
  
      if( value % 15 == 0 ) {
        assertEquals( "FizzBuzz", FizzBuzz5.evaluate( value ), "Value " + value + " failed divisible by fifteen == FizzBuzz" );
      }
      else if( value % 3 == 0 ) {
        assertEquals( "Fizz", FizzBuzz5.evaluate( value ), "Value " + value + " failed divisible by fifteen == FizzBuzz" );
      }
      else if( value % 5 == 0 ) {
        assertEquals( "Buzz", FizzBuzz5.evaluate( value ), "Value " + value + " failed divisible by fifteen == FizzBuzz" );
    }
    else {
        assertEquals( Integer.toString( value ), FizzBuzz5.evaluate( value ), "Value " + value + " failed on non-Fizzbuzzable" );
      }
    }
  }
}
