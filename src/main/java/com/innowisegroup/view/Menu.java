package main.java.com.innowisegroup.view;

import main.java.com.innowisegroup.exception.ValidationException;

import java.util.List;

public interface Menu {
  String create() throws ValidationException;

  String remove();

  String update();

  void runMenu();

  List<String> allUsers();

}
