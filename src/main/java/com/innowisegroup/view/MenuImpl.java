package main.java.com.innowisegroup.view;

import main.java.com.innowisegroup.entity.Role;
import main.java.com.innowisegroup.entity.User;
import main.java.com.innowisegroup.exception.ServiceException;
import main.java.com.innowisegroup.exception.ValidationException;
import main.java.com.innowisegroup.service.Service;
import main.java.com.innowisegroup.service.user.UserService;
import main.java.com.innowisegroup.utils.consoleinput.ConsoleInput;
import main.java.com.innowisegroup.utils.consoleinput.ConsoleInputImpl;
import main.java.com.innowisegroup.utils.validator.EmailValidator;
import main.java.com.innowisegroup.utils.validator.PhoneValidator;
import main.java.com.innowisegroup.utils.validator.Validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MenuImpl implements Menu {
  private final ConsoleInput consoleInput = new ConsoleInputImpl();
  private final Service<User> service = new UserService();
  private final Validator emailValidator = new EmailValidator();
  private final Validator phoneValidator = new PhoneValidator();

  @Override
  public String create() throws ValidationException {
    String firstName = parseFirstName();
    String lastName = parseLastName();
    String email = parseEmail();
    Role[] roles = roleParser();
    String[] phones = parsePhones();

    User user = new User();

    user.setFirstName(firstName);
    user.setLastName(lastName);
    user.setEmail(email);
    user.setRoles(roles);
    user.setPhoneNumbers(phones);

    return service.create(user);
  }

  @Override
  public String remove() {
    System.out.println(CommandMessages.ID_USER_MESSAGE);
    int userId = consoleInput.readInt();
    return service.remove(userId);
  }

  @Override
  public String update() {
    System.out.println(CommandMessages.ID_USER_MESSAGE);
    int userId = consoleInput.readInt();
    User currentUser;
    try {
      currentUser = service.read(userId);
      if (isNeedUpdate(CommandMessages.CURRENT_NAME + currentUser.getFirstName())) {
        currentUser.setFirstName(parseFirstName());
      }
      if (isNeedUpdate(CommandMessages.CURRENT_SURNAME + currentUser.getLastName())) {
        currentUser.setLastName(parseLastName());
      }
      if (isNeedUpdate(CommandMessages.CURRENT_EMAIL + currentUser.getEmail())) {
        currentUser.setEmail(parseEmail());
      }
      if (isNeedUpdate((CommandMessages.CURRENT_ROLES + Arrays.toString(currentUser.getRoles())))) {
        currentUser.setRoles(roleParser());
      }
      if (isNeedUpdate(CommandMessages.CURRENT_PHONES + Arrays.toString(currentUser.getPhoneNumbers()))) {
        currentUser.setPhoneNumbers(parsePhones());
      }
      return service.update(userId, currentUser);

    } catch (ServiceException | ValidationException e) {
      System.out.println(e.getMessage());

    }
    return "Something goes wrong";
  }

  @Override
  public void runMenu() {
    System.out.println(CommandMessages.COMMAND_1_MSG);
    int var = 0;
    do {
      var = consoleInput.readInt();
      switch (var) {
        case 1:
          try {
            String currentUser = create();
            showResult(currentUser);
            System.out.println(CommandMessages.COMMAND_1_MSG);
          } catch (ValidationException e) {
            System.out.println(e.getMessage());
            System.out.println(CommandMessages.COMMAND_1_MSG);
            break;
          }
          break;
        case 2:
          String result = update();
          showResult(result);
          System.out.println(CommandMessages.COMMAND_1_MSG);
          break;
        case 3:
          showResult(remove());
          System.out.println(CommandMessages.COMMAND_1_MSG);
          break;
        case 4:
          List<String> strings = allUsers();
          showResult(strings);
          System.out.println(CommandMessages.COMMAND_1_MSG);
          break;
      }
    } while (var != 0);
    System.out.println("Good bye!");
  }

  @Override
  public List<String> allUsers() {
    return service.readAll();
  }

  private Role[] roleParser() {
    List<Role> roles = new ArrayList<>();
    List<Role> iterationRoles = Arrays.asList(Role.values());
    List<String> messages =
        Arrays.asList(CommandMessages.EXPENSE_TYPE_MSG, CommandMessages.EXPENSE_TYPE_MSG_2);
    for (int i = 0; i < 2; i++) {
      showCurrentRoles(messages.get(i), iterationRoles);
      int parsedValue = consoleInput.readInt();
      if (parsedValue + 1 > 0 && parsedValue < iterationRoles.size() + 1) {
        roles.add(iterationRoles.get(parsedValue - 1));
      }
      if (roles.get(0).getLevel() == 3L) {
        return roles.toArray(Role[]::new);
      }
      iterationRoles = roleFilter(iterationRoles, roles);
    }
    return roles.toArray(Role[]::new);
  }

  private List<Role> roleFilter(List<Role> iterationRoles, List<Role> currentRoles) {
    return iterationRoles.stream().filter(
        role -> role.getLevel() != currentRoles.get(0).getLevel()
            && role.getLevel() != 3L).collect(Collectors.toList());
  }

  private void showCurrentRoles(String message, List<Role> roles) {
    System.out.println(message);
    int i = 1;
    for (Role role : roles) {
      System.out.println(i + " - " + role);
      i++;
    }
  }

  private String parseFirstName() {
    System.out.println(CommandMessages.NAME_MESSAGE);
    return consoleInput.readString();
  }

  private String parseLastName() {
    System.out.println(CommandMessages.FAMILY_MESSAGE);
    return consoleInput.readString();
  }

  private String parseEmail() throws ValidationException {
    System.out.println(CommandMessages.EMAIL_MESSAGE);
    String parsedLine = consoleInput.readString();
    if (!emailValidator.validate(parsedLine)) {
      throw new ValidationException("Wrong format of Email");
    }
    return parsedLine;
  }


  private String[] parsePhones() throws ValidationException {
    List<String> phones = new ArrayList<>();
    List<String> messages =
        Arrays.asList(CommandMessages.PHONE_NUMBER_FIRST,
            CommandMessages.PHONE_NUMBER_SECOND,
            CommandMessages.PHONE_NUMBER_THIRD);
    for (int i = 0; i < 3; i++) {
      showResult(messages.get(i));
      String parsedValue = consoleInput.readString();
      if (!phoneValidator.validate(parsedValue)) {
        throw new ValidationException("Wrong phone format, please write in 375(25,33,17,29,44) xxxxxxx");
      }
      phones.add(parsedValue);
      if (i == 2) {
        return phones.toArray(String[]::new);
      }
      showResult(CommandMessages.CONTINUE);
      int parsedNumber = consoleInput.readInt();
      if (parsedNumber == 0) {
        return phones.toArray(String[]::new);
      }
    }
    return phones.toArray(String[]::new);
  }

  private boolean isNeedUpdate(String msg) {
    System.out.println(msg);
    System.out.println(CommandMessages.CONTINUE);
    int parsedNumber = consoleInput.readInt();
    return parsedNumber != 0;
  }

  private void showResult(String string) {
    System.out.println(string);
  }

  private void showResult(List<String> strings) {
    strings.forEach(System.out::println);
  }
}

