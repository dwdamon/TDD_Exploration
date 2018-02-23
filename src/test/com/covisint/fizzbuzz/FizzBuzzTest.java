package com.covisint.fizzbuzz;

import com.covisint.FizzBuzz;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
  void canIstantiateFizzBuzz()
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
}
