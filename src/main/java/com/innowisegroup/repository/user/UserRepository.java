package main.java.com.innowisegroup.repository.user;

import main.java.com.innowisegroup.entity.User;
import main.java.com.innowisegroup.exception.RepositoryException;
import main.java.com.innowisegroup.repository.Repository;
import main.java.com.innowisegroup.repository.checker.AllRowsChecker;
import main.java.com.innowisegroup.repository.checker.Checker;
import main.java.com.innowisegroup.utils.filemanager.FileManager;
import main.java.com.innowisegroup.utils.filemanager.FileManagerImpl;
import main.java.com.innowisegroup.utils.mapper.Mapper;
import main.java.com.innowisegroup.utils.mapper.UserMapper;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserRepository implements Repository<User> {
  private static final String DEFAULT_PATH = "src/main/resources/Users.txt";
  private static UserRepository INSTANCE;

  private final FileManager fileManager;
  private final Mapper<User> mapper;
  private List<User> users;

  private UserRepository() {
    this.mapper = new UserMapper();
    this.fileManager = new FileManagerImpl();
    try {
      this.users = initializeUsers();
    } catch (RepositoryException e) {
      System.out.println(e.getMessage());
    }
  }

  public static UserRepository getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new UserRepository();
    }
    return INSTANCE;
  }

  @Override
  public User create(User user) {
    long userId = calcId();
    user.setId(userId);
    users.add(user);
    saveChanges();
    return user;
  }

  @Override
  public Optional<User> read(long id) {
    List<User> filteredList =
        users.stream().filter(user -> user.getId() == id).distinct().collect(Collectors.toList());
    if (filteredList.isEmpty()) {
      return Optional.empty();
    }
    return Optional.of(filteredList.get(0));
  }

  @Override
  public User update(long id, User user) {
    Optional<User> currentUser = read(id);
    if (currentUser.isEmpty()) {
      return null;
    }
    int index = users.indexOf(currentUser.get());
    users.set(index, user);
    saveChanges();
    return user;
  }

  @Override
  public Optional<User> remove(long id) {
    Optional<User> optionalUser = read(id);
    if (optionalUser.isEmpty()) {
      return Optional.empty();
    }
    users.remove(optionalUser.get());
    saveChanges();
    return optionalUser;
  }

  @Override
  public List<User> readAll() {
    return users;
  }

  private long calcId() {
    if (users.isEmpty()) {
      return 1;
    }
    int lastUserIndex = users.size() - 1;
    User lastUser = users.get(lastUserIndex);
    return lastUser.getId() + 1;
  }

  private List<User> initializeUsers() throws RepositoryException {
    Checker<User> checker = new AllRowsChecker();
    List<User> users;
    try {
      users = fileManager.readStrings(checker, DEFAULT_PATH).stream().map(mapper::toEntity).collect(Collectors.toList());
    } catch (IOException | RepositoryException e) {
      throw new RepositoryException("No file found or something got wrong", e);
    }
    return users;
  }

  private void saveChanges() {
    List<String> strings = users.stream().map(mapper::toString).collect(Collectors.toList());
    try {
      fileManager.writeStrings(strings, DEFAULT_PATH);
    } catch (IOException | RepositoryException e) {
      e.printStackTrace();
    }
  }

}
