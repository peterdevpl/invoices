package pl.peterdev.invoices.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public final class Address {
  private final String street;
  private final String city;
  private final String zipCode;

  @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
  public Address(@JsonProperty("street") String street,
                 @JsonProperty("city") String city,
                 @JsonProperty("zipCode") String zipCode) {
    this.street = street;
    this.city = city;
    this.zipCode = zipCode;
  }
}
