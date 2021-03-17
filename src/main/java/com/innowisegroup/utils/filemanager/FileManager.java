package main.java.com.innowisegroup.utils.filemanager;

import main.java.com.innowisegroup.exception.RepositoryException;
import main.java.com.innowisegroup.exception.ServiceException;
import main.java.com.innowisegroup.repository.checker.Checker;

import java.io.IOException;
import java.util.List;

/**
 * This interface {@FileManager} is a layer to read and store rows from the file
 */
public interface FileManager {
  /**
   * @param checker it is a check for compliance with a certain condition
   * @param fileName it is a path to file
   * @return List<String> depending on checker
   * @throws IOException if some unforeseen circumstances arose in the code
   * @throws RepositoryException if some unforeseen circumstances arose in the code
   */
  List<String> readStrings(Checker checker, String fileName) throws IOException, RepositoryException;
  /**
   * @param strings this is a list of lines to be saved in a file.
   * @param fileName it is a path to file
   * @throws IOException if some unforeseen circumstances arose in the code
   * @throws RepositoryException if some unforeseen circumstances arose in the code
   */

  void writeStrings(List<String> strings, String fileName) throws IOException, RepositoryException;
}
