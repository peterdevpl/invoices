package pl.peterdev.invoices.domain.payment;

public final class BankAccountFixture {
  public static BankAccount exampleAccount() {
    return new BankAccount("10 0000 0000 0000 0000 0000", "MyBank");
  }
}
