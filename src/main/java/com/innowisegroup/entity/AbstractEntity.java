package main.java.com.innowisegroup.entity;

import java.util.Objects;

public abstract class AbstractEntity<T extends Number> {
  private T id;

  public AbstractEntity() {
  }

  public AbstractEntity(T id) {
    this.id = id;
  }

  public T getId() {
    return id;
  }

  public void setId(T id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AbstractEntity<?> entity = (AbstractEntity<?>) o;
    return Objects.equals(id, entity.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "Entity{" +
        "id=" + id +
        '}';
  }
}
