package pl.peterdev.invoices.domain.tax;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Value;

@Value
public final class VatId {
  private final String id;

  @JsonValue
  public String getId() {
    return id;
  }
}
