package com.covisint.fizzbuzz;

import java.util.concurrent.ThreadLocalRandom;

import com.covisint.FizzBuzz;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

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
public class FizzBuzzTest
{
  private FizzBuzz fizzBuzz = null;
  
  @BeforeEach
  void setUp()
  {
     fizzBuzz = new FizzBuzz();
  }
  
  @Test
  void canInstantiateFizzBuzz()
  {
    assertNotNull( fizzBuzz );
    assert( fizzBuzz instanceof com.covisint.FizzBuzz );
  }
  
  @Test
  void threeDivisibleByThree()
  {
    String value = fizzBuzz.evaluate( 3 );
    assertEquals( value, "Fizz" );
  }
  
  @Test
  void fiveDivisibleByFive()
  {
    String value = fizzBuzz.evaluate( 5 );
    assertEquals( value, "Buzz" );
  }
  
  @Test
  void fifteenDivisibleByFifteen()
  {
    String value = fizzBuzz.evaluate( 15 );
    assertEquals( value, "FizzBuzz" );
  }
  
  private boolean divisibleBy( int value, int divisor ) {
    return value % divisor == 0;
  }
  
  @Test
  void fizzbuzz()
  {
    for( int i = 0; i < 10000; ++i )
    {
      int value = ThreadLocalRandom.current().nextInt( 0, 65535 + 1 );
      String fizzString = fizzBuzz.evaluate( value );
      boolean passed = false;
  
      if( divisibleBy( value, 15 ) && fizzString.equals( "FizzBuzz" ))
      {
        passed = true;
      }
      else if( divisibleBy( value, 3 ) && fizzString.equals( "Fizz" ))
      {
        passed = true;
      }
      else if( divisibleBy( value, 5 ) && fizzString.equals( "Buzz" ))
      {
        passed = true;
      }
      else if( value == Integer.parseInt( fizzString ))
      {
        passed = true;
      }
  
      Assertions.assertTrue( passed, value + " Failed the FizzBuzz test." );
    }
  }
  
  @Test
  void negativeThreeDivisibleByThree() {
    String value = fizzBuzz.evaluate( -3 );
    assertEquals( value, "Fizz" );
  }
  
  @Test
  void negativeFiveDivisibleByFive()
  {
    String value = fizzBuzz.evaluate( -5 );
    assertEquals( value, "Buzz" );
  }
  
  @Test
  void negativeFifteenDivisibleByFifteen()
  {
    String value = fizzBuzz.evaluate( -15 );
    assertEquals( value, "FizzBuzz" );
  }
  
  @Test
  void negativeFizzbuzz()
  {
    for( int i = 0; i < 10000; ++i )
    {
      int value = ThreadLocalRandom.current().nextInt( 0, 65535 + 1 ) * -1;
      String fizzString = fizzBuzz.evaluate( value );
      boolean passed = false;
      
      if( divisibleBy( value, 15 ) && fizzString.equals( "FizzBuzz" ))
      {
        passed = true;
      }
      else if( divisibleBy( value, 3 ) && fizzString.equals( "Fizz" ))
      {
        passed = true;
      }
      else if( divisibleBy( value, 5 ) && fizzString.equals( "Buzz" ))
      {
        passed = true;
      }
      else if( value == Integer.parseInt( fizzString ))
      {
        passed = true;
      }
      
      Assertions.assertTrue( passed, value + " Failed the FizzBuzz test." );
    }
  }
}
