package com.covisint;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance( TestInstance.Lifecycle.PER_CLASS )
public class FizzBuzz2Test {
  @Test
  public void shoudlReturnFizz() {
    assertEquals( "Fizz", FizzBuzz2.evaluate( 3 ));
    assertEquals( "Fizz", FizzBuzz2.evaluate( 6 ));
    assertEquals( "Fizz", FizzBuzz2.evaluate( 9 ));
    assertEquals( "Fizz", FizzBuzz2.evaluate( 12 ));
    assertEquals( "Fizz", FizzBuzz2.evaluate( 24 ));
  }
  
  @Test
  public void shouldReturnBuzz() {
    assertEquals( "Buzz", FizzBuzz2.evaluate( 5 ));
    assertEquals( "Buzz", FizzBuzz2.evaluate( 10 ));
    assertEquals( "Buzz", FizzBuzz2.evaluate( 20 ));
    assertEquals( "Buzz", FizzBuzz2.evaluate( 25 ));
    assertEquals( "Buzz", FizzBuzz2.evaluate( 35 ));
  }
  
  @Test
  public void shouldReturnFizzBuzz() {
    assertEquals( "FizzBuzz", FizzBuzz2.evaluate( 15 ));
    assertEquals( "FizzBuzz", FizzBuzz2.evaluate( 30 ));
    assertEquals( "FizzBuzz", FizzBuzz2.evaluate( 45 ));
    assertEquals( "FizzBuzz", FizzBuzz2.evaluate( 60 ));
  }
  
  @Test void shouldReturnNumber() {
    assertEquals( "2", FizzBuzz2.evaluate( 2 ));
    assertEquals( "4", FizzBuzz2.evaluate( 4 ));
    assertEquals( "7", FizzBuzz2.evaluate( 7 ));
    assertEquals( "23", FizzBuzz2.evaluate( 23 ));
  }
  
}
