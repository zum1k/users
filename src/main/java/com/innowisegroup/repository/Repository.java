package main.java.com.innowisegroup.repository;

import main.java.com.innowisegroup.entity.AbstractEntity;

import java.util.List;
import java.util.Optional;

/**
 * This interface is built on Repository pattern and it's medium level between .txt files and service logic
 *
 * @param <T> the type of objects with which implementation operates.
 */

public interface Repository<T extends AbstractEntity<? extends Number>> {
  /**
   * @param t T
   * @return T of last created entity
   */
  T create(T t);

  /**
   * @param id id of the entry in the file
   * @return Optional<T> depending on id
   */

  Optional<T> read(long id);

  /**
   * @param id id of the entry in the file
   * @param t  of updated entity
   * @return T depending on id
   */

  T update(long id, T t);

  /**
   * @param id - id of the entry in the file
   * @return Optional<T> depending on id
   */
  Optional<T> remove(long id);

  /**
   * @return List<T> of all rows in the file
   */
  List<T> readAll();
}
