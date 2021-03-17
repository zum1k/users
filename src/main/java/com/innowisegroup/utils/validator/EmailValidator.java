package main.java.com.innowisegroup.utils.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator implements Validator {
  private static final String EMAIL_PATTERN = "^([\\w\\-\\.]+)@([\\w\\-\\.]+)\\.(\\p{Alpha}{2,5})$";

  @Override
  public boolean validate(String value) {
    Pattern valuePattern = Pattern.compile(EMAIL_PATTERN);
    Matcher valueMatcher = valuePattern.matcher(value);
    return valueMatcher.matches();
  }
}
