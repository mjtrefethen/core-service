package com.egoshard.service.core.domain;

import org.junit.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 */
public class ModelUnitTests {

  @Test
  public void testModelBuilderEquality() {
    UUID uuid = UUID.randomUUID();
    Model model = new Model(uuid);
    assertEquals(uuid.toString(), model.getKey().toString());
    assertTrue(model.isActive());
  }

  @Test
  public void testModelBuilderKeyGeneration() {
    UUID uuid = UUID.randomUUID();
    Model model = new Model(uuid);
    assertNotNull(model.getKey());
  }

}