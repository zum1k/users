package main.java.com.innowisegroup.utils.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneValidator implements Validator {
  private static final String PHONE_PATTERN = "^(375)(29|25|44|33|17)(\\s)(\\d{7})$";
  @Override
  public boolean validate(String value) {
    Pattern valuePattern = Pattern.compile(PHONE_PATTERN);
    Matcher valueMatcher = valuePattern.matcher(value);
    return valueMatcher.matches();
  }
}
