package pl.peterdev.invoices.domain.payment;

import lombok.Value;

@Value
public final class BankAccount {
  private final String accountNumber;
  private final String bankName;
}
