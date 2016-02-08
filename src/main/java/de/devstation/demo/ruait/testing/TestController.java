package de.devstation.demo.ruait.testing;

import de.devstation.demo.ruait.models.Address;
import de.devstation.demo.ruait.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class TestController {

  private static final User TEST_USER = new User("my-test-user", "my-password");
  private static final String TEST_ADDRESS_STREET = "test str. 123";
  private static final String TEST_ADDRESS_ZIP = "12345";
  private static final String TEST_ADDRESS_CITY = "test city";
  private static final String TEST_ADDRESS_COUNTRY = "test country";

  @Autowired
  private IntegrationTest integrationTest;

  @RequestMapping("test")
  public HttpEntity<List<TestResult>> test() {
    List<TestResult> results = new ArrayList<>();
    results.add(resetUsers());
    results.add(resetAddresses());
    results.add(getEmptyUserList());
    results.add(putNewUserWithAddress());
    return new ResponseEntity<>(results, HttpStatus.OK);
  }

  private TestResult resetUsers() {
    try {
      integrationTest.resetUsers();
      return TestResult.ok("resetUsers");
    } catch (Exception e) {
      return TestResult.errorWithException("resetUsers", e);
    }
  }

  private TestResult resetAddresses() {
    try {
      integrationTest.resetAddresses();
      return TestResult.ok("resetAddresses");
    } catch (Exception e) {
      return TestResult.errorWithException("resetAddresses", e);
    }
  }

  private TestResult getEmptyUserList() {
    try {
      List<User> userList = integrationTest.getUserList();
      if (userList.size() == 0) {
        return TestResult.ok("getEmptyUserList");
      } else {
        return TestResult.errorWithDetails("getEmptyUserList", "not empty, size=" + userList.size());
      }
    } catch (Exception e) {
      return TestResult.errorWithException("getEmptyUserList", e);
    }
  }

  private TestResult putNewUserWithAddress() {
    try {
      User user = integrationTest.postUser(TEST_USER);
      if (user.getId() <= 0) {
        return TestResult.errorWithDetails("putNewUserWithAddress", "user id is not valid, id=" + user.getId());
      }
      Address address = integrationTest.postAddress(createAddressForUser(user));
      if (address.getId() <= 0) {
        return TestResult.errorWithDetails("putNewUserWithAddress", "address id is not valid, id=" + address.getId());
      }
      return TestResult.ok("putNewUserWithAddress");
    } catch (Exception e) {
      return TestResult.errorWithException("putNewUserWithAddress", e);
    }
  }

  private static Address createAddressForUser(User user) {
    return new Address(user.getId(), TEST_ADDRESS_STREET, TEST_ADDRESS_ZIP, TEST_ADDRESS_CITY, TEST_ADDRESS_COUNTRY);
  }

  private static class TestResult {
    private String name;
    private String result;
    private String details;

    private TestResult(String name, String result, String details) {
      this.name = name;
      this.result = result;
      this.details = details;
    }

    public String getName() {
      return name;
    }

    public String getResult() {
      return result;
    }

    public String getDetails() {
      return details;
    }

    private static TestResult ok(String name) {
      return new TestResult(name, "OK", null);
    }

    private static TestResult okWithDetails(String name, String details) {
      return new TestResult(name, "OK", details);
    }

    private static TestResult errorWithException(String name, Exception e) {
      return new TestResult(name, "ERROR", e.getMessage());
    }

    private static TestResult errorWithDetails(String name, String details) {
      return new TestResult(name, "ERROR", details);
    }
  }

}

