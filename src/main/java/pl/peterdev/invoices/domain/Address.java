package pl.peterdev.invoices.domain;

import lombok.Value;

@Value
public final class Address {
  private final String street;
  private final String city;
  private final String zipCode;
}
