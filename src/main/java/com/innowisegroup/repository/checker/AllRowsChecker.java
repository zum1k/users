package main.java.com.innowisegroup.repository.checker;

import main.java.com.innowisegroup.entity.User;

public class AllRowsChecker implements Checker<User> {
  @Override
  public boolean check(String string) {
    return true;
  }
}
