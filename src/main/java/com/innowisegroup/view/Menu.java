package main.java.com.innowisegroup.view;

import main.java.com.innowisegroup.exception.ValidationException;

import java.util.List;

/**
 * This interface {@Menu} is view level between User's operations and Service logic
 */
public interface Menu {
  /**
   * Creates a new entity from console input
   *
   * @return String if entity was created
   */
  String create() throws ValidationException;

  /**
   * Removes an entity from file
   *
   * @return String if entity was removed
   */

  String remove();

  /**
   * Update an entity from file
   *
   * @return String if entity was updated
   */


  String update();

  /**
   * Runs main menu
   */

  void runMenu();

  /**
   * Update an entity from file
   *
   * @return List<String> returns all entries from a file in a string representation
   **/

  List<String> allUsers();

}
