package pl.peterdev.invoices.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public final class BankAccount {
  private final String accountNumber;
  private final String bankName;

  public BankAccount(@NotBlank String accountNumber, @NotNull String bankName) {
    this.accountNumber = accountNumber;
    this.bankName = bankName;
  }

  public String accountNumber() {
    return accountNumber;
  }

  public String bankName() {
    return bankName;
  }
}
