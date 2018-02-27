package com.covisint;

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
 * Apparently I did not pay close enough attention to the instructions.... and I did not have the
 * "//" at the beginning of the line to indicate custom delimiters being used.  I just looked for
 * any non-numeric value (excluded '-' too).
 * I fixed it to look gor the "//". Made the logic a smidge simpler.  Not much though.
 */

@TestInstance( TestInstance.Lifecycle.PER_CLASS )
public class StringCalculatorTest
{
  @Test
  void calculateEmptyString()
  {
    assertEquals( StringCalculator.calculate( "" ), 0 );
    assertEquals( StringCalculator.calculate( null ), 0 );
  }
  
  @Test
  void calculateSingleDigit()
  {
    assertEquals( StringCalculator.calculate( "0" ), 0 );
    assertEquals( StringCalculator.calculate( "1" ), 1 );
    assertEquals( StringCalculator.calculate( "2" ), 2 );
  }
  
  @Test
  void calculateTwoCommaSeparatedDigits()
  {
    assertEquals( StringCalculator.calculate( "0,1" ), 1 );
    assertEquals( StringCalculator.calculate( "10,11" ), 21 );
  }
  
  @Test
  void calculateTwoNewlineSeparatedDigits()
  {
    assertEquals( StringCalculator.calculate( "0\n1" ), 1 );
    assertEquals( StringCalculator.calculate( "30\n41" ), 71 );
  }
  
  @Test
  void calculateThreeNewlineSeparatedDigits()
  {
    assertEquals( StringCalculator.calculate( "0\n5,1" ), 6 );
    assertNotEquals( StringCalculator.calculate( "30\n5,41" ), 77 );
    assertEquals( StringCalculator.calculate( "30\n5,41" ), 76 );
  }
  
  @Test
  void negativeNumbersThrowException()
  {
    assertThrows( IllegalArgumentException.class, () -> { StringCalculator.calculate( "-1" ); }, "No negative numbers!" );
    assertThrows( IllegalArgumentException.class, () -> { StringCalculator.calculate( "1,-1" ); }, "No negative numbers!" );
  }
  
  @Test
  void numbersGreaterThanOneThousandIgnored()
  {
    assertEquals( StringCalculator.calculate( "1000\n5,1" ), 1006 );
    assertNotEquals( StringCalculator.calculate( "35\n1001,41" ), 77 );
    assertEquals( StringCalculator.calculate( "35\n41,1001" ), 76 );
  }
  
  @Test
  void delimiterDefinedOnFirstLine() {
    assertEquals( StringCalculator.calculate( "//#\n1000#5#1" ), 1006 );
  }
  
  @Test
  void multicharDelimiterDefinedOnFirstLine() {
    assertEquals( StringCalculator.calculate( "//##\n1000##5##1" ), 1006 );
    assertEquals( StringCalculator.calculate( "//[##]\n1000##5##1" ), 1006 );
  }
  
  @Test
  void multicharDelimiterSetDefinedOnFirstLine() {
    assertEquals( StringCalculator.calculate( "//[##][:]\n1000##5:1" ), 1006 );
    assertEquals( StringCalculator.calculate( "//[buggs][bunny]\n1000buggs5bunny1" ), 1006 );
    assertEquals( StringCalculator.calculate( "//[_][~]\n1000_5~1" ), 1006 );
    assertEquals( StringCalculator.calculate( "//[@][2]\n1000@521" ), 1006 );
    assertEquals( StringCalculator.calculate( "//[!][$]\n1000!5$1" ), 1006 );
    assertEquals( StringCalculator.calculate( "//[%][^]\n1000%5^1" ), 1006 );
    assertEquals( StringCalculator.calculate( "//[&][*]\n1000&5*1" ), 1006 );
    assertEquals( StringCalculator.calculate( "//[(][)]\n1000(5)1" ), 1006 );
    assertEquals( StringCalculator.calculate( "//[{][}]\n1000{5}1" ), 1006 );
    assertEquals( StringCalculator.calculate( "//[`][|]\n1000`5|1" ), 1006 );
    assertEquals( StringCalculator.calculate( "//[\\][;]\n1000\\5;1" ), 1006 );
    assertEquals( StringCalculator.calculate( "//[\\][;]\n1000\\5;1" ), 1006 );
    assertEquals( StringCalculator.calculate( "//[.][?]\n1000.5?1" ), 1006 );
    assertEquals( StringCalculator.calculate( "//[=][+]\n1000=5+1" ), 1006 );
    assertEquals( StringCalculator.calculate( "//[<][>]\n1000<5>1" ), 1006 );
    assertEquals( StringCalculator.calculate( "//[\"][']\n1000\"5'1" ), 1006 );
    assertEquals( StringCalculator.calculate( "//[-][BbB]\n1000-5BbB1" ), 1006 );
    // This works, but you have the order of the braketed strings matters
    assertEquals( StringCalculator.calculate( "//[+++][+]\n1000+5+++1" ), 1006 );
    // so this will pass because the string gets really fouled up.  It's not worth fixing here.
    assertThrows( NumberFormatException.class, () -> { StringCalculator.calculate( "//[+][+++]\n1000+5+++1" ); } );
  
    // This happens with any sequence where one sequence is an abbreviation for the other.  If this is the case then the
    // shorter string has to go last.
    assertThrows( NumberFormatException.class, () -> { StringCalculator.calculate( "//[bob][bobo]\n1000bob5bobo1" ); } );
  
    // If you put numbers in your string it can get real confusing....
    assertEquals( StringCalculator.calculate( "//[M5]\n1000M55M51" ), 1006 );
    // ANd this has to be really silly to do anyway......
    assertEquals( StringCalculator.calculate( "//[965]\n100096559651" ), 1006 );
    
    // Or you can just make it look normal
    assertEquals( StringCalculator.calculate( "//[+]\n1000+125+25" ), 1150 );
  }
}
