package com.covisint;

import com.covisint.FizzBuzz1;
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
public class FizzBuzz1Test {
  @Test
  void shouldReturnInFizz() {
    assertEquals( "Fizz", FizzBuzz1.evaluate( 3 ));
  }
  
  @Test
  void shouldReturnBuzz() {
    assertEquals( "Buzz", FizzBuzz1.evaluate( 5 ));
  }
  
  @Test
  void shouldReturnFizzBuzz() {
    assertEquals( "FizzBuzz", FizzBuzz1.evaluate( 15 ));
  }
  
  @Test
  void shouldReturnNumber() {
    assertEquals( "4", FizzBuzz1.evaluate( 4 ));
  }
  
  private boolean divisibleBy( int divisor, int value ) {
    return( value % divisor == 0 );
  }
  
  @Test
  void randomSequence() {
    int i = 1000;
    while( i-- > 0 ) {
      int random = ThreadLocalRandom.current().nextInt( 0, Integer.MAX_VALUE );
      if( divisibleBy( 15, random )) {
        assertEquals( "FizzBuzz", FizzBuzz1.evaluate( random ) );
      }
      else if( divisibleBy( 3, random )) {
        assertEquals( "Fizz", FizzBuzz1.evaluate( random ) );
      }
      else if( divisibleBy( 5, random )) {
        assertEquals( "Buzz", FizzBuzz1.evaluate( random ) );
      }
      else {
        assertEquals( Integer.toString( random ), FizzBuzz1.evaluate( random ) );
      }
    }
  }
}
