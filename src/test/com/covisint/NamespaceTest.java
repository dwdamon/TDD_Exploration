package com.covisint;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


@TestInstance( TestInstance.Lifecycle.PER_CLASS )
public class NamespaceTest {
  private Namespace namespace;
  
  @BeforeEach
  public void createNameInstance() {
    namespace = new Namespace( "test" );
  }
  
  @Test
  public void ensureNamespaceAssigned() {
    assertTrue( namespace != null );
  }
  
  @Test
  public void addNamedObject() {
    assertNull( namespace.put( "com.covisint.test", new Object() ));
  }
}
