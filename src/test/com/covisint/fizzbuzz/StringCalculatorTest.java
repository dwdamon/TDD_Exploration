package com.covisint.fizzbuzz;

import com.covisint.StringCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import sun.plugin.viewer.LifeCycleManager;

import static org.junit.jupiter.api.Assertions.assertEquals;


@TestInstance( TestInstance.Lifecycle.PER_CLASS )
public class StringCalculatorTest {
  private StringCalculator calculator;
  
  @BeforeEach
  void setUp() {
    calculator = new StringCalculator();
  }
  
  @Test
  void calculateEmptyString() {
    assertEquals( calculator.calculate( "" ), 0 );
  }
  
}
