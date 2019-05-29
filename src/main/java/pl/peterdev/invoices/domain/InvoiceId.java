package pl.peterdev.invoices.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Value;

@Value
public final class InvoiceId {
  private final String value;

  @JsonValue
  public String toString() {
    return value;
  }
}
