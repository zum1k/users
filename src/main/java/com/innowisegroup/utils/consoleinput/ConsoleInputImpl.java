package main.java.com.innowisegroup.utils.consoleinput;

import java.util.Scanner;

public class ConsoleInputImpl implements ConsoleInput {
  private final Scanner scanner;

  public ConsoleInputImpl() {
    this.scanner = new Scanner(System.in);
  }

  @Override
  public int readInt() {
    int flag = 0;
    int value = 0;
    do {
      try {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
          flag = 1;
          value = scanner.nextInt();
          break;
        }
        throw new Exception("Введите число!");
      } catch (Exception ex) {
        System.out.println(ex.getMessage());
      }
    } while (flag != 1);
    return value;
  }

  @Override
  public String readString() {
    return scanner.nextLine();
  }
}
