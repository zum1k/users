package main.java.com.innowisegroup.entity;

public enum Role {
  USER(1L),
  CUSTOMER(1L),
  ADMIN(2L),
  PROVIDER(2L),
  SUPER_ADMIN(3L);
  private final Long level;

  Role(Long level) {
    this.level = level;
  }

  public Long getLevel() {
    return level;
  }
}
