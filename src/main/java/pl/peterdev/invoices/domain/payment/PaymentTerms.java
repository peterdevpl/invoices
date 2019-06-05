package pl.peterdev.invoices.domain.payment;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public final class PaymentTerms {
  private final BankAccount bankAccount;
  private final int days;

  @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
  public PaymentTerms(@JsonProperty("bankAccount") BankAccount bankAccount,
                      @JsonProperty("days") int days) {
    this.bankAccount = bankAccount;
    this.days = days;
  }
}
