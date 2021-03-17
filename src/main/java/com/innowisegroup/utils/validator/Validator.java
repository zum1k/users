package main.java.com.innowisegroup.utils.validator;

/**
 * This interface {@Validator} used to check incoming string values for compliance with a certain condition
 */

public interface Validator {
  /**
   * converts an entity to a string
   *
   * @param value the string being checked
   * @return true/false
   */
  boolean validate(String value);
}
