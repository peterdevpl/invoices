package pl.peterdev.invoices.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.javamoney.moneta.Money;
import pl.peterdev.invoices.domain.payment.PaymentTerms;
import pl.peterdev.invoices.domain.tax.VatRate;

import javax.money.CurrencyUnit;
import javax.money.MonetaryAmount;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public final class Invoice {
  private final CurrencyUnit currency;
  private final Sender sender;
  private final Recipient recipient;
  private PaymentTerms paymentTerms;
  private LocalDate issueDate = LocalDate.now();
  private String number = "1";
  private final List<InvoiceItem> items = new ArrayList<>();

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
