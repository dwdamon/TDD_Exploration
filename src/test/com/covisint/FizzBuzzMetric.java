package com.covisint;

import java.util.Calendar;
import java.util.concurrent.ThreadLocalRandom;

public class FizzBuzzMetric {
  
  public static void main( String[] args ) {
    
    long start1 = Calendar.getInstance().getTimeInMillis();
    for( int i = 0; i < 10000000; ++i ) {
      int value = ThreadLocalRandom.current().nextInt( 0, Integer.MAX_VALUE );
      FizzBuzz4.evaluate( value );
    }
    long end1 = Calendar.getInstance().getTimeInMillis();
    
    long start2 = Calendar.getInstance().getTimeInMillis();
    for( int i = 0; i < 10000000; ++i ) {
      int value = ThreadLocalRandom.current().nextInt( 0, Integer.MAX_VALUE );
      FizzBuzz4_1.evaluate( value );
    }
    long end2 = Calendar.getInstance().getTimeInMillis();
    
    long test1 = end1 - start1;
    long test2 = end2 - start2;
    System.out.println( "One: " + test1 + " Two: " + test2  + "  one - two: " + (test1 - test2 ));
    
    // pretty consistent.  10 million iterations of FizzBuzzTest4 was about 148ms to 180ms faster than FizzBuzzTest2
    // One: 482 Two: 630  two - one: 148
    // However......
    // When I ran FizzBuzz4 against itself this way the second run was also iabout 5 - 50ms differing between the two.
    // FizzBuzz4 was also faster than FizzBuzz4_1 (~20ms)
    // But that may be because the first test was at a disadvantage due to JIT compilation.
  }
}
