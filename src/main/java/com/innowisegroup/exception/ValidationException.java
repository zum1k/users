package main.java.com.innowisegroup.exception;

public class ValidationException extends Exception {
  public ValidationException(String msg, Throwable cause) {
    super(msg, cause);
  }

  public ValidationException(String msg) {
    super(msg);
  }
}
