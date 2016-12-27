package com.egoshard.service.core.util;

import java.nio.ByteBuffer;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * Utilities for converting UUIDs into different formats.
 */
public class UuidUtils {

  private static final String UUID_VALID_PATTERN =
      "\\{?\\p{XDigit}{8}-\\p{XDigit}{4}-\\p{XDigit}{4}-\\p{XDigit}{4}-\\p{XDigit}{12}\\}?";
  private static final Pattern validPattern = Pattern.compile(UUID_VALID_PATTERN, Pattern.CASE_INSENSITIVE);
  private static final char DELIMITER = '-';

  private UuidUtils() {
  }

  /**
   * Generates a UUID from an array of bytes.
   *
   * @param value array of bytes to convert.
   * @return UUID value.
   */
  public static UUID fromBytes(byte[] value) {
    if (value == null || value.length != 16) {
      throw new IllegalArgumentException();
    }
    int counter = 0;
    long mostSignificant = 0;
    for (; counter < 8; counter++) {
      mostSignificant = (mostSignificant << 8) | (value[counter] & 0xFF);
    }
    long leastSignificant = 0;
    for (; counter < 16; counter++) {
      leastSignificant = (leastSignificant << 8) | (value[counter] & 0xFF);
    }
    return new UUID(mostSignificant, leastSignificant);
  }

  /**
   * Converts a UUID object into a byte array.
   *
   * @param uuid UUID object to convert.
   * @return byte array containing dashes.
   */
  public static byte[] toBytes(UUID uuid) {
    ByteBuffer buffer = ByteBuffer.wrap(new byte[16]);
    buffer.putLong(uuid.getMostSignificantBits());
    buffer.putLong(uuid.getLeastSignificantBits());
    return buffer.array();
  }

  /**
   * Generates a UUID from a String without dashes.
   *
   * @param value String value without dashes.
   * @return UUID value.
   */
  public static UUID fromNonDashString(String value) {
    if (value == null || value.length() != 32) {
      throw new IllegalArgumentException();
    }
    int counter = 0;
    char[] uuidChars = new char[36];
    char[] valueChars = value.toCharArray();
    for (int i = 0; i < valueChars.length; i++) {
      if (i == 8 || i == 12 || i == 16 || i == 20) {
        uuidChars[counter] = DELIMITER;
        counter++;
      }
      uuidChars[counter] = valueChars[i];
      counter++;
    }
    return UUID.fromString(new String(uuidChars));
  }

  /**
   * Generates a String without dashes from a UUID.
   *
   * @param uuid UUID value to convert.
   * @return String representation without dashes.
   */
  public static String toNonDashString(UUID uuid) {
    if (uuid == null) {
      throw new IllegalArgumentException();
    }
    long mostSignificant = uuid.getMostSignificantBits();
    long leastSignificant = uuid.getLeastSignificantBits();
    return getDigits(mostSignificant >> 32, 8)
        + getDigits(mostSignificant >> 16, 4)
        + getDigits(mostSignificant, 4)
        + getDigits(leastSignificant >> 48, 4)
        + getDigits(leastSignificant, 12);
  }

  /**
   * Validates the format of a String representing a UUID.
   *
   * @param value String representation of a UUID.
   * @return true if the String could be converted to a UUID, false if not.
   */
  public static boolean isStringValidUuid(String value) {
    return validPattern.matcher(value).matches();
  }

  /**
   * Internal method to retrieve hex digit values from a value.
   *
   * @param value  value to perform bitwise operations on.
   * @param digits digit position.
   * @return String representation of the hex value.
   */
  private static String getDigits(long value, int digits) {
    long hi = 1L << (digits * 4);
    return Long.toHexString(hi | (value & (hi - 1))).substring(1);
  }

}