package de.devstation.demo.ruait.testing;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Header;
import com.jayway.restassured.response.ResponseBodyExtractionOptions;
import de.devstation.demo.ruait.models.Address;
import de.devstation.demo.ruait.models.User;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;

@Service
public class IntegrationTest {

  private static final Logger log = LoggerFactory.getLogger(IntegrationTest.class);

  private static final Header jsonAcceptHeader = new Header("Accept", "application/json; charset=UTF-8");

  @Autowired
  private IntegrationTestConfig config;

  @PostConstruct
  public void postConstruct() {
    log.info("config: {}", config);
  }

  /*
  public void putOrganization_ExistingOrganization_StatusCodeConflict() {
    given().auth().basic(USER_EMAIL, USER_PASSWORD).contentType(ContentType.JSON).body(JsonTestObjects.ORG_1)
        .when().post(organizationsBaseUrl)
        .then().statusCode(HttpStatus.SC_CREATED);

    given().auth().basic(USER_EMAIL, USER_PASSWORD).contentType(ContentType.JSON).body(JsonTestObjects.ORG_1)
        .when().post(organizationsBaseUrl)
        .then().statusCode(HttpStatus.SC_CONFLICT);
  }
  */

  public void resetUsers() {
    given()
        .when().get(config.getUserServiceRestEndpoint() + "reset")
        .then().statusCode(HttpStatus.SC_OK);
  }

  public void resetAddresses() {
    given()
        .when().get(config.getAddressServiceRestEndpoint() + "reset")
        .then().statusCode(HttpStatus.SC_OK);
  }

  public List<User> getUserList() {
    ResponseBodyExtractionOptions body = given()
        .contentType(ContentType.JSON)
        .header(jsonAcceptHeader)
        .when().get(config.getUserServiceRestEndpoint())
        .then().statusCode(HttpStatus.SC_OK)
        .and().extract().body();
    return (new Gson()).fromJson(body.asString(), new TypeToken<List<User>>() {
    }.getType());
  }

  public List<User> getUser(int id) {
    ResponseBodyExtractionOptions body = given()
        .contentType(ContentType.JSON)
        .header(jsonAcceptHeader)
        .when().get(config.getUserServiceRestEndpoint())
        .then().statusCode(HttpStatus.SC_OK)
        .and().extract().body();
    return (new Gson()).fromJson(body.asString(), new TypeToken<List<User>>() {
    }.getType());
  }

  public User postUser(User user) {
    ResponseBodyExtractionOptions body = given()
        .contentType(ContentType.JSON)
        .body(user)
        .when().post(config.getUserServiceRestEndpoint())
        .then().statusCode(HttpStatus.SC_CREATED)
        .and().extract().body();
    return (new Gson()).fromJson(body.asString(), new TypeToken<User>() {
    }.getType());
  }

  public Address postAddress(Address address) {
    ResponseBodyExtractionOptions body = given()
        .contentType(ContentType.JSON)
        .body(address)
        .when().post(config.getAddressServiceRestEndpoint())
        .then().statusCode(HttpStatus.SC_CREATED)
        .and().extract().body();
    return (new Gson()).fromJson(body.asString(), new TypeToken<Address>() {
    }.getType());
  }

}
