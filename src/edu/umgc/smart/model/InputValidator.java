package edu.umgc.smart.model;

public final class InputValidator {

  private InputValidator() {
    // Hide the default constructor so no instances are made
    // This prevents using unneeded memory and undesired behaviour
  }

  public static boolean isValidReferenceNumber(String input) {
    // First char must be one of RLMPT followed by 2 digits, followed by 1-5 digits
    // Ex. R-01-123 or M-22-54321
    return input.matches("^[RLMPT]-\\d{2}-\\d{1,5}$");
  }

  public static boolean isValidRecordType(String input) {
    try {
      RecordType.valueOf(input.toUpperCase());
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public static boolean isValidName(String input) {
    // Regex: Input must be only alphabet letters
    return null != input && input.matches("^[a-zA-Z]+$");
  }

  public static boolean isValidDate(String input) {
    // YYYY-MM-DD, where MM and DD can be single digits
    return input.matches("\\d{4}-([1-9]|0[1-9]|1[0-2])-([1-9]|0[1-9]|[12][0-9]|3[01])");
  }

  public static boolean isValidSummaryLength(String input) {
    // Summary cannot be longer than 500 chararacters
    return input.length() < 501;
  }

  

}
