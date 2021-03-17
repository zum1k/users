package main.java.com.innowisegroup.entity;

import java.util.Arrays;
import java.util.Objects;

public class User extends AbstractEntity<Long> {
  private String firstName;
  private String lastName;
  private String email;
  private Role[] roles;
  private String[] phoneNumbers;

  public User() {
  }

  public User(Long id, String firstName, String lastName, String email, Role[] roles, String[] phoneNumbers) {
    super(id);
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.roles = roles;
    this.phoneNumbers = phoneNumbers;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Role[] getRoles() {
    return roles;
  }

  public void setRoles(Role[] roles) {
    this.roles = roles;
  }

  public String[] getPhoneNumbers() {
    return phoneNumbers;
  }

  public void setPhoneNumbers(String[] phoneNumbers) {
    this.phoneNumbers = phoneNumbers;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    User user = (User) o;
    return Objects.equals(firstName, user.firstName) &&
        Objects.equals(lastName, user.lastName) &&
        Objects.equals(email, user.email) &&
        Arrays.equals(roles, user.roles) &&
        Arrays.equals(phoneNumbers, user.phoneNumbers);
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(super.hashCode(), firstName, lastName, email);
    result = 31 * result + Arrays.hashCode(roles);
    result = 31 * result + Arrays.hashCode(phoneNumbers);
    return result;
  }

  @Override
  public String toString() {
    return "User{" +
        "id='" + getId() + '\'' +
        "firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", email='" + email + '\'' +
        ", roles=" + Arrays.toString(roles) +
        ", phoneNumbers=" + Arrays.toString(phoneNumbers) +
        '}';
  }
}
