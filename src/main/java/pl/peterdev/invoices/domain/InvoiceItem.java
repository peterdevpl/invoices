package pl.peterdev.invoices.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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

  @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
  public InvoiceItem(@JsonProperty("name") String name,
                     @JsonProperty("quantity") BigDecimal quantity,
                     @JsonProperty("unitNetAmount") MonetaryAmount unitNetAmount,
                     @JsonProperty("vatRate") VatRate vatRate) {
    this.name = name;
    this.quantity = quantity;
    this.unitNetAmount = unitNetAmount;
    this.vatRate = vatRate;
  }

  public MonetaryAmount getTotalNetAmount() {
    return unitNetAmount.multiply(quantity);
  }

  public MonetaryAmount getTotalVatAmount() {
    return getTotalNetAmount().multiply(vatRate.getRate());
  }

  public MonetaryAmount getTotalGrossAmount() {
    return getTotalNetAmount().add(getTotalVatAmount());
  }
}
