package pl.peterdev.invoices.domain;

import org.javamoney.moneta.Money;

import javax.money.CurrencyUnit;
import javax.money.MonetaryAmount;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class Invoice {
  private final CurrencyUnit currency;
  private Sender sender;
  private Recipient recipient;
  private PaymentTerms paymentTerms;
  private LocalDate issueDate = LocalDate.now();
  private String number = "1";
  private List<InvoiceItem> items = new ArrayList<>();

  public Invoice(CurrencyUnit currency) {
    this.currency = currency;
  }

  public Sender sender() {
    return sender;
  }

  public Recipient recipient() {
    return recipient;
  }

  public PaymentTerms paymentTerms() {
    return paymentTerms;
  }

  public LocalDate issueDate() {
    return issueDate;
  }

  public void issueDate(@NotNull LocalDate issueDate) {
    this.issueDate = issueDate;
  }

  public String number() {
    return number;
  }

  public void number(@NotBlank String number) {
    this.number = number;
  }

  public List<InvoiceItem> items() {
    return items;
  }

  public void addItem(@NotBlank String name, @NotNull BigDecimal quantity, @NotNull MonetaryAmount unitNetPrice, @NotNull VatRate vatRate) {
    if (!unitNetPrice.getCurrency().equals(currency)) {
      throw new IllegalArgumentException("Item's currency does not match invoice currency");
    }
    items.add(new InvoiceItem(name, quantity, unitNetPrice, vatRate));
  }

  public MonetaryAmount totalGrossAmount() {
    Money sum = Money.zero(currency);
    for (InvoiceItem item : items) {
      sum = sum.add(item.totalGrossAmount());
    }

    return sum;
  }
}
