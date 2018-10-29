package pl.peterdev.invoices.domain;

import javax.validation.constraints.NotNull;

public final class Address {
  private final String street;
  private final String city;
  private final String zipCode;

  public Address(@NotNull String street, @NotNull String city, @NotNull String zipCode) {
    this.street = street;
    this.city = city;
    this.zipCode = zipCode;
  }

  public String street() {
    return street;
  }

  public String city() {
    return city;
  }

  public String zipCode() {
    return zipCode;
  }
}
