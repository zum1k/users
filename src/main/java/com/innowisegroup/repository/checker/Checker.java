package main.java.com.innowisegroup.repository.checker;

import main.java.com.innowisegroup.entity.AbstractEntity;
import main.java.com.innowisegroup.utils.mapper.Mapper;

public interface Checker<T extends AbstractEntity<? extends Number>> {
  boolean check(String string);
}
