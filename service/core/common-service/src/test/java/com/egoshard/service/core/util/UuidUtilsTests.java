package com.egoshard.service.core.util;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 */
public class UuidUtilsTests {

  private static final String staticNonDashStringUuid = "c8c3c1e5b3a94082b4ecb566fee04407";
  private static UUID staticUuid;
  private static UUID staticDashUuid;

  @BeforeAll
  public static void setUpOnce() {
    staticUuid = UUID.randomUUID();
    staticDashUuid = UuidUtils.fromNonDashString(staticNonDashStringUuid);
  }

  @Test
  public void testUuidValidity() {
    assertTrue(UuidUtils.isStringValidUuid(staticUuid.toString()));
  }

  @Test
  public void testUuidValidityFail() {
    assertFalse(UuidUtils.isStringValidUuid("b"));
  }

  @Test
  public void testByteUsage() {
    byte[] bytes = UuidUtils.toBytes(staticUuid);
    UUID localUuid = UuidUtils.fromBytes(bytes);
    assertEquals(staticUuid, localUuid);
  }

  @Test
  public void testNonDashString() {
    String uuidString = UuidUtils.toNonDashString(staticUuid);
    UUID localUuid = UuidUtils.fromNonDashString(uuidString);
    assertEquals(staticUuid, localUuid);
    assertEquals(staticNonDashStringUuid, UuidUtils.toNonDashString(staticDashUuid));
  }

  @Test
  public void testFullConversion() throws UnsupportedEncodingException {
    String staticUuidString = "4e08cf81-d452-4179-a9a2-06cd17ec7be5";
    UUID existingUuid = UUID.fromString(staticUuidString);
    byte[] uuidBytes = UuidUtils.toBytes(existingUuid);
    UUID newUuid = UuidUtils.fromBytes(uuidBytes);
    assertEquals(existingUuid, newUuid);
    assertEquals(staticUuidString, newUuid.toString());
  }

}