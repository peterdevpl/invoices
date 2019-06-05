package pl.peterdev.invoices.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import org.javamoney.moneta.Money;
import pl.peterdev.invoices.domain.exception.InvoiceIsEmpty;
import pl.peterdev.invoices.domain.exception.InvoiceItemsDoNotMatchInvoiceCurrency;
import pl.peterdev.invoices.domain.payment.PaymentTerms;

import javax.money.CurrencyUnit;
import javax.money.MonetaryAmount;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Value
public final class Invoice {
  private final InvoiceId number;
  private final CurrencyUnit currency;
  private final Sender sender;
  private final Recipient recipient;
  private final PaymentTerms paymentTerms;
  private final LocalDate issueDate;
  private final List<InvoiceItem> items;

  @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
  public Invoice(@JsonProperty("number") InvoiceId number,
                 @JsonProperty("currency") CurrencyUnit currency,
                 @JsonProperty("sender") Sender sender,
                 @JsonProperty("recipient") Recipient recipient,
                 @JsonProperty("paymentTerms") PaymentTerms paymentTerms,
                 @JsonProperty("issueDate") LocalDate issueDate,
                 @JsonProperty("items") List<InvoiceItem> items) {
    validateItems(currency, items);
    this.number = number;
    this.currency = currency;
    this.sender = sender;
    this.recipient = recipient;
    this.paymentTerms = paymentTerms;
    this.issueDate = issueDate;
    this.items = items;
  }

  private void validateItems(CurrencyUnit currency, List<InvoiceItem> items) {
    if (items.size() < 1) {
      throw new InvoiceIsEmpty();
    }

    if (items.stream().anyMatch(item -> !item.getUnitNetAmount().getCurrency().equals(currency))) {
      throw new InvoiceItemsDoNotMatchInvoiceCurrency(currency);
    }
  }

  public MonetaryAmount totalNetAmount() {
    Money sum = Money.zero(currency);
    for (InvoiceItem item : items) {
      sum = sum.add(item.totalNetAmount());
    }

    return sum;
  }

  public MonetaryAmount totalVatAmount() {
    Money sum = Money.zero(currency);
    for (InvoiceItem item : items) {
      sum = sum.add(item.totalVatAmount());
    }

    return sum;
  }

  public MonetaryAmount totalGrossAmount() {
    Money sum = Money.zero(currency);
    for (InvoiceItem item : items) {
      sum = sum.add(item.totalGrossAmount());
    }

    return sum;
  }

  public Optional<LocalDate> getDueDate() {
    if (paymentTerms == null) {
      return Optional.empty();
    }

    return Optional.of(issueDate.plusDays(paymentTerms.getDays()));
  }
}
