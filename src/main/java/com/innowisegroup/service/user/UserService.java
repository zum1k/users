package main.java.com.innowisegroup.service.user;

import main.java.com.innowisegroup.entity.User;
import main.java.com.innowisegroup.exception.ServiceException;
import main.java.com.innowisegroup.repository.Repository;
import main.java.com.innowisegroup.repository.user.UserRepository;
import main.java.com.innowisegroup.service.Service;
import main.java.com.innowisegroup.utils.mapper.Mapper;
import main.java.com.innowisegroup.utils.mapper.UserMapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserService implements Service<User> {
  private final Repository<User> repository = UserRepository.getInstance();
  private final Mapper<User> mapper = new UserMapper();


  @Override
  public String create(User entity) {
    return repository.create(entity).toString();
  }

  @Override
  public User read(long id) throws ServiceException {
    Optional<User> userOptional = repository.read(id);
    if (userOptional.isEmpty()) {
      throw new ServiceException("User not found");
    }
    return userOptional.get();
  }

  @Override
  public String update(long id, User user) {
    User updatedUser = repository.update(id, user);
    return mapper.toString(updatedUser);
  }

  @Override
  public String remove(long id) {
    Optional<User> optionalUser = repository.remove(id);
    return optionalUser.isEmpty() ? "User not found" : mapper.toString(optionalUser.get());
  }

  @Override
  public List<String> readAll() {
    List<User> users = repository.readAll();
    if (users.isEmpty()) {
      return Collections.emptyList();
    }
    return users.stream().map(User::toString).collect(Collectors.toList());
  }
}
