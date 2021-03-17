package main.java.com.innowisegroup.utils.mapper;

import main.java.com.innowisegroup.entity.AbstractEntity;

public interface Mapper<T extends AbstractEntity<? extends Number>> {
  String toString(T t);

  T toEntity(String str);
}
