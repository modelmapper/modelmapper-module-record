package org.modelmapper.record;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Tests the mapping of a POJO to a Record.
 */
@Test
public class RecordConstructorInjectorTest {
  private ModelMapper modelMapper;

  record UserRecord(String userId, String userName) {
  }

  static class User {
    String userId;
    String userName;
  }

  @BeforeMethod
  public void setup() {
    modelMapper = new ModelMapper()
        .registerModule(new RecordModule());
    modelMapper.getConfiguration()
        .setFieldMatchingEnabled(true)
        .setFieldAccessLevel(AccessLevel.PACKAGE_PRIVATE)
        .setMethodAccessLevel(AccessLevel.PACKAGE_PRIVATE);
  }

  public void shouldMapToRecord() {
    User user = new User();
    user.userId = "id";
    user.userName = "name";

    UserRecord record = modelMapper.map(user, UserRecord.class);
    assertEquals(record.userId, "id");
    assertEquals(record.userName, "name");
  }
}