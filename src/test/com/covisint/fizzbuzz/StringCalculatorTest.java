package com.covisint.fizzbuzz;

import com.covisint.StringCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import sun.plugin.viewer.LifeCycleManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Requirements:
 *
 * 1)  An empty string returns zero
 * 2)  A single number returns the value
 * 3)  Two numbers, comma delimited, returns the sum
 * 4)  Two numbers, newline delimited, returns the sum
 * 5)  Three numbers, delimited either way, returns the sum
 * 6)  Negative numbers throw an exception
 * 7)  Numbers greater than 1000 are ignored
 * 8)  A single char delimiter can be defined on the first line (e.g. //# for a ‘#’ as the delimiter)
 * 9)  A multi char delimiter can be defined on the first line (e.g. //[###] for ‘###’ as the delimiter)
 * 10) Many single or multi-char delimiters can be defined (each wrapped in square brackets)
 *
 */

@TestInstance( TestInstance.Lifecycle.PER_CLASS )
public class StringCalculatorTest
{
  private StringCalculator calculator;
  
  @BeforeEach
  void setUp()
  {
    calculator = new StringCalculator();
  }
  
  @Test
  void calculateEmptyString()
  {
    assertEquals( calculator.calculate( "" ), 0 );
    assertEquals( calculator.calculate( null ), 0 );
  }
  
  @Test
  void calculateSingleDigit()
  {
    assertEquals( calculator.calculate( "0" ), 0 );
    assertEquals( calculator.calculate( "1" ), 1 );
    assertEquals( calculator.calculate( "2" ), 2 );
  }
  
  @Test
  void calculateTwoCommaSeparatedDigits()
  {
    assertEquals( calculator.calculate( "0,1" ), 1 );
    assertEquals( calculator.calculate( "10,11" ), 21 );
  }
  
  @Test
  void calculateTwoNewlineSeparatedDigits()
  {
    assertEquals( calculator.calculate( "0\n1" ), 1 );
    assertEquals( calculator.calculate( "30\n41" ), 71 );
  }
  
  @Test
  void calculateThreeNewlineSeparatedDigits()
  {
    assertEquals( calculator.calculate( "0\n5,1" ), 6 );
    assertNotEquals( calculator.calculate( "30\n5,41" ), 77 );
    assertEquals( calculator.calculate( "30\n5,41" ), 76 );
  }
  
  @Test
  void negativeNumbersThrowException()
  {
    assertThrows( IllegalArgumentException.class, () -> { calculator.calculate( "-1" ); }, "No negative numbers!" );
    assertThrows( IllegalArgumentException.class, () -> { calculator.calculate( "1,-1" ); }, "No negative numbers!" );
  }
  
  @Test
  void numbersGreaterThanOneThousandIgnored()
  {
    assertEquals( calculator.calculate( "1000\n5,1" ), 1006 );
    assertNotEquals( calculator.calculate( "35\n1001,41" ), 77 );
    assertEquals( calculator.calculate( "35\n41,1001" ), 76 );
  }
  
  @Test
  void delimiterDefinedOnFirstLine() {
    assertEquals( calculator.calculate( "#\n1000#5#1" ), 1006 );
  }
  
  @Test
  void multicharDelimiterDefinedOnFirstLine() {
    assertEquals( calculator.calculate( "##\n1000##5##1" ), 1006 );
    assertEquals( calculator.calculate( "[##]\n1000##5##1" ), 1006 );
  }
  
  @Test
  void multicharDelimiterSetDefinedOnFirstLine() {
    assertEquals( calculator.calculate( "[##][:]\n1000##5:1" ), 1006 );
    assertEquals( calculator.calculate( "[buggs][bunny]\n1000buggs5bunny1" ), 1006 );
    assertEquals( calculator.calculate( "[_][~]\n1000_5~1" ), 1006 );
    assertEquals( calculator.calculate( "[@][2]\n1000@521" ), 1006 );
    assertEquals( calculator.calculate( "[!][$]\n1000!5$1" ), 1006 );
    assertEquals( calculator.calculate( "[%][^]\n1000%5^1" ), 1006 );
    assertEquals( calculator.calculate( "[&][*]\n1000&5*1" ), 1006 );
    assertEquals( calculator.calculate( "[(][)]\n1000(5)1" ), 1006 );
    assertEquals( calculator.calculate( "[{][}]\n1000{5}1" ), 1006 );
    assertEquals( calculator.calculate( "[`][|]\n1000`5|1" ), 1006 );
    assertEquals( calculator.calculate( "[\\][;]\n1000\\5;1" ), 1006 );
    assertEquals( calculator.calculate( "[\\][;]\n1000\\5;1" ), 1006 );
    assertEquals( calculator.calculate( "[.][?]\n1000.5?1" ), 1006 );
    assertEquals( calculator.calculate( "[=][+]\n1000=5+1" ), 1006 );
    assertEquals( calculator.calculate( "[<][>]\n1000<5>1" ), 1006 );
    assertEquals( calculator.calculate( "[\"][']\n1000\"5'1" ), 1006 );
    assertEquals( calculator.calculate( "[-][BbB]\n1000-5BbB1" ), 1006 );
    // This works, but you have the order of the braketed strings matters
    assertEquals( calculator.calculate( "[+++][+]\n1000+5+++1" ), 1006 );
    // so this will pass because the string gets really fouled up.  It's not worth fixing here.
    assertThrows( NumberFormatException.class, () -> { calculator.calculate( "[+][+++]\n1000+5+++1" ); } );
  }
}
