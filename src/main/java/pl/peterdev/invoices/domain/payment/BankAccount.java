package pl.peterdev.invoices.domain.payment;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public final class BankAccount {
  private final String accountNumber;
  private final String bankName;

  @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
  public BankAccount(@JsonProperty("accountNumber") String accountNumber,
                     @JsonProperty("bankName") String bankName) {
    this.accountNumber = accountNumber;
    this.bankName = bankName;
  }
}
