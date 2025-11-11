package org.modelmapper.record;

import java.lang.reflect.Constructor;
import java.lang.reflect.RecordComponent;
import java.util.stream.IntStream;
import org.modelmapper.ConstructorParam;
import org.modelmapper.spi.ConstructorInjector;

import java.util.List;

public class RecordConstructorInjector implements ConstructorInjector {

  @Override
  public List<ConstructorParam> getParameters(Class<?> type) {
    if (!isApplicable(type)) {
      throw new IllegalArgumentException(
          String.format("Type %s is not a Record type.", type.getName()));
    }
    Constructor<?> constructor = type.getDeclaredConstructors()[0];
    RecordComponent[] components = type.getRecordComponents();
    return IntStream.range(0, components.length)
        .mapToObj(index -> new ConstructorParam(constructor, index, components[index].getName(),
            components[index].getType()))
        .toList();
  }

  @Override
  public boolean isApplicable(Class<?> type) {
    return Record.class.isAssignableFrom(type);
  }
}
