package de.devstation.demo.ruait.testing;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "integration.test")
public class IntegrationTestConfig {

  @NotEmpty
  private String addressServiceRestEndpoint;

  @NotEmpty
  private String userServiceRestEndpoint;

  public String getAddressServiceRestEndpoint() {
    return addressServiceRestEndpoint;
  }

  public void setAddressServiceRestEndpoint(String addressServiceRestEndpoint) {
    this.addressServiceRestEndpoint = addressServiceRestEndpoint;
  }

  public String getUserServiceRestEndpoint() {
    return userServiceRestEndpoint;
  }

  public void setUserServiceRestEndpoint(String userServiceRestEndpoint) {
    this.userServiceRestEndpoint = userServiceRestEndpoint;
  }

  @Override
  public String toString() {
    return "IntegrationTestConfig{" +
        "addressServiceRestEndpoint='" + addressServiceRestEndpoint + '\'' +
        ", userServiceRestEndpoint='" + userServiceRestEndpoint + '\'' +
        '}';
  }
}
