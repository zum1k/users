package main.java.com.innowisegroup.service;

import main.java.com.innowisegroup.entity.AbstractEntity;
import main.java.com.innowisegroup.exception.ServiceException;

import java.util.List;

public interface Service<T extends AbstractEntity<? extends Number>> {
  String create(T entity);

  T read(long id) throws ServiceException;

  String update(long id, T t);

  String remove(long id);

  List<String> readAll();
}
