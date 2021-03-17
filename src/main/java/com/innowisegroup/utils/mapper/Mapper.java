package main.java.com.innowisegroup.utils.mapper;

import main.java.com.innowisegroup.entity.AbstractEntity;

/**
 * This interface {@Mapper} is used to convert entities to a string representation and vice versa
 *
 * @param T defines of type
 */

public interface Mapper<T extends AbstractEntity<? extends Number>> {
  /**
   * converts an entity to a string
   *
   * @param t entity
   * @return String
   */
  String toString(T t);

  /**
   * converts a String to an entity
   *
   * @param str String
   * @return T entity
   */

  T toEntity(String str);
}
