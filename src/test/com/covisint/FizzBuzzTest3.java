package com.covisint;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Players generally sit in a circle. The player designated to go first says the number "1",
 * and each player thenceforth counts one number in turn.
 * However, any number divisible by three is replaced by the word fizz
 * and any divisible by five by the word buzz.
 * Numbers divisible by both become fizz buzz.
 * A player who hesitates or makes a mistake is eliminated from the game.`
 *
 * Write a program to determine the fizz-buzziness for any given whole number.
 */

@TestInstance( TestInstance.Lifecycle.PER_CLASS )
public class FizzBuzzTest3 {

  @Test
  public void ifThreeReturnFizz() {
    assertEquals( "Fizz", FizzBuzz3.evaluate( 3 ));
  }
  
  @Test
  public void ifFiveReturnBuzz() {
    assertEquals( "Buzz", FizzBuzz3.evaluate( 5 ));
  }
  
  @Test
  public void ifFifteenReturnFizzBuzz() {
    assertEquals( "FizzBuzz", FizzBuzz3.evaluate( 15 ));
  }
  
  @Test
  public void ifDivisibleByThreeReturnFizz() {
    for( int i = 3; i < 300; i = i + 3 ) {
      if( i % 15 != 0 ) {
        assertEquals( "Fizz", FizzBuzz3.evaluate( i ), "The value " + i + " did not return 'Fizz'" );
      }
      else {
        assertEquals( "FizzBuzz", FizzBuzz3.evaluate( i ));
      }
    }
  }
  
  @Test
  public void ifNotDivisibleByThreeOrFiveReturnTheNumber() {
    assertEquals( "1", FizzBuzz3.evaluate( 1 ));
  }
  
  @Test
  public void randomTest() {
    for( int i = 0; i < 10000000; ++i ) {
      int value = ThreadLocalRandom.current().nextInt( 0, Integer.MAX_VALUE );
      
      if( value % 15 == 0 ) {
        assertEquals( "FizzBuzz", FizzBuzz3.evaluate( value ));
      }
      else if( value % 3 == 0 ) {
        assertEquals( "Fizz", FizzBuzz3.evaluate( value ));
      }
      else if( value % 5 == 0 ) {
        assertEquals( "Buzz", FizzBuzz3.evaluate( value ));
      }
      else {
        assertEquals( Integer.toString( value ), FizzBuzz3.evaluate( value ));
      }
    }
  }
  
}
