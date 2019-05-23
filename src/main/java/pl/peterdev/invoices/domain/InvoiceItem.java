package pl.peterdev.invoices.domain;

import lombok.Value;
import pl.peterdev.invoices.domain.tax.VatRate;

import java.math.BigDecimal;
import javax.money.MonetaryAmount;

@Value
public final class InvoiceItem {
  private final String name;
  private final BigDecimal quantity;
  private final MonetaryAmount unitNetAmount;
  private final VatRate vatRate;

  public MonetaryAmount totalNetAmount() {
    return unitNetAmount.multiply(quantity);
  }

  public MonetaryAmount totalVatAmount() {
    return totalNetAmount().multiply(vatRate.getRate());
  }

  public MonetaryAmount totalGrossAmount() {
    return totalNetAmount().add(totalVatAmount());
  }
}
