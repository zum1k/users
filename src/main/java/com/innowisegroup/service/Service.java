package main.java.com.innowisegroup.service;

import main.java.com.innowisegroup.entity.AbstractEntity;
import main.java.com.innowisegroup.exception.ServiceException;

import java.util.List;

/**
 * This interface {@Service} is medium level between Repository and View layers
 *
 * @param <T> the type of objects with which implementation operates.
 */

public interface Service<T extends AbstractEntity<? extends Number>> {
  /**
   * @param entity T
   * @return String of last created entity
   */
  String create(T entity);

  /**
   * @param id id of the entry in the file
   * @return T depending on id
   * @throws ServiceException if some unforeseen circumstances arose in the code
   */
  T read(long id) throws ServiceException;

  /**
   * @param id id of the entry in the file
   * @param t  of updated entity
   * @return String of t
   */

  String update(long id, T t);

  /**
   * @param id - id of the entry in the file
   * @return String of deleted entity
   */
  String remove(long id);

  /**
   * @return List<String> of all rows in the file
   */
  List<String> readAll();
}
