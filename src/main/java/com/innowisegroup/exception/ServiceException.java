package main.java.com.innowisegroup.exception;

public class ServiceException extends Exception {
  public ServiceException(String msg, Throwable cause) {
    super(msg, cause);
  }

  public ServiceException(String msg) {
    super(msg);
  }
}
