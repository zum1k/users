package main.java.com.innowisegroup.utils.filemanager;

import main.java.com.innowisegroup.exception.RepositoryException;
import main.java.com.innowisegroup.repository.checker.Checker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManagerImpl implements FileManager {
  public FileManagerImpl() {
  }

  @Override
  public List<String> readStrings(Checker checker, String filename) throws RepositoryException {
    List<String> list = new ArrayList<>();
    try {
      Scanner in = new Scanner(new File(filename));
      while (in.hasNextLine()) {
        String line = in.nextLine();
        if (checker.check(line)) {
          list.add(line);
        }
      }
    } catch (IOException ex) {
      throw new RepositoryException("Reading error: ", ex);
    }
    return list;
  }

  @Override
  public void writeString(String string, String filename) throws RepositoryException {
    try (FileWriter fileWriter = new FileWriter(filename, true)) {
      fileWriter.write(string + "\n");
      fileWriter.flush();
    } catch (IOException ex) {
      throw new RepositoryException("Recording error: ", ex);
    }
  }

  @Override
  public void writeStrings(List<String> strings, String fileName) throws RepositoryException {
    try (FileWriter fileWriter = new FileWriter(fileName, false)) {
      for (String string : strings) {
        fileWriter.write(string + "\n");
        fileWriter.flush();
      }
    } catch (IOException ex) {
      throw new RepositoryException("Recording error: ", ex);
    }
  }

}
