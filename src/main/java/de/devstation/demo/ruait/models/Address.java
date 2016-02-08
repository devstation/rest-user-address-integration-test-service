package de.devstation.demo.ruait.models;

public class Address {

  public static int UNPERSISTED_USER_ID = -1;

  private int id = UNPERSISTED_USER_ID;
  private int userId;
  private String street;
  private String zip;
  private String city;
  private String country;

  public Address() {
  }

  public Address(int userId, String street, String zip, String city, String country) {
    this.userId = userId;
    this.street = street;
    this.zip = zip;
    this.city = city;
    this.country = country;
  }

  public Address(int id, int userId, String street, String zip, String city, String country) {
    this.id = id;
    this.userId = userId;
    this.street = street;
    this.zip = zip;
    this.city = city;
    this.country = country;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Address address = (Address) o;

    if (id != address.id) return false;
    if (!street.equals(address.street)) return false;
    if (!zip.equals(address.zip)) return false;
    if (!city.equals(address.city)) return false;
    return country.equals(address.country);

  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + street.hashCode();
    result = 31 * result + zip.hashCode();
    result = 31 * result + city.hashCode();
    result = 31 * result + country.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "Address{" +
        "id=" + id +
        ", street='" + street + '\'' +
        ", zip='" + zip + '\'' +
        ", city='" + city + '\'' +
        ", country='" + country + '\'' +
        '}';
  }
}
