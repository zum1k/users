package main.java.com.innowisegroup.repository;

import main.java.com.innowisegroup.entity.AbstractEntity;

import java.util.List;
import java.util.Optional;

public interface Repository<T extends AbstractEntity<? extends Number>> {
  T create(T t);

  Optional<T> read(long id);

  T update(long id, T t);

  Optional<T> remove(long id);

  List<T> readAll();
}
