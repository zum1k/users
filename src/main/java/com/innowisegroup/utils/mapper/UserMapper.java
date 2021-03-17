package main.java.com.innowisegroup.utils.mapper;

import main.java.com.innowisegroup.entity.Role;
import main.java.com.innowisegroup.entity.User;

import java.util.Arrays;

public class UserMapper implements Mapper<User> {
  public static final String SPLITERATOR = "//";
  public static final String ARRAY_REGEXP = "^\\[|\\]$";
  public static final String ARRAY_SPLITERATOR = ", ";
  public static final int ID_INDEX = 0;
  public static final int NAME_INDEX = 1;
  public static final int SURNAME_INDEX = 2;
  public static final int EMAIL_INDEX = 3;
  public static final int ROLES_INDEX = 4;
  public static final int PHONES_INDEX = 5;

  @Override
  public String toString(User user) {
    return user.getId() + SPLITERATOR
        + user.getFirstName() + SPLITERATOR
        + user.getLastName() + SPLITERATOR
        + user.getEmail() + SPLITERATOR
        + Arrays.toString(user.getRoles()) + SPLITERATOR
        + Arrays.toString(user.getPhoneNumbers());
  }

  @Override
  public User toEntity(String string) {
    if (string == null) {
      return null;
    }
    String[] strings = string.split(SPLITERATOR);
    User user = new User();
    user.setId(Long.parseLong(strings[ID_INDEX]));
    user.setFirstName(strings[NAME_INDEX]);
    user.setLastName(strings[SURNAME_INDEX]);
    user.setEmail(strings[EMAIL_INDEX]);
    user.setRoles(parseRoles(strings[ROLES_INDEX]));
    user.setPhoneNumbers(parsePhones(strings[PHONES_INDEX]));

    return user;
  }

  private Role[] parseRoles(String value) {
    String resultString = value.replaceAll(ARRAY_REGEXP, "");
    String[] strings = resultString.split(ARRAY_SPLITERATOR);
    return Arrays.stream(strings).map(Role::valueOf).toArray(Role[]::new);
  }

  private String[] parsePhones(String value) {
    String resultString = value.replaceAll(ARRAY_REGEXP, "");
    String[] strings = resultString.split(ARRAY_SPLITERATOR);
    return strings;
  }
}
