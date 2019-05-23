package pl.peterdev.invoices.domain.payment;

import lombok.Value;

@Value
public final class PaymentTerms {
  private final BankAccount bankAccount;
  private final int days;
}
