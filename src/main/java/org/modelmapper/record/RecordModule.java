package org.modelmapper.record;

import org.modelmapper.ModelMapper;
import org.modelmapper.Module;

public class RecordModule implements Module {
  @Override
  public void setupModule(ModelMapper modelMapper) {
    modelMapper.getConfiguration().addValueReader(new RecordValueReader());
  }
}
