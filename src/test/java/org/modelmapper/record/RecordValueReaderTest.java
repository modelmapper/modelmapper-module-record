package org.modelmapper.record;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Tests the mapping of a Record to a POJO.
 *
 * @author okaponta
 */
@Test
public class RecordValueReaderTest {
  record UserRecord(String userId, String userName) {
  }

  static class User {
    String userId;
    String userName;
  }

  private ModelMapper modelMapper;

  @BeforeMethod
  public void setup() {
    modelMapper = new ModelMapper()
        .registerModule(new RecordModule());
    modelMapper.getConfiguration()
        .setFieldMatchingEnabled(true)
        .setFieldAccessLevel(AccessLevel.PACKAGE_PRIVATE)
        .setMethodAccessLevel(AccessLevel.PACKAGE_PRIVATE);;
  }

  public void shouldMapRecordToBean() {
    User user = modelMapper.map(new UserRecord("id", "name"), User.class);
    assertEquals(user.userId, "id");
    assertEquals(user.userName, "name");
  }

  public void shouldMapNullValue() {
    User user = modelMapper.map(new UserRecord("id", null), User.class);
    assertEquals(user.userId, "id");
    assertNull(user.userName);
  }
}