package main.java.com.innowisegroup.utils.filemanager;

import main.java.com.innowisegroup.exception.RepositoryException;
import main.java.com.innowisegroup.repository.checker.Checker;

import java.io.IOException;
import java.util.List;

public interface FileManager {
  List<String> readStrings(Checker checker, String fileName) throws IOException, RepositoryException;

  void writeString(String string, String fileName) throws IOException, RepositoryException;

  void writeStrings(List<String> strings, String fileName) throws IOException, RepositoryException;
}
